$(function () {
    //1.抽取变量
    var department_form = $("#department_form");
    var department_datagrid = $("#department_datagrid");
    var department_dialog = $("#department_dialog");

    //2.方法统一管理,使用一个对象去管理方法
    var methodObj = {
        //新增
        add: function () {
            //清空表单数据
            department_form.form('clear');
            //设置标题
            department_dialog.dialog("setTitle", "新增部门");
            //打开弹出框
            department_dialog.dialog("open");
        },

        //编辑
        edit: function () {
            //判断选中数据
            var row = department_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据！', 'info');
                return;
            }
            //清空表单数据
            department_form.form('clear');
            //回显部门经理
            if(row.managerId){
                row["managerId.id"] = row.managerId.id;
            }
            //回显上级部门
            if (row.parentId){
                row["parentId.id"] = row.parentId.id;
            }

            //回显表单
            department_form.form("load", row);
            //设置标题
            department_dialog.dialog("setTitle", "编辑部门");
            //打开弹出框
            department_dialog.dialog("open");
        },
        //取消
        cancel: function () {
            department_dialog.dialog("close");
        },
        //保存
        save: function () {
            department_form.form("submit", {
                url: '/department/saveOrUpdate.do',
                success: function (data) {
                    //使用easyui的form提交,需要把data转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.message, 'info', function () {
                            //重新加载数据表格
                            department_datagrid.datagrid("load");
                            //关闭弹出框
                            department_dialog.dialog("close");

                        });
                    } else {
                        $.messager.alert('温馨提示', data.message, 'info');
                    }
                }
            })
        },

        //停用
        changeState: function () {
            //判断选中数据
            var row = department_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据！', 'info');
                return;
            }
            $.messager.confirm('温馨提示', '确认停用部门吗？', function (r) {
                if (r) {
                    // 发送请求
                    $.get("/department/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', data.message, 'info', function () {
                                //重新加载数据表格(保持在当前页)
                                department_datagrid.datagrid("reload");
                                //关闭弹出框
                                department_dialog.dialog("close");
                            });
                        } else {
                            $.messager.alert('温馨提示', data.message, 'info');
                        }
                    })
                }
            });
        },

        //从新加载
        reload:function () {
            department_datagrid.datagrid("reload");
        }
    }

    //统一绑定点击事件
    $("[data-cmd]").click(function () {
        var methodName = $(this).data("cmd");
        methodObj[methodName]();
    })


    //初始化数据表格
    department_datagrid.datagrid({
        fit: true,
        url:"/department/query.do",
        fitColumns: true,
        striped: true,
        toolbar: '#btn_toolbar',
        pagination: true,
        singleSelect: true,
        rownumbers:true,
        columns: [[
            {field: 'sn', title: '部门编号', width: 100},
            {field: 'name', title: '部门名称', width: 100},
            {field: 'managerId', title: '部门经理', width: 100,
                formatter: function (value, row, index) {
                    return value ? value.realname : "";
                }
            },
            {field: 'parentId', title: '上级部门', width: 100,
                formatter: function (value, row, index) {
                    return value ? value.name : "";
                }
            },
            {field: 'state', title: '状态', width: 100,
                formatter: function (value, row, index) {
                    return value ? "正常" : "<font color='red'>停用</font>"
                }
            }
        ]],
        onClickRow: function (index, row) {
            if (!row.state) {
                //如果为离职,那么就设置按钮为入职
                $("#changeState").linkbutton({
                    text: "使用"
                })

            } else if (row.state) {
                //如果为在职,那么就设置按钮为离职
                $("#changeState").linkbutton({
                    text: "停用"
                })
            }
        }
    })

    //初始化弹出框
    department_dialog.dialog({
        width: 300,
        height: 250,
        buttons: '#btn_buttons',
        closed: true
    })
})