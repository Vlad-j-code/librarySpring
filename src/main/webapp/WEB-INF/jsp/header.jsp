<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${lang.code}">
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <title>Library</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/src/main/webapp/html/favicon.ico">
    <%--        <script src="http://code.jquery.com/jquery-latest.min.js"></script>--%>
    <%--        <script src="/js/libraryCustom.js"></script>--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#locales").change(function () {
                var selectedOption = $('#locales').val();
                if (selectedOption != '') {
                    window.location.replace('?lang=' + selectedOption);
                }
            });
        });
    </script>
</head>
<body class="d-flex flex-column min-vh-100" style="background: linear-gradient(to bottom left, #2c3e50, #bdc3c7);">
<nav class="navbar navbar-dark bg-dark ">
    <div class="container-fluid">
        <a class="navbar-brand h1 pl-4"><fmt:message key='header.brand'/></a>
        <ul class="nav nav-pills">
            <c:if test="${not empty user and user.role eq 'USER' and user.fine > 0}">
                <li class="nav-item">
                    <a class="nav-link text-danger fw-bold">
                        <fmt:message key='header.user.fine'/>: <c:out value="${user.fine}"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="/"><fmt:message key="link.home"/></a>
            </li>
            <c:if test="${not empty pageContext.request.userPrincipal and pageContext.request.userPrincipal.name ne 'admin@gmail.com'
            and pageContext.request.userPrincipal.name ne 'librarian@gmail.com'}">
                <li class="nav-item">
                    <a class="nav-link" href="/subscriptions"><fmt:message key="link.user.books"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/basket"><fmt:message key="link.user.basket"/></a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name eq 'librarian@gmail.com'}">
                <li class="nav-item">
                    <a class="nav-link" href="/booking"><fmt:message key="link.librarian.booking"/></a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name eq 'admin@gmail.com'}">
                <li class="nav-item">
                    <a class="nav-link" href="/users"><fmt:message key="link.admin.users"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/authors"><fmt:message key="header.authors"/></a>
                </li>
            </c:if>

            <%--                    <c:if test="${not empty user and user.role eq 'USER'}">--%>
            <%--                        <%@ include file="/WEB-INF/jspf/user/header.jsp" %>--%>
            <%--                    </c:if>--%>
            <%--                    <c:if test="${not empty user and user.role eq 'ADMIN'}">--%>
            <%--                        <%@ include file="/WEB-INF/jspf/admin/header.jsp" %>--%>
            <%--                    </c:if>--%>
            <%--                    <c:if test="${not empty user and user.role eq 'LIBRARIAN'}">--%>
            <%--                        <%@ include file="/WEB-INF/jspf/librarian/header.jsp" %>--%>
            <%--                    </c:if>--%>

            <c:if test="${not empty pageContext.request.userPrincipal}">
                <li class="nav-item" style="margin-top: 5px">
                    <select class="form-select form-select-sm" id="locales">
                        <option value=""><fmt:message key="link.language"/></option>
                        <option value="en">EN</option>
                        <option value="ru">RU</option>
                    </select>
                </li>
            </c:if>
            <c:choose>
                <c:when test="${empty pageContext.request.userPrincipal}">
                    <li class="nav-item">
                        <a class="nav-link" href="/login"><fmt:message key="link.sign.in"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                                ${pageContext.request.userPrincipal.name}
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <%--                                    <li>--%>
                                <%--                                        <a class="dropdown-item" href="/controller?command=user.edit&id=${user.id}">--%>
                                <%--                                            <fmt:message key="header.edit.my.info"/>--%>
                                <%--                                        </a>--%>
                                <%--                                    </li>--%>
                            <li><a class="dropdown-item" href="/logout"><fmt:message key="link.logout"/></a></li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>