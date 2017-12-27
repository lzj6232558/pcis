$(function () {
    $("#transfer_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,  // 斑马线
        url:"/customertransfer/query.do",
        pagination: true,
        singleSelect: true,
        rownumbers:true,
        columns: [[
            {field:'transdate',title:'移交日期',width:100},
            {field:'transuser',title:'操作人',width:60,formatter: function(value,row,index){
                return value != null? value.username:'';
            }},
            {field:'customer',title:'客户',width:60,formatter: function(value,row,index){
                return value !=null ? value.name:'';
            }},
            {field:'newseller',title:'新市场专员',width:60,formatter: function(value,row,index){
                return value !=null ? value.username:'';
            }},
            {field:'transreason',title:'移交原因',width:200}
        ]]
    })
})