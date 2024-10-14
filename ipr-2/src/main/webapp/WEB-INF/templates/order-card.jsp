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
<%@ page import="by.bsuir.entity.Order" %>

<%
  Order order = (Order) request.getAttribute("order");
%>

<%= order.toString() %>