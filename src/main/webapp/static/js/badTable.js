$(function () {
        //抽取变量
        var badTable_form = $("#badTable_form");
        var badTable_datagrid = $("#badTable_datagrid");

        //01:初始化datagrid
        badTable_datagrid.datagrid({
            fit: true,
            url: "/badTable/query.do",
            fitColumns: true,
            striped: true,
            pagination: true,
            singleSelect: true,
            toolbar: "#badTable_toobar",
            columns: [[
                {
                    field: 'reporterName', title: '保单号', width: 100, align: 'center', formatter: function (value, row, index) {
                        return row.accidentItemId.accidentId.policySn;
                    }
                },
                {field: 'badPath',title: '受损位置',width:100,align:'center'},
                {field:'badDegree',title:'受损程度',width:100,align:'center',formatter:function (value, row, index) {
                    if(row.badDegree == 0){return "较轻"}
                    if(row.badDegree == 1){return "较重"}
                    if(row.badDegree == 2){return "严重"}
                }},
                {field:'badMoney', title:'赔偿金额', width: 100, align:'center'},
                {field: 'employeeId', title: '处理员工', width: 100, align: 'center',formatter:function(value, row, index) {
                    return row.accidentItemId.employeeId.username;
                }},
            ]],
        });

        //03:所有按钮标签的方法都交给methodObject对象来管理
        var methodObject = {
            //搜索:
            searchForm: function () {
                //获取表单数据:
                var policySn = $("[name='policySn']").val();
                var employeeName = $("[name='employeeName']").val();
                //重新加载
                badTable_datagrid.datagrid("load", {
                    policySn: policySn,
                    employeeName: employeeName
                });
            },

            //刷新
            reload: function () {
                badTable_datagrid.datagrid("load");
            },

        };
        //所有按钮标签的点击事件对应的方法都交给methodObject对象来管理,调用的时候只需要获取对应的cmd质量,然后通过method对象来调用即可
        $("a[data-cmd]").click(function () {
            var cmd = $(this).data("cmd");
            methodObject[cmd]();
        });

});