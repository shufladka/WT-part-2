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

<%
  Room room = (Room) request.getAttribute("room");

  if (room != null) {
%>
<div class="col mx-4">
  <div class="row mb-4">
    <div class="card p-0">
      <div class="row g-0 d-flex align-items-center">
        <div class="col-md-8 d-flex align-items-center">
          <div class="card-body">
            <h5 class="card-title">"<%= room.getName() %>"</h5>
            <p class="card-text m-0"><%= room.getCapacity() %></p>
<%--            <a href="/rooms" class="btn btn-outline-primary"><fmt:message bundle="${lang}" key="lang.hotels.redirect"/></a>--%>
          </div>
        </div>

        <div class="col-md-4 text-end">
<%--          <%--%>
<%--            if (!room.getImagePath().equals("")) {--%>
<%--          %>--%>
<%--          <img src="<%= room.getImagePath() %>" width="250" height="250" class="img-fluid rounded-start" alt="...">--%>
<%--          <%--%>
<%--          } else {--%>
<%--          %>--%>
          <img src="https://via.placeholder.com/200" width="200" height="200" class="img-fluid rounded-start" alt="...">
<%--          <%--%>
<%--            }--%>
<%--          %>--%>
        </div>
      </div>
    </div>
  </div>
</div>
<%
} else {
%>
<div class="col mx-4">
  <p>No rooms available.</p>
</div>
<%
  }
%>
