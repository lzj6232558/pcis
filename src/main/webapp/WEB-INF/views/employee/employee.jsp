<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>员工管理</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/employee.js"></script>

</head>
<body>
<table id="emp_datagrid"></table>
<div id="emp_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-add" plain=true data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain=true data-cmd="edit">编辑</a>
    <a id="changeState" class="easyui-linkbutton" iconCls="icon-remove" plain=true data-cmd="changeState">离职</a>
    <a class="easyui-linkbutton"
       iconCls="icon-reload" plain=true data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton"
       iconCls="icon-redo" plain=true data-cmd="exportXls">导出</a>
    <a class="easyui-linkbutton"
       iconCls="icon-undo" plain=true data-cmd="importXls">导入</a>

    <%-- <form id="emp_table" method="post">--%>
    姓名/邮箱
    <input type="text" id="keyword" name="keyword" class="easyui-textbox"
           data-options="iconCls:'icon-key',prompt:'名字/邮箱',iconAlign:'right'"/>
    部门:
    <select id="cc" class="easyui-combobox" name="deptId" style="width:140px;">
        <option value="-1">请选择</option>
        <c:forEach var="dept" items="${depts}">
            <option value="${dept.id}">${dept.name}</option>
        </c:forEach>
    </select>
    <a id="submit_search" class="easyui-linkbutton" iconCls="icon-search" plain=true>搜索</a>


</div>

<div id="emp_buttons">
    <a class="easyui-linkbutton" iconCls="icon-add" plain=true
       data-cmd="save">保存</a> <a class="easyui-linkbutton"
                                 iconCls="icon-cancel" plain=true data-cmd="cancel">取消</a>
</div>
<div id="present_buttons">
    <a class="easyui-linkbutton" iconCls="icon-present" plain=true
       data-cmd="present">提交</a>
</div>
<div id="dowload_buttons">
    <a class="easyui-linkbutton" iconCls="icon-daochu" plain=true
       data-cmd="dowloadXls">下载模板</a>
</div>
<%--导入弹出框--%>
<div id="import_dialog">
    <form id="import_form" enctype="multipart/form-data" method="post">
        <table align='center' style="margin-top: 20px">
            <input class="easyui-filebox" name="file"
                   style="width:200px" data-options="accept:'application/vnd.ms-excel',buttonText: '选择需要上传的excel文件'">
        </table>
    </form>
</div>
<!-- 表单弹出框 -->
<div id="emp_dialog">
    <form id="emp_form" method="post">
        <table align='center' style="margin-top: 20px">
            <tbody>
            <input type="hidden" name="id"/>
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username" class="easyui-textbox"
                           prompt="请输入姓名"/></td>
            </tr>
            <tr id="password">
                <td>密码</td>
                <td>
                    <input name="password" class="easyui-passwordbox"/>
                </td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td>
                    <input type="text" name="realname" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>电话</td>
                <td>
                    <input type="text" name="tel" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>入职时间</td>
                <td>
                    <input id="dd" name="inputTime" type="text" class="easyui-datebox" required="required"> </input>
                </td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td>
                    <input type="text" name="email" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>部门:</td>
                <td>
                    <input class="easyui-combobox" name="dept.id"
                           data-options="valueField:'id',textField:'name',url:'/department/query.do',panelHeight:true" />
                </td>
            </tr>
            <tr>
                <td>角色:</td>
                <td>
                    <input id="myCombotree" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'/role/query.do',panelHeight:true,multiple:true" />
                </td>
            </tr>
            <tr>
                <td class="ui_text_lt" width="140">状态</td>
                <td>

                    <select class="easyui-combobox" name="admin" style="width:145px;" data-options="panelHeight:true" id="state">
                        <option value="1">在职</option>
                        <option value="0">离职</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="ui_text_lt" width="140">是否是管理员</td>
                <td>

                    <select class="easyui-combobox" name="admin" style="width:145px;" data-options="panelHeight:true" id="admin_box">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>


</body>
</html>
