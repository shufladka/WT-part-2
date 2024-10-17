<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<%@ page import="java.util.List" %>
<%@ page import="by.bsuir.entity.Hotel" %>
<%@ page import="by.bsuir.entity.Address" %>
<%@ page import="by.bsuir.entity.Room" %>
<%@ page import="by.bsuir.entity.Order" %>

<%
  Room room = (Room) request.getAttribute("room");
  List<Hotel> hotelList = (List<Hotel>) request.getAttribute("hotels");
  List<Address> addressList = (List<Address>) request.getAttribute("addresses");
  List<Order> orderList = (List<Order>) request.getAttribute("orders");
  Hotel currentHotel = null;
  Address currentAddress = null;
  Order currentOrder = null;

  if (hotelList != null && addressList != null) {
      for (Hotel hotel : hotelList) {
          if (hotel.getId() == room.getHotelId()) {
              currentHotel = hotel;
          }
      }

      for (Address address : addressList) {
          if (address.getId() == currentHotel.getAddressId()) {
              currentAddress = address;
          }
      }
  }

  if (orderList != null) {
      for (Order order : orderList) {
          if (room.getId() == order.getRoomId() && !room.isAvailable()) {
              currentOrder = order;
          }
      }
  }

  if (room != null && currentHotel != null && currentAddress != null) {
%>
<div class="col mx-4">
  <div class="row mb-4">
    <div class="card p-0">
      <div class="row g-0 d-flex align-items-center">
        <div class="col-md-8 d-flex align-items-center">
          <div class="card-body">
              <h5 class="card-title"><fmt:message bundle="${lang}" key="lang.rooms.name"/> <%= room.getName() %></h5>
              <p class="card-text m-0">
                  <%
                      if (room.isAvailable()) {
                  %>
                  <fmt:message bundle="${lang}" key="lang.rooms.is_available"/>
                  <%
                      } else {
                  %>
                  <fmt:message bundle="${lang}" key="lang.rooms.is_unavailable"/>
                  <%
                      }
                  %>
              </p>
              <p class="card-text m-0"><fmt:message bundle="${lang}" key="lang.rooms.capacity"/>: <%= room.getCapacity() %></p>
              <p class="card-text m-0"><fmt:message bundle="${lang}" key="lang.rooms.basic_price"/> <%= room.getBasicPrice().intValue() %> BYN</p>
              <p class="card-text m-0"><fmt:message bundle="${lang}" key="lang.rooms.weekend_price"/> <%= room.getWeekendPrice().intValue() %> BYN</p>
              <p class="card-text mb-2"><fmt:message bundle="${lang}" key="lang.hotels.name"/> "<%= currentHotel.getName() %>" (<%= currentAddress.getCity() %>, <%= currentAddress.getStreet() %>, <%= currentAddress.getBuilding() %>, <%= room.getFloor() %> <fmt:message bundle="${lang}" key="lang.rooms.floor"/>)</p>

              <%
                  request.setAttribute("chosen_room_id", String.valueOf(room.getId()));

                  // Проверка на занятость номера
                  if (currentOrder == null) {
              %>
              <jsp:include page="order-modal.jsp" />
              <%
                  } else {
              %>
              <p class="btn btn-danger disabled"><fmt:message bundle="${lang}" key="lang.rooms.busy"/></p>
              <%
                  }
              %>
          </div>
        </div>

        <div class="col-md-4 text-end">
          <%
            if (room.getImagePath() != null) {
          %>
          <img src="<%= room.getImagePath() %>" width="250" height="250" class="img-fluid rounded-start" alt="...">
          <%
          } else {
          %>
          <img src="https://via.placeholder.com/250" width="250" height="250" class="img-fluid rounded-start" alt="...">
          <%
            }
          %>
        </div>
      </div>
    </div>
  </div>
</div>
<%
} else {
%>
<div class="col mx-4">
  <p><fmt:message bundle="${lang}" key="lang.rooms.no_rooms"/>.</p>
</div>
<%
  }
%>
