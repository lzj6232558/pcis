<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>车险系统</title>

    <%--    <%@include file="/static/common/common.jsp" %>--%>
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
    <%--<link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">--%>

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
    <link href="/static/plugins/FontAwesomemaster/css/font-awesome.min.css" rel="stylesheet" type="text/css">


    <%--按钮样式--%>
    <link href="/static/css/buttons.css" rel="stylesheet" type="text/css">


    <%--日历css--%>
    <link rel="stylesheet" href="/static/css/calender.css">
    <style>#BAIDU_DSPUI_FLOWBAR, .adsbygoogle, .ad, div[class^="ad-widsget"], div[id^="div-gpt-ad-"], a[href*="cpro.baidu.com"], a[href*="@"][href*=".exe"], a[href*="/?/"][href*=".exe"], .adpushwin {
        display: none !important;
        max-width: 0 !important;
        max-height: 0 !important;
        overflow: hidden !important;
    }</style>
    <style>.tb960x90, .tb300x250, div[style*="300px"][style*="-15px"][style*="hidden"] {
        display: none !important;
        max-width: 0 !important;
        max-height: 0 !important;
        overflow: hidden !important;
    }</style>
    <style>.tb960x90 {
        display: none !important;
        max-width: 0 !important;
        max-height: 0 !important;
        overflow: hidden !important;
    }</style>
    <%--日历css--%>


    <script type="text/javascript" src="/static/plugins/myTheme/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/myTheme/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/myTheme/themes/insdep/jquery.insdep-extend.min.js"></script>
    <script type="text/javascript" src="/static/js/main2.js"></script>


</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">

    <div class="easyui-layout" data-options="region:'west',border:false" style="width:220px;">
        <!--开始左侧菜单-->
        <div data-options="region:'west',border:false,bodyCls:'theme-left-layout'" style="width:220px;">
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
                                    <img src="/static/plugins/jquery-easyui/themes/insdep/images/pjn.jpg" width="43"
                                         height="43">
                                </dt>
                                <dd>
                                    <b class="badge-prompt"><shiro:principal property="username"/> </b>
                                    <span><shiro:principal property="email"/> </span>
                                    <p><a href="/logout.do">注销</a><i class="text-success"> </i></p>
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
                                <ul id="customer"></ul>
                            </div>
                            <div title="信息管理">
                                <ul id="message"></ul>
                            </div>
                            <div title="保单管理">
                                <ul id="policy"></ul>
                            </div>
                            <div title="询价单管理">
                                <ul id="enquiry"></ul>
                            </div>
                            <div title="见费出单">
                                <ul id="fee"></ul>
                            </div>
                            <div title="团单管理">
                                <ul id="team"></ul>
                            </div>
                            <div title="理赔管理">
                                <ul id="claim"></ul>
                            </div>
                            <div title="员工管理">
                                <ul id="employee"></ul>
                            </div>
                            <div title="报表管理">
                                <ul id="data"></ul>
                            </div>
                            <div title="系统模块">
                                <ul id="system"></ul>
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

    </div>

    <div class="easyui-layout" data-options="region:'center',border:false">
        <div data-options="region:'north',height:55,border:false" style="background-color: #232323">
            <%--<div>
                <h1 align="center">
                    <font color="#f0f8ff">车险系统</font>
                </h1>
            </div>--%>
            <div>
                <a href="javascript:;" onclick="signOut()"
                   class="button button-action button-rounded"
                   style="float: right;font-size: 16px;Margin:6px;padding-left: 20px;padding-right: 20px;">签退</a>
                <a href="javascript:;" onclick="signIn()"
                   class="button button-action button-rounded"
                   style="float: right;font-size: 16px;Margin:6px;padding-left: 20px;padding-right: 20px;">签到</a>

            </div>
        </div>
        <div data-options="region: 'center',border:false">
            <div id="myTabs" class="easyui-tabs">
                <div title="主页">
                    <div>
                        <iframe allowtransparency="true" frameborder="0" width="385" height="75" scrolling="no"
                                src="//tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=0&d=3&bd=0&k=000000&f=&ltf=009944&htf=cc0000&q=1&e=1&c=54511&w=385&h=96&align=right"></iframe>
                    </div>
                    <%--日历插件开始--%>
                    <div id="calendar" style="float: left;border: 4px">
                        <header class="header">
                            <ul>
                                <li class="cur">月</li>
                                <li>年</li>
                            </ul>
                        </header>
                        <aside class="sidebar">
                            <div class="wrapper">
                                <div class="title">
                                    <span class="btn btn-prev"><</span>
                                    <span class="date">2017年9月</span>
                                    <span class="btn btn-next">></span>
                                </div>
                                <ul class="week">
                                    <li>日</li>
                                    <li>一</li>
                                    <li>二</li>
                                    <li>三</li>
                                    <li>四</li>
                                    <li>五</li>
                                    <li>六</li>
                                </ul>
                                <ul class="day"></ul>
                            </div>
                        </aside>
                        <div class="container">
                            <div id="renderMonth" class="render render-show">
                                <h2 class="title">2017年9月</h2>
                                <ul class="week">
                                    <li>周日</li>
                                    <li>周一</li>
                                    <li>周二</li>
                                    <li>周三</li>
                                    <li>周四</li>
                                    <li>周五</li>
                                    <li>周六</li>
                                </ul>
                                <ul class="day"></ul>
                            </div>
                            <div id="renderFullYear" class="render">
                                <div class="title">
                                    <h2>2017年</h2>
                                    <p>
                                        <span class="lunar-year"><i></i>丁酉鸡年</span>
                                        <span class="info"><i></i>农历初一</span>
                                    </p>
                                </div>
                                <ul class="month"></ul>
                            </div>
                            <div id="control">
                                <input type="button" value="<" class="btn btn-prev">
                                <input type="button" value="今天" class="today">
                                <input type="button" value=">" class="btn btn-next">
                            </div>
                            <div id="popup">
                                <h3 class="title">七夕节</h3>
                                <i class="arrow arrow-left"></i>
                                <i class="arrow arrow-right"></i>
                                <p class="date">2017年8月28日</p>
                                <p class="lunar">丁酉鸡年</p>
                            </div>
                        </div>
                    </div>
                    <script src="/static/plugins/calender/cal-tool.js"></script>
                    <script src="/static/plugins/calender/chineseCal.js"></script>
                    <script src="/static/plugins/calender/cal.js"></script>
                    <style>div[class^="tb"][class*="x"] {
                        display: none !important;
                        max-width: 0 !important;
                        max-height: 0 !important;
                        overflow: hidden !important;
                    }</style>
                    <%--                     日历插件结束             --%>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>



