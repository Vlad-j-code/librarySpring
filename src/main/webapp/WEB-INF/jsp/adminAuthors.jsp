<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setBundle basename="messages"/>

<head>
    <title><fmt:message key="header.authors"/></title>
</head>
<header>
    <jsp:include page="header.jsp"/>
</header>
<div class="container">
    <br>
    <div style="text-align: right">
        <a class="btn btn-primary" href="/createAuthor"><fmt:message key="header.create.author"/></a>
    </div>
    <div class="container pt-4">
        <c:if test="${not empty authors}">
            <c:choose>
                <c:when test="${authors.totalPages > 0}">
                    <table class="table table-striped table-hover table-dark">
                        <thead class="text-success">
                        <th scope="col"><fmt:message key='header.name'/></th>
<%--                        <th scope="col"><fmt:message key='header.action'/></th>--%>
<%--                        <th scope="col"><fmt:message key='header.delete'/></th>--%>
                        </thead>
                        <tbody>
                        <c:forEach var="author" items="${authors.content}">
                            <tr>
                                <td><c:out value="${author.name}"/></td>
<%--                                <td>--%>
<%--                                    <c:if test="${user.role != 'ADMIN'}">--%>
<%--                                        <form:form method="post" action="/users{email}" modelAttribute="user">--%>
<%--                                            <form:input type="hidden" path="email" value="${user.email}"/>--%>
<%--                                            <c:if test="${user.active == 1}">--%>
<%--                                                <form:button type="submit" name="block"--%>
<%--                                                             class="btn btn-danger shadow-lg "><fmt:message--%>
<%--                                                        key="header.block"/></form:button>--%>
<%--                                            </c:if>--%>
<%--                                            <c:if test="${user.active == 0}">--%>
<%--                                                <form:button type="submit" name="block"--%>
<%--                                                             class="btn btn-success shadow-lg "><fmt:message--%>
<%--                                                        key="header.enable"/></form:button>--%>
<%--                                            </c:if>--%>
<%--                                        </form:form>--%>
<%--                                    </c:if>--%>
<%--                                </td>--%>
<%--                                <td>--%>
<%--                                    <c:if test="${user.role == 'LIBRARIAN'}">--%>
<%--                                        <form:form method="post" action="/users{email}" modelAttribute="user">--%>
<%--                                            <form:input type="hidden" path="email" value="${user.email}"/>--%>
<%--                                            <form:button type="submit" name="delete"--%>
<%--                                                         class="btn btn-danger shadow-lg "><fmt:message--%>
<%--                                                    key="header.delete"/></form:button>--%>
<%--                                        </form:form>--%>
<%--                                    </c:if>--%>
<%--                                </td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <h5 style="text-align: center">No authors Found... Search again!</h5>
                </c:otherwise>
            </c:choose>
            <div style="float: right">
                <c:if test="${authors.totalPages > 0}">
                    <nav aria-label="Page navigation example" style="margin:auto;">
                        <ul class="pagination">
                            <c:set var="prev" value="0"/>
                            <c:if test="${param.page > 0}">
                                <c:set var="prev" value="${param.page -1}"/>
                            </c:if>
                            <c:if test="${authors.totalPages > 0}">
                                <li class='page-item <c:if test="${empty param.page || param.page eq 0}">disabled</c:if>'>
                                    <a class="page-link" href="/authors?page=${prev}&size=${maxTraySize}">Prev</a></li>
                            </c:if>
                            <c:forEach var="i" begin="0" end="${authors.totalPages -1}">
                                <li class='page-item <c:if test="${param.page eq i || (empty param.page && i eq 0)}">active</c:if>'>
                                    <a class="page-link" href="/authors?page=${i}&size=${maxTraySize}">${i+1}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${authors.totalPages > 0}">
                                <li class='page-item <c:if test="${authors.totalPages <= (param.page + 1)}">disabled</c:if>'>
                                    <a class="page-link"
                                       href="/authors?page=${param.page + 1}&size=${maxTraySize}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </c:if>
                <input type="hidden" name="currentPage" value="${currentPage}" id="currentPageNo">
            </div>
        </c:if>
    </div>
</div>