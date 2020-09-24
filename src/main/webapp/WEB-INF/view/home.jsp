<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rosy
  Date: 21/09/2020
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/assets/img/apple-icon.png"/>" />
    <link rel="icon" type="image/png" href="<c:url value="/assets/img/favicon.png"/>" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>
        Material Kit by Creative Tim
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

<body class="landing-page sidebar-collapse">

<!-- Inclusion du header sur le haut de page -->
<jsp:include page="fragments/header.jsp" flush="true"/>

<div class="page-header header-filter" data-parallax="true" style="background-image: url('/assets/img/profile_city.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1 class="title">Your Story Starts With Us.</h1>
                <h4>Every landing page needs a small description after the big bold title, that&apos;s why we added this text here. Add here all the information that can make you or your product create the first impression.</h4>
                <br>
                <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank" class="btn btn-danger btn-raised btn-lg">
                    <i class="fa fa-play"></i> Watch video
                </a>
            </div>
        </div>
    </div>
</div>
<div class="main main-raised">
    <div class="container">
        <div class="section text-center">
            <div class="row">
                <div class="col-md-8 ml-auto mr-auto">
                    <h2 class="title">Let&apos;s talk product</h2>
                    <h5 class="description">This is the paragraph where you can write more details about your product. Keep you user engaged by providing meaningful information. Remember that by this time, the user is curious, otherwise he wouldn&apos;t scroll to get here. Add a button if you want the user to see more.</h5>
                </div>
            </div>
            <div class="features">
                <div class="row">
                    <div class="col-md-4">
                        <div class="info">
                            <div class="icon icon-info">
                                <i class="material-icons">chat</i>
                            </div>
                            <h4 class="info-title">Free Chat</h4>
                            <p>Divide details about your product or agency work into parts. Write a few lines about each one. A paragraph describing a feature will be enough.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="info">
                            <div class="icon icon-success">
                                <i class="material-icons">verified_user</i>
                            </div>
                            <h4 class="info-title">Verified Users</h4>
                            <p>Divide details about your product or agency work into parts. Write a few lines about each one. A paragraph describing a feature will be enough.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="info">
                            <div class="icon icon-danger">
                                <i class="material-icons">fingerprint</i>
                            </div>
                            <h4 class="info-title">Fingerprint</h4>
                            <p>Divide details about your product or agency work into parts. Write a few lines about each one. A paragraph describing a feature will be enough.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="section text-center">
            <h2 class="title">Here is our team</h2>
            <div class="team">
                <div class="row">
                    <div class="col-md-4">
                        <div class="team-player">
                            <div class="card card-plain">
                                <div class="col-md-6 ml-auto mr-auto">
                                    <img src="../assets/img/faces/avatar.jpg" alt="Thumbnail Image" class="img-raised rounded-circle img-fluid">
                                </div>
                                <h4 class="card-title">Gigi Hadid
                                    <br>
                                    <small class="card-description text-muted">Model</small>
                                </h4>
                                <div class="card-body">
                                    <p class="card-description">You can write here details about one of your team members. You can give more details about what they do. Feel free to add some
                                        <a href="#">links</a> for people to be able to follow them outside the site.</p>
                                </div>
                                <div class="card-footer justify-content-center">
                                    <a href="#pablo" class="btn btn-link btn-just-icon"><i class="fa fa-twitter"></i></a>
                                    <a href="#pablo" class="btn btn-link btn-just-icon"><i class="fa fa-instagram"></i></a>
                                    <a href="#pablo" class="btn btn-link btn-just-icon"><i class="fa fa-facebook-square"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="team-player">
                            <div class="card card-plain">
                                <div class="col-md-6 ml-auto mr-auto">
                                    <img src="../assets/img/faces/christian.jpg" alt="Thumbnail Image" class="img-raised rounded-circle img-fluid">
                                </div>
                                <h4 class="card-title">Christian Louboutin
                                    <br>
                                    <small class="card-description text-muted">Designer</small>
                                </h4>
                                <div class="card-body">
                                    <p class="card-description">You can write here details about one of your team members. You can give more details about what they do. Feel free to add some
                                        <a href="#">links</a> for people to be able to follow them outside the site.</p>
                                </div>
                                <div class="card-footer justify-content-center">
                                    <a href="#pablo" class="btn btn-link btn-just-icon"><i class="fa fa-twitter"></i></a>
                                    <a href="#pablo" class="btn btn-link btn-just-icon"><i class="fa fa-linkedin"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="team-player">
                            <div class="card card-plain">
                                <div class="col-md-6 ml-auto mr-auto">
                                    <img src="../assets/img/faces/kendall.jpg" alt="Thumbnail Image" class="img-raised rounded-circle img-fluid">
                                </div>
                                <h4 class="card-title">Kendall Jenner
                                    <br>
                                    <small class="card-description text-muted">Model</small>
                                </h4>
                                <div class="card-body">
                                    <p class="card-description">You can write here details about one of your team members. You can give more details about what they do. Feel free to add some
                                        <a href="#">links</a> for people to be able to follow them outside the site.</p>
                                </div>
                                <div class="card-footer justify-content-center">
                                    <a href="#pablo" class="btn btn-link btn-just-icon"><i class="fa fa-twitter"></i></a>
                                    <a href="#pablo" class="btn btn-link btn-just-icon"><i class="fa fa-instagram"></i></a>
                                    <a href="#pablo" class="btn btn-link btn-just-icon"><i class="fa fa-facebook-square"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="section section-contacts">
            <div class="row">
                <div class="col-md-8 ml-auto mr-auto">
                    <h2 class="text-center title">Work with us</h2>
                    <h4 class="text-center description">Divide details about your product or agency work into parts. Write a few lines about each one and contact us about any further collaboration. We will responde get back to you in a couple of hours.</h4>
                    <form class="contact-form">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Your Name</label>
                                    <input type="email" class="form-control">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Your Email</label>
                                    <input type="email" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="exampleMessage" class="bmd-label-floating">Your Message</label>
                            <textarea type="email" class="form-control" rows="4" id="exampleMessage"></textarea>
                        </div>
                        <div class="row">
                            <div class="col-md-4 ml-auto mr-auto text-center">
                                <button class="btn btn-primary btn-raised">
                                    Send Message
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Inclusion du header sur le pied de page -->
<jsp:include page="fragments/footer.jsp" flush="true"/>

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