$(function () {

    var role_datagrid = $("#role_datagrid");
    var role_dialog = $("#role_dialog");
    var role_form = $("#role_form");

    var allPermissions = $("#allPermissions");
    var selfPermissions = $("#selfPermissions");


    var methods = {
        add: function () {
            role_dialog.dialog('setTitle', '新增角色');
            role_form.form('clear');
            //先清空已选列表中的选项,每次点击新增已选权限都是空的
            selfPermissions.datagrid('loadData', []);
            role_dialog.dialog("open");
        },

        edit: function () {
            var row = role_datagrid.datagrid('getSelected');
            console.log(row);
            if (!row) {
                $.messager.alert('温馨提示', '请选择要编辑的一条数据', 'info');
            } else {
                //清空表单:
                role_form.form('clear');
                //先清空已选列表中的选项
                selfPermissions.datagrid('loadData', []);
                //回显角色拥有的权限数据:
                selfPermissions.datagrid('load', {roleId: row.id});

                role_form.form('load', row);
                role_dialog.dialog('setTitle', '编辑角色');
                role_dialog.dialog("open");
            }
        },

        delete: function () {
            var row = role_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选择要操作的一条数据', 'info');
            } else {
                $.messager.confirm('温馨提示', '是否要删除该数据?', function (t) {
                    if (t) {
                        $.get('/role/delete.do', {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert('温馨提示', '操作成功', 'info', function () {
                                    role_datagrid.datagrid('reload');
                                });
                            } else {
                                $.messager.alert('温馨提示', data.msg, 'error');
                            }
                        });
                    }
                });
            }
        },

        reload: function () {
            role_datagrid.datagrid('reload');
        },

        //取消
        cancel: function () {
            role_dialog.dialog("close");
        },

        //保存
        save: function () {
            role_form.form('submit', {
                url: '/role/saveOrUpdate.do',
                /**
                 * onSubmit会在表单提交之前执行,默认返回true表示确认,返回false则会阻止表单提交
                 * 还可以在这里提供表单提交的其他数据,
                 * 这里要获取已选权限中的所有权限作为参数一起提交
                 */
                onSubmit: function (param) {
                    //获取所有已选权限行
                    var rows = selfPermissions.datagrid('getRows');
                    for (var i = 0; i < rows.length; i++) {
                        //spring mvc能识别permissions[0].id这样的参数,所以我们要封装成这样子
                        param['permissions[' + i + '].id'] = rows[i].id;
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        role_dialog.dialog("close");
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            role_datagrid.datagrid('reload');
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        }

    }

    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        methods[method]();
    });

    role_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        striped: true,
        url: '/role/query.do',
        columns: [[
            {field: 'sn', title: '角色编号', width: 100},
            {field: 'name', title: '角色名称', width: 100}
        ]],
        toolbar: '#role_btns',
        //角色不多,分页没加;
        //pagination: true,
        //pageSize: 8,
        //pageList: [8, 15, 20],
    });

    role_dialog.dialog({
        top: 50,
        width: 500,
        height: 420,
        closed: true,
        buttons: '#dialog_btns'
    });

    allPermissions.datagrid({
        title: '所有权限',
        rownumbers: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        width: 210,
        height: 290,
        url: '/permission/getAll.do',
        columns: [[
            {field: 'name', title: '角色名称', width: 100}
        ]], toolbar: [{
            iconCls: 'icon-reload',
            plain: true,
            text: '重新加载所有权限',
            handler: function () {
                $.get('/permission/reload.do', function (data) {
                    if (data.success) {
                        $.messager.alert('温馨提示', '加载成功', 'info');
                        allPermissions.datagrid('load');
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }, "json");
            }
        }],
        /**
         * 将选中行的添加到已选权限列表中,
         * 先先判断已选权限列表中是否已经有该权限,
         * 如果有该权限就在已选权限列表中选中该权限,
         * 如果没有,那就添加该权限
         * @param index
         * @param row
         */
        onClickRow: function (index, row) {
            //获取已选权限中的所有行
            var rows = selfPermissions.datagrid('getRows');
            //console.log(row.resource);//该行的权限表达式
            //console.log(rows);//object对象数组
            //判断该权限是否存在
            for (var i = 0; i < rows.length; i++) {
                if (row.resource == rows[i].resource) {
                    //存在该权限就选中该权限
                    selfPermissions.datagrid('selectRow', i);
                    return;
                }
            }
            //将点击的那一行添加到已选权限中去
            selfPermissions.datagrid('appendRow', row);


        }
    });

    selfPermissions.datagrid({
        title: '已选权限',
        rownumbers: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        width: 210,
        height: 290,
        url: '/permission/getPermissionByRoleId.do',
        columns: [[
            {field: 'name', title: '角色名称', width: 100}
        ]],
        toolbar: [{
            iconCls: 'icon-remove',
            plain: true,
            text: '清空已选权限',
            handler: function () {
                selfPermissions.datagrid('loadData', []);
            }
        }],
        onClickRow: function (index, row) {
            selfPermissions.datagrid('deleteRow', index);
        }
    });
});

