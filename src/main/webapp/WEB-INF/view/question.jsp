<%--
  Created by IntelliJ IDEA.
  User: rosy
  Date: 29/10/2020
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="question" type="stackoverflow.application.question.QuestionsDTO.QuestionDTO"/>
<jsp:useBean scope="request" id="answers" type="stackoverflow.application.answer.AnswersDTO"/>

<html>
<head>
    <title>Question</title>
</head>
<body>

<h1>Titre : ${question.title}</h1>
<p> Contenu : ${question.text}</p>
<p> Votes : ${votes} </p>

<c:forEach var="answer" items="${answers.answers}">
    <div>${answer.text}</div>
</c:forEach>



</body>
</html>
