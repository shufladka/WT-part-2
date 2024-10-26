<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 14.10.2024
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="by.bsuir.entity.Order" %>
<%@ page import="java.util.Comparator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang.lang" var="lang"/>
<head>
    <jsp:include page="templates/head.jsp"/>
    <title><fmt:message bundle="${lang}" key="lang.orders.title"/></title>
</head>

<body class="container">
<jsp:include page="templates/nav.jsp"/>
<div class="d-flex flex-column min-vh-100 flex-grow-1">
    <div class="wrapper flex-grow-1">
        <%
            List<Order> orderList = (List<Order>) request.getAttribute("orders");
            String orderId = (String) request.getAttribute("order_id");
            String personId = (String) request.getAttribute("person_id");
            String isAdmin = (String) request.getAttribute("is_admin");
            boolean isAdminValue = Boolean.parseBoolean(isAdmin);

            // Сортировка по order ID
            orderList.sort(Comparator.comparingInt(Order::getId).reversed());

            // Если указан orderId, показываем только этот заказ
            if (orderId != null) {
                int orderIdValue = Integer.parseInt(orderId);
                for (Order order : orderList) {
                    if (order.getId() == orderIdValue) {
                        request.setAttribute("order", order);
    %>

    <jsp:include page="templates/order-card.jsp"/>
    <%
            }
        }
        } else if (!orderList.isEmpty()) {
            boolean hasOrders = false;

            // Если указан personId, фильтруем заказы по id пользователя или показываем все для администратора
            if (personId != null && isAdmin != null) {
                int personIdValue = Integer.parseInt(personId);

                for (Order order : orderList) {
                    if ((order.getPersonId() == personIdValue) || isAdminValue) {
                        request.setAttribute("order", order);
                        hasOrders = true;
        %>

        <jsp:include page="templates/order-card.jsp"/>

        <%
                }
            }

            if (!hasOrders) {
        %>
        <div class="col mx-4">
            <p><fmt:message bundle="${lang}" key="lang.orders.no_orders"/>.</p>
        </div>
        <%
            }

            // Если нет personId и isAdmin, показываем все заказы
        } else {
            for (Order order : orderList) {
                request.setAttribute("order", order);
        %>
        <jsp:include page="templates/order-card.jsp"/>
        <%
                }
            }
        } else {
        %>
        <div class="col mx-4">
            <p><fmt:message bundle="${lang}" key="lang.orders.no_orders"/>.</p>
        </div>
        <%
            }
        %>
    </div>
    <jsp:include page="templates/footer.jsp"/>
</div>
</body>
