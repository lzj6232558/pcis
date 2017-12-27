$(function () {
    //变量抽取
    var product_datagrid = $("#product_datagrid");
    var product_dialog = $("#product_dialog");
    var product_form = $("#product_form");


    var methodObj = {

        add: function () {
            //清空表单数据
            product_form.form("clear");
            product_dialog.dialog("setTitle", "添加保险");
            product_dialog.dialog("open");


        },
        edit: function () {
            var row = product_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条记录！', 'info');
                return;
            }
            ;
            //清空数据
            product_form.form("clear");
            product_dialog.dialog("setTitle", "修改保险");
            //保险机构
            if (row.safetyMechanism) {
                row["safetyMechanism.name"] = row.safetyMechanism.name;
                row["safetyMechanism.id"] = row.safetyMechanism.id;
            }
            //回显
            product_form.form("load", row);
            product_dialog.dialog("open");
        },
        reload: function () {
            product_datagrid.datagrid('load');
        },
        save: function () {
            product_form.form('submit', {
                url: '/product/saveOrUpdate.do',
                success: function (data) {
                    //使用easyui的form提交,需要把data转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            //重新加载数据表格
                            product_datagrid.datagrid("load");
                            //关闭弹出框
                            product_dialog.dialog("close");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.message, 'info');
                    }
                }
            })


        },
        cancel: function () {
            product_dialog.dialog("close");
        },
        searchForm: function () {
            var keywords = $("#keywords").textbox('getValue');
            var smId = $("#smId").textbox('getValue');
            var sStatus = $("#sStatus").textbox('getValue');
            //带上关键字重新加载
            product_datagrid.datagrid('load', {
                keywords: keywords,
                smId: smId,
                sStatus: sStatus
            });

        }


    };

    //统一绑定点击事件
    $('[data-cmd]').click(function () {
        var method = $(this).data('cmd');
        //执行
        methodObj[method]();

    });


    product_datagrid.datagrid({
        url: '/product/query.do',
        columns: [[
            {field: 'sn', title: '编号', width: 100, align: 'center'},
            {field: 'name', title: '保险名称', width: 100, align: 'center'},
            {
                field: 'safetyMechanism', title: '保险机构', width: 200,
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {field: 'safeguardYear', title: '保障年限', width: 100, align: 'center'},
            {field: 'totalMoney', title: '保额', width: 100, align: 'center'},
            {field: 'annuafee', title: '基本年费', width: 100, align: 'center'},
            {
                field: 'salesStatus', title: '销售状态', width: 50, align: 'center',
                formatter: function (value, row, index) {
                    return value ? "<font color='blue'>在售</font>" : "<font color='red'>停售</font>"
                }
            },
            {
                field: 'undeduction', title: '不计免赔', width: 50, align: 'center',
                formatter: function (value, row, index) {
                    return value ? "<font color='blue'>是</font>" : "<font color='red'>否</font>"
                }
            },
            {field: 'intro', title: '简介', width: 100, align: 'center'}
        ]],
        fit: true,
        fitColumns: true,
        toolbar: "#product_toobar",
        striped: true,
        pagination: true,
        singleSelect: true,
        ctrlSelect: true
    });
    //初始化弹出框
    product_dialog.dialog({
        width: 300,
        height: 450,
        buttons: '#product_btns',
        closed: true
    })


});