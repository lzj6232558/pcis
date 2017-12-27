$(function () {
    //定义变量
    var plan_datagrid = $("#plan_datagrid");
    var plan_dialog = $('#plan_dialog');
    var plan_form = $("#plan_form");
    var chooseCust_dialog = $("#chooseCust_dialog");
    var cust_datagrid = $("#cust_datagrid");
    var mark_dialog = $("#mark_dialog");
    var mark_form = $("#mark_form");


    //初始化dialog
    mark_dialog.dialog({
        title: '标记执行结果',
        width: 300,
        height: 230,
        // 底部按钮
        buttons: "#mark_btns",
        closed: true,
        modal: true,
    });


    //初始化dialog
    chooseCust_dialog.dialog({
        title: '请选择潜在客户',
        width: 760,
        height: 490,
        buttons: "#chooseCust_btns",
        closed: true,
        resizable: true,
        //定义是否将窗体显示为模式化窗口。
        modal: true
    });


    var methodObj = {
        //标记结果提交
        result: function () {
            mark_form.form('submit', {
                url: '/plan/result.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('提示', '操作成功', 'info');
                        mark_dialog.dialog('close');
                        plan_datagrid.datagrid('reload')
                    } else {
                        $.messager.alert('提示', "操作失败", 'info');

                    }
                }


            })


        },

        selectCust: function () {
            //获取选中行
            var row = cust_datagrid.datagrid("getSelected");
            //设值
            $("[name='customer.id']").val(row.id);
            $("#cust").textbox("setText", row.name)


            chooseCust_dialog.dialog("close");
        },

        add: function () {
            plan_form.form('clear');
            plan_dialog.dialog('open')
            plan_dialog.dialog('setTitle', '添加计划')

        },
        edit: function () {
            //选中一条数据
            var row = plan_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('提示', '请选中一条信息', 'info');
                return;
            }

            plan_form.form('clear');
            plan_dialog.dialog('setTitle', '调整计划')
            if (row.customer) {
                row["customer.id"] = row.customer.id;
                row["customer.name"] = row.customer.name;
            }
            //回显
            plan_form.form('load', row);

            plan_dialog.dialog('open');

        },
        save: function () {
            plan_form.form('submit', {
                url: '/plan/saveOrUpdate.do',
                success: function (data) {
                    //转json格式
                    var data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('提示', '操作成功!', 'info');
                        //关闭弹框
                        plan_dialog.dialog('close');
                        //加载表格
                        plan_datagrid.datagrid('reload');

                    } else {

                        $.messager.alert('提示', '操作失败!', 'info');
                    }
                }
            })
        },
        cancel: function () {
            plan_dialog.dialog('close');
            mark_dialog.dialog('close')
        },
        reload: function () {
            plan_datagrid.datagrid('load')
        },
        //弹出潜在客户列表
        chooseCust: function () {
            chooseCust_dialog.dialog("open")
        },
        mark: function () {
            //选中一条数据
            var row = plan_datagrid.datagrid('getSelected');

            if (!row) {
                $.messager.alert('提示', '请选中一条信息', 'info');
                return;
            }
            mark_dialog.dialog("open");
            //回显,用于获取row的id
            mark_form.form("load", row)

            row["id"] = row.id;
            $("#sn").html(row.id);
            $("#subject").html(row.subject);


        },


        remove: function () {
            //选中一条数据
            var row = plan_datagrid.datagrid('getSelected');

            if (!row) {
                $.messager.alert('提示', '请选中一条信息', 'info');
                return;
            }
            $.messager.confirm("温馨提示", "确定要删除计划 : " + "<span style='color: #ff1b2e'>" + row.subject + "</span>" + " 吗?", function (r) {
                console.log("确认删除" + r)
                if (r) {
                    $.get("/plan/delete.do", {id: row.id}, function (data) {
                        console.log(data)
                        if (data.success) {
                            $.messager.alert("温馨提示", "删除成功", "info", function () {
                                plan_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert("温馨提示", "删除失败", "info");
                        }
                    })
                }
            })


        },
        searchForm: function () {
            var keywords = $("#keywords").textbox('getValue');
            var beginDate = $("input[name=beginDate]").val();
            var endDate = $("input[name=endDate]").val();
            plan_datagrid.datagrid('load',
                {
                    keywords: keywords,
                    beginDate: beginDate,
                    endDate: endDate

                })
        }

    }
    //统一绑定事件
    $('[data-cmd]').click(function () {
        var method = $(this).data('cmd');
        methodObj[method]()
    })

    //潜在客户列表
    cust_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: '/customer/query.do?status=1',
        columns: [[
            {
                field: 'inputuser', title: '负责人', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.username : '';
            }
            },
            {field: 'name', title: '客户姓名', width: 100, align: 'center'},
            {field: 'gender', title: '性别', width: 100, align: 'center'},
            {field: 'tel', title: '电话', width: 100, align: 'center'},
            {field: 'email', title: '邮箱', width: 100, align: 'center'},
            {field: 'idno', title: '证件号码', width: 100, align: 'center'},
            {field: 'address', title: '地址', width: 100, align: 'center'}
        ]],
        striped: true,
        pagination: true,
        singleSelect: true

    })
    plan_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: '/plan/query.do',
        toolbar: '#plan_tools',
        columns: [[
            {field: 'id', title: '编号', width: 40, align: 'center'},
            {field: 'date', title: '计划日期', width: 100, align: 'center'},
            {
                field: 'inputuser', title: '录入人', width: 80,
                formatter: function (value, row, index) {
                    return value ? value.username : '';
                }
            },
            {
                field: 'customer', title: '客户姓名', width: 80,
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {field: 'subject', title: '计划名称', width: 100, align: 'center'},
            {field: 'detail', title: '计划详情', width: 300, align: 'center'},
            {field: 'type', title: '实施方式', width: 100, align: 'center'},
            {
                field: 'result', title: '执行效果', width: 100, formatter: function (value, row, index) {
                if (value == 3) {
                    return "<span style='color: #3eff12'>优</span>";
                }
                ;
                if (value == 2) {
                    return "<span style='color: #140eff'>中</span>";
                }
                ;
                if (value == 1) {
                    return "<span style='color: #ff1812'>差</span>";
                }
                ;
            }
            },
            {field: 'remark', title: '备注', width: 100, align: 'center'}
        ]],
        striped: true,
        pagination: true,
        singleSelect: true,
        ctrlSelect: true

    });
    plan_dialog.dialog({
        width: 670,
        height: 380,
        buttons: '#plan_btns',
        closed: true
    });


});