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
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/assets/img/apple-icon.png"/>" />
    <link rel="icon" type="image/png" href="<c:url value="/assets/img/favicon.png"/>" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>
        HomePage
    </title>
    <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css" href="<c:url value="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons"/>" />
    <link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />" />
    <!-- CSS Files -->
    <link href="<c:url value="/assets/css/material-kit.css?v=2.0.7" />" rel="stylesheet"  />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="<c:url value="/assets/demo/demo.css"/>" rel="stylesheet" />
</head>

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
                            <h4 class="card-title">Sign up</h4>
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
                            <input id="fLogin" type="submit" class="btn btn-primary btn-link btn-wd btn-lg" value="Login">
                        </div>
                    </form>
                </div>
                <div class="card card-login">
                    <form class="form" method="post" action="register.do">
                        <div class="card-header card-header-primary text-center">
                            <h4 class="card-title">Sign in</h4>
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
                            <input id="fRegister" type="submit" class="btn btn-primary btn-link btn-wd btn-lg" value="Register">
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
<!--   Core JS Files   -->
<script src="<c:url value="/assets/js/core/jquery.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/assets/js/core/popper.min.js"  />" type="text/javascript"></script>
<script src="<c:url value="/assets/js/core/bootstrap-material-design.min.js"  />" type="text/javascript"></script>
<script src="<c:url value="/assets/js/plugins/moment.min.js"/>"></script>
<!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
<script src="<c:url value="/assets/js/plugins/bootstrap-datetimepicker.js" />" type="text/javascript"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="<c:url value="/assets/js/plugins/nouislider.min.js" />" type="text/javascript"></script>
<!--  Google Maps Plugin    -->
<!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
<script src="<c:url value="/assets/js/material-kit.js?v=2.0.7" />" type="text/javascript"></script>
</body>

</html>
