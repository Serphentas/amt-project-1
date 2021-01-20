<%--
  Created by IntelliJ IDEA.
  User: dacoj
  Date: 23.09.2020
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <div class="page-header header-filter" data-parallax="true" style="background-image: url('../assets/img/city-profile.jpg');"></div>
    <div class="main main-raised">
        <div class="profile-content">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 ml-auto mr-auto">
                        <div class="profile">
                            <div class="name">
                                <h3 class="title">${currentUser.username}</h3>

                                <form class="form" method="post" action="profil.do">

                                    <h4> First name : ${currentUser.firstName}</h4>
                                    <div class="input-group">
                                        <input id="FirstName" name="FirstName" type="text" class="form-control" placeholder="${currentUser.firstName}">
                                    </div>
                                    <h4> Last name : ${currentUser.lastName}</h4>
                                    <div class="input-group">
                                        <input id="LastName" name="LastName" type="text" class="form-control" placeholder="${currentUser.lastName}">
                                    </div>
                                    <h4> Email : ${currentUser.email}</h4>
                                    <div class="input-group">
                                        <input id="email" name="email" type="text" class="form-control" placeholder="${currentUser.email}">
                                    </div>
                                    <button id="fProfilUpdate" role="submit"  class="btn btn-primary btn-link btn-wd btn-lg">update</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-6 ml-auto mr-auto">
                        <div>
                            <div>
                                <h3>Niveau : ${game.level}</h3>
                                <p>pts nécessaires pour le prochain niveau ${game.nbExp}</p>
                                <p>Exp : ${game.exp}</p>
                            </div>
                            <div>
                                <h3>vos Badges</h3>
                                <c:forEach var = "i" begin = "1" end = "5">
                                    <span>${i}</span>
                                </c:forEach>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:base>