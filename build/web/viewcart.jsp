<%-- 
    Document   : viewcart
    Created on : Apr 18, 2023, 10:07:55 AM
    Author     : Fstore
--%>

<%@page import="java.util.List"%>
<%@page import="khoita.detailcart.DetailCartDTO"%>
<%@page import="khoita.registration.RegistrationDTO"%>
<%@page import="khoita.product.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>YOUR CART</h1>
        <% ProductDAO dao = new ProductDAO();
            RegistrationDTO username = (RegistrationDTO) session.getAttribute("USER_NAME");
            dao.getProductFromCartSQL(username.getAccount());
            List<DetailCartDTO> product = dao.getProductFromCart();
            if (product != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ProductID</th>
                    <th>ProductName</th>
                    <th>ProductDescription</th>
                    <th>ProductPrice</th>
                    <th>ProductQuantity</th>
                    <th>Process</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (DetailCartDTO productInfo : product) {
                %>
                <!--use productId and cardID from user to remove item in their cart-->
            <form action="DispatchController">
                <tr>
                    <td><%= productInfo.getProductID()%></td>
                <input type="hidden" name="txtProductID" value="<%= productInfo.getProductID()%>" />
                <td><%= productInfo.getProductName()%></td>
                <td><%= productInfo.getProductDescription()%></td>
                <td><%= productInfo.getProductPrice()%></td>
                <td>
                    <input style="width: 95%;text-align: center" type="number" name="txtProductQuantity" value="<%= productInfo.getProductQuantity()%>" min="1" max="50" />
                </td>
                <td><%= productInfo.getProcess()%></td>
                <input type="hidden" name="txtCardID" value="<%= productInfo.getCardID()%>" />

                <td>
                    <input type="submit" value="Update Item" name="btAction" />
                    <a href="DispatchController?btAction=Delete Item&txtProductID=<%= productInfo.getProductID()%>
                       &txtCardID=<%= productInfo.getCardID()%>">DeleteItem</a>
                </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>

    <%
        }
    %>
</body>
</html>
