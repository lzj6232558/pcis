$(function () {
    var saleChart_datagrid = $("#saleChart_datagrid");



    //显示报表事件
    $(function () {
        $(".btn_chart").click(function () {
            var url = $(this).data("url");
            $.dialog.open(url + $("#searchForm").serialize(), {
                id: "saleChart",
                title: "销售报表",
                width:800,
                height:550,
                background:'#000000'
            })
        });
    })
    saleChart_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: "/chart/list.do",
        singleSelect: true,
        toolbar: "#chart_toolbar",
        rownumbers: true,
        columns: [[
            {field: 'groupType', title: '分组类型', width: 180, align: 'center'},
            {field: 'totalAmount', title: '销售总金额', width: 180, align: 'center'}
        ]]
    });

});
//查询条件
function doSearch() {
    var safe = $("#safe").val();
    var product = $("#product").val();
    var beginDate = $("#beginDate").val();
    var endDate = $("#endDate").val();
    var groupType = $("#groupType").val();
    $("#saleChart_datagrid").datagrid("load", {
        sId: safe,
        pId: product,
        beginDate: beginDate,
        endDate: endDate,
        groupType: groupType
    });
}