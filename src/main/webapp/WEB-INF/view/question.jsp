<%--
  Created by IntelliJ IDEA.
  User: rosy
  Date: 29/10/2020
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Question</title>
</head>
<body>

<h1>Titre : ${question.title}</h1>
<p> Contenu : ${question.content}</p>
<p> Votes : ${votes}</p>

<c:forEach var="answer" items="${answers}">
    <div>${answer}</div>
</c:forEach>

</body>
</html>
