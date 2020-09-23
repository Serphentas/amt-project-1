<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rosy
  Date: 21/09/2020
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
    <title>Our StackOverFlow Homepage</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css" href="<c:url value="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />" />
    <link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/>" />
    <!-- Material Kit CSS -->
    <link type="text/css" rel="stylesheet" href="<c:url value="/assets/css/material-kit.css" />" />
</head>

<body>
<jsp:include page="fragments/header.jsp" />
<div class="page-header header-filter" data-parallax="true" style="background-image: url('assets/img/profile_city.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-md-8 ml-auto mr-auto">
                <div class="brand text-center">
                    <h1>Let us answer your questions</h1>
                    <h3 class="title text-center">Discover our own version of StackOverflow. It is StackOverflow but much much better guys!</h3>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="main main-raised">
    <div class="container">
        <div class="section text-center">
            <h2 class="title">Your main section here</h2>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp" />


</body>

</html>