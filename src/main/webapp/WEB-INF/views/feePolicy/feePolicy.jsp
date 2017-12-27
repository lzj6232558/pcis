<%--
  Created by IntelliJ IDEA.
  User: cc450
  Date: 17/12/24/0024
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收费管理</title>
    <%@ include file="../common/common_head.jsp" %>
    <script type="text/javascript" src="/static/js/feePolicy.js"></script>
</head>
<body>

<%--数据表格--%>
<table id="fee_datagrid"></table>

<%--登陆按钮--%>
<div id="fee_toolbar">
    快速查询:
    <input id="keywords" name="keywords" prompt="客户姓名/身份证"/>
    <input id="policySn" name="policySn" style="width: 200px" prompt="核保单号"/>
    起保日期:<input id="beginDate" name="beginDate" type="text" class="easyui-datebox"> </input>
    ~<input id="endDate" name="endDate" type="text" class="easyui-datebox"> </input>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:true" data-cmd="reset">重置</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
</div>

<div id="fee_dialog">
    <form id="fee_form" method="post">
        <table align="center" style="margin-top: 20px">
            <tr>
                <td width="30px"></td>
                <td>投保人</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="customer.name" style="width: 195px" readonly="readonly"/></td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>身份证号</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="customer.idno" style="width: 195px" readonly="readonly"/></td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>保单号</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="serialNumber" style="width: 195px" readonly="readonly"/></td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>核保单号</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="policySn" style="width: 195px" readonly="readonly"/></td>
            </tr>
        </table>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td width="30px"></td>
                <td>保险机构</td>
                <td width="30px"></td>
                <td>
                    <input class="easyui-textbox" name="safetymechanism.name" style="width: 195px" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>保险产品</td>
                <td width="30px"></td>
                <td>
                    <table id="product_datagrid" title="已购产品列表"
                           style="width: 195px">
                    </table>
                </td>
            </tr>

            <tr>
                <td width="30px"></td>
                <td>起保时间</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" class="easyui-datebox" name="beginDate" style="width: 195px"
                           readonly="readonly"></td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>止保时间</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" class="easyui-datebox" name="endDate" style="width: 195px"
                           readonly="readonly"></td>
            </tr>
        </table>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td width="30px"></td>
                <td>保险金额</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="totalAmount" style="width: 195px" readonly="readonly"/></td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>缴费方式</td>
                <td width="30px"></td>
                <td>
                    <input class="easyui-textbox" name="paymentWay" style="width: 195px" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="30px"></td>
                <td>录入人</td>
                <td width="30px"></td>
                <td><input class="easyui-textbox" name="inputUser.username" style="width: 195px" readonly="readonly"/>
                </td>
            </tr>

        </table>
    </form>
</div>

<%--保存取消按钮--%>
<div id="dialog_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>

</body>
</html>
