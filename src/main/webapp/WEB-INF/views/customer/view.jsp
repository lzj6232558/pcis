<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../common/common_head.jsp"%>  <!---抽取的共同代码 放这里-->

    <script type="text/javascript" src="/static/js/customer/customer.js"></script>
</head>
<body>
    <div id="toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="add()">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"   onclick="edit()">修改信息</a>
        <a class="easyui-linkbutton" iconCls="icon-ok" plain="true"  plain="true" onclick="ok()">开发成功</a>
        <a class="easyui-linkbutton" iconCls="icon-failure" plain="true"   onclick="fail()">开发失败</a>
        <a class="easyui-linkbutton" iconCls="icon-turnover" plain="true"  onclick="transfer()">移交客户</a>
        <a class="easyui-linkbutton" iconCls="icon-chehui" plain="true"   onclick="bed()">流失客户</a>
        <a class="easyui-linkbutton" iconCls="icon-share" plain="true"  onclick="share()">移入资源池</a>
        <br> <!--换行-->
        创建日期:
        <input name="beginDate" id="beg" type= "text" class= "easyui-datebox" style="width: 130px"> </input>
        ~
        <input name="endDate" id="end" type= "text" class= "easyui-datebox" style="width: 130px"> </input>
        关键字:<input class="easyui-textbox" id="keyword" name="keywords" data-options="prompt:'薪资/身份证/邮箱/电话'" style="width:160px">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="mysearch()">搜索</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true"   onclick="reload()">刷新</a>
    </div>

    <div id="customer_btns">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   onclick="save()">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   onclick="cancel()">取消</a>
    </div>

    <!--开发失败按钮-->
    <div id="customerfail_btns">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   onclick="bigo()">确定</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   onclick="non()">取消</a>
    </div>

    <!--客户流失按钮-->
    <div id="customerlost_btns">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   onclick="nice()">确定</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   onclick="cance()">取消</a>
    </div>

    <!--客户移交按钮-->
    <div id="customertrans_btns">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   onclick="yees()">确定</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   onclick="known()">取消</a>
    </div>


    <!--客户移交记录表-->
    <div id="transfer_dialog">
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







    <!--客户开发失败记录-->
    <div id="fail_dialog">
        <form id="fail_form" method="post">
           <input type="hidden" name="cust.id" >
            <input type="hidden" name="chargeuser.id" >
            <table id="xx" align="center" style="margin-top:15px">
                <tr>
                    当前负责人:
                    <span id="nowname"></span>
                </tr>
                <tr>
                    <td>记录详细原因:</td>
                </tr>
                <tr>
                    <td colspan="2"><textarea name="failreason" cols="20" rows="5"></textarea></td>
                </tr>
            </table>
        </form>
    </div>

    <!--流失表-->
    <div id="lost_dialog">
        <form id="lost_form" method="post">
            <input type="hidden" name="cust.id" >
            <input type="hidden" name="chargeuser.id" >
            <table align="center" style="margin-top:15px">
                <tr>
                    当前负责人:
                    <span id="nowname_lost"></span>
                </tr>
                <tr>
                    <td>记录详细原因:</td>
                </tr>
                <tr>
                    <td colspan="2"><textarea name="lostreason" cols="20" rows="5"></textarea></td>
                </tr>
            </table>
        </form>
    </div>



    <!--显示所有客户  数据表格-->
    <table id="customer_datagrid"></table>
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
