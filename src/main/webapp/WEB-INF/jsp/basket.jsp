<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setBundle basename="messages"/>

<head>
    <title><fmt:message key="link.user.basket"/></title>
    <style>
        .alert {
            padding: 20px;
            background-color: #1e6a96;
            color: white;
            text-align: center;
        }

        .closebtn {
            margin-left: 15px;
            color: white;
            font-weight: bold;
            float: right;
            font-size: 22px;
            line-height: 20px;
            cursor: pointer;
            transition: 0.3s;
        }

        .closebtn:hover {
            color: black;
        }
    </style>
</head>
<header>
    <jsp:include page="header.jsp"/>
</header>
<div class="container pt-4">
    <c:if test="${not empty message}">
        <div class="alert">
            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
            <h5><c:out value="${message}"/></h5>
        </div>
    </c:if>
    <h2><fmt:message key='header.current.booking'/></h2>
    <c:if test="${not empty sessionScope.cart}">
        <form method="post" action="/basket">
            <div class="card">
                <div class="card-body">
                    <c:if test="${not empty sessionScope.cart}">
                        <table class="table table-hover">
                            <thead>
                            <th scope="col"><fmt:message key='header.title'/></th>
                            <th scope="col"><fmt:message key='header.authors'/></th>
                            <th scope="col"><fmt:message key='header.isbn'/></th>
                            <th scope="col"><fmt:message key='header.year'/></th>
                            </thead>
                            <tbody>
                            <c:forEach var="book" items="${sessionScope.cart}">
                                <tr class="table-light">
                                    <td><c:out value="${book.title}"/></td>
                                    <td>
                                        <c:out value="${book.author.name}"/>
                                    </td>
                                    <td><c:out value="${book.isbn}"/></td>
                                    <td><c:out value="${book.year}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="row justify-content-end row-cols-auto">
                            <div class="col">
                                <input type="submit" class="btn btn-success shadow-lg " name="submit"
                                       value="<fmt:message key="header.finish.booking"/>"/>
                            </div>
                            <div class="col">
                                <input type="submit" class="btn btn-danger shadow-lg " name="cancel"
                                       value="<fmt:message key="header.cancel.booking"/>"/>
                            </div>
                        </div>

                    </c:if>
                </div>
            </div>
        </form>
    </c:if>
    <c:choose>
        <c:when test="${empty sessionScope.cart}">
            <div class="container pt-4 fw-bold"><h5><fmt:message key='message.no.current.booking'/></h5></div>
        </c:when>
    </c:choose>
</div>

<div class="container pt-4">
    <h2><fmt:message key='header.past.bookings'/></h2>
    <c:if test="${not empty bookings}">
        <div class="accordion pt-4" id="bookingAccordion">
            <div class="accordion-item HEADER">
                <h5 class="accordion-header" id="head">
                    <button class="accordion-button bg-secondary bg-gradient text-white">
                        <div class="container">
                            <div class="row fw-bold align-items-center">
                                <div class="col-3"><fmt:message key='header.created'/></div>
                                <div class="col-2"><fmt:message key='header.state'/></div>
                            </div>
                        </div>
                    </button>
                </h5>
            </div>
            <c:set var="k" value="-1"/>
            <c:forEach var="booking" items="${bookings}">
                <c:set var="k" value="${k+1}"/>
                <c:choose>
                    <c:when test='${booking.state eq "DELIVERED" and booking.located eq "USER"}'>
                        <c:set var="toggleClass" value="SUBSCRIPTION"/>
                    </c:when>
                    <c:when test="${booking.state eq 'DELIVERED' and booking.located eq 'LIBRARY'}">
                        <c:set var="toggleClass" value="READING_ROOM"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="toggleClass" value="${booking.state}"/>
                    </c:otherwise>
                </c:choose>
                <div class="accordion-item ${toggleClass}">
                    <h2 class="accordion-header" id="heading${booking.id}">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapse${booking.id}" aria-expanded="true"
                                aria-controls="collapse${booking.id}">
                            <div class="container">
                                <div class="row">
                                    <div class="col-3">
                                        <c:out value="${booking.modified.getTime()}"/>
                                    </div>
                                    <div class="col-2">
                                        <c:choose>
                                            <c:when test="${booking.state eq 'DELIVERED' and booking.located eq 'USER'}">
                                                <fmt:message key="header.booking.state.subscription"/>
                                            </c:when>
                                            <c:when test="${booking.state eq 'DELIVERED' and booking.located eq 'LIBRARY'}">
                                                <fmt:message key="header.booking.state.library"/>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:message key="header.booking.state.${booking.state}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </button>
                    </h2>
                    <div id="collapse${booking.id}" class="accordion-collapse collapse"
                         aria-labelledby="heading${booking.id}" data-bs-parent="#bookingAccordion">
                        <div class="accordion-body">
                            <c:if test="${not empty books}">
                                <div class="card">
                                    <div class="card-header">
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-hover">
                                            <thead>
                                            <th scope="col"><fmt:message key='header.title'/></th>
                                            <th scope="col"><fmt:message key='header.authors'/></th>
                                            <th scope="col"><fmt:message key='header.isbn'/></th>
                                            <th scope="col"><fmt:message key='header.year'/></th>
                                            </thead>
                                            <tbody>
                                            <tr class="table-light">
                                                <td><c:out value="${books.get(k).title}"/></td>
                                                <td><c:out value="${books.get(k).author.name}"/></td>
                                                <td><c:out value="${books.get(k).isbn}"/></td>
                                                <td><c:out value="${books.get(k).year}"/></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${empty bookings}">
        <div class="container pt-4 fw-bold"><h5><fmt:message key='message.no.past.booking'/></h5></div>
    </c:if>
</div>
