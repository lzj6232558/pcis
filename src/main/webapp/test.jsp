


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="shortcut icon" href="favicon.ico" />

    <style>
        tr {
            color: #3c3c3c;
            font-family: Arial,"Microsoft YaHei","微软雅黑";
            font-size: 12px;
            font-style: normal;
        }
    </style>

    <title>大风车车险后台管理系统</title>
    <!--
        JQuery EasyUI 1.5.x of Insdep Theme 1.0.0
        演示地址：https://www.insdep.com/example/
        下载地址：https://www.insdep.com
        问答地址：https://bbs.insdep.com

        项目地址：http://git.oschina.net/weavors/JQuery-EasyUI-1.5.x-Of-Insdep-Theme

        QQ交流群：184075694 （优先发布更新主题及内测包）
    -->
    <!--
        注意样式表优先级
        主题样式必须在easyui组件样式后。
    -->

    <link href="/static/plugins/myTheme/themes/insdep/easyui.css" rel="stylesheet" type="text/css">


    <!--
        themes/insdep/easyui_animation.css
        Insdep对easyui的额外增加的动画效果样式，根据需求引入或不引入，此样式不会对easyui产生影响
    -->
    <link href="/static/plugins/myTheme/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">

    <!--
        themes/insdep/easyui_plus.css
        Insdep对easyui的额外增强样式,内涵所有 insdep_xxx.css 的所有组件样式
        根据需求可单独引入insdep_xxx.css或不引入，此样式不会对easyui产生影响
    -->
    <link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">

    <!--
        themes/insdep/insdep_theme_default.css
        Insdep官方默认主题样式,更新需要自行引入，此样式不会对easyui产生影响
    -->
    <link href="/static/plugins/myTheme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">

    <!--
        themes/insdep/icon.css
        美化过的easyui官方icons样式，根据需要自行引入
    -->

    <link href="/static/plugins/myTheme/themes/insdep/icon.css" rel="stylesheet" type="text/css">
    <!--
        2017/03/22 新增
        plugin/font-awesome-4.7.0/css/font-awesome.min.css
        第三方图标库样式，用于显示左侧菜单栏图标，根据需要自行引入
    -->
    <link href="/static/plugins/myTheme/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/static/plugins/myTheme/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/myTheme/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/myTheme/themes/insdep/jquery.insdep-extend.min.js"></script>
    <script type="text/javascript" src="/static/js/index.js"></script>

</head>

<body>
<div id="master-layout">
    <div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
        <div class="theme-navigate">
            <div class="left">
                <a href="#" class="easyui-linkbutton left-control-switch"><i class="fa fa-bars fa-lg"></i></a>
                <a href="#" class="easyui-menubutton theme-navigate-user-button" data-options="menu:'.theme-navigate-user-panel'">admin</a>
                <a href="#" class="easyui-linkbutton">新建</a>
                <a href="#" class="easyui-menubutton" data-options="menu:'#mm1',hasDownArrow:false">文件</a>
                <a href="#" class="easyui-menubutton" data-options="menu:'#mm2',hasDownArrow:false">编辑</a>
                <a href="#" class="easyui-menubutton" data-options="menu:'#mm3',hasDownArrow:false">消息<span class="badge color-default">15</span></a>

                <select id="cc1" class="easyui-combobox theme-navigate-combobox" name="dept" style="width:120px;">
                    <option>选择样式</option>
                    <option>Insdep</option>
                    <option>Bootstrap</option>
                    <option>Gray</option>
                    <option>Metro</option>
                    <option>Material</option>
                </select>

                <div id="mm1" class="theme-navigate-menu-panel">
                    <div>新建</div>
                    <div>打开</div>
                    <div>
                        <span>打开最近文件</span>
                        <div>
                            <div>1 index.html</div>
                            <div>2 calendar-custom.html</div>
                            <div>3 combo-animation.html</div>
                            <div>4 datebox-restrict.html</div>
                            <div>5 datetimespinner-icon.html</div>
                            <div>6 filebox-button-align.html</div>
                            <div>7 menubutton-alignment.html</div>
                            <div>8 messager-interactive.html</div>
                            <div>9 propertygrid-group-format.html</div>
                            <div class="menu-sep"></div>
                            <div>启动时重新打开文件</div>
                        </div>
                    </div>
                    <div>关闭</div>
                    <div>全部关闭</div>
                    <div class="menu-sep"></div>
                    <div data-options="disabled:true,iconCls:'icon-save'">保存</div>
                    <div>另存为</div>
                    <div data-options="disabled:true">保存为全部</div>
                    <div class="menu-sep"></div>
                    <div>
                        <span>导入</span>
                        <div>
                            <div>XML 到模板</div>
                            <div>表格式数据</div>
                            <div data-options="disabled:true">Word 文档</div>
                            <div data-options="disabled:true">Excel 文档</div>
                        </div>
                    </div>
                    <div>
                        <span>导出</span>
                        <div>
                            <div>表格</div>
                        </div>
                    </div>
                    <div class="menu-sep"></div>
                    <div>退出</div>
                </div>



            </div>

        </div>

    </div>

    <!--开始左侧菜单-->
    <div data-options="region:'west',border:false,bodyCls:'theme-left-layout'" style="width:200px;">
        <!--正常菜单-->
        <div class="theme-left-normal">
            <!--start class="easyui-layout"-->
            <div class="easyui-layout" data-options="border:false,fit:true">
                <!--start region:'north'-->
                <div data-options="region:'north',border:false" style="height:100px;">
                    <!--start theme-left-user-panel-->
                    <div class="theme-left-user-panel">
                        <dl>
                            <dt>
                                <img src="/static/plugins/jquery-easyui/themes/insdep/images/2.png" width="43" height="43">
                            </dt>
                            <dd>
                                <b class="badge-prompt">admin <i class="badge color-important">1</i></b>
                                <span>will@qq.com </span>
                                <p>Hi!<i class="text-success"> </i></p>
                            </dd>

                        </dl>
                    </div>
                    <!--end theme-left-user-panel-->
                </div>
                <!--end region:'north'-->

                <!--start region:'center'-->
                <div data-options="region:'center',border:false">

                    <!--start easyui-accordion-->
                    <div class="easyui-accordion" data-options="border:false,fit:true">
                        <div title="客户管理">
                            <ul id="menu_customer" class="easyui-datalist"></ul>
                        </div>
                        <div title="信息管理">
                            <ul id="menu_info" class="easyui-datalist"></ul>
                        </div>
                        <div title="保单管理">
                            <ul id="menu_policy" class="easyui-datalist"></ul>
                        </div>
                        <div title="见费出单">
                            <ul id="menu_fee" class="easyui-datalist"></ul>
                        </div>
                        <div title="理赔管理">
                            <ul id="menu_loss" class="easyui-datalist"></ul>
                        </div>
                        <div title="员工管理">
                            <ul id="menu_emp" class="easyui-datalist"></ul>
                        </div>
                        <div title="报表管理">
                            <ul id="menu_chart" class="easyui-datalist"></ul>
                        </div>
                        <div title="系统管理">
                            <ul id="menu_sys" class="easyui-datalist"></ul>
                        </div>

                    </div>
                    <!--end easyui-accordion-->

                </div>
                <!--end region:'center'-->
            </div>
            <!--end class="easyui-layout"-->
        </div>
        <!--最小化菜单-->
    </div>
    <!--结束左侧菜单-->






    <div data-options="region: 'center',border:false">
        <div id="tt" class="easyui-tabs">
        </div>
    </div>
</div>


<script>
    $(function(){
        $('#tt').tabs('add',{
            title:'欢迎登陆',
//            content: '<iframe src="/policy/all.do" width="100%" height="100%" frameborder=0></iframe>',
            content: '<iframe src="/static/model/model.jsp" frameborder="0" style="width: 100%; height: 100%"></iframe>',
            closable:true,
        });
        //初始化
        $("#menu_customer").datalist({
                url: 'menu/getMenuByParentId.do?id=15',

                onClickRow: function (index, row) {
                    var target = row.url;
                    //判断对应节点文本内容的选项卡面板是否已存在,如果已存在就选中,不存在则新增
                    if ($("#tt").tabs("exists", row.text)) {
                        //console.debug(node);
                        $("#tt").tabs("select", row.text);
                        return;
                    }
                    $("#tt").tabs("add",{
                        closable:true,
                        title:row.text,
                        content:'<iframe src='+target+' frameborder="0" style="width: 100%; height: 92%"></iframe>',
                    })
                }
            }
        )
        //初始化2
        $("#menu_info").datalist({
                url: 'menu/getMenuByParentId.do?id=42',
                onClickRow: function (index, row) {
                    var target = row.url;
                    //判断对应节点文本内容的选项卡面板是否已存在,如果已存在就选中,不存在则新增
                    if ($("#tt").tabs("exists", row.text)) {
                        //console.debug(node);
                        $("#tt").tabs("select", row.text);
                        return;
                    }
                    $("#tt").tabs("add",{
                        closable:true,
                        title:row.text,
                        content:'<iframe src='+target+' frameborder="0" style="width: 100%; height: 92%"></iframe>',
                    })
                }
            }
        )
        //初始化3
        $("#menu_policy").datalist({
                url: 'menu/getMenuByParentId.do?id=30',
                onClickRow: function (index, row) {
                    var target = row.url;
                    //判断对应节点文本内容的选项卡面板是否已存在,如果已存在就选中,不存在则新增
                    if ($("#tt").tabs("exists", row.text)) {
                        //console.debug(node);
                        $("#tt").tabs("select", row.text);
                        return;
                    }
                    $("#tt").tabs("add",{
                        closable:true,
                        title:row.text,
                        content:'<iframe src='+target+' frameborder="0" style="width: 100%; height: 92%"></iframe>',
                    })
                }
            }
        )
        //初始化4
        $("#menu_fee").datalist({
                closable:true,
                url: 'menu/getMenuByParentId.do?id=27',
                onClickRow: function (index, row) {
                    var target = row.url;
                    //判断对应节点文本内容的选项卡面板是否已存在,如果已存在就选中,不存在则新增
                    if ($("#tt").tabs("exists", row.text)) {
                        //console.debug(node);
                        $("#tt").tabs("select", row.text);
                        return;
                    }
                    $("#tt").tabs("add",{
                        closable:true,
                        title:row.text,
                        content:'<iframe src='+target+' frameborder="0" style="width: 100%; height: 92%"></iframe>',
                    })
                }
            }
        )
        //初始化5
        $("#menu_loss").datalist({
                closable:true,
                url: 'menu/getMenuByParentId.do?id=17',
                onClickRow: function (index, row) {
                    var target = row.url;
                    //判断对应节点文本内容的选项卡面板是否已存在,如果已存在就选中,不存在则新增
                    if ($("#tt").tabs("exists", row.text)) {
                        //console.debug(node);
                        $("#tt").tabs("select", row.text);
                        return;
                    }
                    $("#tt").tabs("add",{
                        closable:true,
                        title:row.text,
                        content:'<iframe src='+target+' frameborder="0" style="width: 100%; height: 92%"></iframe>',
                    })
                }
            }
        )
        //初始化6
        $("#menu_emp").datalist({
                closable:true,
                url: 'menu/getMenuByParentId.do?id=34',
                onClickRow: function (index, row) {
                    var target = row.url;
                    //判断对应节点文本内容的选项卡面板是否已存在,如果已存在就选中,不存在则新增
                    if ($("#tt").tabs("exists", row.text)) {
                        //console.debug(node);
                        $("#tt").tabs("select", row.text);
                        return;
                    }
                    $("#tt").tabs("add",{
                        closable:true,
                        title:row.text,
                        content:'<iframe src='+target+' frameborder="0" style="width: 100%; height: 92%"></iframe>',
                    })
                }
            }
        )
        //初始化7
        $("#menu_chart").datalist({
                closable:true,
                url: 'menu/getMenuByParentId.do?id=46',
                onClickRow: function (index, row) {
                    var target = row.url;
                    //判断对应节点文本内容的选项卡面板是否已存在,如果已存在就选中,不存在则新增
                    if ($("#tt").tabs("exists", row.text)) {
                        //console.debug(node);
                        $("#tt").tabs("select", row.text);
                        return;
                    }
                    $("#tt").tabs("add",{
                        closable:true,
                        title:row.text,
                        content:'<iframe src='+target+' frameborder="0" style="width: 100%; height: 92%"></iframe>',
                    })
                }
            }
        )
        //初始化8
        $("#menu_sys").datalist({
                closable:true,
                url: 'menu/getMenuByParentId.do?id=1',
                onClickRow: function (index, row) {
                    var target = row.url;
                    //判断对应节点文本内容的选项卡面板是否已存在,如果已存在就选中,不存在则新增
                    if ($("#tt").tabs("exists", row.text)) {
                        //console.debug(node);
                        $("#tt").tabs("select", row.text);
                        return;
                    }
                    $("#tt").tabs("add",{
                        closable:true,
                        title:row.text,
                        content:'<iframe src='+target+' frameborder="0" style="width: 100%; height: 92%"></iframe>',
                    })
                }
            }
        )


        /*布局部分*/
        $('#master-layout').layout({
            fit:true/*布局框架全屏*/
        });

        /*右侧菜单控制部分*/
        var left_control_status=true;
        var left_control_panel=$("#master-layout").layout("panel",'west');

        $(".left-control-switch").on("click",function(){
            if(left_control_status){
                left_control_panel.panel('resize',{width:70});
                left_control_status=false;
                $(".theme-left-normal").hide();
                $(".theme-left-minimal").show();
            }else{
                left_control_panel.panel('resize',{width:200});
                left_control_status=true;
                $(".theme-left-normal").show();
                $(".theme-left-minimal").hide();
            }
            $("#master-layout").layout('resize', {width:'100%'})
        });

        /*右侧菜单控制结束*/





        $(".theme-navigate-user-modify").on("click",function(){
            $('.theme-navigate-user-panel').menu('hide');
            $.insdep.window({id:"personal-set-window",href:"user.html",title:"修改资料"});

        });
        //$.insdep.control("list.html");



        var cc1=$('#cc1').combo('panel');
        cc1.panel({cls:"theme-navigate-combobox-panel"});
        var cc2=$('#cc2').combo('panel');
        cc2.panel({cls:"theme-navigate-combobox-panel"});



        /*$("#open-layout").on("click",function(){
         var option = {
         "region":"west",
         "split":true,
         "title":"title",
         "width":180
         };
         $('#master-layout').layout('add', option);

         });*/


    });
    function doSearch(value,name){
        alert('You input: ' + value+'('+name+')');
    }

</script>

<!--第三方插件加载-->



<!--第三方插件加载结束-->

</body>
</html>
