<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<html>
<head>
    <jsp:include page="templates/head.jsp"/>
    <title><fmt:message bundle="${lang}" key="lang.intro.title"/></title>
</head>
<body class="container login_container">
<div class="position-relative top-50 start-50 translate-middle shadow-lg p-3 mb-5 bg-body rounded-3">
    <p class="d-flex justify-content-center fw-bold fs-2 text-muted"><fmt:message bundle="${lang}" key="lang.intro.title"/></p>
    <div class="mb-3">
        <p class="fw-bold fs-5 text-muted d-flex justify-content-center">Вас приветствует система по поиску и бронированию номеров</p>
        <p class="fw-bold fs-5 text-muted d-flex justify-content-center">Для продолжения работы требуется авторизация в сервисе</p>
    </div>
    <div class="d-flex justify-content-center">
        <a class="m-2 btn btn-outline-success" href="login"><fmt:message bundle="${lang}" key="lang.intro.login"/></a>
        <a class="m-2 btn btn-outline-primary" href="registration"><fmt:message bundle="${lang}" key="lang.intro.signup"/></a>
    </div>

</div>
</body>
</html>

<style>
    body {
        background-image: url('https://i.imgur.com/95bxjND.png');
        background-size: cover;
        background-position: center;
        height: 100vh;
    }
    .login_container {
        max-width: 720px;
    }
</style>
