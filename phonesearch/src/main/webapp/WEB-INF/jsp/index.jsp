<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<%@ include file="header.jsp" %>


<div class="container mt-4">

    <div class="jumbotron" style="margin-top: 2rem">
        <h1 class="display-4">欢迎您使用归属地查询系统</h1>
        <p class="lead">您可以使用以下功能：</p>
        <hr class="my-4">
        <p>Support on SpringBoot and JSP！</p>
        <c:if test="${sessionScope.qx==1}">
            <div class="ml-2">
                <a href="/manager/phone">
                    <button type="button" class="btn btn-primary btn-lg">归属地管理</button>
                </a>
                <button type="button" class="btn btn-secondary btn-lg">用户管理</button>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.username==null}"><a class="btn btn-primary btn-lg" href="/user/login"
                                                             role="button">游客请登录</a></c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

        <c:if test="${sessionScope.qx==0}">

        <a class="btn btn-primary btn-lg" href="/index/search" role="button">查询</a>
    </div>
    </c:if>
</div>


</body>
</html>
