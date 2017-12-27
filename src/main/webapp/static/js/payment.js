$(function () {

    var payment_datagrid = $('#payment_datagrid');//数据表格
    var product_datagrid = $('#product_datagrid');
    var searchForm = $('#searchForm');//高级查询提交
    var payment_dialog = $("#payment_dialog");//新增/编辑对话框
    var payment_form = $("#payment_form");//对话框表单


    var methods = {
        //缴费弹窗
        pay: function () {
            var row = payment_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选择要操作的一条数据', 'info');
            } else {
                payment_form.form('clear');
                //顾客回显
                if (row.customer) {
                    row['customer.name'] = row.customer.name;
                }
                //保险公司回显
                if (row.safetymechanism) {
                    row['safetymechanism.name'] = row.safetymechanism.name;
                }
                //所买产品回显
                product_datagrid.datagrid({
                    width: 250,
                    striped: true,
                    scrollbarSize: 0,
                    fitColumns: true,
                    url: '/payment/getProducts.do?policyId=' + row.id,
                    columns: [[
                        {field: 'name', title: '保险名称', width: 120, align: 'center'},
                        {field: 'annuafee', title: '基本年费', width: 80, align: 'center'},
                    ]]
                });
                //数据回显
                payment_form.form('load', row);
                //设置标题
                payment_dialog.dialog('setTitle', '缴费确认');
                payment_dialog.dialog("open");
            }
        },
        //刷新按钮
        reload: function () {
            payment_datagrid.datagrid('reload');
        },
        //对话框取消
        cancel: function () {
            payment_dialog.dialog("close");
        },
        //对话框保存
        save: function () {
            payment_form.form('submit', {
                url: '/payment/save.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        payment_dialog.dialog("close");
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            payment_datagrid.datagrid('reload');
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //退回按钮
        return: function () {
            var row = payment_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', '请选择要操作的一条数据', 'info');
            } else {
                $.messager.confirm('温馨提示', '是否要退回该数据到待修改状态?', function (t) {
                    if (t) {
                        $.get('/payment/returnBack.do', {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert('温馨提示', '操作成功', 'info', function () {
                                    payment_datagrid.datagrid('reload');
                                });
                            } else {
                                $.messager.alert('温馨提示', data.msg, 'error');
                            }
                        });
                    }
                });
            }
        },
        //高级查询提交
        searchForm: function () {
            var applicant = $("#applicant").textbox('getValue');
            var policyNo = $("#policyNo").textbox('getValue');
            payment_datagrid.datagrid('load', {
                applicant: applicant,
                policyNo: policyNo
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

    payment_datagrid.datagrid({
        fitColumns: true,
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        scrollbarSize:0,
        pagination: true,
        url: '/payment/query.do',
        columns: [[
            {
                field: 'customer', title: '投保人', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value ? value.name : "";
                }
            },
            {
                field: 'sn', title: '保单编号', width: 100, align: 'center', formatter:
                    function (value, row, index) {
                        return '<span style="font-size:10px">' + value + '</span>';//改变表格中内容字体的大小
                    },
            },
            {field: 'totalAmount', title: '投保总金额', width: 100, align: 'center'},
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    var state = "";
                    if (value == 0) {
                        state = "暂存";
                    } else if (value == 1) {
                        state = "已提交待审核"
                    } else if (value == 2) {
                        state = "已审核待缴费"
                    } else if (value == 3) {
                        state = "待修改"
                    } else if (value == 4) {
                        state = "已缴费"
                    } else if (value == 5) {
                        state = "拒保"
                    }
                    return state;
                }
            },
            {field: 'duration', title: '保险时长(年)', width: 100, align: 'center'},
            {field: 'checkDate', title: '核保日期', width: 100, align: 'center'},
            {
                field: 'safetymechanism', title: '保险机构', width: 100,
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            }
        ]],
        toolbar: '#payment_toolbar'
    });


    payment_dialog.dialog({
        width: 500,
        height: 500,
        closed: true,
        buttons: '#dialog_btns'
    });

});