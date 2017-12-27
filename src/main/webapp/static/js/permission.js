$(function () {
    //权限菜单
    $("#permission_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect: true,
        rownumbers: true,
        striped: true,
        pageSize: 10,
        pageList: [10, 15, 20],
        url: '/permission/query.do',
        columns: [[
            {field: 'name', title: '权限名称', width: 100},
            {field: 'resource', title: '权限表达式', width: 100}
        ]],
        toolbar: '#btn_toolbar'
    });
});

//加载权限:
function reload() {
    $.get('/permission/reload.do', function (data) {
        if (data.flag) {
            $.messager.alert('温馨提示', '加载成功', 'info', function () {
                $("#permission_datagrid").datagrid('reload');
            });
        } else {
            $.messager.alert('温馨提示', data.success, 'error');
        }
    }, "json");
}