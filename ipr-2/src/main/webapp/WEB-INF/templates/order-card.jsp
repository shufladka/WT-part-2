<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 14.10.2024
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<%@ page import="java.util.List" %>
<%@ page import="by.bsuir.entity.*" %>

<%
  String isAdmin = (String) request.getAttribute("is_admin");
  Order order = (Order) request.getAttribute("order");
  List<Person> personList = (List<Person>) request.getAttribute("people");
  List<Room> roomList = (List<Room>) request.getAttribute("rooms");
  List<Address> addressList = (List<Address>) request.getAttribute("addresses");
  List<Hotel> hotelList = (List<Hotel>) request.getAttribute("hotels");
  Person currentPerson = null;
  Room currentRoom = null;
  Hotel currentHotel = null;
  Address currentAddress = null;

  if (personList != null && roomList != null && hotelList != null && order != null) {

    for (Person person : personList) {
      if (person.getId() == order.getPersonId()) {
        currentPerson = person;
      }
    }

    for (Room room : roomList) {
      if (room.getId() == order.getRoomId()) {
        currentRoom = room;
      }
    }

    if (currentRoom != null) {
      for (Hotel hotel : hotelList) {
        if (hotel.getId() == currentRoom.getHotelId()) {
          currentHotel = hotel;
        }
      }
    }

    if (currentHotel != null) {
      for (Address address : addressList) {
        if (address.getId() == currentHotel.getAddressId()) {
          currentAddress = address;
        }
      }
    }
  }

  if (currentPerson != null && currentRoom != null && currentHotel != null && currentAddress != null) {

    boolean isAdminValue = false;
      if (isAdmin != null) {
        isAdminValue = Boolean.parseBoolean(isAdmin);
    }
%>

<div class="col mx-4">
  <div class="row mb-4">
    <div class="card p-0">
      <div class="row g-0 d-flex align-items-center">
        <div class="col-md-8 d-flex align-items-center">
          <div class="card-body">
            <h5 class="card-title"><fmt:message bundle="${lang}" key="lang.orders.order"/> № <%= order.getId() %></h5>
            <p class="card-text m-0"><fmt:message bundle="${lang}" key="lang.orders.client"/>: <%= currentPerson.getUsername() %></p>
            <p class="card-text m-0"><fmt:message bundle="${lang}" key="lang.orders.full_name"/>: <%= currentPerson.getFirstName() %> <%= currentPerson.getLastName() %></p>
            <p class="card-text m-0"><fmt:message bundle="${lang}" key="lang.orders.room_name"/>: <%= currentRoom.getName() %></p>
            <p class="card-text m-0"><fmt:message bundle="${lang}" key="lang.orders.hotel_name"/> "<%= currentHotel.getName() %>" (<%= currentAddress.getCity() %>, <%= currentAddress.getStreet() %>, <%= currentAddress.getBuilding() %>, <%= currentRoom.getFloor() %> <fmt:message bundle="${lang}" key="lang.rooms.floor"/>)</p>
            <p class="card-text mb-2"><fmt:message bundle="${lang}" key="lang.orders.order_status"/>: <%= order.getStatus() %></p>
            <div class="d-flex justify-content-start">

              <%
                if (isAdminValue && order.getStatus().equals("CREATED")) {
              %>
              <form method="post" action="${pageContext.request.contextPath}/orders/update" class="me-2">
                <input type="hidden" name="chosen_order_id" value="<%= order.getId() %>">
                <button type="submit" class="btn btn-outline-success"><fmt:message bundle="${lang}" key="lang.orders.accept_for_work"/></button>
              </form>
              <%
                }
              %>
              <%
                if ((isAdminValue && !order.getStatus().equals("CLOSED")) || (!isAdminValue && order.getStatus().equals("CREATED"))) {
              %>
              <form method="post" action="${pageContext.request.contextPath}/orders/delete">
                <input type="hidden" name="chosen_order_id" value="<%= order.getId() %>">
                <button type="submit" class="btn btn-outline-danger"><fmt:message bundle="${lang}" key="lang.orders.cancel"/></button>
              </form>
              <%
                }
              %>
            </div>
          </div>
        </div>

        <div class="col-md-4 text-end">
          <%
            if (currentRoom.getImagePath() != null) {
          %>
          <img src="<%= currentRoom.getImagePath() %>" width="250" height="250" class="img-fluid rounded-start" alt="...">
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
  <p><fmt:message bundle="${lang}" key="lang.orders.no_orders"/>.</p>
</div>

<%
  }
%>
