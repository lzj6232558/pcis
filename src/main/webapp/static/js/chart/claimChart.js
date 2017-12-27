$(function () {
    var claimChart_datagrid = $("#claimChart_datagrid");



    //显示报表事件
    $(".btn_chart").click(function () {
        var url = $(this).data("url");
        console.log($("#searchForm").serialize());
        $.dialog.open(url + $("#searchForm").serialize(), {
            id: "claimChart",
            title: "理赔报表",
            width:800,
            height:550,
            background:'#000000'
        })
    });
    claimChart_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: "/chart/claimList.do",
        singleSelect: true,
        toolbar: "#chart_toolbar",
        rownumbers: true,
        columns: [[
            {field: 'groupType', title: '分组类型', width: 180, align: 'center'},
            {field: 'totalAmount', title: '理赔总金额', width: 180, align: 'center'}
        ]]
    });

});
//查询条件
function doSearch() {
    var emp = $("#emp").val();
    var beginDate = $("#beginDate").val();
    var endDate = $("#endDate").val();
    var groupType = $("#groupType").val();
    $("#claimChart_datagrid").datagrid("load", {
        eId: emp,
        beginDate: beginDate,
        endDate: endDate,
        groupType: groupType
    });
}