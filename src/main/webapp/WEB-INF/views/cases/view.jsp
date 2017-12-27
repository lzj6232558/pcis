<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>案件受理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui_animation.css">
    <link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/icon.css">

    <link href="/static/plugins/myTheme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/themes/insdep/jquery.insdep-extend.min.js"></script>



    <script type="text/javascript" src="/static/js/cases.js"></script>
    <style>
        tr {
            color: #3c3c3c;
            font-family: Arial,"Microsoft YaHei","微软雅黑";
            font-size: 12px;
            font-style: normal;
        }
    </style>
</head>
<body>
<table id="case_datagrid"></table>
<div id="case_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-xinzeng" plain="true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" iconCls="icon-bianji" plain="true" data-cmd="edit">编辑</a>
    <a id="btn_changeState" class="easyui-linkbutton" iconCls="icon-chaxun" plain="true"
       data-cmd="seachState">查询保单状态</a>
    <input class="easyui-searchbox" data-options="prompt:'报案人名/保单号',searcher:doSearch" style="width:auto">
    <a class="easyui-linkbutton" iconCls="icon-shuaxin" plain="true" data-cmd="reload">刷新</a>
</div>

<div id="case_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<div id="case_dialog" align="center">
    <form id="case_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 00px" cellspacing="10px" cellpadding="5px">
            <tr>
                <td align="center">报案人:</td>
                <td align="center"><input class="easyui-validatebox textbox" data-options="required:true" name="name"/></td>
            </tr>
            <tr>
                <td align="center">发生时间:</td>
                <td align="center"><input class="easyui-datetimebox textbox" name="receivetime"
                                          data-options="required:true,showSeconds:false" value="3/4/2010 2:3" style="width:150px"></td>
            </tr>
            <tr>
                <td align="center" >性别:</td>
                <td align="center">
                    <select name="sex" class="easyui-combobox textbox" style="width: 150px;height: 30px;" data-options="panelHeight:'auto',width:'50px'">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center">车牌号:</td>
                <td align="center"><input type="text" class="easyui-validatebox textbox" name="platenumbers"/></td>
            </tr>
            <tr>
                <td align="center">联系电话:</td>
                <td align="center"><input type="text" class="easyui-validatebox textbox" name="tel"/></td>
            </tr>
            <tr>
                <td align="center">事故发地址:</td>
                <td align="center"><input type="text" class="easyui-validatebox textbox" data-options="required:true" name="address"/>
                </td>
            </tr>
            <tr>
                <td align="center">保单号:</td>
                <td align="center"><input type="text" class="easyui-validatebox textbox" data-options="required:true" name="policySn"/>
                </td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>