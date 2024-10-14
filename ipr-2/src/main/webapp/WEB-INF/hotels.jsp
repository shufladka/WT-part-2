<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="by.bsuir.entity.Hotel" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<head>
  <jsp:include page="templates/head.jsp"/>
  <title><fmt:message bundle="${lang}" key="lang.hotels.title"/></title>
</head>

<body class="container">
<jsp:include page="templates/nav.jsp"/>
<div class="d-flex flex-column min-vh-100 flex-grow-1">
    <div class="wrapper flex-grow-1">

        <%
            List<Hotel> hotelList = (List<Hotel>) request.getAttribute("hotels");
            Object addressesReq = request.getAttribute("addresses");
            String id = (String) request.getAttribute("id");

            // Сортировка по hotel ID
            Collections.sort(hotelList, new Comparator<Hotel>() {
                @Override
                public int compare(Hotel h1, Hotel h2) {
                    return Integer.compare(h1.getId(), h2.getId());
                }
            });

            if (hotelList != null && addressesReq != null) {
                if (id == null) {
                    for (Hotel hotel : hotelList) {
                        request.setAttribute("hotel", hotel);
        %>

        <jsp:include page="templates/hotel-card.jsp" />

        <%
            }
        } else {
            try {
                int index = Integer.parseInt(id);
                for (Hotel hotel : hotelList) {
                    if (hotel.getId() == index) {
                        request.setAttribute("hotel", hotel);
                    }
                }
        %>

        <jsp:include page="templates/hotel-card.jsp" />

        <%
        } catch (NumberFormatException e) {
        %>
        <div class="col mx-4">
            <p>Invalid hotel ID format.</p>
        </div>
        <%
        } catch (IndexOutOfBoundsException e) {
        %>
        <div class="col mx-4">
            <p>No hotels available.</p>
        </div>
        <%
                }
            }
        } else {
        %>
        <div class="col mx-4">
            <p>No hotels available.</p>
        </div>
        <%
            }
        %>
    </div>
    <jsp:include page="templates/footer.jsp"/>
</div>
</body>
</html>
