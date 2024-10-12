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
<jsp:include page="templates/nav.jsp"/>

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