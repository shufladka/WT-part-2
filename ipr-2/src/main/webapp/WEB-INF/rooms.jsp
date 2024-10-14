<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
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
            List<Room> roomList = (List<Room>) request.getAttribute("rooms");
            String hotelId = (String) request.getAttribute("hotel_id");
            String roomId = (String) request.getAttribute("room_id");

            if (roomList != null && !roomList.isEmpty()) {
                boolean hasRooms = false;

                // Если указан hotelId, фильтруем комнаты по отелю
                if (hotelId != null) {
                    int hotelIdValue = Integer.parseInt(hotelId);
                    for (Room room : roomList) {
                        if (room.getHotelId() == hotelIdValue) {
                            request.setAttribute("room", room);
                            hasRooms = true;
        %>
        <jsp:include page="templates/room-card.jsp"/>
        <%
                }
            }

            if (!hasRooms) {
        %>
        <div class="col mx-4">
            <p>No rooms available for this hotel.</p>
        </div>
        <%
            }

            // Если указан roomId, показываем только эту комнату
        } else if (roomId != null) {
            int roomIdValue = Integer.parseInt(roomId);
            for (Room room : roomList) {
                if (room.getId() == roomIdValue) {
                    request.setAttribute("room", room);
        %>
        <jsp:include page="templates/room-card.jsp"/>
        <%
                }
            }

            // Если нет ни hotelId, ни roomId, показываем все комнаты
        } else {
            for (Room room : roomList) {
                request.setAttribute("room", room);
        %>
        <jsp:include page="templates/room-card.jsp"/>
        <%
                }
            }
        } else {
        %>
        <div class="col mx-4">
            <p>No rooms available.</p>
        </div>
        <%
            }
        %>
    </div>
    <jsp:include page="templates/footer.jsp"/>
</div>
</body>
