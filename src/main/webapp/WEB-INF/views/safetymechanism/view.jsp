<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/common_head.jsp"%>


<script type="text/javascript" src="/static/js/safetymechanism.js"></script>
<body>
<%--列表--%>
<table id="safe_datagrid"></table>
<%--工具栏--%>
<div id="safe_toobar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <%--高级查询--%>
    <input type="text" class="easyui-textbox" name="keywords" id="keywords">
    <a  class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchForm">搜索</a>
</div>
<%--弹框按钮--%>
<%--添加在弹出框上的按钮--%>
<div id="safe_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   data-cmd="cancel">取消</a>
</div>


<div id="safe_dialog">
    <form id="safe_form" method="post">
        <input type="hidden" name="id"/>
        <table id="safe_table">
            <tr>
                <td   width="35px"></td>
                <td>机构编号:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="sn"/>
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>机构名称:</td>
                <td>
                    <input class="easyui-textbox"  type="text" name="name" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>法人代表:</td>
                <td>
                    <input class="easyui-textbox"  type="text" name="legalperson" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>证件号码:</td>
                <td>
                    <input class="easyui-textbox"  type="text" name="dentitycard" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>联系方式:</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="contactway" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>地址:</td>
                <td>
                    <input type="text" class="easyui-textbox" name="address">
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>合作状态:</td>
                <td>
                    <select class="easyui-combobox" name="cooperation" style="width:120px;"
                            data-options="panelHeight:'auto'">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </td>
            </tr>

        </table>
    </form>
</div>


</body>
</html>
