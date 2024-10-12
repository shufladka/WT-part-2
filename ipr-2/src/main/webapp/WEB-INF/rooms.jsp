<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 14:36
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
    <title><fmt:message bundle="${lang}" key="lang.rooms.title"/></title>
</head>

<body class="container">
<jsp:include page="templates/nav.jsp"/>
<div class="d-flex flex-column min-vh-100 flex-grow-1">
    <div class="wrapper flex-grow-1">

        <%
            Object hotelsReq = request.getAttribute("hotels");
            Object addressesReq = request.getAttribute("addresses");

            if (hotelsReq != null && addressesReq != null) {
                List<Object> hotelsObjects = (List<Object>) request.getAttribute("hotels");
                List<Object> addressesObjects = (List<Object>) request.getAttribute("addresses");

                request.setAttribute("hotels", hotelsObjects);
                request.setAttribute("addresses", addressesObjects);
        %>
        <jsp:include page="templates/room-card.jsp" />
        <%
            }
        %>
    </div>
    <jsp:include page="templates/footer.jsp"/>
</div>
</body>
</html>
