<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>归属地查询</title>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
    <div class="jumbotron" style="margin-top: 2rem">
        <h1 class="display-4">查询手机号归属地</h1>
        <p class="lead">请输入您要查询手机号的前七位</p>
        <hr class="my-4">
        <p>Support On SpringBoot or JSP！</p>
        <form method="post" action="/phone/search" class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" maxlength="7" type="search" name="num" placeholder="请输入手机号前七位"
                   aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查找</button>
        </form>
        <c:if test="${sessionScope.qx==1}">
            <button type="button" class="btn btn-primary ml-3 mt-3" data-toggle="modal" data-target="#exampleModal"
                    data-whatever="@mdo">添加信息
            </button>

            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">添加归属地信息</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="/manager/addphone" method="post">
                                <div class="form-group">
                                    <label for="num" class="col-form-label">手机号：</label>
                                    <input maxlength="7" type="tel" class="form-control" id="num" name="num">
                                </div>
                                <div class="form-group">
                                    <label for="city" class="col-form-label">归属地：</label>
                                    <input type="text" class="form-control" id="city" name="city">
                                </div>
                                <div class="form-group">
                                    <label for="cardtype" class="col-form-label">手机卡类型：</label>
                                    <input type="text" class="form-control" id="cardtype" name="cardtype">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                                    <button type="submit" class="btn btn-primary">提交</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>


    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">手机号</th>
            <th scope="col">归属地</th>
            <th scope="col">手机卡类型</th>
            <c:if test="${sessionScope.qx==1}">
                <th scope="col">操作</th>
            </c:if>
        </tr>
        </thead>
        <tbody>

        <%--用户或者管理员查询--%>
        <c:if test="${sessionScope.qx==0||sessionScope.qx==1}">
            <c:choose>
                <c:when test="${lists.size()==0}">
                    <div class="alert alert-danger" role="alert">
                        查找结果为空或者输入不正确
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${lists}" var="listed">
                        <tr>
                            <td>${listed.id}</td>
                            <td>${listed.num}</td>
                            <td>${listed.city}</td>
                            <td>${listed.cardType}</td>
                                <%--管理员操作--%>
                            <c:if test="${sessionScope.qx==1}">
                                <td><a class="text-info" href="#">编辑</a>&nbsp;&nbsp;&nbsp;<a class="text-danger"
                                                                                             href="/manager/delphone?num=${listed.num}">删除</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:if>


        <%--管理员查询--%>
        <c:if test="${pageInfo!=null}">
            <c:if test="${sessionScope.qx==1}">

                <c:choose>
                    <c:when test="${mobiles.size()==0}">
                        <div class="alert alert-danger" role="alert">
                            查找结果为空或者输入不正确
                        </div>
                    </c:when>

                    <c:otherwise>
                        总计：${pageInfo.total}条数据 ${pageInfo.pages}页数据）
                        <span class="pl-5">当前页码：</span>
                        <form action="/manager/phone"><input name="pagenum" type="number"
                                                             placeholder="${pageInfo.pageNum}"></form>
                        <c:forEach items="${pageInfo.list}" var="obj">
                            <tr>
                                <td>${obj.id}</td>
                                <td>${obj.num}</td>
                                <td>${obj.city}</td>
                                <td>${obj.cardType}</td>
                                <td>
                                    <a class="text-info update_btn" href="/manager/updatephone?num=${obj.num}">编辑</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a class="text-danger" href="/manager/delphone?num=${obj.num}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </c:if>
        </tbody>
    </table>


    <%--    页码：管理员--%>
    <c:if test="${pageInfo!=null}">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${pageInfo.hasPreviousPage}">

                    <li class="page-item"><a class="page-link" href="/manager/phone?pagenum=${pageInfo.prePage}"><</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/manager/phone?pagenum=1">首页</a></li>
                    <c:if test="${pageInfo.pageNum>=11}">
                        <li class="page-item"><a class="page-link"
                                                 href="/manager/phone?pagenum=${pageInfo.pageNum-10}">${pageInfo.pageNum-10}</a>
                        </li>
                        </li>
                    </c:if>
                    <li class="page-item"><a class="page-link"
                                             href="/manager/phone?pagenum=${pageInfo.prePage}">${pageInfo.prePage}</a>
                    </li>
                    </li>
                </c:if>

                <li class="page-item active" aria-current="page"><span class="page-link">${pageInfo.pageNum}<span
                        class="sr-only">(current)</span></span></li>
                <c:if test="${pageInfo.hasNextPage}">
                    <li class="page-item">
                    <c:forEach var="item" varStatus="status" begin="1" end="10">
                        <li class="page-item"><a class="page-link"
                                                 href="/manager/phone?pagenum=${pageInfo.nextPage+status.index-1}">${pageInfo.pageNum+status.index}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item"><a class="page-link"
                                             href="/manager/phone?pagenum=${pageInfo.pageNum+20}">${pageInfo.pageNum+20}</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="/manager/phone?pagenum=${pageInfo.pages}">${pageInfo.pages}</a>
                    </li>
                    <a class="page-link" href="/manager/phone?pagenum=${pageInfo.nextPage}">></a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>

</div>
</body>
</html>
