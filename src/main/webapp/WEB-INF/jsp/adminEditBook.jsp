<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<head>
    <title><fmt:message key="header.edit"/></title>
</head>
<header>
    <jsp:include page="header.jsp"/>
</header>

<div class="container-sm bg-light border col-sm-6 col-sm-offset-3 my-5 pt-2">
    <div class="container-sm col-sm-10 col-sm-offset-1">
        <div class="row">
            <h1>
                <fmt:message key='header.edit'/>
            </h1>
        </div>
        <form:form method="post" action="${pageContext.request.contextPath}/editBook" modelAttribute="book">
            <div class="row mb-1">
                <label for="title" class="col-md-3 col-form-label"><fmt:message key='header.title'/>: </label>
                <div class="col-md-7">
                    <input name="title" type="text" id="title"
                           class="col-md-6 form-control" required
                    <c:if test="${not empty book}">
                           value="<c:out value='${book.title}' />"
                    </c:if>
                    >
                </div>
            </div>
            <div class="row mb-1">
                <label for="isbn" class="col-md-3 col-form-label"><fmt:message key='header.isbn'/>: </label>
                <div class="col-md-7">
                    <input name="isbn" type="text" id="isbn"
                           class="col-md-6 form-control" required
                    <c:if test="${not empty book}">
                           value="<c:out value='${book.isbn}' />"
                    </c:if>
                    >
                </div>
            </div>
            <div class="row mb-2">
                <label for="year" class="col-md-3 col-form-label"><fmt:message key='header.year'/>: </label>
                <div class="col-md-7">
                    <input name="year" type="number" id="year" min=1900 max="${thisYear}"
                           class="form-control"
                    <c:if test="${not empty book}">
                           value="<c:out value='${book.year}' />"
                    </c:if>
                           required>
                </div>
            </div>
            <div class="row mb-2">
                <label for="langCode" class="col-md-3 col-form-label"><fmt:message key='link.language'/>: </label>
                <div class="col-md-7">
                    <input name="langCode" type="text" id="langCode" class="form-control"
                    <c:if test="${not empty book}">
                           value="<c:out value='${book.langCode}' />"
                    </c:if>
                           required>
                </div>
            </div>
            <div class="row mb-2">
                <label for="keepPeriod" class="col-md-3 col-form-label"><fmt:message
                        key='header.keep.period'/>: </label>
                <div class="col-md-7">
                    <input name="keepPeriod" type="number" min="1" max="365" id="keepPeriod"
                           class="form-control"
                    <c:if test="${not empty book}">
                           value="<c:out value='${book.keepPeriod}' />"
                    </c:if>
                           required>
                </div>
            </div>
            <div class="row mb-2 my-2">
                <div class="col-sm container overflow-hidden">
                    <button type="submit" class="btn btn-primary"><fmt:message key='header.apply'/></button>
                    <a class="btn btn-danger" href="${cancelLink}"><fmt:message key='header.cancel'/></a>
                </div>
            </div>
        </form:form>
    </div>
</div>