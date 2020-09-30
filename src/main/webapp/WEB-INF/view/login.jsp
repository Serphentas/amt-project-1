<%--
  Created by IntelliJ IDEA.
  User: joshua
  Date: 21.09.2020
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<!--Inclusion du head avec importation des fichiers css utiles-->
<jsp:include page="fragments/head.jsp" flush="true"/>
<body class="login-page sidebar-collapse">

<!-- Inclusion du header sur le haut de page -->
<jsp:include page="fragments/header.jsp" flush="true"/>

<div class="page-header header-filter" style="background-image: url(<c:url value="/assets/img/bg7.jpg"/>); background-size: cover; background-position: top center;">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-6 ml-auto mr-auto">
                <div class="card card-login">
                    <form class="form" method="post" action="login.do">
                        <div class="card-header card-header-primary text-center">
                            <h4 class="card-title">Login</h4>
                        </div>
                        <p class="description text-center">Sign in :D </p>
                        <div class="card-body">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">face</i>
                                    </span>
                                </div>
                                <input id="fLoginUsername" name="username" type="text" class="form-control" placeholder="Username...">
                            </div>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">lock_outline</i>
                                    </span>
                                </div>
                                <input id="fLoginPassword" name="password" type="password" class="form-control" placeholder="Password...">
                            </div>
                        </div>
                        <div class="footer text-center">
                            <label>
                                <input type="checkbox" checked="checked" name="remember"> Remember me
                            </label>
                            <br/>
                            <input id="fLogin" type="submit" class="btn btn-primary btn-link btn-wd btn-lg" value="Get Started">
                        </div>
                    </form>
                </div>
                <div class="card card-login">
                    <form class="form" method="post" action="register.do">
                        <div class="card-header card-header-primary text-center">
                            <h4 class="card-title">Register</h4>
                        </div>
                        <p class="description text-center">Nice to meet you :D </p>
                        <div class="card-body">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">face</i>
                                    </span>
                                </div>
                                <input id="fUsername" name="username" type="text" class="form-control" placeholder="Username...">
                            </div>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">face</i>
                                    </span>
                                </div>
                                <input id="fEmail" name="email" type="text" class="form-control" placeholder="Email...">
                            </div>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">face</i>
                                    </span>
                                </div>
                                <input id="fFirstName" name="firstName" type="text" class="form-control" placeholder="FirstName...">
                            </div>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">face</i>
                                    </span>
                                </div>
                                <input id="fLastName" name="lastName" type="text" class="form-control" placeholder="LastName...">
                            </div>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">lock_outline</i>
                                    </span>
                                </div>
                                <input id="fPassword" name="password" type="password" class="form-control" placeholder="Password...">
                            </div>
                        </div><!--end of card body-->
                        <div class="footer text-center">
                            <label>
                                <input type="checkbox" checked="checked" name="remember"> Remember me
                            </label>
                            <br/>
                            <input id="fRegister" type="submit" class="btn btn-primary btn-link btn-wd btn-lg" value="Get Started">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="fragments/errors.jsp" flush="true"/>

    <!-- Inclusion du footer sur le pied de page -->
    <jsp:include page="fragments/footer.jsp" flush="true"/>
</div>

<!-- Inclusion des core scripts utiles -->
<jsp:include page="fragments/scripts.jsp" flush="true"/>

</body>

</html>
