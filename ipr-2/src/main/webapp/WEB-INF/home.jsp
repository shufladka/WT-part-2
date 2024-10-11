<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 04.10.2024
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <jsp:include page="templates/head.jsp"/>
    <title>Главная</title>
</head>

<body class="container">
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <div class="container-fluid">

    <a class="navbar-brand" href="#">
        <img src="https://i.imgur.com/KlMVi1H.png" alt="brand" width="30" height="30" class="d-inline-block align-text-top">
        <span class="navbar-brand mb-0 h1 fs-5">Bookin</span>
    </a>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav fs-5">
<%--                <a class="nav-link active" aria-current="page" href="#">Home</a>--%>
                <a class="nav-link" href="#">Главная</a>
            </div>
        </div>
    </div>
    <div class="d-grid gap-2 d-md-flex navbar-brand">
        <a class="btn btn-outline-success" href="login">Log In</a>
        <a class="btn btn-outline-primary" href="registration">Sign Up</a>
    </div>
</nav>

<p><a href="?lang=en">Switch to English</a></p>
<p><a href="?lang=ru">Переключиться на русский</a></p>


<%--<%--%>
<%--    Object booksObj = request.getAttribute("books");--%>
<%--    out.println("<tbody>");--%>
<%--    out.println("<tr>");--%>
<%--    out.println("<th>" + booksObj.toString() + "</th>");--%>
<%--    out.println("</tr>");--%>
<%--    out.println("</tbody><br>");--%>
<%--%>--%>

<%--<%--%>
<%--    Object booksObj = request.getAttribute("books");--%>
<%--    if (booksObj != null) {--%>
<%--        List<Object> books = (List<Object>)request.getAttribute("books");--%>
<%--        for (Object book : books) {--%>
<%--            try {--%>
<%--                out.println("<tbody>");--%>
<%--                out.println("<tr>");--%>
<%--                out.println("<th>" + book.toString() + "</th>");--%>
<%--                out.println("</tr>");--%>
<%--                out.println("</tbody><br>");--%>
<%--            } catch (Exception exc) {--%>
<%--                request.getRequestDispatcher("/error.jsp").forward(request,response);--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>

<jsp:include page="templates/footer.jsp"/>
</body>
</html>