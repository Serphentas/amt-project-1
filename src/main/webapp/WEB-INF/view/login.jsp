<%--
  Created by IntelliJ IDEA.
  User: joshua
  Date: 21.09.2020
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <div class="page-header header-filter" style="background-image: url('/assets/img/bg7.jpg'); background-size: cover; background-position: top center; height: 100vh;">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="card card-login">
                        <form class="form" method="post" action="login.do">
                            <div class="card-header card-header-primary text-center">
                                <h4 class="card-title">Sign in</h4>
                            </div>
                            <p class="description text-center">Log in to access the community</p>
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
                                <br>
                                <label>
                                    <input type="checkbox" checked="checked" name="remember"> Remember me
                                </label>
                                <br/>
                                <button id="fLogin" role="submit" class="btn btn-primary btn-link btn-wd btn-lg" >Login</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col">
                    <div class="card card-login">
                        <form class="form" method="post" action="register.do">
                            <div class="card-header card-header-primary text-center">
                                <h4 class="card-title">Sign up</h4>
                            </div>
                            <p class="description text-center">Create your account to access our service</p>
                            <div class="card-body">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="fas fa-grin-alt"></i>
                                    </span>
                                    </div>
                                    <input id="fUsername" name="username" type="text" class="form-control" placeholder="Username...">
                                </div>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fas fa-envelope"></i>
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
                                    <small>Your password must be 8 characters long, in which there must be one small and uppercase letter, a digit, a special character (like $).</small>
                                </div>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                      <i class="material-icons">lock_outline</i>
                                    </span>
                                    </div>
                                    <input id="fConfirmPassword" name="confirmPassword" type="password" class="form-control" placeholder="Confirm Password...">
                                </div>

                                <div class="footer text-center">
                                    <br>
                                    <label>
                                        <input type="checkbox" checked="checked" name="remember"> Remember me
                                    </label>
                                    <br>
                                    <button id="fRegister" role="submit"  class="btn btn-primary btn-link btn-wd btn-lg">Register</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <jsp:include page="fragments/errors.jsp" flush="true"/>
            </div>
        </div>
    </div>
</t:base>