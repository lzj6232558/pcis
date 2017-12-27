<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>暂存单页面</title>
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui_animation.css">
    <link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/icon.css">

    <link href="/static/plugins/myTheme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
            src="/static/plugins/jquery-easyui/themes/insdep/jquery.insdep-extend.min.js"></script>


    <style>
        tr {
            color: #3c3c3c;
            font-family: Arial, "Microsoft YaHei", "微软雅黑";
            font-size: 12px;
            font-style: normal;
        }
    </style>
    <script type="text/javascript" src="/static/js/policy/temppoary.js"></script>
</head>
<body>


<div id="temporary_toolbar">
    <%--<shiro:hasPermission name="temporaryloyee:save">&lt;%&ndash;使用shiro标签判断当前用户是否有对应权限,来决定是否显示对应的按钮&ndash;%&gt;
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>--%>
    <form id="temporary_form" method="post">
        <%--<a id="btn_changeState" class="easyui-linkbutton" iconCls="icon-jujue" plain="true" data-cmd="refuse">拒保</a>--%>
        <a id="btn_putUp" class="easyui-linkbutton" iconCls="icon-putUp" plain="true" data-cmd="putUp">提审</a>
        <a id="btn_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        投保人: <input class="easyui-textbox" name="customerName" id="applicant">
        保单编号: <input class="easyui-textbox" name="policySn" id="policyNo">
        <a type="button" class="easyui-linkbutton"
           iconCls="icon-search" plain="true" onclick="searchTemporary();">查询</a>
    </form>
</div>
<%--初始化暂存表--%>
<table id="tempoary_datagrid"></table>

<div id="temp_dialog" class="easyui-dialog">
    <form id="temp_form" method="post">
        <table>
            <!--拿到的保险产品的id-->
            <input type="hidden" name="car.customer.id" id="carCustomer">
            <input type="hidden" name="customer.id" id="customerId"/>
            <input type="hidden" name="car.id" id="carId"/>
            <input type="hidden" name="safetymechanism.id" id="safetyMechanismId"/>
            <%--保单的id--%>
            <input type="hidden" name="id" id="policyId"/>
            <%--申请日期--%>
            <input type="hidden" name="applyDate" id="applyDate"/>
            <%--开始时间--%>
            <input type="hidden" name="beginDate" id="beginDate"/>

            <tr style="height: 40px;">
                <td>姓名</td>
                <td width="300"><input class="easyui-validatebox textbox" name="customer.name"
                                       data-options="required:true"></td>
                <td>性别</td>
                <td width="300">
                    <select id="customergender" name="customer.gender" class="easyui-combobox"
                            data-options="panelHeight:'auto'" style="width:150px;">
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                </td>
                <td>身份证</td>
                <td width="300px">
                    <input name="customer.idno" class="easyui-validatebox textbox"
                           style="width: 152px;height: 30px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr style="height: 40px;">
                <td>联系方式</td>
                <td>
                    <input class="easyui-validatebox textbox" style="width: 152px;height: 30px;" name="customer.tel"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr style="height: 40px;">
                <td>通讯地址</td>
                <td>
                    <input id="address" name="customer.address"
                           class="easyui-textbox" style="width: 300px"/>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <th>
                    <a class="easyui-linkbutton"
                       data-options="iconCls:'icon-search'" data-cmd="choose_customer">会员客户</a>
                </th>
            </tr>
        </table>


        <table>
            <tr style="height: 40px;">
                <td>车主姓名:</td>
                <td width="300px">
                    <input class="easyui-textbox" style="width: 150px;" type="text" name="car.customer.name"
                           id="car.customer.name"
                           data-options="required:true"/>
                </td>
                <td>品牌</td>
                <td width="300px">
                    <input id="carbrand" class="easyui-textbox" style="width: 150px;" name="car.brand"/>
                </td>
                <td>型号</td>
                <td width="300px">
                    <input id="carmodel" name="car.model" style="width: 150px;" class="easyui-textbox"/>
                </td>
            </tr>
            <tr style="height: 40px;">
                <td>车牌号码</td>
                <td>
                    <input id="carplateNumber" name="car.plateNumber" class="easyui-validatebox textbox"
                           data-options="required:true"
                           style=" width: 150px"/>
                </td>
                <td>排气量</td>
                <td>
                    <input id="cargasDisplacement" name="car.gasDisplacement" style="width: 150px;"
                           class="easyui-textbox"/>L
                </td>
                <td>汽车分类</td>
                <td>
                    <select class="easyui-combobox" name="car.category" id="carcategory" style="width:150px;"
                            data-options="panelHeight:'auto'">
                        <option value="1">载客</option>
                        <option value="0">载物</option>
                    </select>
                </td>
            </tr>
            <tr style="height: 40px;">
                <td>购置日期</td>
                <td>
                    <input name="car.purchaseDate" id="carpurchaseDate" style="width:150px;"
                           class="easyui-datebox"/>
                </td>
                <td>市场估价</td>
                <td>
                    <input name="car.valuation" id="carvaluation" class="easyui-textbox" style="width:150px;"/>万
                </td>
                <td>车载大小</td>
                <td>
                    <select class="easyui-combobox" id="carsize" name="car.size" style="width:150px;"
                            data-options="panelHeight:'auto'">
                        <option value="0">小型</option>
                        <option value="1">中型</option>
                        <option value="2">大型</option>
                        <option value="3">超大型</option>
                    </select>
                </td>
            </tr>

            <tr style="height: 40px;">
                <td>
                    <a class="easyui-linkbutton" style="width:150px;"
                       data-options="iconCls:'icon-search'" data-cmd="choose_car">常见车型选择</a>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
        <table>
            <tr>
                <td>承保机构</td>
                <td width="300">
                    <input id="safe_select" name="safetymechanism.id" style="width:200px;"
                           onchange="changeSafe();"/>
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-xinzeng'"
                       data-cmd="add_product">添加产品</a>
                </td>
                <td>保险时长</td>
                <td>
                    <input name="duration" style="width: 40px;" id="duration" class="easyui-validatebox textbox"
                           data-options="required:true" onchange="calculate();"/>年
                </td>
            </tr>
            <tr id="hidethis" class="product-tr" data-oid="-1">
                <td>保险产品:</td>
                <td id="productName"  width="300">防碰瓷险</td>
                <td>产品编号:</td>
                <td id="productSn"  width="200">5464</td>
                <td>购买保额(万):</td>
                <td id="totalMoney" class="totalMoney" width="200">5464</td>
                <td>保险年费:</td>
                <td id="annuaFlee" class="annuaFlee" width="100">0</td>
                <td><a class="easyui-linkbutton"
                       data-options="iconCls:'icon-shanchu',plain:'true'" onclick="delete_this(this);"></a></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td valign="bottom"><font>总计: </font></td>
                <td valign="bottom"><font color="#9acd32" size="10" id="totalAmount">0</font></td>
                <td valign="bottom"><font>元</font></td>
            </tr>
        </table>
    </form>
</div>


<div id="choose_product_dialog">
    <table id="product_datagrid"></table>
</div>
<div id="choose_customer_dialog">
    <table id="customer_datagrid"></table>
</div>
<div id="choose_car_dialog">
    <table id="car_datagrid"></table>
</div>
<div id="choose_productItem_dialog">
    <table id="productItem_datagrid"></table>
</div>
<div id="product_btns2">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="select_product">确定</a>
</div>
<div id="customer_btns2">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="select_customer">确定</a>
</div>
<div id="car_btns2">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="select_car">确定</a>
</div>
<div id="temporary_btns">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="temporary_submit();">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

</div>
</body>
</html>
