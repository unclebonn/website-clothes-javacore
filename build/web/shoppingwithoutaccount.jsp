<%-- 
    Document   : shopping
    Created on : Apr 17, 2023, 5:55:24 PM
    Author     : Fstore
--%>

<%@page import="java.util.Map"%>
<%@page import="khoita.product.ProductDTO"%>
<%@page import="khoita.product.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>BookStore</h1>
        <form action="DispatchController">
            <label>Choose book</label>
            <select name="cboBook">
                <option>Servlet</option>
                <option>Tomcat</option>
                <option>JSP</option>
                <option>MVC</option>
                <option>JavaEE</option>
                <option>EL</option>
                <option>EJB2</option>
            </select></br>
            <input type="submit" value="Add to your cart" name="btAction" /></br>
            <input type="submit" value="View cart" name="btAction" />
        </form>
    </body>
</html>
