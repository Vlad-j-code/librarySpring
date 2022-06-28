<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<head>
    <title><fmt:message key='header.create.book'/></title>
    <style>
        .register-page {
            color: #06051b;
            text-align: center;
            position: relative;
            width: 250px;
            padding: 7% 0 0;
            margin: 0 auto;
        }

        .form-register {
            position: relative;
            width: 330px;
            height: 670px;
            top: 50%;
            left: 50%;
            margin-top: -260px;
            margin-left: -170px;
            background: #ffffff;
            border-radius: 3px;
            border: 1px solid #ccc;
            box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
            -webkit-animation-name: bounceIn;
            -webkit-animation-fill-mode: both;
            -webkit-animation-duration: 1s;
            -webkit-animation-iteration-count: 1;
            -webkit-animation-timing-function: linear;
            -moz-animation-name: bounceIn;
            -moz-animation-fill-mode: both;
            -moz-animation-duration: 1s;
            -moz-animation-iteration-count: 1;
            -moz-animation-timing-function: linear;
            animation-name: bounceIn;
            animation-fill-mode: both;
            animation-duration: 1s;
            animation-iteration-count: 1;
            animation-timing-function: linear;
        }
    </style>
</head>
<header>
    <jsp:include page="header.jsp"/>
</header>
<div>
    <h4 class="text-danger" align="center">${authorMsg}</h4>
</div>
<c:if test="${not empty authors}">
    <div class="form-register">
        <div id="main-container" class="register-page">
            <form:form method="post" action="${pageContext.request.contextPath}/createBook"
                       modelAttribute="book">
                <h2 align="center"><fmt:message key='header.create.book'/></h2>
                <div class="form-group">
                    <form:errors path="title" cssStyle="color: #ff0000; font-weight: bold"/>
                    <br>
                    <label for="title"><fmt:message key='header.title'/>: </label>
                    <div class="cols-sm-10">
                        <form:input type="title" class="form-control" path="title" id="title"
                                    placeholder="Title"/>
                    </div>
                </div>
                <div class="form-group">
                    <span style="color: #ff0000; font-weight: bold">${bookMsg}</span>
                    <form:errors path="isbn" cssStyle="color: #ff0000; font-weight: bold"/>
                    <br>
                    <label for="isbn"><fmt:message key='header.isbn'/>: </label>
                    <div class="cols-sm-10">
                        <form:input type="isbn" class="form-control" path="isbn" id="isbn"
                                    placeholder="Isbn"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:errors path="year" cssStyle="color: #ff0000; font-weight: bold"/>
                    <br>
                    <label for="year"><fmt:message key='header.year'/>: </label>
                    <div class="cols-sm-10">
                        <form:input type="year" class="form-control" path="year" id="year"
                                    placeholder="Year"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:errors path="langCode" cssStyle="color: #ff0000; font-weight: bold"/>
                    <br>
                    <label for="langCode"><fmt:message key='link.language'/>: </label>
                    <div class="cols-sm-10">
                        <form:input type="langCode" class="form-control" path="langCode" id="langCode"
                                    placeholder="Lang code"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:errors path="keepPeriod" cssStyle="color: #ff0000; font-weight: bold"/>
                    <br>
                    <label for="keepPeriod"><fmt:message key='header.keep.period'/>: </label>
                    <div class="cols-sm-10">
                        <form:input type="keepPeriod" class="form-control" path="keepPeriod" id="keepPeriod"
                                    placeholder="Keep period"/>
                    </div>
                </div>
                <div class="form-group">
                    <label><fmt:message key='header.author'/>: <c:out value="${authors.get(0).name}"/> </label>
                </div>
                <div class="row mb-2 my-2">
                    <div class="col-sm container overflow-hidden">
                        <br>
                        <button type="submit" value="submit" class="btn btn-primary">
                            <fmt:message key='header.apply'/>
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</c:if>