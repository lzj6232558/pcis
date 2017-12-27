
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Designed by Pinwheel.Group</title>

<!-- for-mobile-apps -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Coming Soon Widget Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!-- //for-mobile-apps -->
<link href='//fonts.googleapis.com/css?family=Audiowide' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<link href="css/jquery.classycountdown.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

	<link rel="stylesheet" href="/static/css/plugins/bootstrap.min.css">
	<link rel="stylesheet" href="/static/css/plugins/animate.min.css">
	<link rel="stylesheet" href="/static/css/plugins/font-awesome.min.css">
	<link rel="stylesheet" href="/static/css/plugins/vegas.min.css">
	<%--<link rel="stylesheet" href="/static/css/plugins/reset.css">--%>
	<link rel="stylesheet" href="/static/css/plugins/style.css">
	<link rel="stylesheet" href="/static/css/plugins/mobile.css">

	<link href="/static/plugins/myTheme/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
	<link href="/static/plugins/myTheme/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
	<link href="/static/plugins/myTheme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
	<link href="/static/plugins/myTheme/themes/insdep/icon.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/static/js/index.js"></script>

	<link rel="stylesheet" href="/static/model/css/font-awesome.css">
	<link rel="stylesheet" href="/static/model/css/font-awesome.min.css">

	<style type="text/css">
		h1.gradient {
			font-weight: bold;
			font-family: helvetica;
			text-align:center;
			/*background: -webkit-linear-gradient(left, hotpink , #0c80d7);*/
			background: -webkit-linear-gradient(left, #aa0d91 , #ff2222);    /* 背景色渐变 */
			-webkit-background-clip: text;         /* 规定背景的划分区域 */
			-webkit-text-fill-color: transparent;  /* 防止字体颜色覆盖 */
		}
	</style>
</head>
<body>
	<div class="content">
		<h1 class="gradient" style="margin-top: -80px;margin-left: -200px;font-style: italic;">大风车车险服务</h1>
		<div class="col-md-6">
			<div class="service-post wow fadeInUp" data-wow-delay="0.7s">
				<br><br><br>
				<i style="margin-left: 50px" class="fa fa-check"></i>
				<h2 style="margin-left: 100px"><span class="main-color">function </span> module</h2>
				<br/>
				<br/>
				<p>
					<input style="margin-left: 40px" type="submit" class="submit" id="sigin" value="快速签到" onclick="onduty()"/>
					<input style="margin-left: 20px" type="submit" class="submit" id="sigout" value="快速签退" onclick="offduty()"/>
				</p>
				<br/>
				<p>
					<input style="margin-left: 40px" type="submit" id="date" class="submit" value="车险计算"/>
					<input style="margin-left: 20px" type="submit" id="message" class="submit" value="今日面板"/>
				</p>
				<br/>
				<p>
					<input style="margin-left: 40px" type="submit" id="saleSccountChartByPie" class="submit" value="数据备份"/>
					<input style="margin-left: 20px" type="submit" id="saleAccountLine" class="submit" value="数据恢复"/>
				</p>
				<br/>
				<br/>
				<ul class="social-list">
					<li style="margin-left: 100px" class="wow zoomIn" data-wow-delay="0.6s"><a href=""><i class="fa fa-apple"></i></a></li>
					<li class="wow zoomIn" data-wow-delay="0.4s"><a href="https://mp.qq.com/ex/portal/login"><i class="fa fa-qq"></i></a></li>
					<li class="wow zoomIn" data-wow-delay="0.9s"><a href="https://wx.qq.com/"><i class="fa fa-weixin"></i></a></li>
					<li class="wow zoomIn" data-wow-delay="0.8s"><a href="https://passport.weibo.cn/signin/login"><i class="fa fa-weibo"></i></a></li>
					<li class="wow zoomIn" data-wow-delay="0.7s"><a href=""><i class="fa fa-git"></i></a></li>
				</ul>
			</div>
		</div>


		<script type="text/javascript">
			$("#message").click(function () {
				window.open("/static/model/text.jsp", "add",
				'height=430, width=1100, top=10, ' +
				'left=100,right=200,toolbar=no,menubar=no,' +
				'scrollbars=yes,resizable=no,location=no, status=no');
			})

			$("#date").click(function () {
				window.open('http://www.ylqlife.cn', "add",
				'height=650, width=1100, top=10, ' +
				'left=100,right=200,toolbar=no,menubar=no,' +
				'scrollbars=yes,resizable=no,location=no, status=no');
			});
		</script>

		<%--<div class="col-md-6">

			<div class="service-post wow fadeInUp" data-wow-delay="0.7s">
				<i class="fa fa-check"></i>
				<h2><span class="main-color">function</span>module</h2>
				<br><br>
				<p>
					<input style="margin-left: -40px" type="submit" class="submit" id="sigin" value="快速签到"/>
				</p>
				<p>
					<input style="margin-left: 100px" type="submit" class="submit" id="sigout" value="快速签退"/>
				</p>
				<p>
					<input style="margin-left: 100px" type="submit" id="date" class="submit" value="行程安排"/>
				</p>
				<p>
					<input style="margin-left: -40px" type="submit" id="message" class="submit" value="公告面板"/>
				</p>
				<p>
					<input style="margin-left: -40px" type="submit" id="saleSccountChartByPie" class="submit" value="一饼状图"/>
				</p>
				<p>
					<input style="margin-left: -40px" type="submit" id="saleAccountLine" class="submit" value="一线形图"/>
				</p>
			</div>

		</div>--%>
		<%--<p>
			<input style="color: whitesmoke;float: left;margin-left: 20px;margin-top: -140px;
				width: 80px;height: 35px;font-size: 20px;font-style: inherit;
				font-family: 楷体;background-color: transparent;border-color: #00bbee;
				cursor: pointer" type="button" onclick="onduty()" value="签到">
		</p>
		<p>
			<input style="margin-top: -100px;margin-left: 20px;color: whitesmoke;float: left;
				width: 80px;height: 35px;font-size: 20px;font-style: inherit;
				font-family: 楷体;background-color: transparent;border-color: #00bbee;
				cursor: pointer" type="button" onclick="offduty()" value="签退">
		</p>
		<p>
			<input style="margin-top: -60px;margin-left: 20px;color: whitesmoke;float: left;
				width: 80px;height: 35px;font-size: 20px;font-style: inherit;
				font-family: 楷体;background-color: transparent;border-color: #00bbee;
				cursor: pointer" type="button" onclick="" value="备份">
		</p>
		<p>
			<input style="margin-top: -20px;margin-left: 20px;color: whitesmoke;float: left;
				width: 80px;height: 35px;font-size: 20px;font-style: inherit;
				font-family: 楷体;background-color: transparent;border-color: #00bbee;
				cursor: pointer" type="button" onclick="" value="恢复">
		</p>
		<p>
			<input style="margin-top: 20px;margin-left: -80px;color: whitesmoke;float: left;
				width: 80px;height: 35px;font-size: 20px;font-style: inherit;
				font-family: 楷体;background-color: transparent;border-color: #00bbee;
				cursor: pointer" type="button" onclick="" value="公告">
		</p>
		<p>
			<input style="margin-top: 60px;margin-left: -80px;color: whitesmoke;float: left;
				width: 80px;height: 35px;font-size: 20px;font-style: inherit;
				font-family: 楷体;background-color: transparent;border-color: #00bbee;
				cursor: pointer" type="button" onclick="" value="行程">
		</p>--%>


			<div class="main">
				<div id="countdown9" class="ClassyCountdownDemo"></div>

				<div class="subscribe">
					<h2 style="float: left;margin-left: -100px">Pinwheel</h2>
					<div class="contact-form">
						<form action="#" method="post">
							<input type="email" value="Email" name="Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" required="">
							<input type="submit" style="margin-top: 0px" value="Subscribe">
						</form>
					</div>
				</div>
			</div>
		<p class="copy_rights">&copy; 2017 Come from Pinwheel.Group | Design by  <a href="http://w3layouts.com/"> Pinwheel</a></p>
	</div>

	<%--<script src="js/jquery.min.js"></script>--%>
				<script src="js/jquery.knob.js"></script>
				<script src="js/jquery.throttle.js"></script>
				<script src="js/jquery.classycountdown.js"></script>
									<script>
                                        $(document).ready(function() {
                                            $('#countdown9').ClassyCountdown({
                                                end: '1388468325',
                                                now: '1380501323',
                                                labels: true,
                                                style: {
                                                    element: "",
                                                    textResponsive: .5,
                                                    days: {
                                                        gauge: {
                                                            thickness: .10,
                                                            bgColor: "rgba(0,0,0,0)",
                                                            fgColor: "#1abc9c",
                                                            lineCap: 'round'
                                                        },
                                                        textCSS: 'font-weight:300; color:#fff;'
                                                    },
                                                    hours: {
                                                        gauge: {
                                                            thickness: .10,
                                                            bgColor: "rgba(0,0,0,0)",
                                                            fgColor: "#05BEF6",
                                                            lineCap: 'round'
                                                        },
                                                        textCSS: ' font-weight:300; color:#fff;'
                                                    },
                                                    minutes: {
                                                        gauge: {
                                                            thickness: .10,
                                                            bgColor: "rgba(0,0,0,0)",
                                                            fgColor: "#8e44ad",
                                                            lineCap: 'round'
                                                        },
                                                        textCSS: ' font-weight:300; color:#fff;'
                                                    },
                                                    seconds: {
                                                        gauge: {
                                                            thickness: .10,
                                                            bgColor: "rgba(0,0,0,0)",
                                                            fgColor: "#f39c12",
                                                            lineCap: 'round'
                                                        },
                                                        textCSS: ' font-weight:300; color:#fff;'
                                                    }

                                                },
                                                onEndCallback: function() {
                                                    console.log("Time out!");
                                                }
                                            });
                                        });
                                    </script>
</body>

</html>
