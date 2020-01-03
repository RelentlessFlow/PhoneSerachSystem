<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/static/css/bootstrap.min.css">
<link rel="stylesheet" href="/static/css/bootstrap-grid.min.css">
<script src="/static/js/jquery-3.4.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/index">归属地查询系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/index">主页<span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${sessionScope.qx.equals('0')}">
            <li class="nav-item">
                <a class="nav-link" href="/index/search">归属地查询<span class="sr-only">(current)</span></a>
            </li>
            </c:if>
            <c:if test="${sessionScope.qx.equals('1')}">
                <li class="nav-item">
                    <a class="nav-link" href="/manager/phone">归属地管理</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.qx.equals('1')}">
                <li class="nav-item">
                    <a class="nav-link" href="/manager/user">用户管理</a>
                </li>
            </c:if>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <c:choose>
            <c:when test="${sessionScope.username==null}">
                <li class="nav-item">
                    <a class="nav-link" href="/user/login">登陆</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${sessionScope.username}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/user/setting">账户设置</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/user/quit">退出登陆</a>
                    </div>
                </li>
            </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>


<c:if test="${result!=null}">
<div class="alert alert-warning" role="alert">
    ${result}
</div>
</c:if>
