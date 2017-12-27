<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>系统日志</title>

    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui_animation.css">
    <link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/icon.css">

    <link href="/static/plugins/myTheme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/themes/insdep/jquery.insdep-extend.min.js"></script>



    <style>
        tr {
            color: #3c3c3c;
            font-family: Arial,"Microsoft YaHei","微软雅黑";
            font-size: 12px;
            font-style: normal;
        }
    </style>
    <script type="text/javascript" src="/static/js/systemlog.js"></script>
</head>
<body>
<table id="systemLog_datagrid"></table>

<div id="systemLog_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-shanchu" plain="true"   data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true"   data-cmd="reload">加载</a>
</div>
</body>
</html>
