<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages" />

<head>
    <title><fmt:message key='header.create.author'/></title>
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
            height: 270px;
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
<div class="form-register">
    <div id="main-container" class="register-page">
        <form:form method="post" action="${pageContext.request.contextPath}/createAuthor"
                   modelAttribute="author">
            <h2 align="center"><fmt:message key='header.create.author'/></h2>
            <div class="form-group">
                <span style="color: #ff0000; font-weight: bold">${authorMsg}</span>
                <form:errors path="name" cssStyle="color: #ff0000; font-weight: bold"/>
                <br>
                <label for="name"><fmt:message key='header.name'/>: </label>
                <div class="cols-sm-10">
                    <form:input type="name" class="form-control" path="name" id="name"
                                placeholder="Name"/>
                </div>
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
