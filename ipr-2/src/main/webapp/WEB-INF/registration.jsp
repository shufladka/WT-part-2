<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 06.10.2024
  Time: 1:54
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
  <title><fmt:message bundle="${lang}" key="lang.registration.title"/></title>
</head>
<body class="container registration_container">
<div class="position-relative top-50 start-50 translate-middle shadow-lg p-3 mb-5 bg-body rounded-3">
  <p class="d-flex justify-content-center fw-bold fs-2 text-muted"><fmt:message bundle="${lang}" key="lang.registration.form_title"/></p>
  <form method="post" action="registration">
    <div class="mb-3">
      <label for="last_name" class="form-label"><fmt:message bundle="${lang}" key="lang.registration.last_name"/></label>
      <input type="text" class="form-control" id="last_name" name="last_name" required />
    </div>
    <div class="mb-3">
      <label for="first_name" class="form-label"><fmt:message bundle="${lang}" key="lang.registration.first_name"/></label>
      <input type="text" class="form-control" id="first_name" name="first_name" required />
    </div>
    <div class="mb-3">
      <label for="username" class="form-label"><fmt:message bundle="${lang}" key="lang.registration.username"/></label>
      <input type="text" class="form-control" id="username" name="username" required />
    </div>
    <div class="mb-3">
      <label for="password" class="form-label"><fmt:message bundle="${lang}" key="lang.registration.password"/></label>
      <input type="text" class="form-control" id="password" name="password" required />
    </div>
    <div class="mb-3">
      <label for="date" class="form-label"><fmt:message bundle="${lang}" key="lang.registration.birth_date"/></label>
      <input type="date" class="form-control" id="date" name="birth_date" required />
    </div>
    <div class="mb-3">
      <label for="email" class="form-label"><fmt:message bundle="${lang}" key="lang.registration.email"/></label>
      <input type="email" class="form-control" id="email" name="email" required />
    </div>
    <button type="submit" class="form-control m-0 btn btn-primary"><fmt:message bundle="${lang}" key="lang.registration.register"/></button>
  </form>
  <div class="d-flex justify-content-center">
    <p class="message">
      <fmt:message bundle="${lang}" key="lang.registration.already_registered"/>
      <a href="login" class="text-body"><fmt:message bundle="${lang}" key="lang.login.login"/></a>
    </p>
  </div>
  <c:if test="${not empty param.error}">
    <hr>
    <div class="d-flex justify-content-center text-danger fw-bold">
      <p>
        <fmt:message bundle="${lang}" key="lang.registration.error"/>
      </p>
    </div>
  </c:if>
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
  .registration_container {
    max-width: 720px;
  }
</style>