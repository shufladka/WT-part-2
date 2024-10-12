<%--
  Created by IntelliJ IDEA.
  User: klezo
  Date: 12.10.2024
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="by.bsuir.entity.Hotel" %>
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
          Object hotelsReq = request.getAttribute("hotels");
          Object addressesReq = request.getAttribute("addresses");
          Object idReq = request.getAttribute("id");

          if (hotelsReq != null && addressesReq != null) {
              List<Hotel> hotelList = (List<Hotel>) hotelsReq;
              String id = (String) idReq;

              request.setAttribute("addresses", addressesReq);

              if (id == null) {
                for (Hotel hotel : hotelList) {
                    request.setAttribute("hotel", hotel);
      %>

      <jsp:include page="templates/hotel-card.jsp" />

      <%
                  }
              } else {
                  try {
                      request.setAttribute("hotel", hotelList.get(Integer.parseInt(id)));
      %>

      <jsp:include page="templates/hotel-card.jsp" />

      <%
          }
                  catch (Exception e)
          {
      %>

      <div class="col mx-4">
          <p>No hotels available.</p>
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
