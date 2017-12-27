<%--
  Created by IntelliJ IDEA.
  User: cc450
  Date: 17/12/23/0023
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任务管理</title>
    <%@ include file="../common/common_head.jsp"%>
    <script type="text/javascript" src="/static/js/schedule.js"></script>
</head>
<body>

<%--表格--%>
<table id="schedule_datagrid"></table>
<%--表格上的工具栏--%>
<div id="schedule_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <%--   <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>--%>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="delete">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <a id="changeState" class="easyui-linkbutton" plain=true data-cmd="changeState">标记完成</a>
    </br>
    <%--高级查询--%>
    姓名查询:<input id="username" class="easyui-textbox" style="width:150px" name="username">
    部门查询:<input id="dept_id" class="easyui-combobox" style="width:150px" name="dept_id"
                data-options="url:'/department/selectAll.do',valueField:'id',textField:'name',panelHeight:'auto',panelMaxHeight:200">
    日期查询:<input id="beginDate" name="beginDate" type="text" class="easyui-datebox"> </input>
    ~<input id="endDate" name="endDate" type="text" class="easyui-datebox"> </input>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:true" data-cmd="resetForm">重置</a>

</div>
<%--添加编辑对话框--%>
<div id="schedule_dialog">
    <form id="schedule_form" method="post">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>员工名称:</td>
                <td>
                    <input id="employee" class="easyui-combobox" type="text" name="employee.id"
                           data-options="url:'/employee/selectAll.do',valueField:'id',textField:'username',panelHeight:'auto',panelMaxHeight:200"/>
                </td>
            </tr>
            <tr>
                <td>任务编码:</td>
                <td>
                    <input id="sn" class="easyui-textbox" type="text" name="sn"/>
                </td>
            </tr>
            <tr>
                <td>完成时间:</td>
                <td>
                    <input id="plan" class="easyui-datebox" type="text" name="plan" data-options="editable:false"/>
                </td>
            </tr>
            <tr>
                <td>任务地址:</td>
                <td>
                    <input id="place" class="easyui-textbox" type="text" name="place"
                           data-options="multiline:true,height:50"/>
                </td>
            </tr>
            <tr>
                <td>任务内容:</td>
                <td>
                    <input id="content" class="easyui-textbox" type="text" name="content"
                           data-options="multiline:true,height:150"/>
                </td>
            </tr>
        </table>
    </form>
</div>


<%--保存取消按钮--%>
<div id="dialog_btns">
    <a id="saveButton" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>

</body>
</html>
