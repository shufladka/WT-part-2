<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 12:48
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

<%
    List<Address> addressList = (List<Address>) request.getAttribute("addresses");
    Hotel hotel = (Hotel) request.getAttribute("hotel");

    if (hotel != null && addressList != null) {
%>

<div class="col mx-4">
    <div class="row mb-4">
        <div class="card p-0">
            <div class="row g-0 d-flex align-items-center">
                <div class="col-md-8 d-flex align-items-center">
                    <div class="card-body">
                        <h5 class="card-title"><fmt:message bundle="${lang}" key="lang.hotels.name"/> "<%= hotel.getName() %>"</h5>
                        <p class="card-text m-0"><%= hotel.getDescription() %></p>
                        <div class="d-flex align-items-center">
                            <p class="card-text mb-0 d-flex align-items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star me-2" viewBox="0 0 16 16">
                                    <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
                                </svg>
                                <%= hotel.getLevel() %>
                            </p>
                        </div>
                        <p class="card-text mb-3"><%= addressList.get(hotel.getAddressId()).getRegion() %>,  <%= addressList.get(hotel.getAddressId()).getCity() %>, <%= addressList.get(hotel.getAddressId()).getStreet() %> <%= addressList.get(hotel.getAddressId()).getBuilding() %>, <%= addressList.get(hotel.getAddressId()).getZip() %></p>
<%--                        <form method="post" action="${pageContext.request.contextPath}/rooms?hotel_id=2">--%>
<%--                            <button class="btn btn-outline-primary">--%>
<%--                                <fmt:message bundle="${lang}" key="lang.hotels.redirect"/>--%>
<%--                            </button>--%>
<%--                        </form>--%>

                        <a href="/rooms?hotel_id=<%= hotel.getId() %>" class="btn btn-outline-primary"><fmt:message bundle="${lang}" key="lang.hotels.redirect"/></a>
                    </div>
                </div>

                <div class="col-md-4 text-end">
                    <%
                        if (!hotel.getImagePath().equals("")) {
                    %>
                    <img src="<%= hotel.getImagePath() %>" width="200" height="200" class="img-fluid rounded-start" alt="...">
                    <%
                    } else {
                    %>
                    <img src="https://via.placeholder.com/200" width="200" height="200" class="img-fluid rounded-start" alt="...">
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
    <p>No hotels available.</p>
</div>
<%
    }
%>
