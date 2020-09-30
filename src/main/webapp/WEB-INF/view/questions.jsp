<%--
  Created by IntelliJ IDEA.
  User: rosy
  Date: 30/09/2020
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>.
<jsp:useBean scope="request" id="questions" type="stackoverflow.application.question.QuestionsDTO"/>

<!DOCTYPE html>
<html lang="en">

<!--Inclusion du head avec importation des fichiers css utiles-->
<jsp:include page="fragments/head.jsp" flush="true"/>

<body class="login-page sidebar-collapse">

<!-- Inclusion du header sur le haut de page -->
<jsp:include page="fragments/header.jsp" flush="true"/>

<br>

<div class="columns">
    <div class = "authoring">
        <div class="title">Submit your questions</div>
        <form id="newQuestion" method="POST" action="submitQuestion.do">
            <textarea id ="tfText" name="text" form="newQuestion" rows="4"></textarea>
            <button id="bSubmitQuestion" type="submit">Submit Question</button>
        </form>
    </div>
    <div class="questions">
        <div class=title">Questions</div>
        <c:forEach var="question" items="${questions.questions}">
            <div class = "question">${question.text}</div>
        </c:forEach>
    </div>
</div>

<br>
<!-- Inclusion du footer sur le pied de page -->
<jsp:include page="fragments/footer.jsp" flush="true"/>
<!-- Inclusion des core scripts utiles -->
<jsp:include page="fragments/scripts.jsp" flush="true"/>

</html>

