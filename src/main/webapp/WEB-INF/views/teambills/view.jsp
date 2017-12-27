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
    <script type="text/javascript">
        var customId;
    </script>
    <script type="text/javascript" src="/static/js/team.js">
    </script>


</head>
<body>
<div id="teamtobal">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-d'" onclick="save()">VIP团专用</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true"   onclick="reload()">刷新</a>
</div>

<table id="team_datagrid" class="easyui-datagrid"></table>

<a href="javascript:;" onclick="acfun()"></a>

<div id="dialog_button">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="add()">确定</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="cancel()">取消</a>
</div>

<!--保单新增-->
<div id="polocy_button">
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="savep()">新增保单</a>
    <a  class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload1()">刷新</a>
    <a  class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancel1()">关闭</a>
</div>

<!--保单明细弹窗-->
<div id="policy_dialog" data-options="iconCls:'icon-d',title:'保单明细'">
    <!--保单明细表格显示-->
    <table id="policy_datagrid">
    </table>
</div>


<!--新增保单按钮按钮-->
<div id="savep_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   onclick="yees()">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   onclick="known()">取消</a>
</div>
<!--新增保单dialog-->
<div id="savep_dialog" data-options="iconCls:'icon-d',title:'请录入信息'">
    <form id="savep_form" method="post">
        <input type="hidden" name="id"/>
       <input id="cust" type="hidden"  name="customId"/>
        <table id="yy" align="center" style="margin-top:15px">
            <tr>
                <td width="32"></td>
                <td>投保人:</td>
                <td>
                    <input class="easyui-textbox" name="name"/>
                </td>
            </tr>
            <tr>
                <td width="32"></td>
                <td>车牌号:</td>
                <td>
                    <input class="easyui-textbox" name="carplate"/>
                </td>
            </tr>
            <tr>
                <td width="32"></td>
                <td>更多功能下次再来~:</td>
            </tr>
        </table>
    </form>
</div>







<!--新增团单 dialog-->
<div id="team_dialog" data-options="iconCls:'icon-d',title:'八星八钻VIP'">
    <form id="team_form" method="post">
        <table align="center" style="margin-top:15px">
            <input type="hidden" name="id"/>
            <tr>
                <td colspan="3">承保机构:</td>
                <td><input class="easyui-combobox" name="safety.id"
                           data-options="url:'/safe/list.do',valueField:'id',textField:'name',width:150"/></td>
            </tr><tr></tr><tr></tr>
            <tr>
                <td colspan="3">选择险种:</td><td><input class="easyui-combobox" name="product.id"
                            data-options="url:'/product/list.do',valueField:'id',textField:'name',width:150"/></td>
            </tr><tr></tr><tr></tr>
            <tr>
                <td colspan="3"> VIP客户:</td>
                <td><input class="easyui-combobox" name="customer.id"
                             data-options="url:'/customer/list.do',valueField:'id',textField:'name',width:150"/><td>
            </tr><tr></tr><tr></tr><tr></tr>
            <tr>
                <td>备注:</td>
                <td width="32"></td>
                <td colspan="2"><textarea name="remark" cols="33" rows="5"></textarea></td>
            </tr>
        </table>
    </form>
</div>










</body>
</html>
