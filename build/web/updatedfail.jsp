<%-- 
    Document   : updatedfail
    Created on : Apr 14, 2023, 9:37:03 AM
    Author     : Fstore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p style="color: red;font-size: 50px"><%= request.getAttribute("DUPLICATE_ACCOUNT") %></p>
        <a href="search.jsp">Please come back and update another name</a>
    </body>
</html>
