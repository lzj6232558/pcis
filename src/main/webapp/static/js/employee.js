$(function () {
    //变量的抽取,这里的选择器每次页面加载都会去消耗内存去选择,,抽取出来可以缓存起来
    var emp_datagrid = $("#emp_datagrid");
    var emp_dialog = $("#emp_dialog");
    var emp_form = $("#emp_form");
    var import_dialog = $("#import_dialog");
    //把方法抽取到对象中,json对象
    var methodObject = {
        //新增按钮
        add: function () {

            $("#password").show();
            //清空表单数据
            emp_form.form("clear");

            //设置标题
            emp_dialog.dialog("setTitle", "新增员工");

            //打开弹出框
            emp_dialog.dialog("open");
        },
        //点击导入按钮
        importXls: function () {
            //设置标题
            import_dialog.dialog("setTitle", "导入文件");
            //打开弹出框
            import_dialog.dialog("open");
        },
        //导出按钮的事件
        exportXls: function () {
            window.location.href = "/employee/export.do";
        },
        //编辑按钮
        edit: function () {
            $("#password").hide();
            //判断用户是否选中数据
            var row = emp_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条数据!", "warning");
                return;
            }
            //清空表单数据
            emp_form.form("clear");
            if (row.dept != undefined) {
                row["dept.id"] = row.dept.id;
            }

            //回显角色
            var data = row.roles;
            var ids = [];
            $(data).each(function (index,item) {
                ids[index] = item.id;
            });
            if (ids.length > 0) {
                $("#myCombotree").combobox("setValues", ids);
            };

            //回显到表单中(根据同名匹配的原则来回显)
            emp_form.form("load", row);


            //设置标题
            emp_dialog.dialog("setTitle", "编辑员工");
            //打开弹出框
            emp_dialog.dialog("open");
        },

        //刷新按钮
        reload: function () {
            emp_datagrid.datagrid("load");
        }, dowloadXls: function () {
            window.location.href = "/employee/dowloadXls.do";
        },
        //提交表单
        save: function () {
            /* if (!$("#admin_box").combobox("getValue")) {
                $("#admin_box").combobox("setValue", 0);
            }*/
            //使用easyui的form表单做提交(ajax)
            emp_form.form('submit', {
                url: '/employee/saveOrUpdate.do',
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //关闭弹出框
                        emp_dialog.dialog("close");
                        //弹出提示信息
                        $.messager.alert("温馨提示", "保存成功!", "info", function () {
                            //重新加载数据表格
                            emp_datagrid.datagrid("reload");
                        });
                    } else {
                        //弹出提示信息
                        $.messager.alert("温馨提示", data.message, "error");
                    }
                },
                //roles[i].id
                //提交之前做的事情
                onSubmit: function (params) {
                    var data = $("#myCombotree").combotree("getValues");
                    if (data) {
                    for (var index = 0; index < data.length; index++) {
                        params["roles[" + index + "].id"] = data[index];
                    }
                    }
                }
            })
        },
        //上传excel的表单提交
        present: function () {
            $("#import_form").form("submit", {
                url: '/employee/importXls.do',
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //关闭弹出框
                        emp_dialog.dialog("close");
                        //弹出提示信息
                        $.messager.alert("温馨提示", "上传成功!", "info", function () {
                            //重新加载数据表格
                            emp_datagrid.datagrid("reload");
                        });
                    } else {
                        //弹出提示信息
                        $.messager.alert("温馨提示", data.message, "error");
                    }
                }
            })
        },
        //取消按钮
        cancel: function () {
            emp_dialog.dialog("close");
        },

        changeState: function () {
            //判断用户是否选中数据
            var row = emp_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条数据!", "warning");
                return;
            }
            $.get("/employee/changeState.do?id=" + row.id, function (data) {
                if (data.success) {
                    //弹出提示信息
                    $.messager.alert("温馨提示", "操作成功!", "info", function () {
                        //重新加载数据表格
                        emp_datagrid.datagrid("reload");
                    });
                } else {
                    //弹出提示信息
                    $.messager.alert("温馨提示", data.message, "error");
                }

            }, "json")
        }
    }

    //用对象名[变量名,也就是方法名](),统一绑定事件
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        //调用json中指定的方法
        methodObject[method]();

    });
    //初始化数据表格框
    emp_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: '/employee/dataGrid.do',
        queryParams: $("#emp_table").serializeObject(),
        checkbox: true,
        singleSelect: true,
        toolbar: '#emp_toolbar',
        striped: true,
        pageList: [3, 5, 10, 15, 20],
        pagination: true,
        rownumbers: true,
        columns: [[
            // {field: 'id', title: '编号', width: 100},
            {field: 'username', title: '用户名', width: 100},
            {field: 'realname', title: '真实姓名', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'email', title: '邮箱', width: 100},
            {field: 'inputTime', title: '入职时间', width: 100},
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                return row.state ? "在职" : "<font color='red'>离职</font>"
            }
            },
            {
                field: 'dept', title: '员工', width: 100, formatter: function (value, row, index) {
                return value ? value.name : "";
            }
            },
            {
                field: 'admin', title: '是否是管理员', width: 100, formatter: function (value, row, index) {
                return row.admin ? "是" : "否"
            }
            }
        ]],
        onClickRow: function (index, row) {
            if (!row.state) {
                //如果为离职,那么就设置按钮为入职
                $("#changeState").linkbutton({
                    text: "入职"
                })

            } else if (row.state) {
                //如果为在职,那么就设置按钮为离职
                $("#changeState").linkbutton({
                    text: "离职"
                })
            }
        }
    })
    //搜索按钮的事件
    $("#submit_search").click(function () {
        var keyword = $(".easyui-textbox").textbox("getValue");
        var deptId = $(".easyui-combobox").combobox("getValue");
        emp_datagrid.datagrid("load", {
            keyword: keyword,
            deptId: deptId
        })
        //emp_datagrid.datagrid({queryParams: $("#emp_table").serializeObject()});   //点击搜索
    });
    $("#deptId").combobox({
        url: '/department/selectAllDepartment.do',
        valueField: 'id',
        textField: 'name'
    });
    //初始化表单弹出框
    emp_dialog.dialog({
        width: 350,
        height: 400,
        constrain: true,
        buttons: '#emp_buttons',
        closed: true
    })
    //初始化导入按钮的弹出框
    import_dialog.dialog({
        width: 250,
        height: 200,
        constrain: true,
        toolbar: "#dowload_buttons",
        buttons: "#present_buttons",
        closed: true,
    })
    //下拉框
    $("#myCombobox").combobox({
        url: '/department/selectAllDepartment.do',
        valueField: 'id',
        textField: 'name',
        panelHeight: 'auto',

    })
    //初始化下拉框tree
    $("#myCombotree").combotree({
        url: '/role/selectAllRole.do',
        valueField: 'id',
        textField: 'name',
        panelHeight: 'auto',
        multiple: true,
    });
})


/*//新增按钮
function add() {
    $("#password").show();
    //清空表单数据
    emp_form.form("clear");

    //设置标题
    emp_dialog.dialog("setTitle", "新增员工");

    //打开弹出框
    emp_dialog.dialog("open");
}

//编辑按钮
function edit() {
    $("#password").hide();
    //判断用户是否选中数据
    var row = emp_datagrid.datagrid("getSelected");
    if (!row) {
        $.messager.alert("温馨提示", "请选中一条数据!", "warning");
        return;
    }
    //清空表单数据
    emp_form.form("clear");
    if (row.dept != undefined) {
        row["dept.id"] = row.dept.id;
    }
    // console.log($("#check_super"))
    $("#check_super").prop("checked", row.admin);
    row["roleIds"] = row.roles;
    //回显到表单中(根据同名匹配的原则来回显)
    emp_form.form("load", row);
    //回显下拉框

    //设置标题
    emp_dialog.dialog("setTitle", "编辑员工");
    //打开弹出框
    emp_dialog.dialog("open");
}


//刷新按钮
function reload() {
    emp_datagrid.datagrid("load");
}

//提交表单
function save() {
    //使用easyui的form表单做提交(ajax)
    emp_form.form("submit", {
        url: 'http://localhost/employee/saveOrUpdate.do',
        success: function (data) {
            //转成json对象
            data = $.parseJSON(data);
            if (data.success) {
                //关闭弹出框
                emp_dialog.dialog("close");
                //弹出提示信息
                $.messager.alert("温馨提示", "操作成功!", "info", function () {
                    //重新加载数据表格
                    emp_datagrid.datagrid("reload");
                });
            } else {
                //弹出提示信息
                $.messager.alert("温馨提示", data.message, "error");
            }
        }
    })
}

//取消按钮
function cancel() {
    emp_dialog.dialog("close");
}

function changeState() {
    //判断用户是否选中数据
    var row = emp_datagrid.datagrid("getSelected");
    if (!row) {
        $.messager.alert("温馨提示", "请选中一条数据!", "warning");
        return;
    }
    $.get("/employee/changeState.do?id=" + row.id, function (data) {
        if (data.success) {
            //弹出提示信息
            $.messager.alert("温馨提示", "操作成功!", "info", function () {
                //重新加载数据表格
                emp_datagrid.datagrid("reload");
            });
        } else {
            //弹出提示信息
            $.messager.alert("温馨提示", data.message, "error");
        }

    }, "json")
}*/


//表单数据转换为json
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/*//删除按钮
function remove() {
    //判断用户是否选中数据
    var row = emp_datagrid.datagrid("getSelected");
    if (!row) {
        $.messager.alert("温馨提示", "请选中一条数据!", "warning");
        return;
    }

    //发送ajax请求
    $.get("/employee/delete.do", {id: row.id}, function (data) {
        if (data.success) {
            //弹出提示信息
            $.messager.alert("温馨提示", "操作成功!", "info", function () {
                //重新加载数据表格
                emp_datagrid.datagrid("reload");
            });
        } else {
            //弹出提示信息
            $.messager.alert("温馨提示", data.message, "error");
        }
    }, "json")


}*/




