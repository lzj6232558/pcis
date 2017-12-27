<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../common/common_head.jsp"%>  <!---抽取的共同代码 放这里-->
    <script type="text/javascript" src="/static/js/customertransfer/customertransfer.js"></script>
</head>
<body>

<table id="transfer_datagrid"></table>

<div id="fail_toolbar">
    日期:
    <input name="beginDate" type= "text" class= "easyui-datebox" style="width: 110px"> </input>
    ~
    <input name="endDate" type= "text" class= "easyui-datebox" style="width: 110px"> </input>

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    模糊搜索:
    <input name="customer" class="easyui-textbox" data-options="prompt:'客户'" style="width:70px">
    <input name="chargeUser" class="easyui-textbox" data-options="prompt:'负责人'" style="width:80px">

    <a class="easyui-linkbutton" data-options="iconCls:'icon-chaxun'" onclick="mysearch()">搜索</a>
    &nbsp;&nbsp;
    <a class="easyui-linkbutton" iconCls="icon-shuaxin"   data-cmd="reload">刷新</a>
</div>


<div id="fail_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   data-cmd="cancel">取消</a>
</div>


</body>
</html>
