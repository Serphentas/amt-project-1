
<%--
  Created by IntelliJ IDEA.
  User: rosy
  Date: 12/10/2020
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:useBean scope="request" id="questions" type="stackoverflow.application.question.QuestionsDTO"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base>
    <div class="page-header header-filter" style="background-image: url('/assets/img/bg.jpg'); background-size: cover; background-position: top center;">
        <div class="container">
            <div class="card card-login">
                <div class="card-header card-header-primary text-center">
                    <h4 class="card-title">Questions List</h4>
                </div>
                <p class="description text-center">View the questions list below</p>
                    <div class="title">Questions</div>
                    <c:forEach var="question" items="${questions.questions}">
                        <div class="card-body text-center">
                            <p class="description text-left">${question.title}</p><br>
                            <p class="description text-left">${question.text}</p><br>
                        </div>
                    </c:forEach>
            </div>

        </div>
    </div>
</t:base>