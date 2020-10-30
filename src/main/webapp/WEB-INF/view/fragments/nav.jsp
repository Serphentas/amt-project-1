<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-transparent navbar-color-on-scroll fixed-top navbar-expand-lg" color-on-scroll="100" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="navbar-brand" href="../">
                Our StackOverflow </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="sr-only">Toggle navigation</span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="dropdown nav-item">
                    <a href="" class="dropdown-toggle nav-link" data-toggle="dropdown">
                        <i class="material-icons">apps</i> Menu
                    </a>
                    <div class="dropdown-menu dropdown-with-icons">
                        <a href="<c:url value="/submitQuestion"/>" class="dropdown-item">
                            <i class="material-icons">layers</i> Ask your questions
                        </a>
                        <a href="<c:url value="/questions"/>" class="dropdown-item">
                            <i class="material-icons">article</i> View questions list
                        </a>
                        <a href="<c:url value="/profil"/>" class="dropdown-item">
                            <i class="material-icons">content_paste</i> Your profil
                        </a>
                    </div>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${currentUser != null}">
                            <div>
                                    ${currentUser.firstName} ${currentUser.lastName}
                            </div>
                            <form method="post" action="logout.do">
                                <button role="submit" >Logout</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <a class="nav-link" href="<c:url value="/login"/>">
                                    Sign up/in
                                </a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
            <form class="form-inline ml-auto" action="/search" method="GET">
                <div class="form-group no-border">
                    <input id="title" name="title" type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-white btn-just-icon btn-round">
                    <i class="material-icons">search</i>
                </button>
            </form>
        </div>
    </div>
</nav>

