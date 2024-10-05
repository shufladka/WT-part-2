<%@ page import="by.bsuir.entity.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="java.awt.print.Book" %>
<%@ page import="by.bsuir.entity.Person" %><%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 04.10.2024
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <jsp:include page="templates/headLinks.jsp"/>
    <link rel="icon" href="../.././assets/favicon.ico" sizes="32x32" type="image/x-icon">
</head>

<body class="container">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="#">Home</a>
                <a class="nav-link" href="#">тест</a>
                <a class="nav-link" href="#">Pricing</a>
                <a class="nav-link disabled">Disabled</a>
            </div>
        </div>
    </div>
</nav>

<%--<%--%>
<%--    Object booksObj = request.getAttribute("books");--%>
<%--    if (booksObj != null) {--%>
<%--        Role book = (Role)request.getAttribute("books");--%>
<%--        try {--%>
<%--            out.println("<tbody>");--%>
<%--            out.println("<tr>");--%>
<%--            out.println("<th>" + book.getName() + "</th>");--%>
<%--            out.println("<th>" + book.getDescription() + "</td>");--%>

<%--            out.println("</tr>");--%>
<%--            out.println("</tbody><br>");--%>
<%--        } catch (Exception exc) {--%>
<%--            request.getRequestDispatcher("/error.jsp").forward(request,response);--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>

<%
    Object booksObj = request.getAttribute("books");
    if (booksObj != null) {
        List<Object> books = (List<Object>)request.getAttribute("books");
        for (Object book : books) {
            try {
                out.println("<tbody>");
                out.println("<tr>");
                out.println("<th>" + book.toString() + "</th>");
                out.println("</tr>");
                out.println("</tbody><br>");
            } catch (Exception exc) {
                request.getRequestDispatcher("/error.jsp").forward(request,response);
            }
        }
    }
%>
</body>
</html>