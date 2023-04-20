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
        <% ProductDAO dao = new ProductDAO();
            dao.getProductFromSQL();
            Map<String, ProductDTO> product = dao.getProduct();
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
                </tr>
            </thead>
            <tbody>
                <%
                    for (String productInfo : product.keySet()) {
                %>
            <form action="DispatchController">
                <tr>
                    <td><%= product.get(productInfo).getProductID()%></td>
                <input type="hidden" name="txtProductID" value="<%= product.get(productInfo).getProductID()%>" />
                <td><%= product.get(productInfo).getProductName()%></td>
                <input type="hidden" name="txtProductName" value="<%= product.get(productInfo).getProductName()%>" />

                <td><%= product.get(productInfo).getProductDescription()%></td>
                <input type="hidden" name="txtProductDescription" value="<%= product.get(productInfo).getProductDescription()%>" />

                <td><%= product.get(productInfo).getProductPrice()%></td>
                <input type="hidden" name="txtProductPrice" value="<%= product.get(productInfo).getProductPrice()%>" />

                <td> <input style="width: 100%;text-align: center" type="number" name="txtProductQuantity" value="" min="1" max="50" /></td>
               

                <td>
                    <input type="submit" value="addtocart" name="btAction" />
                </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
        <button>
            <a href="DispatchController?btAction=viewcart">View cart</a>
        </button>
    <%
        }
    %>
</body>
</html>
