<%--
  Created by IntelliJ IDEA.
  User: joshua
  Date: 21.09.2020
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="login.jsp" method="post">
        <div>
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="uname" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required>

            <button type="submit">Login</button>
            <label>
                <input type="checkbox" checked="checked" name="remember"> Remember me
            </label>
        </div>

        <div style="background-color:#f1f1f1">
            <button type="button">Cancel</button>
            <span>Forgot <a href="#">password?</a></span>
        </div>
    </form>
</body>
</html>
