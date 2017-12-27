$(function () {
    //变量抽取
    var safe_datagrid = $("#safe_datagrid");
    var safe_dialog = $("#safe_dialog");
    var safe_form = $("#safe_form");


    var methodObj = {

        add: function () {
            //清空表单数据
            safe_form.form("clear");
            safe_dialog.dialog("setTitle", "新增机构");
            safe_dialog.dialog("open");


        },
        edit: function () {
            var row = safe_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条记录！', 'info');
                return;
            }
            ;
            //清空数据
            safe_form.form("clear");
            safe_dialog.dialog("setTitle", "编辑机构");
            //回显
            safe_form.form("load", row);
            safe_dialog.dialog("open");
        },
        reload: function () {
            safe_datagrid.datagrid('reload');
        },
        save: function () {
            safe_form.form('submit', {
                url: '/safe/saveOrUpdate.do',
                success: function (data) {
                    //使用easyui的form提交,需要把data转成json对象
                    data=$.parseJSON(data);
                    if(data.success){
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            //重新加载数据表格
                            safe_datagrid.datagrid("load");
                            //关闭弹出框
                            safe_dialog.dialog("close");
                        });
                    }else{
                        $.messager.alert('温馨提示', data.message, 'info');
                    }
                }
            })


        },
        cancel: function () {
            safe_dialog.dialog("close");
        },
        searchForm:function () {
            console.log($("#keywords"));
            var keywords=$("#keywords").textbox('getValue')//获取组建的值
            //让表格重新加载,并且带上查询数据
            //会帮你带上查询的参数到后台,直接把后台返回的数据封装到datagrid中
            safe_datagrid.datagrid('load',{keywords:keywords});


        }


    };

    //统一绑定点击事件
    $('[data-cmd]').click(function () {
        var method=$(this).data('cmd');
        //执行
        methodObj[method]();

    });


    safe_datagrid.datagrid({
        url: '/safe/query.do',
        columns: [[
            {field: 'sn', title: '编号', width: 100, align: 'center'},
            {field: 'name', title: '机构名称', width: 100, align: 'center'},
            {field: 'legalperson', title: '法人代表', width: 100, align: 'center'},
            {field: 'dentitycard', title: '证件号码', width: 100, align: 'center'},
            {field: 'contactway', title: '联系方式', width: 100, align: 'center'},
            {field: 'address', title: '地址', width: 100, align: 'center'},
            {
                field: 'cooperation', title: '合作状态', width: 50, align: 'center',
                formatter: function (value, row, index) {
                    return value ? "<font color='blue'>是</font>" : "<font color='red'>否</font>"
                }
            }
        ]],
        fit:true,
        fitColumns: true,
        toolbar: "#safe_toobar",
        striped: true,
        pagination: true,
        singleSelect: true,
        ctrlSelect: true
    });
    //初始化弹出框
    safe_dialog.dialog({

        width: 270,
        height: 380,
        buttons: '#safe_btns',
        closed: true


    })


});