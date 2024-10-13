<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 06.10.2024
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />
<footer class="footer">
    <div class="bg-light py-3 py-md-3 py-xl-8">
        <div class="container overflow-hidden">
            <div class="col gy-3 gy-md-3 gy-xl-0 align-items-center">

                <div class="bg-light mb-3">
                    <div class="container overflow-hidden">
                        <div class="row">
                            <div class="footer-copyright-wrapper text-center">
                                <a class="navbar-brand" href="/">
                                    <img src="https://i.imgur.com/KlMVi1H.png" alt="brand" width="30" height="30" class="d-inline-block align-text-top">
                                    <span class="navbar-brand mb-0 h1 fs-5 text-body">Bookin</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col order-2 order-xl-1">
                    <ul class="nav justify-content-center">
                        <li class="nav-item">
                            <a class="nav-link link-secondary px-2 px-md-3" href="/"><fmt:message bundle="${lang}" key="lang.footer.home"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link link-secondary px-2 px-md-3" href="/hotels"><fmt:message bundle="${lang}" key="lang.footer.hotels"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link link-secondary px-2 px-md-3" href="/rooms"><fmt:message bundle="${lang}" key="lang.footer.rooms"/></a>
                        </li>
                        <c:if test="${sessionScope.lang == 'ru' || sessionScope.lang == null}">
                            <li class="nav-item">
                                <a class="nav-link link-secondary px-2 px-md-3"href="?lang=en"><fmt:message bundle="${lang}" key="lang.footer.change_language"/></a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.lang == 'en'}">
                            <li class="nav-item">
                                <a class="nav-link link-secondary px-2 px-md-3" href="?lang=ru">Сменить язык</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="bg-light py-3 border-top border-light-subtle">
        <div class="container overflow-hidden">
            <div class="row">
                <div class="col">
                    <div class="footer-copyright-wrapper text-center">
                        &copy; <c:out value="${currentYear}" />. <fmt:message bundle="${lang}" key="lang.footer.copy"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
