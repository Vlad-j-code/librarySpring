<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setBundle basename="messages"/>

<head>
    <title>Booking</title>
</head>
<header>
    <jsp:include page="header.jsp"/>
</header>
<c:if test="${not empty bookings}">
    <div class="accordion pt-4" id="bookingAccordion">
        <div class="accordion-item HEADER">
            <h5 class="accordion-header" id="head">
                <button class="accordion-button bg-secondary bg-gradient text-white">
                    <div class="container">
                        <div class="row fw-bold align-items-center">
                            <div class="col-3"><fmt:message key='header.created'/></div>
                            <div class="col-2"><fmt:message key='header.state'/></div>
                            <c:if test="${pageContext.request.userPrincipal.name eq 'librarian@gmail.com'}">
                                <div class="col-2"><fmt:message key='header.email'/></div>
                                <div class="col-2"><fmt:message key='header.name'/></div>
                            </c:if>
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
                                <c:if test="${pageContext.request.userPrincipal.name eq 'librarian@gmail.com'}">
                                    <div class="col-2"><c:out value="${booking.user.email}"/></div>
                                    <div class="col-2"><c:out value="${booking.user.name}" default="none"/></div>
                                </c:if>
                            </div>
                        </div>
                    </button>
                </h2>
                <div id="collapse${booking.id}" class="accordion-collapse collapse"
                     aria-labelledby="heading${booking.id}" data-bs-parent="#bookingAccordion">
                    <div class="accordion-body">
                        <c:if test="${not empty reservedBooks}">
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
                                            <td><c:out value="${reservedBooks.get(k).title}"/></td>
                                            <td><c:out value="${reservedBooks.get(k).author.name}"/></td>
                                            <td><c:out value="${reservedBooks.get(k).isbn}"/></td>
                                            <td><c:out value="${reservedBooks.get(k).year}"/></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>
                        <div class="container pt-2">
                            <div class="row justify-content-end row-cols-auto">
                                <c:if test="${booking.state eq 'BOOKED'}">
                                    <div class="col">
                                        <form:form method="post" action="/booking{id}" modelAttribute="booking">
                                            <form:input type="hidden" path="id" value="${booking.id}"/>
                                            <form:button type="submit" name="subscribe" class="btn btn-primary">
                                                <fmt:message key='header.subscription'/></form:button>
                                        </form:form>
                                    </div>
                                    <div class="col">
                                        <form:form method="post" action="/booking{id}" modelAttribute="booking">
                                            <form:input type="hidden" path="id" value="${booking.id}"/>
                                            <form:button type="submit" name="room" class="btn btn-secondary">
                                                <fmt:message key='header.in.house'/></form:button>
                                        </form:form>
                                    </div>
                                    <div class="col">
                                        <form:form method="post" action="/booking{id}" modelAttribute="booking">
                                            <form:input type="hidden" path="id" value="${booking.id}"/>
                                            <form:button type="submit" name="cancel" class="btn btn-danger">
                                                <fmt:message key='header.cancel.booking'/></form:button>
                                        </form:form>
                                    </div>
                                </c:if>
                                <c:if test="${booking.state eq 'DELIVERED'}">
                                    <div class="col">
                                        <form:form method="post" action="/booking{id}" modelAttribute="booking">
                                            <form:input type="hidden" path="id" value="${booking.id}"/>
                                            <form:button type="submit" name="finish" class="btn btn-primary">
                                                <fmt:message key='header.done'/></form:button>
                                        </form:form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>