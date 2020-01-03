<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/1/1
  Time: 1:26 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自定义账号</title>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
<form action="/user/setting" method="post" class="mt-5">
    <div class="container">
        <div class="form-group">
            <label for="password">请输入密码</label>
            <input value="${users.password}" name="password" type="password" class="form-control" id="password" placeholder="密码" max="10">
        </div>
        <div class="form-group">
            <label for="age">请输入年龄</label>
            <input value="${users.age}" name="age" type="text" class="form-control" id="age" placeholder="年龄" maxlength="2">
        </div>
        <div class="form-group">
            <label for="number">请输入手机号</label>
            <input value="${users.number}" name="number" type="text" class="form-control" id="number" placeholder="手机号码" maxlength="11">
        </div>

        <button type="submit" class="btn btn-primary">修改</button>
        <a href="/index">取消</a>
    </div>
</form>
</div>
</body>
</html>
