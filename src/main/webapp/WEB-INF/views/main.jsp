<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>叩丁狼教育PSS（演示版）</title>
    <%@include  file="/WEB-INF/views/common/common_head.jsp" %>
    <script type="text/javascript" src="/static/js/main.js"></script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',height:70">
        <h1 align="center">叩丁狼仓储管理系统<%--<shiro:principal property="username"></shiro:principal> <a href="/logout.do">注销</a>--%></h1>
    </div>
    <div data-options="region:'south',height:50">
        <h4 align="center">版权声明</h4>
    </div>
    <div data-options="region:'west',width:150">
        <div class="easyui-accordion" data-options="fit:true">
            <div title="菜单">
                <ul id="myTree"></ul>
            </div>
            <div title="待办事项">待办事项</div>
            <div title="公司公告">公司公告</div>
        </div>

    </div>
    <%--这里是我们显示信息的窗口--%>
    <div data-options="region:'center'">
        <div id="myTabs" >
            <div title="主页">
                主页内容...
            </div>
        </div>
    </div>
</div>
</body>
<script>

</script>
</html>

