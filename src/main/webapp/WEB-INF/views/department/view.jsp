<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>部门管理</title>
    <%-- 引入插件相关文件 --%>
    <%@ include file="/static/common/common.jsp"%>
    <%-- 引入js: --%>
    <script type="text/javascript" src="/static/js/department.js"></script>
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

<%-- 上面按钮: --%>
<div id="btn_toolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" data-cmd="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-cmd="edit">编辑</a>
    <a href="#" id="changeState" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" data-cmd="changeState">停用</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" data-cmd="reload">刷新</a>
</div>
<%-- 下面两个按钮 --%>
<div id="btn_buttons">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" data-cmd="save">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>

<%-- 部门表单: --%>
<table id="department_datagrid"></table>

<%-- 弹出框--%>
<div id="department_dialog">
    <form id="department_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top:15px">
            <tr>
                <td width="20px"></td>
                <td>部门编号:</td>
                <td>
                    <input type="text" class="easyui-textbox" name="sn"/>
                </td>
            </tr>
            <tr>
                <td width="20px"></td>
                <td>部门名称:</td>
                <td>
                    <input type="text" class="easyui-textbox" name="name" />
                </td>
            </tr>
            <tr>
                <td width="20px"></td>
                <td>上级部门:</td>
                <td>
                    <input name="parentId.id" id="department-combobox" class="easyui-combobox"
                           data-options="url:'/department/query.do',valueField:'id',textField:'name',panelHeight:true"/>
                </td>
            </tr>
            <tr>
                <td width="20px"></td>
                <td>部门经理:</td>
                <td>
                    <input class="easyui-combobox" name="managerId.id"
                           data-options="url:'/employee/selectAll.do',valueField:'id',textField:'username',panelHeight:true"/>
                </td>
            </tr>
            <tr>
                <td width="20px"></td>
                <td>状态:</td>
                <td>
                    <select class="easyui-combobox" name="state" style="width:146px;" data-options="panelHeight:true"">
                        <option value="1">正常</option>
                        <option value="0">停用</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>