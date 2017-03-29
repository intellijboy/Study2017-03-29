<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2017/3/28
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jcaptcha测试</title>
</head>
<body>
    <form>
        <input type="text" id="captcha" name="captcha" maxlength="4" />
        <img src="/third/captcha" onclick="this.src='/third/captcha?d='+new Date().getMilliseconds()"/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
