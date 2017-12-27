<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>询价明细</title>

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
    <script type="text/javascript"
            src="/static/plugins/jquery-easyui/themes/insdep/expand/jquery-easyui-datagridview/datagrid-detailview.js"></script>


    <style>
        tr {
            color: #3c3c3c;
            font-family: Arial, "Microsoft YaHei", "微软雅黑";
            font-size: 12px;
            font-style: normal;
        }
        .datagrid-row-selected {
            background: #f4b35c;
            color: #fff;
        }
    </style>
    <script type="text/javascript" src="/static/js/enquiry/enquiryDetail.js"></script>
</head>
<body>
<table id="enquiryDetail"></table>

<div id="p" class="easyui-dialog">
    <form id="form" method="post">
        <table>
            <tr>
                <th>
                    <a class="easyui-linkbutton"
                       data-options="iconCls:'icon-search'">会员客户</a>
                </th>
            </tr>
        </table>
        <table>
            <!--拿到的保险产品的id-->
            <input type="hidden" name="customer.id" id="customerId"/>
            <input type="hidden" name="car.id" id="carId"/>
            <input type="hidden" name="safetymechanism.id" id="safetyMechanismId"/>
            <input type="hidden" name="totalAmount" id="totalAmount2"/>
            <input type="hidden" name="id" id="enquiryId"/>

            <tr style="height: 40px;">
                <td>姓名</td>
                <td width="300"><input class="easyui-textbox " name="customer.name"
                                       data-options="required:true" id="name"></td>
                <td>性别</td>
                <td width="300">
                    <select id="customergender" name="customer.gender" class="easyui-combobox"
                            data-options="panelHeight:'auto',width:'60px'">
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                </td>
                <td>身份证</td>
                <td width="300px">
                    <input name="customer.idno" class="easyui-textbox "
                           style="width: 152px;height: 30px;"
                           data-options="required:true" id="idno"/>
                </td>
            </tr>
            <tr style="height: 40px;">
                <td>联系方式</td>
                <td>
                    <input class="easyui-textbox" style="width: 152px;height: 30px;" name="customer.tel"
                           data-options="required:true" id="tel"/>
                </td>
                <%--<td>行驶区域(热门城市)</td>
                <td>
                    <select id="city" class="easyui-combobox" name="city" style="width:100px;"
                            data-options="valueField:'id',textField:'name',url:'/province/selectHotCity.do'">
                    </select>
                </td>--%>
            </tr>
            <tr style="height: 40px;">
                <td>信息来源</td>
                <td>
                    <input class="easyui-textbox" style="width: 152px;height: 30px;" name="origin" id="origin"
                    />
                </td>
                <td>接待客服</td>
                <td>
                    <select id="employeeId" class="easyui-combobox" name="employee.id" style="width:100px;"
                            data-options="valueField:'id',textField:'realname',url:'/employee/selectAll.do'">
                    </select>
                </td>
            </tr>
        </table>

        <table>
            <tr style="height: 40px;">
                <td>
                    <a class="easyui-linkbutton" style="width:150px;"
                       data-options="iconCls:'icon-search'">车主信息</a>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr style="height: 40px;">
                <td>车主姓名:</td>
                <td width="300px">
                    <input class="easyui-textbox" style="width: 150px;" type="text" name="car.customer.name"
                           id="customerName"
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
                    <input id="carplateNumber" name="car.plateNumber" class="easyui-textbox"
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
            <tr>
                <td>车架号</td>
                <td>
                    <input name="car.VIN" id="VIN" class="easyui-textbox" style="width:150px;"/>
                </td>
                <td>发动机号</td>
                <td>
                    <input name="car.engineNumber" id="engineNumber" class="easyui-textbox" style="width:150px;"/>
                </td>
            </tr>

        </table>
        <%--初始化机构产品数据表格--%>
        <table id="safe_product_dataGrid"></table>
        <table>
            <tr align="center">
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
        <%--<input type="button" style="margin-left: 300px;width:110px;height: 40px;" onclick="savaBill()"
               class="easyui-linkbutton"  value="报价单转乘保单">--%>
        <input type="button" style="margin-left: 150px;width:150px;height: 40px;" onclick="calculate2();"
               class="easyui-linkbutton" data-options="iconCls:'icon-edit'" value="报价计算">
        <input type="button" style="margin-left: 150px;width:150px;height: 40px;" onclick="updateEnquiry();"
               class="easyui-linkbutton" data-options="iconCls:'icon-money'" value="修改询价信息">
        <input type="button" style="margin-left: 150px;width:150px;height: 40px;" onclick="saveBill();"
               class="easyui-linkbutton" data-options="iconCls:'icon-money'" value="转承保单">
    </form>
</div>

<div id="toolbar" width="50">
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    报价单号:
    <input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:150px" id="number">
    <%--车牌号:
    <input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:150px" id="plateNumber">--%>
    开始时间:
    <input id="beginDate" type="text" class="easyui-datebox" > </input>
    结束时间:
    <input id="endDate" type="text" class="easyui-datebox"> </input>
    <a id="submit_search" class="easyui-linkbutton" iconCls="icon-search" plain=true>搜索</a>
</div>
<div id="buttons" width="50">
    <a id="dialog_edit" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存订单</a>
    <a id="cancel_btn" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
