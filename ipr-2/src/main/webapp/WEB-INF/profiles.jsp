<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 13.10.2024
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="by.bsuir.entity.Hotel" %>
<%@ page import="by.bsuir.entity.Person" %>
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
      List<Person> people = (List<Person>) request.getAttribute("people");
      String id = (String) request.getAttribute("id");

      if (people != null) {
        if (id == null) {
          for (Person person : people) {
            request.setAttribute("person", person);
    %>

    <jsp:include page="templates/person-card.jsp" />

    <%
      }
    } else {
      try {
        int index = Integer.parseInt(id);
        for (Person person : people) {
          if (person.getId() == index) {
            request.setAttribute("person", person);
          }
        }
    %>

    <jsp:include page="templates/person-card.jsp" />

    <%
    } catch (NumberFormatException e) {
    %>
    <div class="col mx-4">
      <p>Invalid person ID format.</p>
    </div>
    <%
    } catch (IndexOutOfBoundsException e) {
    %>
    <div class="col mx-4">
      <p>No people available.</p>
    </div>
    <%
        }
      }
    } else {
    %>
    <div class="col mx-4">
      <p>No people available.</p>
    </div>
    <%
      }
    %>
  </div>
  <jsp:include page="templates/footer.jsp"/>
</div>

