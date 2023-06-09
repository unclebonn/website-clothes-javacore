<%-- 
    Document   : register
    Created on : Apr 12, 2023, 8:23:32 AM
    Author     : Fstore
--%>

<%@page import="khoita.registration.RegistrationErrors"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>     
        <h1>Registration</h1>
        <form action="DispatchController" method="POST">

            <!--This error catch for username-->
            <c:if test="${not empty requestScope.RegisterErros.usernameLengthError}">
                <p style="color:red">${requestScope.RegisterErros.usernameLengthError}</p>

            </c:if>
            <c:if test="${not empty requestScope.RegisterErros.usernameExisted}">
                <p style="color:red">${requestScope.RegisterErros.usernameExisted}</p>

            </c:if>
            <label>Username</label></br>
            <input type="text" name="username" value="" /> (6-25 characters)</br>

            <!--This error catch for password-->
            <c:if test="${not empty requestScope.RegisterErros.passwordLengthError}">
                <p style="color:red">${requestScope.RegisterErros.passwordLengthError}</p>

            </c:if>
            <c:if test="${not empty requestScope.RegisterErros.confirmPassword}">
                <p style="color:red">${requestScope.RegisterErros.confirmPassword}</p>

            </c:if>
            <label>Password</label></br>
            <input type="password" name="password" value="" />(6-25 characters) </br>
            <label>Confirm Password</label></br>
            <input type="password" name="confirmPpassword" value="" /></br>
            <c:if test="${not empty requestScope.RegisterErros.fullnameLengthError}">
                <p style="color:red">${requestScope.RegisterErros.fullnameLengthError}</p>
            </c:if>

            <!--This error catch for fullname-->
            <label>Fullname</label></br>
            <input type="text" name="fullname" value="" />(6-25 characters)</br>
            <input type="submit" value="register" name="btAction" />

        </form>

    </body>
</html>
