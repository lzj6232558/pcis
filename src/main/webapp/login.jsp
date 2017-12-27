<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>车险管理系统</title>
    <link rel="stylesheet" href="/static/css/login-style.css">
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript">
        function input() {
            $.post('/login.do', $("form").serialize(), function (data) {
                if (data.success) {
                    window.parent.location.href = "/main.do"
                } else {
                    alert(data.message);
                }
            }, "json");
        }

        function reset() {
            $("#username").text('');
            $("#password").val('');
        }
    </script>
</head>
<body>
<section class="container">
    <div class="login">
        <h1>用户登录</h1>
        <form id="form" method="post">
            <p><input id="username" type="text" name="username" value="admin" placeholder="账号"></p>
            <p><input id="password" type="password" name="password" value="" placeholder="密码"
                      onkeydown='if(event.keyCode==13){input();}'></p>
            <p class="submit">
                <input type="button" value="登录" onclick="input()">
                <input type="button" value="重置" onclick="reset()">
            </p>
        </form>
    </div>
</section>
</body>
</html>