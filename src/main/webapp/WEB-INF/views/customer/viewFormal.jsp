<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../common/common_head.jsp"%>  <!---抽取的共同代码 放这里-->
    <script type="text/javascript" src="/static/js/customer/customerFormal.js"></script>
</head>
<body>
    <div id="toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"   onclick="edit()">修改信息</a>
        <a class="easyui-linkbutton" iconCls="icon-turnover" plain="true"  onclick="transfer()">移交客户</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true"   onclick="reload()">刷新</a>
        创建日期:
        <input name="beginDate" id="beg" type= "text" class= "easyui-datebox" style="width: 130px"> </input>
        ~
        <input name="endDate" id="end" type= "text" class= "easyui-datebox" style="width: 130px"> </input>
        关键字:<input class="easyui-textbox" id="keyword" name="keywords" style="width:110px">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="mysearch()">搜索</a>
    </div>

    <div id="customer_btns">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   onclick="save()">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   onclick="cancel()">取消</a>
    </div>

    <div id="customerformal_btns">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   onclick="yees()">确定</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   onclick="known()">取消</a>
    </div>

    <!--显示所有客户  数据表格-->
    <table id="customer_datagrid"></table>

    <!--框-->
    <div id="fomal_dialog">
        <form id="transfer_form" method="post">
            <table align="center" style="margin-top:15px">
                <tr>
                    <td width="32"></td>
                    <td>要移交的客户:</td>
                    <input type="hidden" name="customer.id">  <!--保存到数据库的时候我们只用保存一个id就去即可-->
                    <td><span id="name"></span></td>
                </tr>
                <tr></tr><tr></tr><tr></tr>
                <tr>
                    <td width="32"></td>
                    <td>要移交给:</td>
                    <td>
                        <input id="newId" class="easyui-combobox"
                               data-options="url:'/employee/selectAll.do',valueField:'id',textField:'username'"
                               name="newseller.id">
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>移交原因:</td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td colspan="2"><textarea name="transreason" cols="33" rows="5"></textarea></td>
                </tr>
            </table>
        </form>
    </div>






    <!---->
    <div id="customer_dialog"> <!--弹出框 依靠的是dialog .  里边其实是一个form表单-->
        <form id="customer_form" method="post">
            <input type="hidden" name="id"/>
            <table id="yy" align="center" style="margin-top:15px">
                <tr>
                    <td width="32"></td>
                    <td>姓名:</td>
                    <td>
                        <input class="easyui-textbox" name="name"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>身份识别码:</td>
                    <td>
                        <input class="easyui-textbox" name="idno"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>email:</td>
                    <td>
                        <input class="easyui-textbox" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>年龄:</td>
                    <td>
                        <input class="easyui-textbox" name="age"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>性别:</td>
                    <td>
                        <select  class="easyui-combobox"   name="gender" data-options="width:'143'">
                            <option value='1'>男</option>
                            <option value='0'>女</option>
                            </select>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>电话:</td>
                    <td>
                        <input class="easyui-textbox" name="tel"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>住址:</td>
                    <td>
                        <input class="easyui-textbox" name="address"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>QQ:</td>
                    <td>
                        <input class="easyui-textbox" name="qq"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>职业:</td>
                    <td>
                        <input class="easyui-combobox" name="job" data-options="url:'/dictionaryItem/select.do?sn=job',valueField:'name',textField:'name'"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>收入:</td>
                    <td>
                        <input class="easyui-combobox" name="salary" data-options="url:'/dictionaryItem/select.do?sn=salaryLevel',valueField:'name',textField:'name'"/>
                    </td>
                </tr>
                <tr>
                    <td width="32"></td>
                    <td>客户来源:</td>
                    <td>
                        <input class="easyui-combobox" name="source" data-options="url:'/dictionaryItem/select.do?sn=customerSource',valueField:'name',textField:'name'"/>
                    </td>
                </tr>
                </table>
            </form>
        </div>
</body>
</html>
