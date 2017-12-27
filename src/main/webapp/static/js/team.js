$(function () {
    $("#team_datagrid").datagrid({
        fitColumns:true,
        pagination:true,
        fit: true,
        url:"/teambills/query.do",
        columns:[[
            {field:'id',title:'编号',width:40},
            {field:'sn',title:'团单单号',width:40},
            {field:'inputdate',title:'录入时间',width:40},
            {field:'inputuser',title:'录入人',width:40,formatter: function(value,row,index){
                return value? value.username:'';
        }
            },
            {field:'safety',title:'承保机构',width:40,formatter: function(value,row,index){
                return value? value.name:'';
            }},
            {field:'product',title:'险种',width:40,formatter: function(value,row,index){
                return value? value.name:'';
            }},
            {field:'customer',title:'客户姓名',width:40,formatter: function(value,row,index){
                return value? value.name:'';
            }},
            {field:'policies',title:'团单明细',width:40,formatter: function(value,row,index){
            return  '<a href="javascript:;" onclick="lookH('+index+')">查看明细</a>';
            }},
            {field:'remark',title:'备注',width:40}
            ]],
        toolbar:"#teamtobal"
    }),
        $("#team_dialog").dialog({
            width: 400,
            height: 400,
            closed:true,
            buttons:"#dialog_button"
        }),
        $("#policy_dialog").dialog({
            width: 1000,
            height: 500,
            closed:true,
            toolbar:"#polocy_button"
        }),
        $("#savep_dialog").dialog({
            width: 300,
            height: 300,
            closed:true,
           buttons:"#savep_btns"
        })
})
// 等下做明细时候用
function lookH(index) {
    // 通过 index 得到指定行
    $("#team_datagrid").datagrid("selectRow",index);    //  该方法.  是选择一行.  行索引从0 开始
    var row = $("#team_datagrid").datagrid("getSelected");    //  之后 通过选中的这一行. 得到我要的row  返回第一个被选中的行
    console.log(row);
    customId = row.customer.id;
    console.log(customId);
    $("#policy_dialog").dialog("open");
    $("#policy_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        pagination: true,
        singleSelect: true,
        //toolbar: "#temporary_toolbar",
        url: "/teampolicy/query.do?customeId="+customId,
        columns: [[
            {
                field: 'name', title: '投保人', width: 100, align: 'center'
            },
            {
                field: 'carplate', title: '车牌号', width: 100, align: 'center'
            },
            {field: 'begindate', title: '开始日期', width: 100, align: 'center'},
            {
                field: 'employee', title: '业务员', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.username : "";
            }
            },
            {
                field: 'audit', title: '审核人', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.username : "";
            }
            }
        ]]
    })
}


//  新增保单
function savep() {
    $("#savep_dialog").dialog("open");
    // 新增暂存单
    $("#savep_form").form("clear");
}



//刷新
function reload() {
    $("#team_datagrid").datagrid("load");
}

function reload1() {
    $("#policy_datagrid").datagrid("load");
}



function save() {
    // 清空表单
    $("#team_form").form("clear");
    $("#team_dialog").dialog("open");
}
function add() {
    // 把表单提交上去
    // 把数据注入进表单
  /*  row["safety.id"] = row.safety.id;
    row["product.id"] = row.product.id;*/
    $("#team_form").form("submit",{
        url:"/teambills/saveOrUpdate.do",
        success: function(data){
            var data = eval('(' + data + ')');
            if (data.success){
                alert(data.message);
                //关闭弹窗
                $("#team_dialog").dialog("close");
                // 刷新表
                $("#team_datagrid").datagrid("load");
            }else {
                alert(data.message)
            }
        }
    });
}
// 做新增保单提交
function yees() {
    $("#cust").val(customId);   // 最原始的方法  val
    $("#savep_form").form("submit",{
        url:"/teampolicy/saveOrUpdate.do",
        success:function(data){
            var data = eval('(' + data + ')');
            if(data.success){
                alert("操作成功");
                $("#savep_dialog").dialog("close");
                $("#policy_datagrid").datagrid("load");
            }else {
                alert("操作失败");
            }
        }
    })
}




function cancel() {
    $("#team_dialog").dialog("close");
}function known() {
    $("#savep_dialog").dialog("close");
}
function cancel1() {
    $("#policy_dialog").dialog("close");
}