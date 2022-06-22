<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages" />

<head>
    <title><fmt:message key='header.login'/></title>
    <style>
        .login-page {
            color: #06051b;
            text-align: center;
            position: relative;
            width: 250px;
            padding: 10% 0 0;
            margin: 0 auto;

        }
        .form-login {
            position: relative;
            width: 330px;
            height: 330px;
            top: 50%;
            left: 50%;
            margin-top: -220px;
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
<div class="form-login">
    <div class="login-page">
        <form action="/login" method="post">
            <h1 align="center"><fmt:message key='header.login'/></h1>
            <div class="form-group ">
                <label for="email"><fmt:message key='header.email'/>:</label>
                <div class="input-group shadow-lg">
                    <input type="text" id="email" name="email" class="form-control" placeholder="email" required>
                </div>
            </div>
            <div class="form-group ">
                <label for="password"><fmt:message key='header.password'/>:</label>
                <div class="input-group shadow-lg">
                    <input type="password" id="password" name="password" class="form-control"
                           placeholder="password" required>
                </div>
            </div>
            <br>
            <div class="col-sm container overflow-hidden">
                <input type="submit" value="<fmt:message key='link.sign.in'/>" class="btn btn-primary">
                <a href="/registration" class="btn btn-secondary"><fmt:message key='link.sign.up'/></a>
            </div>
            <c:if test="${not empty error}">
                <div class="error" style="font-weight:bold; color: #ff0000;">${error}</div>
            </c:if>
        </form>
    </div>
</div>