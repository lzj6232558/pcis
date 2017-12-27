<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>事故明细单</title>
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入共同的插件: --%>
<%@include file="/static/common/common.jsp"%>
<%-- 引入自己的js: --%>
<script type="text/javascript" src="/static/js/badTable.js"></script>
<body>
<%--01:列表--%>
<table id="badTable_datagrid"></table>

<%--02:工具栏--%>
<div id="badTable_toobar">
    <%--高级查询--%>
    &nbsp;&nbsp;&nbsp;
    <input class="easyui-textbox" name="policySn" prompt="保单号">
    <input class="easyui-textbox" name="employeeName" prompt="处理人员">
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchForm">查询</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" data-cmd="reload">刷新</a>
</div>
</body>
</html>
