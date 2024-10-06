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
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<html>
<head>
  <jsp:include page="templates/head.jsp"/>
  <title><fmt:message bundle="${lang}" key="lang.login.title"/></title>
</head>
<body class="container login_container">
    <div class="position-relative top-50 start-50 translate-middle shadow-lg p-3 mb-5 bg-body rounded-3">
      <p class="d-flex justify-content-center fw-bold fs-2 text-muted"><fmt:message bundle="${lang}" key="lang.login.form_title"/></p>
      <form method="post" action="/login">
        <div class="mb-3">
          <label for="username" class="form-label"><fmt:message bundle="${lang}" key="lang.login.username"/></label>
          <input type="text" class="form-control" id="username" name="username" required />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label"><fmt:message bundle="${lang}" key="lang.login.password"/></label>
          <input type="password" class="form-control" id="password" name="password" required />
        </div>
        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="remember_me" name="remember_me">
          <label class="form-check-label" for="remember_me"><fmt:message bundle="${lang}" key="lang.login.remember_me"/></label>
        </div>
        <button type="submit" class="form-control m-0 btn btn-primary"><fmt:message bundle="${lang}" key="lang.login.login"/></button>
      </form>
      <div class="d-flex justify-content-center">
        <p class="message">
          <fmt:message bundle="${lang}" key="lang.login.not_registered"/>
          <a href="registration" class="text-body"><fmt:message bundle="${lang}" key="lang.login.create_account"/></a>
        </p>
      </div>
      <c:if test="${not empty param.error}">
        <hr>
        <div class="d-flex justify-content-center text-danger fw-bold">
          <p>
            <fmt:message bundle="${lang}" key="lang.login.error"/>
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
  .login_container {
    max-width: 720px;
  }
</style>