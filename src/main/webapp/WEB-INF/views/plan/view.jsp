<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>潜在客户开发计划</title>
    <%@include file="/WEB-INF/views/common/common_head.jsp"%>
    <script type="text/javascript" src="/static/js/plan.js"></script>
</head>
<body>
<table id="plan_datagrid"></table>
<div id="chooseCust_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-cmd="selectCust">选择</a>
</div>
<div id="mark_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-cmd="result">标记</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>


<div id="plan_tools">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增计划</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">调整计划</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">取消计划</a>
    <a class="easyui-linkbutton" iconCls="icon-mark" plain="true"   data-cmd="mark">标记结果</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <br>
    日期:
    <input  name="beginDate" type= "text" class= "easyui-datebox" style="width: 130px"> </input>
    ~
    <input  name="endDate" type= "text" class= "easyui-datebox" style="width: 130px"> </input>

    模糊搜索:
    <input id="keywords" name="keywords" class="easyui-textbox" data-options="prompt:'客户/计划主题'" style="width:170px">


    <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" data-cmd="searchForm">搜索</a>
</div>
<div id="plan_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<div id="plan_dialog">
    <form id="plan_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px">
            <th colspan="3" align="center">客户开发计划</th>

            <tr></tr>
            <tr>
                <td width="32"></td>
                <td>客户</td>
                <input type="hidden" name="customer.id"/>
                <td><input id="cust" name="customer.name" class="easyui-textbox" readonly="true"/></td>


                <td><a class="easyui-linkbutton" data-options="plian:true,iconCls:'icon-search'" data-cmd="chooseCust">查询客户</a></td>
            </tr>
            <tr>
                <td width="32"></td>
                <td>计划时间</td>
                <td><input type= "text" class= "easyui-datebox" name="date" required ="required"></td>
                <td>计划主题</td>
                <td><input class="easyui-textbox" name="subject"></td>
            </tr>
            <tr>
                <td width="32"></td>
                <td>实施方式</td>
                <td><select class="easyui-combobox"  name="type" style="width:200px;">
                    <option value="上门拜访">上门拜访</option>
                    <option value="饭局">饭局</option>
                    <option value="电话联系">电话联系</option>
                </select></td>
                <td>备注</td>
                <td><input class="easyui-textbox" name="remark"></td>
            </tr>
            <tr>
                <td width="32"></td>
                <td align="center">计划详情</td>
                <td >
                    <input class="easyui-textbox" data-options="multiline:true" name="detail" value="detail"
                           style="width:300px;height:100px">
                </td>
            </tr>
            <tr>
                <td width="32"></td>

            </tr>
        </table>
    </form>
</div>
<%--弹出客户列表--%>
<div id="chooseCust_dialog">
<%--选择潜在客户表格--%>
    <table id="cust_datagrid">
    </table>
</div>
<div id="mark_dialog">
    <form id="mark_form" method="post">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>计划编号:<span id="sn"></span></td>
            </tr>
            <tr>
                <td>计划主题:<span id="subject"></span></td>
            </tr>
            <tr>
                <td>计划效果
                    <select class="easyui-combobox" name="result" style="width: 80px" data-options="panelHeight:'auto'">
                        <option value="3">优</option>
                        <option value="2">中</option>
                        <option value="1">差</option>

                    </select>

                </td>
            </tr>

        </table>
    </form>


</div>



</body>
</html>
