<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/25/025
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui_animation.css">
    <link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/icon.css">

    <link href="/static/plugins/myTheme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
            src="/static/plugins/jquery-easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
    <style>
        tr {
            color: #3c3c3c;
            font-family: Arial, "Microsoft YaHei", "微软雅黑";
            font-size: 12px;
            font-style: normal;
        }
    </style>
    <script type="text/javascript" src="/static/js/teampolicy.js">
    </script>
</head>
<body>
<div id="teamtobal">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-d'" onclick="save()">VIP团专用</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true"   onclick="reload()">刷新</a>
</div>


<table id="team_datagrid" class="easyui-datagrid"></table>

</div>










</body>
</html>
