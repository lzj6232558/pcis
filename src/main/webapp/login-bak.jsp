<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>登陆</title>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <%-- 样式 --%>
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
    </script>
</head>
<body>
<div class="log-w3">
        <form id="login_form" action="login.do" method="post">
            <br>
            <input type="text" class="ggg" name="username" placeholder="用户名" required value="admin">
            <br>
            <input type="password" class="ggg" name="password" placeholder="密码" required value="1">
            <br>
            <input type="button" value="上车" onclick="input()">
        </form>
    </div>
</div>
</body>
</html>