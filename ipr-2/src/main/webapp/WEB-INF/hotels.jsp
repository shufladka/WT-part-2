<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<head>
  <jsp:include page="templates/head.jsp"/>
  <title><fmt:message bundle="${lang}" key="lang.home.title"/></title>
</head>

<body class="container">
<jsp:include page="templates/nav.jsp"/>
<div class="d-flex flex-column min-vh-100 flex-grow-1">
  <div class="wrapper flex-grow-1">
    <jsp:include page="templates/hotel-card.jsp"/>
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
  </div>
  <jsp:include page="templates/footer.jsp"/>
</div>
</body>
</html>
