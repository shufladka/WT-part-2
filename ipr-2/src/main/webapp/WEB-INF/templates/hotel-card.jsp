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
<div class="col mx-4">
    <div class="row mb-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
    </div>

    <div class="row mb-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
    </div>

    <div class="row mb-4">
        <div class="card p-0">
            <div class="row g-0 d-flex align-items-center">

                <!-- Блок с текстом слева -->
                <div class="col-md-8 d-flex align-items-center">
                    <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>
                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>

                <!-- Картинка справа -->
                <div class="col-md-4 text-end">
                    <img src="https://via.placeholder.com/150" width="150" height="150" class="img-fluid rounded-start" alt="...">
                </div>

            </div>
        </div>
    </div>
</div>
