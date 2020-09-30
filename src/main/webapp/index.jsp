
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

<!--Inclusion du head avec importation des fichiers css utiles-->
<jsp:include page="WEB-INF/view/fragments/head.jsp" flush="true"/>

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

<!-- Inclusion des core scripts utiles -->
<jsp:include page="WEB-INF/view/fragments/scripts.jsp" flush="true"/>


</body>
</html>