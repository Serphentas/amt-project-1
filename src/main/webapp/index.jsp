
<%--
  Created by IntelliJ IDEA.
  User: Rosy
  Date: 21/09/2020
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


<body class="landing-page sidebar-collapse">

<!-- Inclusion du header sur le haut de page -->
<jsp:include page="WEB-INF/view/fragments/header.jsp" flush="true"/>

<div class="page-header header-filter" data-parallax="true" style="background-image: url('<c:url value="/assets/img/profile_city.jpg"/>')">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1 class="title">Let us answer your coding questions.</h1>
                <h4>This is our own version of the StackOverflow web application.</h4>
            </div>
        </div>
    </div>
</div>

<!-- Inclusion du home content dans la page -->
<jsp:include page="WEB-INF/view/fragments/home_content.jsp" flush="true"/>

<!-- Inclusion du footer sur le pied de page -->
<jsp:include page="WEB-INF/view/fragments/footer.jsp" flush="true"/>

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