<%--
  Created by IntelliJ IDEA.
  User: dacoj
  Date: 22.10.2020
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>.
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:useBean scope="request" id="question" type="stackoverflow.application.question.QuestionsDTO.QuestionDTO"/>

<t:base>
    <div class="page-header header-filter" style="background-image: url('../assets/img/bg.jpg'); background-size: cover; background-position: top center;">
        <div class="container">
            <div class="card card-login">
                <form id="newAnswer" method="POST" action="submitAnswer.do" class="form">
                    <input type="hidden" id="fId" name="id" value="${question.id.asString()}">
                    <div class="card-header card-header-primary text-center">
                        <h4 class="card-title">Answer a Question</h4>
                    </div>
                    <p class="description text-center">the question</p>
                    <div class="card-body text-center">
                        <p>${question.text}</p>
                    </div>
                    <div class="card-body text-center">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                  <i class="material-icons">answer</i>
                                </span>
                            </div>
                            <textarea class="form-control" id ="tfText" name="text" form="newAnswer" rows="4" placeholder="Content..."></textarea>
                        </div>
                    </div>
                    <div class="footer text-center">
                        <button id="bSubmitAnswer" type="submit" class="btn btn-primary btn-link btn-wd btn-lg">Submit Answer</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</t:base>
