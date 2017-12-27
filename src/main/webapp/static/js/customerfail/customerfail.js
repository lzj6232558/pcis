$(function () {
    $("#fail_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,  // 斑马线
        url:"/customerfail/query.do",
        pagination: true,
        singleSelect: true,
        toolbar:"#toolbar",
        rownumbers:true,
        columns: [[
            {field:'faildate',title:'时间',width:100},
            {field:'chargeuser',title:'负责人',width:60,formatter: function(value,row,index){
                return value != null? value.username:'';
            }},
            {field:'cust',title:'客户',width:60,formatter: function(value,row,index){
                return value !=null ? value.name:'';
            }},
            {field:'failreason',title:'失败原因',width:200}
        ]]
    })
})
function reload() {
    $("#fail_datagrid").datagrid("load");
}