<%--
  Created by IntelliJ IDEA.
  User: cc450
  Date: 17/12/21/0021
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考勤管理</title>
    <%@ include file="../common/common_head.jsp"%>
    <script type="text/javascript" src="/static/js/attendance.js"></script>
</head>
<body>

<%--表格--%>
<table id="attendance_datagrid"></table>
<%--表格上的工具栏--%>
<div id="attendance_toolbar">
    <%--    <a class="easyui-linkbutton" data-options="plain:true" data-cmd="signIn">签到</a>
        <a class="easyui-linkbutton" data-options="plain:true" data-cmd="signOut">签退</a>--%>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="delete">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton" data-options="plain:true" data-cmd="resignIn">补签</a>
    </br>
    <%--高级查询--%>
    姓名查询:<input id="username" class="easyui-textbox" style="width:150px" name="username">
    部门查询:<input id="department" class="easyui-combobox" style="width:150px" name="department">
    日期查询:<input id="beginDate" name="beginDate" type="text" class="easyui-datebox"> </input>
    ~<input id="endDate" name="endDate" type="text" class="easyui-datebox"> </input>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:true" data-cmd="reset">重置</a>
</div>
<%--添加编辑对话框--%>
<div id="attendance_dialog">
    <form id="attendance_form" method="post">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>员工名称:</td>
                <td>
                    <input class="easyui-combobox" type="text" name="employee.id" style="width: 200px"
                           data-options="url:'/employee/selectAll.do',valueField:'id',textField:'username',panelHeight:'auto',panelMaxHeight:200"/>
                </td>
            </tr>
            <tr>
                <td>签到IP:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="signInIP" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td>签到时间:</td>
                <td>
                    <input class="easyui-datetimebox" type="text" name="signInDate" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td>签退时间:</td>
                <td>
                    <input class="easyui-datetimebox" type="text" name="signOutDate" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td>补签人:</td>
                <td>
                    <input class="easyui-combobox" type="text" name="resignInEmployee.id" style="width: 200px"
                           data-options="url:'/employee/selectAll.do',valueField:'id',textField:'username',panelHeight:'auto',panelMaxHeight:200"/>
                </td>
            </tr>
            <tr>
                <td>补签时间:</td>
                <td>
                    <input class="easyui-datetimebox" type="text" name="resignInDate" style="width: 200px"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--保存取消按钮--%>
<div id="dialog_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
</body>
</html>

