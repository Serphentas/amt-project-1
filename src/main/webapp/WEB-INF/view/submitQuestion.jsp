<%--
  Created by IntelliJ IDEA.
  User: rosy
  Date: 30/09/2020
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <div class="page-header header-filter" style="background-image: url('../assets/img/bg.jpg'); background-size: cover; background-position: top center;">
        <div class="container">
             <div class="card card-login">
                <form id="newQuestion" method="POST" action="submitQuestion.do" class="form">
                    <div class="card-header card-header-primary text-center">
                        <h4 class="card-title">Question</h4>
                    </div>
                    <p class="description text-center">Ask any question you want below</p>
                    <div class="card-body text-center">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                  <i class="material-icons">label</i>
                                </span>
                            </div>
                            <input class="form-control" id ="tfTitle" name="title" form="newQuestion" type="text" placeholder="Title..." >
                        </div>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                  <i class="material-icons">description</i>
                                </span>
                            </div>
                            <textarea class="form-control" id ="tfText" name="text" form="newQuestion" rows="4" placeholder="Content..."></textarea>
                        </div>
                    </div>
                    <div class="footer text-center">
                        <button id="bSubmitQuestion" type="submit" class="btn btn-primary btn-link btn-wd btn-lg">Submit Question</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</t:base>

