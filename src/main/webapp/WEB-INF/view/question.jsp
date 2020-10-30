<%--
  Created by IntelliJ IDEA.
  User: rosy
  Date: 29/10/2020
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:useBean scope="request" id="question" type="stackoverflow.application.question.QuestionsDTO.QuestionDTO"/>
<jsp:useBean scope="request" id="answers" type="stackoverflow.application.answer.AnswersDTO"/>
<jsp:useBean scope="request" id="comments" type="stackoverflow.application.comment.CommentsDTO"/>


<t:base>
    <div class="page-header header-filter" data-parallax="true" style="background-image: url('/assets/img/city-profile.jpg');">
        <div class="container">
            <div class="row">
                <div class="col-md-8 ml-auto mr-auto">
                    <div class="brand text-center text-light">
                        <h1 class="description text-center text-light text-capitalize" >Question</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main main-raised bg-dark">
        <div class="container">
            <div class="section">
                <div class="alert alert-secondary" role="alert">
                    <a class="nav-link" role="tab" data-toggle="tab">
                    <i class="material-icons">camera</i>
                    <h3 class="description text-left text-uppercase font-weight-bold text-dark">${question.title}</h3>
                    <p class="description text-left text-dark ">${question.text}</p><br>
                    <p class="description text-left text-dark "> Votes : ${votes} </p><br>
                    <p class="description text-left text-dark "> Comments :  </p><br>
                    <c:forEach var="comment" items="${comments.comments}">
                        <p class = "description text-left text-dark ">${comment.text}</p>
                    </c:forEach>
                    <div>
                        <a href="/submitComment?id=${question.id.asString()}" class="btn btn-outline-secondary">Comment</a>
                    </div>
                     <c:forEach var="answer" items="${answers.answers}">
                        <div class="row">
                            <p class = "description text-left text-dark ">${answer.text}</p>
                            <div class="column">
                                <div>
                                    <a href="/submitAnswer?id=${question.id.asString()}" class="btn btn-outline-secondary">Answer</a>
                                </div>
                            </div>
                            <div class="column">
                                <div>
                                    <a href="/submitComment?id=${answer.id.asString()}" class="btn btn-outline-secondary">Comment</a>
                                </div>
                            </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</t:base>
