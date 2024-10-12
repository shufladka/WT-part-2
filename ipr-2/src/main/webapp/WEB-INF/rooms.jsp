<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="by.bsuir.entity.Address" %>
<%@ page import="by.bsuir.entity.Hotel" %>
<%@ page import="by.bsuir.entity.Room" %>
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
//            Object roomsReq = request.getAttribute("rooms");
            Object addressesReq = request.getAttribute("addresses");
            Object idReq = request.getAttribute("id");
            Object hotelIdReq = request.getAttribute("hotel_id");

            if (hotelsReq != null && addressesReq != null) {
//                List<Room> roomList = (List<Room>) roomsReq;
                List<Hotel> hotelList = (List<Hotel>) hotelsReq;
                String id = (String) idReq;

                if (hotelIdReq != null) {
                    System.out.println(hotelIdReq);
                }

                request.setAttribute("addresses", addressesReq);
//                request.setAttribute("rooms", roomsReq);

                if (id == null) {
                    for (Hotel hotel : hotelList) {
                        request.setAttribute("hotel", hotel);
        %>

        <jsp:include page="templates/room-card.jsp" />

        <%
            }
        } else {
            try {
                request.setAttribute("hotel", hotelList.get(Integer.parseInt(id)));
        %>

        <jsp:include page="templates/room-card.jsp" />

        <%
        }
        catch (Exception e)
        {
        %>

        <div class="col mx-4">
            <p>No rooms available.</p>
        </div>

        <%
                    }
                }
            }
        %>
    </div>
    <jsp:include page="templates/footer.jsp"/>
</div>
</body>
</html>
