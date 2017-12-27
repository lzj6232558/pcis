<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>车辆信息</title>

    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/insdep/easyui_animation.css">
    <link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jquery-easyui/themes/icon.css">

    <link href="/static/plugins/myTheme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/themes/insdep/jquery.insdep-extend.min.js"></script>



    <script type="text/javascript" src="/static/js/car.js"></script>
    <style>
        tr {
            color: #3c3c3c;
            font-family: Arial,"Microsoft YaHei","微软雅黑";
            font-size: 12px;
            font-style: normal;
        }
    </style>
</head>
<body>


<table id="car_datagrid"></table>


<div id="car_dialog">
    <form id="car_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top:15px" >
            <tr>
                <td   width="35px"></td>
                <td>车主姓名:</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="customer.name" data-options="required:true"/>
                    <input type="hidden" name="customer.id"  >
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>车辆品牌:</td>
                <td>
                    <input   class="easyui-textbox" type="text" name="brand"/>
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>车辆型号:</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="model" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>车牌号码:</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="plateNumber" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>排气量/L:</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="gasDisplacement" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>购买日期:</td>
                <td>
                    <input type="text" class="easyui-datebox" required="required" name="purchaseDate">
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>市场估价/万元:</td>
                <td>
                    <input class="easyui-textbox"  type="text" name="valuation" />
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>分类:</td>
                <td>
                    <select class="easyui-combobox" name="category" style="width:124px;"
                            data-options="panelHeight:'auto'">
                        <option value="1">载客</option>
                        <option value="0">载物</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>车载大小:</td>
                <td>
                    <select class="easyui-combobox" name="size" style="width:124px;"
                            data-options="panelHeight:'auto'">
                        <option value="0">小型</option>
                        <option value="1">中型</option>
                        <option value="2">大型</option>
                        <option value="3">超大型</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td   width="35px"></td>
                <td>备注信息:</td>
                <td>
                    <textarea name="remarks" id="type_Remark"
                              class="easyui-validatebox"  style="height:80px;width: 120px">
                    </textarea>
                </td>
            </tr>
        </table>
    </form>

</div>



<div id="car_toolbar">

    <a id="edit_btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>

    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton" iconCls="icon-add" plain=true data-cmd="add">新增</a>
    <a class="easyui-linkbutton"
       iconCls="icon-export" plain=true data-cmd="exportXls">导出</a>
    <a class="easyui-linkbutton"
       iconCls="icon-import" plain=true data-cmd="importXls">导入</a>
    车牌号/品牌/车主
    <input type="text" id="keyword" name="keyword" class="easyui-textbox"
           data-options="prompt:'车牌号/品牌/车主',iconAlign:'right'"/>
    <a id="submit_search" class="easyui-linkbutton" iconCls="icon-search" plain=true>搜索</a>
</div>


<div id="car_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true"   data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true"   data-cmd="cancel">取消</a>
</div>
</body>
</html>