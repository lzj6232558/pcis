<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/common_head.jsp" %>
<html>
<head>
    <title>销售报表</title>

<script type="text/javascript" src="/static/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/static/plugins/artDialog/iframeTools.js"></script>
<script type="text/javascript" src="/static/js/chart/saleChart.js"></script>
</head>




<body>
<div id="chart_toolbar">
    <form id="searchForm">

        机构:<input id="safe" class="easyui-combobox"
                  data-options="url:'/safe/list.do',valueField:'id',textField:'name',panelHeight:'auto'"
                  name="sId">
        险种:<input id="product" class="easyui-combobox"
                  data-options="url:'/product/list.do',valueField:'id',textField:'name',panelHeight:'auto'"
                  name="pId">


        日期:
        <input id="beginDate" name="beginDate" type="text" class="easyui-datebox" style="width: 130px"> </input>
        ~
        <input id="endDate" name="endDate" type="text" class="easyui-datebox" style="width: 130px"> </input>
        <br>


        分组<select id="groupType" class="easyui-combobox" data-options="panelHeight:'auto',width:118" name="groupType">
        <option value="e.username">销售人员</option>
        <option value="safe.name">保险机构</option>
        <option value="p.name">保险产品</option>
        <option value="date_format(po.beginDate,'%Y-%m')">保单日期(月)</option>
        <option value="date_format(po.beginDate,'%Y-%m-%d')">保单日期(日)</option>
    </select>

        <a class="easyui-linkbutton btn_chart" iconCls="icon-large-chart" plain="true"
           data-url="/chart/saleBar.do?">柱状图</a>
        <a class="easyui-linkbutton btn_chart" iconCls="icon-filter" plain="true"
           data-url="/chart/salePie.do?">饼状图</a>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">查询</a>

    </form>
</div>
<table id="saleChart_datagrid"></table>

</body>
</html>
