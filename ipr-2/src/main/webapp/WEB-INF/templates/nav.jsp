<%--
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
        <a class="nav-link" href="${pageContext.request.contextPath}/hotels"><fmt:message bundle="${lang}" key="lang.nav.hotels"/></a>
        <a class="nav-link" href="${pageContext.request.contextPath}/rooms"><fmt:message bundle="${lang}" key="lang.nav.rooms"/></a>
        <a class="nav-link" href="${pageContext.request.contextPath}/orders"><fmt:message bundle="${lang}" key="lang.nav.orders"/></a>
      </div>
    </div>
  </div>
  <div class="d-grid gap-2 d-md-flex navbar-brand">
    <%
        if (session.getAttribute("userinfo") != null) {
    %>
    <a class="btn btn-outline-danger" href="logout"><fmt:message bundle="${lang}" key="lang.nav.logout"/></a>
    <%
        } else {
    %>
    <a class="btn btn-outline-success" href="login"><fmt:message bundle="${lang}" key="lang.intro.login"/></a>
    <a class="btn btn-outline-primary" href="registration"><fmt:message bundle="${lang}" key="lang.intro.signup"/></a>
    <%
        }
    %>
  </div>
</nav>
