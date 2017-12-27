<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>险种信息</title>
</head>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/common_head.jsp"%>


<script type="text/javascript" src="/static/js/product.js"></script>
<body>
<%--列表--%>
<table id="product_datagrid"></table>
<%--工具栏--%>
<div id="product_toobar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <%--高级查询--%>
    <input type="text" class="easyui-textbox" name="keywords" id="keywords" prompt="保险名称/编号">

    <select id="sStatus" class="easyui-combobox" data-options="panelHeight:'auto'"
            name="salesStatus" style="width:130px;" editable="false" >
        <option value="-1">--销售状态--</option>
        <option value="1">在售</option>
        <option value="0">停售</option>
    </select>
    <select id="smId" class="easyui-combobox" data-options="panelHeight:'auto'"
            name="safetyMechanism" style="width:250px;" editable="false" >
        <option value="-1">--机构名称--</option>
        <c:forEach items="${sms}" var="sm">
        <option value="${sm.id}">${sm.name}</option>
        </c:forEach>
    </select>


    <a  class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchForm">搜索</a>

</div>
<%--弹框按钮--%>
<%--添加在弹出框上的按钮--%>
<div id="product_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   data-cmd="cancel">取消</a>
</div>


<div id="product_dialog">
    <form id="product_form" method="post">
        <input type="hidden" name="id"/>
        <table id="product_table">
            <tr>
                <td   width="35px"></td>
                <td>保险编号:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="sn"/>
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>保险名称:</td>
                <td>
                    <input class="easyui-textbox"  type="text" name="name" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>保险机构:</td>
                <td>
                    <input class="easyui-combobox"
                           data-options="url:'/safe/list.do',
                           valueField:'id',textField:'name',panelHeight:'auto'"
                           name="safetyMechanism.id">
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>保障年限:</td>
                <td>
                    <input class="easyui-textbox"  type="text" name="safeguardYear" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>保额:</td>
                <td>
                    <input class="easyui-textbox"  type="text" name="totalMoney" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>基本年费:</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="annuafee" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>销售状态:</td>
                <td>
                    <select class="easyui-combobox" name="salesStatus" style="width:120px;"
                            data-options="panelHeight:'auto'">
                        <option value="1">在售</option>
                        <option value="0">停售</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>不计免赔:</td>
                <td>
                    <select class="easyui-combobox" name="undeduction" style="width:120px;"
                            data-options="panelHeight:'auto'">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>简介:</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="intro" />
                </td>
            </tr>

        </table>
    </form>
</div>


</body>
</html>
