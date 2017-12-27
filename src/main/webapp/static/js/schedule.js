$(function () {
    var schedule_datagrid = $('#schedule_datagrid');//数据表格
    var searchForm = $('#searchForm');//高级查询提交
    var schedule_dialog = $("#schedule_dialog");//新增/编辑对话框
    var schedule_form = $("#schedule_form");//对话框表单


    var methods = {
        //刷新按钮
        reload: function () {
            schedule_datagrid.datagrid('reload');
        },
        //新增按钮
        add: function () {
            schedule_dialog.dialog('setTitle', '新增任务');
            schedule_form.form('clear');
            $("#employee").combobox('enable', true);
            $("#sn").textbox('enable', true);
            $("#plan").datebox('enable', true);
            $("#place").textbox('enable', true);
            $("#content").textbox('enable', true);
            $("#saveButton").linkbutton('enable', true);
            schedule_dialog.dialog("open");
        },
        //编辑按钮
        edit: function () {
            var row = schedule_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选择要编辑的一条数据', 'info');
            } else {
                schedule_form.form('clear');
                //员工姓名回显
                row.task['employee.id'] = row.task.employee.id;
                //数据回显
                schedule_form.form('load', row.task);
                //设置标题
                schedule_dialog.dialog('setTitle', '任务编辑');
                schedule_dialog.dialog("open");
            }
        },
        //对话框取消
        cancel: function () {
            schedule_dialog.dialog("close");
        },
        //对话框保存
        save: function () {
            schedule_form.form('submit', {
                url: '/schedule/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        schedule_dialog.dialog("close");
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            schedule_datagrid.datagrid('reload');
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //删除按钮
        delete: function () {
            var row = schedule_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选择要操作的一条数据', 'info');
            } else {
                $.messager.confirm('温馨提示', '是否要操作该数据?', function (t) {
                    if (t) {
                        $.get('/schedule/deleteByPrimaryKey.do', {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert('温馨提示', '操作成功', 'info', function () {
                                    schedule_datagrid.datagrid('reload');
                                });
                            } else {
                                $.messager.alert('温馨提示', data.msg, 'error');
                            }
                        });
                    }
                });
            }
        },
        //标记完成按钮
        changeState: function () {
            //判断用户是否选中数据
            var row = schedule_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条数据!", "warning");
                return;
            }
            $.messager.confirm('温馨提示', '是否要标记为已完成？', function (r) {
                if (r) {
                    $.get("/schedule/changeState.do?id=" + row.id, function (data) {
                        if (data.success) {
                            //弹出提示信息
                            $.messager.alert("温馨提示", "操作成功!", "info", function () {
                                //重新加载数据表格
                                schedule_datagrid.datagrid("reload");
                            });
                        } else {
                            //弹出提示信息
                            $.messager.alert("温馨提示", data.message, "error");
                        }

                    }, "json")
                }
            });
        },
        //高级查询
        searchForm: function () {
            var username = $("#username").textbox('getValue');
            var dept_id = $("#dept_id").textbox('getValue');
            var beginDate = $("#beginDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            schedule_datagrid.datagrid('load', {
                username: username,
                dept_id: dept_id,
                beginDate: beginDate,
                endDate: endDate
            });
        },
        resetForm: function () {
            $("#username").textbox('setValue', []);
            $("#dept_id").textbox('setValue', []);
            $("#beginDate").datebox('clear');
            $("#endDate").datebox('clear');
            //methods['serachForm']();
            var username = $("#username").textbox('getValue');
            var dept_id = $("#dept_id").textbox('getValue');
            var beginDate = $("#beginDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            schedule_datagrid.datagrid('load', {
                username: username,
                dept_id: dept_id,
                beginDate: beginDate,
                endDate: endDate
            });
        },
    }


    /**
     * 统一绑定按钮方法
     */
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        methods[method]();
    });


    schedule_datagrid.datagrid({
        fitColumns: true,
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/schedule/list.do',
        columns: [[
            {field: 'createDate', title: '创建时间', width: 100, align: 'center'},
            {
                field: 'employee', title: '员工姓名', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value ? value.username : '';
                }
            },
            {
                field: 'department', title: '部门', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {
                field: 'task', title: '任务', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value ? value.sn : '';
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (value == null) {
                        return '<font color="orange">待完成</font>'
                    } else if (value) {
                        return '<font color="#006400">已完成</font>'
                    } else {
                        return '<font color="red">未完成</font>';
                    }
                }
            }
        ]],
        toolbar: '#schedule_toolbar',
        onClickRow: function (index, row) {
            if (row.state == true || row.state == false) {
                $('#changeState').linkbutton('disable');
            } else {
                $('#changeState').linkbutton('enable');
            }
        },
        //双击某行弹出对话框
        onDblClickRow: function (index, row) {
            if (row.state == true || row.state == false) {
                //打开对话框
                //员工姓名回显
                row.task['employee.id'] = row.task.employee.id;
                //数据回显
                schedule_form.form('load', row.task);
                //设置标题
                schedule_dialog.dialog('setTitle', '任务查看');
                $("#employee").combobox('disable', true);
                $("#sn").textbox('disable', true);
                $("#plan").datebox('disable', true);
                $("#place").textbox('disable', true);
                $("#content").textbox('disable', true);
                $("#saveButton").linkbutton('disable', true);
                schedule_dialog.dialog('open');
            } else {
                //员工姓名回显
                row.task['employee.id'] = row.task.employee.id;
                //数据回显
                schedule_form.form('load', row.task);
                //设置标题
                schedule_dialog.dialog('setTitle', '任务查看');
                $("#employee").combobox('enable', true);
                $("#sn").textbox('enable', true);
                $("#plan").datebox('enable', true);
                $("#place").textbox('enable', true);
                $("#content").textbox('enable', true);
                $("#saveButton").linkbutton('enable', true);
                schedule_dialog.dialog('open');
            }
        }
    });

    /*对话框初始化*/
    schedule_dialog.dialog({
        width: 300,
        height:410,
        closed: true,
        buttons: '#dialog_btns'
    });

    //任务完成时间验证,只能选未来的日期
    $("#plan").datebox('calendar').calendar({
        validator: function (date) {
            var now = new Date();
            var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
            return date > d1;
        }
    });

    $("#username").textbox({
        onChange: function (newValue, oldValue) {
            methods['searchForm']();
        }
    });

    $("#dept_id").combobox({
        onChange: function (newValue, oldValue) {
            methods['searchForm']();
        }
    });

    $("#beginDate").datebox({
        onChange: function (newValue, oldValue) {
            methods['searchForm']();
        }
    });
    $("#endDate").datebox({
        onChange: function (newValue, oldValue) {
            methods['searchForm']();
        }
    });

});