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
<form action="/manager/updateuser" method="post" class="mt-5">
    <div class="container">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input value="${users.username}" name="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input value="${users.password}" name="password" type="password" class="form-control" id="password" placeholder="请输入密码" max="10">
        </div>
        <div class="form-group">
            <label for="age">年龄:</label>
            <input value="${users.age}" name="age" type="text" class="form-control" id="age" placeholder="请输入年龄" maxlength="2">
        </div>
        <div class="form-group">
            <label for="number">手机号:</label>
            <input value="${users.number}" name="number" type="text" class="form-control" id="number" placeholder="请输入手机号码" maxlength="11">
        </div>
        <div class="form-group">
                <label for="qx">权限信息:</label>
            <input value="${users.qx}" name="qx" type="text" class="form-control" id="qx" placeholder="请输入权限信息，1或0" maxlength="11">
        </div>


        <button type="submit" class="btn btn-primary">修改</button>
        <a href="/index">取消</a>
    </div>
</form>
</div>
</body>
</html>
