<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: cc450
  Date: 17/12/24/0024
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>缴费处理</title>
    <%@ include file="../common/common_head.jsp"%>
    <script type="text/javascript" src="/static/js/payment.js"></script>
</head>
<body>

<%--数据表格--%>
<table id="payment_datagrid"></table>
<%--登陆按钮--%>
<div id="payment_toolbar">
    <input id="applicant" class="easyui-textbox" name="applicant" prompt="客户姓名"/>
    <input id="policyNo" class="easyui-textbox" name="policyNo" prompt="核保单号"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-jiaofei" plain="true" data-cmd="pay">缴费</a>
    <a class="easyui-linkbutton" iconCls="icon-jiaofei" plain="true" data-cmd="return">退回</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
</div>
<div id="payment_dialog">
    <form id="payment_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 20px">
            <tr>
                <td width="30px"></td>
                <td>投保人</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="customer.name" style="width: 250px" readonly="readonly"/></td>
            </tr>

            <tr>
                <td width="30px"></td>
                <td>核保单号</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="sn" style="width: 250px" readonly="readonly"/></td>
            </tr>

            <tr>
                <td width="30px"></td>
                <td>保险机构</td>
                <td width="30px"></td>
                <td>
                    <input class="easyui-textbox" name="safetymechanism.name" style="width: 250px" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>保险产品</td>
                <td width="30px"></td>
                <td>
                    <ul id="product_datagrid" class="easyui-datagrid" title="已购产品列表" lines="true"
                        style="width:250px;height:auto">
                    </ul>
                </td>
            </tr>

            <tr>
                <td width="30px"></td>
                <td>起保时间</td>
                <td width="30px"></td>
                <td><input class="easyui-datebox" required="required" style="width: 250px" name="beginDate"  readonly="readonly"></td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>止保时间</td>
                <td width="30px"></td>
                <td><input class="easyui-datebox" required="required" style="width: 250px" name="endDate"  readonly="readonly"></td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>保险金额</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="totalAmount" style="width: 250px" readonly="readonly"></td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>缴费方式</td>
                <td width="30px"></td>
                <td>
                    <select class="easyui-combobox" name="paymentWay" style="width:250px;"
                            data-options="panelHeight:'auto',required:true">
                        <option value="0">现金支付</option>
                        <option value="1">刷卡支付</option>
                        <option value="2">支票支付</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<%--确认取消按钮--%>
<div id="dialog_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">确认</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
