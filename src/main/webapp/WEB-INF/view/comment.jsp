<%--
  Created by IntelliJ IDEA.
  User: dacoj
  Date: 30/09/2020
  Time: 13:03
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>.
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <div class="page-header header-filter" style="background-image: url('../assets/img/bg.jpg'); background-size: cover; background-position: top center;">
        <div class="container">
            <div class="card card-login">
                <form id="newComment" method="POST" action="submitComment.do" class="form">
                    <input type="hidden" id="fId" name="id" value="${entity.id.asString()}">
                    <input type="hidden" id="fEntity" name="entity" value="${entityName}">
                    <div class="card-header card-header-primary text-center">
                        <h4 class="card-title">Comment a Question</h4>
                    </div>
                    <p class="description text-center">the ${entityName}</p>
                    <div class="card-body text-center">
                        <p>${entity.text}</p>
                    </div>
                    <div class="card-body text-center">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                  <i class="material-icons">comment</i>
                                </span>
                            </div>
                            <textarea class="form-control" id ="tfText" name="text" form="newComment" rows="4" placeholder="Content..."></textarea>
                        </div>
                    </div>
                    <div class="footer text-center">
                        <button id="bSubmitComment" type="submit" class="btn btn-primary btn-link btn-wd btn-lg">Submit Comment</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</t:base>