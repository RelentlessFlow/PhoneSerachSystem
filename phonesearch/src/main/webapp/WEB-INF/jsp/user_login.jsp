<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/1/1
  Time: 9:31 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆</title>
</head>
<body>

<form method="post" action="/user/login">
    <%@ include file="header.jsp" %>
    <div class="container">
        <div class="alert alert-primary mt-4" role="alert">
            如果您想要注册我们的账号，可以直接输入用户名和密码，我们将为您自动注册一个账号！
        </div>
        <div class="alert alert-info mt-4" role="alert">
            注：用户名将作为您的唯一标示，请牢记改用户名
        </div>

        <div class="form-group">
            <label for="number">请输入用户名</label>
            <input name="username" type="password" class="form-control" id="number" placeholder="用户名">
        </div>
        <div class="form-group">
            <label for="password">请输入密码</label>
            <input name="password" type="password" class="form-control" id="password" placeholder="密码">
        </div>
        <button type="submit" class="btn btn-primary">登陆</button>
    </div>
</form>
</body>
</html>
