<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2017/3/28
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<body>
<form action="/code/login" id="loginForm" method="post">
    用户名:<input type="text" name="username"/><br>
    验证码:<input type="text" name="imgcode"/><br>
    <img id="codeImg" src="/code/servlet"/><a id="reflush" href="https://www.baidu.com">看不清</a><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
<script src="http://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        reflushEvent();//看不清事件
        codeImgEvent();//验证码图片点击事件
        loginEvent();//登陆事件
    })
    
    function loginEvent() {
        $('[type="submit"]').bind('click',function (e) {
            e.preventDefault();
            $('#loginForm').submit();
        });
    }
    
    function reflushEvent() {
        $("#reflush").bind("click", function (e) {
            e.preventDefault();//阻止默认事件
            $("#codeImg").attr("src", "/code/servlet?temp" + new Date().getMilliseconds());
        })
    }
    
    function codeImgEvent() {
        $("#codeImg").bind("click", function (e) {
            e.preventDefault();//阻止默认事件
            $("#codeImg").attr("src", "/code/servlet?temp" + new Date().getMilliseconds());
        })
    }
</script>
