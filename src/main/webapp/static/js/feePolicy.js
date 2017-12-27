$(function () {

    var fee_datagrid = $('#fee_datagrid');//数据表格
    var product_datagrid = $('#product_datagrid');
    var fee_dialog = $("#fee_dialog");//查看对话框
    var fee_form = $("#fee_form");//对话框表单


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
                    width: 249,
                    striped: true,
                    //scrollbarSize: 0,
                    fitColumns: false,
                    url: '/payment/getProducts.do?policyId=' + row.id,
                    columns: [[
                        {field: 'name', title: '保险名称', width: 130, align: 'center'},
                        {field: 'annuafee', title: '基本年费', width: 120, align: 'center'},
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
            fee_datagrid.datagrid('reload');
        },
        //对话框取消
        cancel: function () {
            payment_dialog.dialog("close");
        },
        //高级查询提交
        searchForm: function () {
            var keywords = $("#keywords").textbox('getValue');
            var policySn = $("#policySn").textbox('getValue');
            var beginDate = $("#beginDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            fee_datagrid.datagrid('load', {
                keywords: keywords,
                policySn: policySn,
                beginDate: beginDate,
                endDate: endDate
            });
        },
        //对话框取消
        cancel: function () {
            fee_dialog.dialog("close");
        },
        //高级查询框重置
        reset: function () {
            $("#keywords").textbox('setValue', []);
            $("#policySn").textbox('setValue', []);
            $("#beginDate").datebox('clear');
            $("#endDate").datebox('clear');
            //methods['serachForm']();
            var keywords = $("#keywords").textbox('getValue');
            var policySn = $("#policySn").textbox('getValue');
            var beginDate = $("#beginDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            fee_datagrid.datagrid('load', {
                keywords: keywords,
                policySn: policySn,
                beginDate: beginDate,
                endDate: endDate
            });
        }
    }

    /**
     * 统一绑定按钮方法
     */
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        methods[method]();
    });


    fee_datagrid.datagrid({
        fitColumns: true,
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        scrollbarSize: true,
        url: '/feePolicy/query.do',
        columns: [[
            {field: 'serialNumber', title: '保单号', width: 100, align: 'center'},
            {
                field: 'customer', title: '投保人', width: 40, align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            /* {
                 field: 'customer.tel', title: '电话', width: 100, align: 'center',
                 formatter: function (value, row, index) {
                     return row.customer ? row.customer.tel : '';
                 }
             },*/
            /*  {
                  field: 'customer.address', title: '客户地址', width: 100, align: 'center',
                  formatter: function (value, row, index) {
                      return row.customer ? row.customer.address : '';
                  }
              },*/
            /*{
                field: 'customer.idno', title: '身份证号', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    return row.customer ? row.customer.idno : '';
                }
            },*/
            {field: 'policySn', title: '核保单号', width: 120, align: 'center'},
            {field: 'totalAmount', title: '保费金额', width: 50, align: 'center'},
            {
                field: 'state', title: '缴费状态', width: 40, align: 'center',
                formatter: function (value, row, index) {
                    return value ? '已缴费' : '<font style="color:red">待缴费</font>';
                }
            },
            {
                field: 'paymentWay', title: '缴费方式', width: 40, align: 'center',
                formatter: function (value, row, index) {
                    if (value == 0) {
                        return "现金支付";
                    }
                    if (value == 1) {
                        return "刷卡支付";
                    }
                    if (value == 2) {
                        return "支票支付";
                    }
                }
            },
            {field: 'beginDate', title: '起保日期', width: 50, align: 'center'},
            {field: 'endDate', title: '止保日期', width: 50, align: 'center'},
            {
                field: 'safetymechanism', title: '保险机构', width: 70, align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            /* {
                 field: 'inputUser', title: '录入人', width: 40, align: 'center',
                 formatter: function (value, row, index) {
                     return value ? value.username : '';
                 }
             }*/
        ]],
        toolbar: '#fee_toolbar',
        //双击某行弹出对话框
        onDblClickRow: function (index, row) {
            //打开对话框

            //其他数据回显
            row['customer.name'] = row.customer.name;
            row['customer.idno'] = row.customer.idno;
            row['safetymechanism.name'] = row.safetymechanism.name;
            row['inputUser.username'] = row.inputUser.username;
            if (row.paymentWay == 0) {
                row['paymentWay'] = '现金';
            } else if (row.paymentWay == 1) {
                row['paymentWay'] = '刷卡';
            } else if (row.paymentWay == 2) {
                row['paymentWay'] = '支票';
            }
            //所买产品回显
            product_datagrid.datagrid({
                width: 200,
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
            fee_form.form('load', row);
            //设置标题
            fee_dialog.dialog('setTitle', '查看详情');
            fee_dialog.dialog('open');
        }
    });

    fee_dialog.dialog({
        width: 550,
        height: 550,
        closed: true,
        buttons: '#dialog_btns'
    });


    $("#keywords").combobox({
        panelHeight: 'auto',//下拉自动高度
        panelMaxHeight: 100,//最大高度
        hasDownArrow: false,//隐藏箭头
        valueField: 'value',
        textField: 'text',
        mode: 'remote',
        loader: function (param, success, error) {
            var q = param.q || ""; //输入框中有值或者为空是都会刷新下拉框中的内容
            if (q.length <= 0) {
                console.log("q.length <= 0");
                return false;
            }
            $.ajax({
                url: '/feePolicy/autoSearchKeywords.do',
                type: "post",
                data: {param: q},//后台使用param这个变量接收传值的
                dataType: "json",
                success: function (data) {
                    //返回的json数据格式,参数名和参数值要和页面valueField,textField项对应
                    success(data);//调用loader的success方法，将items添加到下拉框中
                }
            });
        },
        onHidePanel: function (newValue, oldValue) {
            methods['searchForm']();
        }
    });

    $("#policySn").combobox({
        panelHeight: 'auto',
        panelMaxHeight: 100,
        hasDownArrow: false,
        valueField: 'value',
        textField: 'text',
        mode: 'remote',
        loader: function (param, success, error) {
            var q = param.q || " "; //输入框中有值或者为空是都会刷新下拉框中的内容
            if (q.length <= 0) {
                console.log("q.length <= 0");
                return false;
            }
            $.ajax({
                url: '/feePolicy/autoSearchPolicySn.do',
                type: "post",
                data: {param: q},//后台使用param这个变量接收传值的，后台用了struts、spring后面就不拓展说明了
                dataType: "json",
                success: function (data) {
                    //返回的json数据格式,参数名和参数值要和页面valueField,textField项对应
                    success(data);//调用loader的success方法，将items添加到下拉框中
                }
            });
        },

        onHidePanel: function (newValue, oldValue) {
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

var btsloader = function (param, success, error) {
    var q = param.q || " "; //输入框中有值或者为空是都会刷新下拉框中的内容
    if (q.length <= 0) {
        console.log("q.length <= 0");
        return false;
    }
    $.ajax({
        url: '/feePolicy/autoSearchPolicySn.do',
        type: "post",
        data: {param: q},//后台使用param这个变量接收传值的，后台用了struts、spring后面就不拓展说明了
        dataType: "json",
        success: function (data) {
            //返回的json数据格式,参数名和参数值要和页面valueField,textField项对应
            success(data);//调用loader的success方法，将items添加到下拉框中
        }
    });
}