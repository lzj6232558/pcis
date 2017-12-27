<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>薪资管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- 引入插件: --%>
    <%@ include file="/static/common/common.jsp"%>
    <%-- 自己的js; --%>
    <script type="text/javascript" src="/static/js/salary.js"></script>
    <style>
        #salary_form input{
            border:0px;
            border-bottom:1px solid #ccc;
            width: 100px;
            background-color: #fff;
        }
    </style>
</head>

<body>
<%-- 表单: --%>
<table id="salary_datagrid"></table>

<%-- 高级查询: --%>
<div id="salary_toolbar">
    <form id="searchForm" action="#" method="post">
        姓名查询 <input class="easyui-textbox" data-options="prompt:'姓名'" name="keyword"> &nbsp
        部门查询 <input class="easyui-combobox" data-options="url:'/department/query.do',valueField:'id',textField:'name',panelHeight:'true'" name="dept"> &nbsp
        日期查询 <input class="easyui-datebox beginDate" name="beginDate"/> ~
                <input class="easyui-datebox endDate" name="endDate"/>
        <a class="easyui-linkbutton" iconCls="icon-chaxun" plain="true" data-cmd="searchForSche">查询</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
        <div style="float: right">
            <a class="easyui-linkbutton" iconCls="icon-export" plain=true data-cmd="exportXls">导出工资表</a>
            <a class="easyui-linkbutton" iconCls="icon-import" plain=true data-cmd="importXls">导入工资表</a>
        </div>
    </form>
</div>

<%-- 工资表导入弹出框 --%>
<div id="importXls">
    <form id="endForm" action="/salary/improtXls.do" method="post" enctype="multipart/form-data">
        <a class="easyui-linkbutton" iconCls="icon-dowload" data-improt="download" data-options="width:236">下载工资表模板</a>
        <br><br><br><br>
        <input class="easyui-filebox" id="improtFileBox" style="width:236px" name="file" data-options="buttonText:'选择文件'">
        <br>
        <a class="easyui-linkbutton" iconCls="icon-import" data-improt="beginImportXls" data-options="width:236">点击导入</a>
    </form>
</div>

<%-- 弹出框: --%>
<div id="salary_dialog">
    <form id="salary_form" method="post">
        <table align="center">
            <tr>
                <td style="text-align: right">基本工资 : </td>
                <td><input id="salary_basesalary" type="text" disabled></td>
            </tr>
            <tr>
                <td style="text-align: right">奖金 : </td>
                <td><input id="salary_bonus" type="text" disabled></td>
            </tr>
            <tr>
                <td style="text-align: right">雇佣方式 : </td>
                <td><input id="salary_hire" type="text" disabled></td>
            </tr>
            <tr>
                <td style="text-align: right">计薪方式 : </td>
                <td><input id="salary_pay" type="text" disabled></td>
            </tr>
            <tr>
                <td style="text-align: right">计税方式 : </td>
                <td><input id="salary_tax" type="text" disabled></td>
            </tr>
            <tr>
                <td style="text-align: right">岗位薪资 : </td>
                <td><input id="salary_postsalary" type="text" disabled></td>
            </tr>
            <tr>
                <td style="text-align: right">是否缴纳基金 : </td>
                <td><input id="salary_ifpay" type="text" disabled></td>
            </tr>
            <tr>
                <td style="text-align: right">缴纳方案 : </td>
                <td><input id="salary_programme" type="text" disabled></td>
            </tr>
            <tr>
                <td style="text-align: right">总薪资 : </td>
                <td><input id="salary_totalsalary" type="text" disabled></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
