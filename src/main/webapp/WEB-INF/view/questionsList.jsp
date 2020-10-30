
<%--
  Created by IntelliJ IDEA.
  User: rosy
  Date: 12/10/2020
  Time: 10:08
  To change this template use File | Settings | File Templates.


--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="questions" type="stackoverflow.application.question.QuestionsDTO"/>

<t:base>
    <div class="page-header header-filter" data-parallax="true" style="background-image: url('/assets/img/city-profile.jpg');">
        <div class="container">
            <div class="row">
                <div class="col-md-8 ml-auto mr-auto">
                    <div class="brand text-center text-light">
                        <h1 class="description text-center text-light text-capitalize" >Questions List</h1>
                        <h5 class="description text-center text-light text-uppercase">View the questions list below</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main main-raised bg-dark">
        <div class="container">
            <div class="section">
                <c:forEach var="question" items="${questions.questions}">
                    <div class="alert alert-secondary" role="alert">
                        <a class="nav-link" role="tab" data-toggle="tab">
                            <h3 class="description text-left text-uppercase font-weight-bold text-dark"><i>${question.title}</i></h3>
                            <p class="description text-left text-dark ">${question.text}</p>
                            <div class="row">
                                <div class="col-md-12">
                                    <a href="/submitAnswer?id=${question.id.asString()}" class="btn btn-outline-secondary">Answer</a>
                                    <a href="/submitComment?id=${question.id.asString()}" class="btn btn-outline-secondary" id="commentBtn">Comment</a>
                                    <a href="/question?id=${question.id.asString()}" class="btn btn-outline-secondary">Go to question</a>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</t:base>
