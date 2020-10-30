<%--
  Created by IntelliJ IDEA.
  User: dacoj
  Date: 26.09.2020
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header card-header-danger">
        <h4 class="card-title">Error</h4>
        <p class="category">Authentication error</p>
    </div>
    <div class="card-body">
        <c:forEach var="error" items="${errors}">
            <div>${error}</div>
        </c:forEach>
    </div>
</div>
