<%-- 
    Document   : viewcartwithoutaccount
    Created on : Apr 19, 2023, 10:00:31 AM
    Author     : Fstore
--%>

<%@page import="java.util.Map"%>
<%@page import="khoita.cartsession.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your Cart!</h1>
        <c:set var="result" value="${requestScope.ProductWTC}" />
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ProductID</th>
                        <th>Productname</th>
                        <th>ProductQuantity</th>
                        <th>RemoveItem</th>
                    </tr>
                </thead>
                <tbody>
                <form action="DispatchController" method="GET">
                    <c:forEach var="product" items="${result}" varStatus="counter"> 


                        <tr>
                            <td>${counter.count} </td>
                            <td>${product.key}</td>
                            <td>${product.value}</td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${product.key}" />
                            </td>

                        </tr>
                    </c:forEach>

                    <tr>
                        <td colspan="2"> <a href="shoppingwithoutaccount.jsp">Back to the shopping cart</a></td>
                        <td colspan="2"> <input type="submit" value="deleteItem" name="btAction" /> </td>
                    </tr>

                </form>

            </tbody>
        </table>       


    </c:if>




    <%--
    <% if (session != null) {
            CartDTO dto = (CartDTO) session.getAttribute("CART");
            if (dto != null) {
                Map<String, Integer> product = dto.getProduct();
                if (product != null) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>ProductID</th>
                <th>Productname</th>
                <th>ProductQuantity</th>
                <th>RemoveItem</th>
            </tr>
        </thead>
        <tbody>
        <form action="DispatchController" method="GET">
            <% int count = 0;
                for (String productinfo : product.keySet()) {
            %>

                <tr>
                    <td><%= count++%> </td>
                    <td><%= productinfo%></td>
                    <td><%= product.get(productinfo)%></td>
                    <td>
                        <input type="checkbox" name="chkItem" value="<%= productinfo%>" />
                    </td>

                </tr>
                <%}%>
                <tr>
                    <td colspan="2"> <a href="shoppingwithoutaccount.jsp">Back to the shopping cart</a></td>
                    <td colspan="2"> <input type="submit" value="deleteItem" name="btAction" /> </td>
                </tr>
            </form>

        </tbody>
    </table>          
    <%
                }
            }

        }
    %>
        
    --%>
</body>
</html>
