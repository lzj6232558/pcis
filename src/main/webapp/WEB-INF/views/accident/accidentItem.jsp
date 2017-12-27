<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>事故明细单</title>
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入共同的插件: --%>
<%@include file="/static/common/common.jsp"%>
<%-- 放大缩小图片: --%>
<link href="/static/css/common_style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/static/css/jquery.fancybox.css" media="screen" />
<script type="text/javascript" src="/static/plugins/fancybox/jquery.fancybox.js"></script>
<%-- 引入自己的js: --%>
<script>var id;</script>
<script type="text/javascript" src="/static/js/accidentItem.js"></script>
<body>
<%--01:列表--%>
<table id="accidentItem_datagrid"></table>

<%--02:工具栏--%>
<div id="accidentItem_toobar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-bianji'" data-cmd="edit">补全明细</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" data-cmd="reload">刷新</a>
    <%--高级查询--%>
    &nbsp;&nbsp;&nbsp;
    <input class="easyui-textbox" name="keyword" prompt="车牌号/保单编号">
    处理时间:<input class="easyui-datebox beginDate" name="beginDate"/> ~
            <input class="easyui-datebox endDate" name="endDate"/>
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchForm">查询</a>
</div>

<%--03:添加在弹出框上的按钮--%>
<div id="accidentItem_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   data-cmd="cancel">取消</a>
</div>


<%-- 04:弹出框: --%>
<div id="accidentItem_dialog">
    <div style=" margin:0 auto;margin-top: 50px">
        <form id="accidentItem_form" method="post" enctype="multipart/form-data">
            <%-- 报案明细id --%>
            <table id="accidentItem_table" style="margin-top:20px;">
            <input type="hidden" name="id" id="itemId"/>
                <tr>
                    <td width="35px"></td>
                    <td style="text-align: right">处理日期:</td>
                    <td>
                        <input class="easyui-datebox" name="handDate" style="width:200px"/>
                    </td>
                </tr>
                <tr>
                    <td   width="35px"></td>
                    <td style="text-align: right">事故描述:</td>
                    <td>
                        <textarea name="introduction" rows="2" cols="21"></textarea>
                    </td>
                </tr>
                <tr>
                    <td   width="35px"></td>
                    <td style="text-align: right">现场图片:</td>
                    <td>
                        <input class="easyui-filebox" name="imageFile" data-options="buttonText:'现场图片'" style="width:200px">
                    </td>
                </tr>

                <tr>
                    <td width="35px"></td>
                    <td style="text-align: right">赔偿单细:</td>
                    <td><a class="easyui-linkbutton" data-options="iconCls:'icon-add',width:200">添加赔偿单</a>  </td>
                </tr>
                <tr style="margin:10px;">
                    <td width="35px"></td>
                    <td></td>
                    <td width="35px">
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0">
                            <thead>
                                <tr style="background-color: orange">
                                    <th>受损位置</th>
                                    <th>受损程度</th>
                                    <th>赔偿金额</th>
                                </tr>
                            </thead>
                            <tbody id="edit_table_body">
                                <tr style="margin: 30px; border-bottom: 1px solid #666;">
                                    <td><input type="text" tag="badPath" style="width: 100px;border-radius: 10px;" ></td>
                                    <td>
                                        <select tag="badDegree" style="width:100px;border-radius: 10px">
                                            <option value="0">较轻</option>
                                            <option value="1">较重</option>
                                            <option value="2">严重</option>
                                        </select>
                                    </td>
                                    <td><input type="text" tag="badMoney" style="width: 100px;border-radius: 10px;" id="badMoney"></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td><input class="easyui-textbox" data-options="width:100,height:40,prompt:'赔偿总金额',readonly:true" name="totalDamage" style="margin:0 auto" id="totalDamage">
                        <span style="color: red">元</span></td>
                </tr>
            </table>
        </form>
    </div>
</div>



<%-- 05:查看赔偿单: --%>
<div id="badTable_dialog">
    <form id="badTable_form">
            <table class="edit_table" cellspacing="0" cellpadding="0" border="0">
                <thead>
                <tr style="background-color: orange">
                    <th>受损位置</th>
                    <th>受损程度</th>
                    <th>赔偿金额</th>
                </tr>
                </thead>
                <tbody id="badTaable">
                <tr style="margin: 30px; border-bottom: 1px solid #666;">
                    <td><input type="text" tag="badPath" style="width: 100px;border-radius: 10px;" ></td>
                    <td>
                        <select tag="badDegree" style="width:100px;border-radius: 10px">
                            <option value="0">较轻</option>
                            <option value="1">较重</option>
                            <option value="2">严重</option>
                        </select>
                    </td>
                    <td><input type="text" tag="badMoney" style="width: 100px;border-radius: 10px;" ></td>
                </tr>
                </tbody>
            </table>
    </form>
</div>
</body>
</html>
