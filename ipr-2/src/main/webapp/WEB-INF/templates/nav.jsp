<%@ page import="by.bsuir.service.ServiceSingleton" %>
<%@ page import="by.bsuir.entity.Person" %>
<%@ page import="by.bsuir.service.AuthService" %><%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">
      <img src="https://i.imgur.com/KlMVi1H.png" alt="brand" width="30" height="30" class="d-inline-block align-text-top">
      <span class="navbar-brand mb-0 h1 fs-5">Bookin</span>
    </a>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav fs-5">
        <a class="nav-link disabled" href="/"><fmt:message bundle="${lang}" key="lang.nav.home"/></a>
        <a class="nav-link" href="/hotels"><fmt:message bundle="${lang}" key="lang.nav.hotels"/></a>
        <a class="nav-link" href="/rooms"><fmt:message bundle="${lang}" key="lang.nav.rooms"/></a>

        <%
          ServiceSingleton service = new ServiceSingleton();
          AuthService authService = service.getAuthService();
          boolean isAdmin = false;

          if (session.getAttribute("userinfo") != null) {
            Person person = authService.deserializePersonBase64(session.getAttribute("userinfo").toString());
            isAdmin = authService.isAdmin(person);
          }

          if (true) {
        %>
        <a class="nav-link" href="/profiles">Профили</a>

        <%
          }
        %>

      </div>
    </div>
  </div>
  <div class="d-grid gap-2 d-md-flex navbar-brand">
    <%
        if (session.getAttribute("userinfo") != null) {
    %>
    <a class="btn btn-outline-primary disabled" href="profile"><fmt:message bundle="${lang}" key="lang.nav.profile"/></a>
    <a class="btn btn-outline-danger" href="logout"><fmt:message bundle="${lang}" key="lang.nav.logout"/></a>
    <%
        } else {
    %>
    <a class="btn btn-outline-success" href="login"><fmt:message bundle="${lang}" key="lang.intro.login"/></a>
    <a class="btn btn-outline-primary" href="registration"><fmt:message bundle="${lang}" key="lang.intro.signup"/></a>
    <%
        }
    %>
<%--    <a class="m-2 btn btn-outline-success" href="login"><fmt:message bundle="${lang}" key="lang.intro.login"/></a>--%>
<%--    <a class="m-2 btn btn-outline-primary" href="registration"><fmt:message bundle="${lang}" key="lang.intro.signup"/></a>--%>
<%--    <a class="btn btn-outline-danger" href="logout"><fmt:message bundle="${lang}" key="lang.nav.logout"/></a>--%>
  </div>
</nav>
