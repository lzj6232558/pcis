<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%-- 共同的插件 --%>
    <%@ include file="/static/common/common.jsp"%>
    <%-- js样式 --%>
    <script type="text/javascript" src="/static/js/role.js"></script>
</head>
<body>

<%--表格--%>
<table id="role_datagrid"></table>

<%--表格上的按钮--%>
<div id="role_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a id="changeState" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
       data-cmd="delete">删除角色</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>

</div>

<%--保存取消按钮--%>
<div id="dialog_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>


<%--添加编辑对话框--%>
<div id="role_dialog">

    <form id="role_form" method="post">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>部门编号: <input class="easyui-textbox" type="text" name="sn"/></td>
                <td>部门名称: <input class="easyui-textbox" type="text" name="name"/></td>
            </tr>
            <tr>
                <td>
                    <div id="allPermissions"></div>
                </td>
                <td>
                    <div id="selfPermissions"></div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
