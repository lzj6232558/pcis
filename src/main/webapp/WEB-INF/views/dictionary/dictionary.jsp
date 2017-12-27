<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui_animation.css">
    <link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/icon.css">

    <link href="/static/plugins/myTheme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/themes/insdep/jquery.insdep-extend.min.js"></script>



    <script type="text/javascript" src="/static/js/dictionary.js"></script>
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

<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
    <div data-options="region:'west',title:'字典目录',split:true" style="width:600px;">
        <table id="dictionary_datagrid"></table>
    </div>
    <div data-options="region:'center',title:'字典目录明细'"  style="padding:5px;background:#eee;">
        <table id="dictionaryItem_datagrid"></table>
    </div>

</div>

<div id="dictionary_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-xinzeng" plain="true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" iconCls="icon-bianji" plain="true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" iconCls="icon-shanchu" plain="true" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-shuaxin" plain="true" data-cmd="reload">刷新</a>
</div>

<div id="dictionary_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

<div id="dictionary_dialog">
    <form id="dictionary_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top:15px">
            <tr>
                <td>目录编码</td>
                <td><input type="text" name="sn"/></td>
            </tr>
            <tr>
                <td>目录名称</td>
                <td><input type="text" name="name"/></td></td>
            </tr>
            <tr>
                <td>目录简介</td>
                <td><input type="text" name="intro"/></td></td>
            </tr>
        </table>
    </form>
</div>

<div id="dictionaryItem_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-xinzeng" plain="true" data-cmd="addItem">新增</a>
    <a class="easyui-linkbutton" iconCls="icon-bianji" plain="true" data-cmd="editItem">编辑</a>
    <a class="easyui-linkbutton" iconCls="icon-shanchu" plain="true" data-cmd="removeItem">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-shuaxin" plain="true" data-cmd="reloadItem">刷新</a>
</div>

<div id="dictionaryItem_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="saveItem">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancelItem">取消</a>
</div>

<div id="dictionaryItem_dialog">
    <form id="dictionaryItem_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top:15px">
            <tr>
                <td>明细名称</td>
                <td><input type="text" name="name"/></td></td>
            </tr>
            <tr>
                <td>明细简介</td>
                <td><input type="text" name="intro"/></td></td>
            </tr>
            <tr>
                <td>明细目录</td>
                <td>
                    <input class="easyui-combobox"
                           data-options="url:'/dictionary/selectDictionary.do',valueField:'id',textField:'name',panelHeight:'auto'"
                           name="dictionary.id">
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
