$(function () {
    //抽取变量
    var accident_form = $("#accident_form");
    var accident_dialog = $("#accident_dialog");
    var accident_datagrid = $("#accident_datagrid");
    var btn_changeState = $("#btn_changeState");

    //01:初始化datagrid
    accident_datagrid.datagrid({
        fit: true,
        url: "/accident/query.do",
        fitColumns: true,
        striped: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#accident_toobar",
        columns: [[
            {field: 'reporterName',title:'报案人姓名',width:100,align:'center'},
            {field: 'reporterSex',title: '报案人性别',width:100,align:'center'},
            {field:'reporterIphon',title:'报案人电话',width:100,align:'center'},
            {field:'reporterTime', title:'事故时间', width: 100, align:'center'},
            {field:'reporterPlace', title:'事故地点', width: 100, align:'center'},
            {field: 'policySn', title: '保单号', width: 100, align: 'center',styler: function(value,row,index){
                return 'color:red;';
            }},
            {field: 'plateNumber', title: '车牌号', width: 100, align: 'center'},
            {field: 'employeeId', title: '客服人员', width: 100, align: 'center',formatter:function(value, row, index) {
                return value ? value.username : "";
            }},
        ]],
    });

    //02: 初始化dialog
    accident_dialog.dialog({
        width: 350,
        height: 360,
        buttons:"#accident_btns",
        closed: true
    });

    //03:所有按钮标签的方法都交给methodObject对象来管理
    var methodObject = {
        //添加
        add: function () {
            accident_form.form("clear");
            accident_dialog.dialog("setTitle", "添加报案单");
            accident_dialog.dialog("open");
        },

        //编辑
        edit: function () {
            var accident = accident_datagrid.datagrid("getSelected");
            if (!accident) {
                $.messager.alert('温馨提示', '亲！请选择一条记录哦^_^', 'info');
                return;
            }
            accident_form.form("clear");
            accident_dialog.dialog("setTitle", "编辑任务单");
            var salId = accident.id;
            $.ajax({
                url: "/accident/listAll.do",
                data: {id: salId},
                async: false,
                success:function(data) {
                    $("[name=id]").val(data[0].task.id)
                    $("#sal_name").val(accident.employee.username);
                    $("#sal_sn").val(data[0].task.sn);
                    $("#_easyui_textbox_input5").val(data[0].task.plan);
                    $("#sal_place").val(data[0].task.place);
                    $("#sal_content").val(data[0].task.content);
                }
            });
            accident_dialog.dialog("open");
        },

        //删除:
        remove:function () {
            //先获取一条数据
            var row = accident_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '亲！请先选择一条记录!', 'info');
                return;
            }
            //确认删除:
            $.messager.confirm('温馨提示', '确认删除吗？', function(){
                $.get("/accident/delete.do",{id:row.id},function (data) {
                    if(data.success){
                        $.messager.confirm('温馨提示', '删除成功!', function(r){
                            //重新加载
                            accident_datagrid.datagrid("reload");
                        });
                    }else{
                        $.messager.alert('温馨提示', '删除失败!');
                    }
                })
            });
        },

        //查看保单状态
        chakan: function () {
            //先选中一条数据
            var row = accident_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "亲！请选中一条数据！", "info");
                return;
            }
            //利用ajax查询保单状态:
            $.post("/policy/getStateBySn.do",{sn:row.policySn},function (data) {
                if(data) {
                    if (data.state == 0) {$.messager.alert("温馨提示", "保单号:[" + row.policySn + "]的目前状态为 : 暂存", "info");}
                    if (data.state == 1) {$.messager.alert("温馨提示", "保单号:[" + row.policySn + "]的目前状态为 : 待审核", "info");}
                    if (data.state == 2) {$.messager.alert("温馨提示", "保单号:[" + row.policySn + "]的目前状态为 : 待缴费", "info");}
                    if (data.state == 3) {$.messager.alert("温馨提示", "保单号:[" + row.policySn + "]的目前状态为 : 待修改", "info");}
                    if (data.state == 4) {$.messager.alert("温馨提示", "保单号:[" + row.policySn + "]的目前状态为 : 已缴费", "info");}
                    if (data.state == 5) {
                        $.messager.alert("温馨提示", "保单号:[" + row.policySn + "]的目前状态为 : 拒保", "info");
                    }
                }else{
                    $.messager.alert("温馨提示", "保单号:[" + row.policySn + "]不存在!", "info");
                }

                })
        },
        //保存
        save: function () {
            accident_form.form("submit", {
                url: "/accident/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.message, "info", function () {
                            accident_dialog.dialog("close");
                            accident_datagrid.datagrid("load");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.message, "info");
                    }
                }
            })
        },

        //取消
        cancel: function () {
            accident_dialog.dialog("close");
        },


        //高级查询
        searchForm: function () {
           //获取表单数据:
          var keyword = $("[name='keyword']").val();
           var beginDate = $(".beginDate").combobox("getValue");
           var endDate = $(".endDate").combobox("getValue");
            //重新加载
          accident_datagrid.datagrid("load", {
               keyword: keyword,
               beginDate: beginDate,
               endDate: endDate
           });
        },

        //生成事故明细表:
        chakan2:function(){
            //先选中一条数据
            var row = accident_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条事故信息！", "info");
                return;
            }
            //生成事故明细表:
            $.post("/accidentItem/save.do",{"accidentId.id":row.id},function (data) {
                if(data.success){
                    $.messager.alert("温馨提示", "事故明细表已生成！", "info");
                }else{
                    $.messager.alert("温馨提示", "事故明细表生成失败！", "info");
                }
            })
        },

        //刷新
        reload: function () {
            accident_datagrid.datagrid("load");
        }
    };
    //所有按钮标签的点击事件对应的方法都交给methodObject对象来管理,调用的时候只需要获取对应的cmd质量,然后通过method对象来调用即可
    $("a[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        methodObject[cmd]();
    });

});

//查看明细:
function look(index) {
    //根据索引选中一行:
    $('#accident_datagrid').datagrid('selectRow',index);
    //获取第一次选中一行的数据
    var accident = $('#accident_datagrid').datagrid('getSelected');
    console.log(accident)
    //清楚所有数据
    $("#accident_form").form("clear");
    //设置标题
    $("#accident_dialog").dialog("setTitle", "查看薪资明细");
    //获取选中数据的id;
    var salId = accident.id;
   $.ajax({
        url: "/accident/getItemBySalaryId.do",
        data: {id: salId},
        async: false,
        success:function(data) {
            console.log(data);
            $("#accident_baseaccident").val(accident.baseSalary);
            $("#accident_bonus").val(accident.bonus);
            $("#accident_hire").val(data.hire);
            $("#accident_pay").val(data.pay);
            $("#accident_tax").val(data.tax);
            $("#accident_postaccident").val(data.postaccident);
            $("#accident_ifpay").val(data.ifpay ? '是':'否');
            $("#accident_programme").val(data.programme);
            $("#accident_totalaccident").val(accident.totalSalary);
        }
    });
    $("#accident_dialog").dialog("open");
}

