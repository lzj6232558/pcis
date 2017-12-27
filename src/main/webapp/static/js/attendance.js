$(function () {

    var attendance_datagrid = $('#attendance_datagrid');//数据表格
    var searchForm = $('#searchForm');//高级查询提交
    var attendance_dialog = $("#attendance_dialog");//新增/编辑对话框
    var attendance_form = $("#attendance_form")//对话框表单
    /**
     * 所有按钮方法抽取
     * @type {{}}
     */
    var methods = {
        //签到按钮
        signIn: function () {
            $.messager.confirm('签到确认', '是否要签到?', function (r) {
                if (r) {
                    $.get('/attendance/signIn.do', function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '签到成功', 'info', function () {
                                attendance_datagrid.datagrid('reload');
                            });
                        } else {
                            $.messager.alert('温馨提示', data.message, 'error');
                            attendance_datagrid.datagrid('reload');
                        }
                    });
                }
            });
        },
        //签退按钮
        signOut: function () {
            $.messager.confirm('签退确认', '是否要签退?', function (r) {
                if (r) {
                    $.get('/attendance/signOut.do', function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '签退成功！', 'info', function () {
                                attendance_datagrid.datagrid('reload');
                            });
                        } else {
                            $.messager.alert('温馨提示', data.message, 'error');
                            attendance_datagrid.datagrid('reload');
                        }
                    });
                }
            });
        },
        //高级查询提交
        searchForm: function () {
            var username = $("#username").textbox('getValue');
            var department = $("#department").combobox('getValue');
            var beginDate = $("#beginDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            attendance_datagrid.datagrid('load', {
                username: username,
                department: department,
                beginDate: beginDate,
                endDate: endDate
            });
        },
        //高级查询重置
        reset: function () {
            $("#username").textbox('setValue', []);
            $("#department").combobox('setValue', []);
            $("#beginDate").datebox('clear');
            $("#endDate").datebox('clear');
            var username = $("#username").textbox('getValue');
            var department = $("#department").combobox('getValue');
            var beginDate = $("#beginDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            attendance_datagrid.datagrid('load', {
                username: username,
                department: department,
                beginDate: beginDate,
                endDate: endDate
            });
        },
        //刷新按钮
        reload: function () {
            attendance_datagrid.datagrid('reload');
        },
        //新增按钮
        add: function () {
            attendance_dialog.dialog('setTitle', '新增签到');
            attendance_form.form('clear');
            attendance_dialog.dialog("open");
        },
        //编辑按钮
        edit: function () {
            var row = attendance_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选择要编辑的一条数据', 'info');
            } else {
                attendance_form.form('clear');
                //员工姓名回显
                if (row.employee != null) {
                    row['employee.id'] = row.employee.id;
                }
                //补签姓名回显
                if (row.resignInEmployee != null) {
                    row['resignInEmployee.id'] = row.resignInEmployee.id;
                }
                //数据回显
                attendance_form.form('load', row);
                //设置标题
                attendance_dialog.dialog('setTitle', '编辑签到信息');
                attendance_dialog.dialog("open");
            }
        },
        //对话框取消
        cancel: function () {
            attendance_dialog.dialog("close");
        },
        //对话框保存
        save: function () {
            attendance_form.form('submit', {
                url: '/attendance/saveOrUpdate.do',
                //员工角色数据
                /*onSubmit: function (param) {
                    var roleIds = $("#role_combobox").combobox("getValues");
                    for (var i = 0; i < roleIds.length; i++) {
                        param['roles[' + i + '].id'] = roleIds[i];
                    }
                },*/
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        attendance_dialog.dialog("close");
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            attendance_datagrid.datagrid('reload');
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //删除按钮
        delete: function () {
            var row = attendance_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选择要操作的一条数据', 'info');
            } else {
                $.messager.confirm('温馨提示', '是否要操作该数据?', function (t) {
                    if (t) {
                        $.get('/attendance/deleteByPrimaryKey.do', {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert('温馨提示', '操作成功', 'info', function () {
                                    attendance_datagrid.datagrid('reload');
                                });
                            } else {
                                $.messager.alert('温馨提示', data.msg, 'error');
                            }
                        });
                    }
                });
            }
        },
        //补签按钮
        resignIn: function () {
            var row = attendance_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选择要操作的一条数据', 'info');
            } else {
                $.messager.confirm('签到确认', '是否要为该员工补签?', function (r) {
                    if (r) {
                        $.get('/attendance/resignIn.do', {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert('温馨提示', '签到成功', 'info', function () {
                                    attendance_datagrid.datagrid('reload');
                                });
                            } else {
                                $.messager.alert('温馨提示', data.message, 'error');
                                attendance_datagrid.datagrid('reload');
                            }
                        });
                    }
                });
            }
        }
    }

    /**
     * 统一绑定按钮方法
     */
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        methods[method]();
    });


    attendance_datagrid.datagrid({
        scrollbarSize: 0,
        fitColumns: true,
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/attendance/list.do',
        columns: [[
            {field: 'id', title: '编号', width: 50, align: 'center'},
            {
                field: 'employee', title: '员工姓名', width: 100, formatter: function (value, row, index) {
                    return value ? value.username : '';
                }
            },
            {field: 'signInIP', title: '签到IP', width: 100, align: 'center'},
            {
                field: 'signInDate', title: '签到时间', width: 120, align: 'center',
                formatter: function (value, row, index) {
                    //为空不操作
                    if (value == null) {
                        return;
                    }
                    //如果不在规定的时间内显示为红色
                    var date = new Date(value);
                    console.log(date.getHours())
                    console.log(date.getMinutes())
                    console.log(date.getSeconds())
                    var begin = 30 * 60 * 1000 + 8 * 60 * 60 * 1000;
                    var end = 30 * 60 * 1000 + 17 * 60 * 60 * 1000;
                    var time = date.getHours() * 60 * 60 * 1000 + date.getMinutes() * 60 * 1000 + date.getSeconds() * 1000;
                    if (time < begin || time > end) {
                        return value
                    } else {
                        return '<font color="red">' + value + '</font>';
                    }
                }
            },
            {
                field: 'signOutDate',
                title: '签退时间',
                width: 120,
                align: 'center',
                formatter: function (value, row, index) {
                    //为空不操作
                    if (value == null) {
                        return;
                    }
                    //如果不在规定的时间内显示为红色
                    var date = new Date(value);
                    var begin = 30 * 60 * 1000 + 8 * 60 * 60 * 1000;
                    var end = 30 * 60 * 1000 + 17 * 60 * 60 * 1000;
                    var time = date.getHours() * 60 * 60 * 1000 + date.getMinutes() * 60 * 1000 + date.getSeconds() * 1000;
                    if (time < begin || time > end) {
                        return value
                    } else {
                        return '<font color="red">' + value + '</font>';
                    }
                }
            },
            {
                field: 'state', title: '考勤状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value ? '正常' : '<font color="red">异常</font>';
                }
            },
            {
                field: 'resignInEmployee',
                title: '补签人',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.username : '';
                }
            },
            {field: 'resignInDate', title: '补签时间', width: 100, align: 'center'}
        ]],
        toolbar: '#attendance_toolbar'
    });


    $("#department").combobox({
        url: '/department/query.do',
        valueField: 'id',
        textField: 'name'
    });

    attendance_dialog.dialog({
        width: 300,
        height: 320,
        closed: true,
        buttons: '#dialog_btns'
    });


    $("#username").textbox({
        onChange: function (newValue, oldValue) {
            methods['searchForm']();
        }
    });
    $("#department").combobox({
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