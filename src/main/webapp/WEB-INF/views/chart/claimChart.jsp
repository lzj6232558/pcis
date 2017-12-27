<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/common_head.jsp" %>
<html>
<head>
    <title>理赔报表</title>
    <script type="text/javascript" src="/static/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/static/plugins/artDialog/iframeTools.js"></script>
</head>



<script type="text/javascript" src="/static/js/chart/claimChart.js"></script>
<body>
<div id="chart_toolbar">
    <form id="searchForm">

        业务人员:<input id="emp" class="easyui-combobox"
                  data-options="url:'/employee/selectAll.do',valueField:'id',textField:'username',panelHeight:'auto'"
                  name="eId">
        日期:
        <input id="beginDate" name="beginDate" type="text" class="easyui-datebox" style="width: 130px"> </input>
        ~
        <input id="endDate" name="endDate" type="text" class="easyui-datebox" style="width: 130px"> </input>
        <br>


        分组<select id="groupType" class="easyui-combobox" data-options="panelHeight:'auto',width:118" name="groupType">
        <option value="e.username">业务人员</option>
        <option value="date_format(a.handDate,'%Y-%m')">理赔日期(月)</option>
        <option value="date_format(a.handDate,'%Y-%m-%d')">理赔日期(日)</option>
    </select>

        <a class="easyui-linkbutton btn_chart" iconCls="icon-large-chart" plain="true"
           data-url="/chart/claimBar.do?">柱状图</a>
        <a class="easyui-linkbutton btn_chart" iconCls="icon-filter" plain="true"
           data-url="/chart/claimPie.do?">饼状图</a>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">查询</a>

    </form>
</div>
<table id="claimChart_datagrid"></table>

</body>
</html>
