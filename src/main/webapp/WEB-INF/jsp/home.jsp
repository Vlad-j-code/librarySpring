<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setBundle basename="messages"/>

<head>
    <title><fmt:message key="link.home"/></title>
</head>
<header>
    <jsp:include page="header.jsp"/>
</header>

<div class="container">
    <c:if test="${not empty user and user.role eq 'ADMIN'}">
        <br>
        <div style="text-align: right">
            <a class="btn btn-primary" href="/chooseAuthor"><fmt:message key="header.create.book"/></a>
        </div>
    </c:if>
    <div class="container pt-4">
        <table class="table table-striped table-hover table-dark">
            <thead class="text-success">
            <tr>
                <th><fmt:message key='header.title'/></th>
                <th><fmt:message key='header.authors'/></th>
                <th><fmt:message key='header.isbn'/></th>
                <th><fmt:message key='header.year'/></th>
                <c:if test="${not empty user and user.role eq 'USER'}">
                    <th scope="col"><fmt:message key='header.in.stock'/></th>
                    <th scope="col"><fmt:message key='header.action'/></th>
                </c:if>
                <c:if test="${not empty user and user.role eq 'ADMIN'}">
                    <th scope="col"><fmt:message key='header.keep.period'/></th>
                    <th scope="col"><fmt:message key='header.total.amount'/></th>
                    <th scope="col"><fmt:message key='header.in.stock'/></th>
                    <th scope="col"><fmt:message key='header.reserved'/></th>
                    <th scope="col"><fmt:message key='header.was.booked'/></th>
                    <th scope="col"><fmt:message key='header.action'/></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td><c:out value="${book.title}"/></td>
                    <td>
                        <c:out value="${book.author.name}"/>
                    </td>
                    <td><c:out value="${book.isbn}"/></td>
                    <td><c:out value="${book.year}"/></td>
                    <c:if test="${not empty user and user.role eq 'USER'}">
                        <c:set var="availableAmount" value="${bookStats.stream().filter(item -> item.getBookId().equals(book.id)).findFirst().get().getInStock()
                - bookStats.stream().filter(item -> item.getBookId().equals(book.id)).findFirst().get().getReserved()}"/>
                        <td>
                            <c:out value="${availableAmount}"/>
                        </td>
                        <td>
                            <c:set var="bookActionState" value="false"/>
                            <c:if test="${not empty sessionScope.cart and sessionScope.cart.contains(book)}">
                                <c:set var="bookActionState" value="true"/>
                            </c:if>
                            <c:if test="${availableAmount <= 0}">
                                <c:set var="bookActionState" value="true"/>
                            </c:if>
                            <form:form method="post" action="/{id}" modelAttribute="book">
                                <form:input type="hidden" path="id" value="${book.id}"/>
                                <form:button type="submit" name="addition"
                                             class="btn btn-primary" disabled="${bookActionState}">
                                    <fmt:message key="header.book"/></form:button>
                            </form:form>
                        </td>
                    </c:if>
                    <c:if test="${not empty user and user.role eq 'ADMIN'}">
                        <td><c:out value="${book.keepPeriod}"/></td>
                        <td><c:out
                                value="${bookStats.stream().filter(item -> item.getBookId().equals(book.id)).findFirst().get().getTotal()}"/></td>
                        <td><c:out
                                value="${bookStats.stream().filter(item -> item.getBookId().equals(book.id)).findFirst().get().getInStock()}"/></td>
                        <td><c:out
                                value="${bookStats.stream().filter(item -> item.getBookId().equals(book.id)).findFirst().get().getReserved()}"/></td>
                        <td><c:out
                                value="${bookStats.stream().filter(item -> item.getBookId().equals(book.id)).findFirst().get().getTimesWasBooked()}"/></td>
                        <td>
                            <div class="container">
                                <div class="row justify-content-start">
                                    <div class="col-auto">
                                        <form:form method="post" action="/" modelAttribute="book">
                                            <form:input type="hidden" path="id" value="${book.id}"/>
                                            <form:button type="submit" name="edit"
                                                         class="btn btn-primary">
                                                <fmt:message key="header.edit"/></form:button>
                                        </form:form>
                                    </div>
                                    <div class="col-auto">
                                            <%--                                <form action="/controller" method="post">--%>
                                            <%--                                    <input type="hidden" name="command" value="book.delete">--%>
                                            <%--                                    <input type="hidden" name="id" value="${book.id}">--%>
                                            <%--                                    <button type="submit" class="btn btn-danger">--%>
                                            <%--                                        <i class="bi bi-trash"></i>--%>
                                            <%--                                    </button>--%>
                                            <%--                                </form>--%>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>