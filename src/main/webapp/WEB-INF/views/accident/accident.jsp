<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>报案单信息</title>
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入共同的插件: --%>
<%@include file="/static/common/common.jsp"%>
<%-- 引入自己的js: --%>
<script type="text/javascript" src="/static/js/accident.js"></script>
<body>
<%--01:列表--%>
<table id="accident_datagrid"></table>

<%--02:工具栏--%>
<div id="accident_toobar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-chakan'" data-cmd="chakan">查看保单状态</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" data-cmd="reload">刷新</a>
    <%--高级查询--%>
    &nbsp;&nbsp;&nbsp;
    <input class="easyui-textbox" name="keyword" prompt="报案人/电话/保单号">
    事故时间:<input class="easyui-datebox beginDate" name="beginDate"/> ~
            <input class="easyui-datebox endDate" name="endDate"/>
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchForm">查询</a>
    <div style="float: right">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-chakan'" data-cmd="chakan2">生成事故明细表</a>
    </div>
</div>

<%--03:添加在弹出框上的按钮--%>
<div id="accident_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   data-cmd="cancel">取消</a>
</div>


<%-- 04:弹出框: --%>
<div id="accident_dialog">
    <form id="accident_form" method="post">
        <input type="hidden" name="id"/>
        <%-- 员工id --%>
        <table id="accident_table" style="margin-top:20px;">
            <tr>
                <td width="35px"></td>
                <td style="text-align: right">报案人姓名:</td>
                <td>
                    <input class="easyui-textbox" name="reporterName"/>
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td style="text-align: right">报案人性别:</td>
                <td>
                    <input class="easyui-textbox" name="reporterSex" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td style="text-align: right">报案人电话:</td>
                <td>
                    <input class="easyui-textbox" name="reporterIphon" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td style="text-align: right">报案时间:</td>
                <td>
                    <input class="easyui-datebox" name="reporterTime" data-options="showSeconds:false">
                </td>
            </tr>
            <tr>
                <td  width="35px"></td>
                <td style="text-align: right">事故地点:</td>
                <td>
                    <input class="easyui-textbox" name="reporterPlace" />
                </td>
            </tr>
            <tr>
                <td width="35px"></td>
                <td style="text-align: right">保单号:</td>
                <td>
                    <input class="easyui-textbox"  type="text" name="policySn" />
                </td>
            </tr>
            <tr>
                <td width="35px"></td>
                <td style="text-align: right">车牌号:</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="plateNumber" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
