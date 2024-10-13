<%@ page import="by.bsuir.entity.Room" %><%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 13.10.2024
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>

<%
    String chosenRoomId = (String) request.getAttribute("chosen_room_id");
    int id = Integer.parseInt(chosenRoomId);
%>

<form method="post" action="${pageContext.request.contextPath}/orders">
    <!-- Передаем id конкретной комнаты через hidden input -->
    <input type="hidden" name="chosen_room_id" value="<%= id %>">
    <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#exampleModalCenter<%= id %>">
        <fmt:message bundle="${lang}" key="lang.rooms.create_order"/>
    </button>

    <!-- Модальное окно для каждой комнаты -->
    <div class="modal fade" id="exampleModalCenter<%= id %>" tabindex="-1" aria-labelledby="exampleModalCenterTitle<%= id %>" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle<%= id %>">
                        <fmt:message bundle="${lang}" key="lang.order_modal.title"/>
                    </h5>
                </div>
                <div class="modal-body">
                    <fmt:message bundle="${lang}" key="lang.order_modal.text"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message bundle="${lang}" key="lang.order_modal.close"/></button>
                    <button type="submit" class="btn btn-outline-primary"><fmt:message bundle="${lang}" key="lang.order_modal.confirm"/></button>
                </div>
            </div>
        </div>
    </div>
</form>
