<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<head>
    <title><fmt:message key="link.user.books"/></title>
</head>
<header>
    <jsp:include page="header.jsp"/>
</header>
<div class="container pt-4">
    <h2><fmt:message key='header.books.in.subscription'/></h2>
    <c:if test="${not empty books}">
        <div class="card">
            <div class="card-header">
            </div>
            <div class="card-body">
                <table class="table table-hover">
                    <thead>
                        <th scope="col"><fmt:message key='header.name'/></th>
                        <th scope="col"><fmt:message key='header.authors'/></th>
                        <th scope="col"><fmt:message key='header.isbn'/></th>
                        <th scope="col"><fmt:message key='header.year'/></th>
                        <th scope="col"><fmt:message key='header.return.up.to'/></th>
                    </thead>
                    <tbody>
                    <c:set var="k" value="-1"/>
                        <c:forEach var="book" items="${books}">
                            <c:set var="k" value="${k+1}"/>
                                <tr class="table-light">
                                    <td><c:out value="${book.title}"/></td>
                                    <td><c:out value="${book.author.name}"/></td>
                                    <td><c:out value="${book.isbn}"/></td>
                                    <td><c:out value="${book.year}"/></td>
                                    <td class="fw-bold">
                                        <c:out value="${bookings.get(k).modified.getTime()}"/>
                                    </td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
    <c:if test="${empty books}">
        <div class="container pt-4"><h5><fmt:message key='message.no.book.in.subscription'/></h5></div>
    </c:if>
</div>