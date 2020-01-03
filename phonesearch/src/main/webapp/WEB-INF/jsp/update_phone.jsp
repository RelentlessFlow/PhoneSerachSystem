<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/1/3
  Time: 3:32 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
<form class="mt-5" action="/manager/updatephone" method="post">
<c:forEach items="${lists}" var="listed">
    <div class="form-group">
        <label for="ID">ID</label>
        <input class="form-control" id="ID" value="${listed.id}" name="id" readonly>
    </div>
    <div class="form-group">
        <label for="phonenum">手机号</label>
        <input type="tel" maxlength="7" class="form-control" id="phonenum" value="${listed.num}" name="num">
    </div>
    <div class="form-group">
        <label for="phonecity">归属地</label>
        <input type="text" class="form-control" id="phonecity" value="${listed.city}" name="city">
    </div>
    <div class="form-group">
        <label for="cardtype">手机卡类型</label>
        <input type="text" class="form-control" id="cardtype" value="${listed.cardType}" name="cardtype">
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</c:forEach>

</form>
</div>
</body>
</html>
