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
                    <h3 class="description text-left text-uppercase font-weight-bold text-dark">${question.title}</h3>
                    <p class="description text-left text-dark"><i class="fas fa-comment-dots"></i> ${question.text}</p><br>
                    <p class="description text-left text-dark"><i class="fas fa-signal"></i> Votes: ${votes} </p><br>

                    <h3><u>Comments</u></h3>
                    <c:forEach var="comment" items="${comments.comments}">
                        <div class="row">
                            <div class="col-md-12">
                                <p class="description text-left text-dark ">
                                    <i class="fas fa-comment-alt"></i> <i>${comment.author}</i> - ${comment.text}
                                </p>
                            </div>
                        </div>
                    </c:forEach>

                    <h3><u>Answers</u></h3>
                    <c:forEach var="answer" items="${answers.answers}">
                        <div class="row">
                            <div class="col-md-12">
                                <p class="description text-left text-dark ">
                                    <i class="fas fa-hand-pointer"></i> <i>${answer.author}</i> - ${answer.text}
                                </p>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="row">
                        <div class="col-md-12">
                            <a href="/submitAnswer?id=${question.id.asString()}" class="btn btn-outline-secondary">Answer</a>
                            <a href="/submitComment?id=${question.id.asString()}" class="btn btn-outline-secondary">Comment</a>
                            <a href="/questions" class="btn btn-outline-secondary">Back to question list</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:base>
