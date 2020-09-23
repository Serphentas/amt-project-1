<%--
  Created by IntelliJ IDEA.
  User: dacoj
  Date: 23.09.2020
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profil</title>
</head>
<body>
<h2>Account</h2>
<ul>
    <c:forEach items="${account}" var="account">
        <li>${account.username} : "${account.password}"</li>
    </c:forEach>
</ul>
</body>
</html>
