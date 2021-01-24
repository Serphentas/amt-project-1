<%--
  Created by IntelliJ IDEA.
  User: dacoj
  Date: 23.09.2020
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base>
    <div class="page-header header-filter" data-parallax="true" style="background-image: url('../assets/img/city-profile.jpg');"></div>
    <div class="main main-raised">
        <div class="profile-content">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 ml-auto mr-auto">
                        <div>
                            <h1>Statistiques Globales</h1>
                            <div>
                                <h3>Top 10 des utilisateurs :</h3>
                                <c:forEach var = "user" items = "${top}">
                                    <div class="card">${user}</div>
                                </c:forEach>
                            </div>
                            <div>
                                <h3>Nombre de commentaires : ${nbComment}</h3>
                            </div>
                            <div>
                                <h3>Nombre de votes : ${nbVote}</h3>
                            </div>
                            <div>
                                <h3>Nombre de réponses : ${nbAnswer}</h3>
                            </div>
                            <div>
                                <h3>Nombre de questions : ${nbQuestion}</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
            <c:when test="${currentUser != null}">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 ml-auto mr-auto">
                            <div>
                                <h1>Statistiques personnelles</h1>
                                <div>
                                    <h3>Nombre de vos commentaires : ${nbCommentOfUser}</h3>
                                </div>
                                <div>
                                    <h3>Nombre de vos votes : ${nbVoteOfUser}</h3>
                                </div>
                                <div>
                                    <h3>Nombre de vos réponses : ${nbAnswerOfUser}</h3>
                                </div>
                                <div>
                                    <h3>Nombre de vos questions : ${nbQuestionOfUser}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            </c:choose>
        </div>
    </div>
</t:base>