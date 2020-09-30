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

<div class="page-header header-filter" style="background-image: url('../assets/img/bg.jpg'); background-size: cover; background-position: top center;">
    <div class="container">
    <div class="row">
        <div class="col">
                <div class="card card-login">
                    <form id="newQuestion" method="POST" action="submitQuestion.do" class="form">
                        <div class="card-header card-header-primary text-center">
                            <h4 class="card-title">Question</h4>
                        </div>
                        <p class="description text-center">Ask any question you want below</p>
                        <div class="card-body text-center">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">face</i>
                                    </span>
                                </div>
                                <textarea class="form-control" id ="tfText" name="text" form="newQuestion" rows="4"></textarea>
                            </div>
                        </div>
                        <div class="footer text-center">
                            <button id="bSubmitQuestion" type="submit" class="btn btn-primary btn-link btn-wd btn-lg">Submit Question</button>
                        </div>
                    </form>
                </div>
        </div>
        <div class="col">
                <div class="card card-login">
                    <div class="card-header card-header-primary text-center">
                        <h4 class="card-title">Questions List</h4>
                    </div>
                    <p class="description text-center">View the questions list below</p>
                    <div class="card-body text-center">
                        <div class="title">Questions</div>
                        <c:forEach var="question" items="${questions.questions}">
                            <button  class="btn btn-primary btn-sm">${question.text}</button><br>
                        </c:forEach>
                    </div>
                    <div>
                        <br>
                    </div>
                </div>
        </div>
    </div>
    </div>


<br>
<!-- Inclusion du footer sur le pied de page -->
<jsp:include page="fragments/footer.jsp" flush="true"/>
<!-- Inclusion des core scripts utiles -->
<jsp:include page="fragments/scripts.jsp" flush="true"/>

</html>

