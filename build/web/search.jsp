<%-- 
    Document   : hellouser
    Created on : Apr 11, 2023, 9:45:05 AM
    Author     : Fstore
--%>

<%@page import="khoita.login.LoginDTO"%>
<%@page import="java.util.List"%>
<%@page import="khoita.registration.RegistrationDAO"%>
<%@page import="khoita.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <% RegistrationDTO user_info = (RegistrationDTO)session.getAttribute("USER_NAME"); %>
        <h1>Welcome <%= user_info.getFullname()%></h1> 
        <form name="btAction" action="DispatchController">
            <input type="text" name="txtSearch" value="<%= request.getParameter("txtSearch")%>" />
            <input type="submit" value="search" name="btAction" />
        </form>


        <div>
            <button>
                <a href="shopping.jsp">Go shopping</a>
            </button>
        </div>

        <% String txtSearchValue = request.getParameter("txtSearch");
            if (txtSearchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("USER_LIST");
                if (result != null) {
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Account</th>
                    <th>Password</th>
                    <th>Fullname</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <% int count = 1;
                    for (RegistrationDTO user : result) {%>
            <form action="DispatchController" method="POST">
                <tr>
                    <td>
                        <%= count++%>
                    </td>
                    <td>    
                        <%--<%= user.getAccount()%>--%>

                        <input type="text" name="txtAccount" value=<%= user.getAccount()%> />
                        <input type="hidden" name="lastAccount" value=<%= user.getAccount()%> />
                    </td>
                    <td>
                        <%--<%= user.getPassword()%>--%>
                        <input type="text" name="txtPassword" value=<%= user.getPassword()%> />
                    </td>
                    <td>
                        <%--<%= user.getFullname()%>--%>
                        <input type="text" name="txtFullname" value=<%= user.getFullname()%> />
                    </td>

                <input type="hidden" name="txtSearchValue" value=<%=request.getParameter("txtSearch")%> />

                <% if (user.isRole()) {
                %>
                <td>
                    <input type="checkbox" name="isRole" value="ON" checked="checked" />
                </td>
                <%
                } else {
                %>
                <td>
                    <input type="checkbox" name="isRole" value="ON" />
                </td>

                <%
                    }
                %>
                <!-- This use to prevent user(Admin) to update themself -->
                <% if (session.getAttribute("USER_NAME").equals(user.getFullname())) {
                %>
                <td>
                    <input disabled="disabled" type="submit" value="update" name="btAction" />
                </td>
                <td>
                    <a style="padding: 1px;border-radius: 2px;text-decoration: none;background-color:rgba(0,0,0,0.4);color:black;pointer-events: none; cursor: default" href="DispatchController?accountID=<%= user.getAccount()%>&txtSearchValue=<%=request.getParameter("txtSearch")%>&btAction=delete">Delete</a>
                </td>
                <%
                } else {
                %>
                <td>
                    <input type="submit" value="update" name="btAction" />
                </td>
                <td>
                    <a href="DispatchController?accountID=<%= user.getAccount()%>&txtSearchValue=<%=request.getParameter("txtSearch")%>&btAction=delete">Delete</a>
                </td>
                <%
                    }
                %>

                <!--                <td>
                                    <input type="submit" value="update" name="btAction" />
                                </td>-->

                <!-- If you want to use the above function 
                            -- command line 91 - 93 and open the command from line 76 - 90
                -->

                </tr>
            </form>
            <%   }
            %>
        </tbody>
    </table>


    <% } else {
    %>
    <h1>No record found</h1>
    <%
            }
        }%>


    <button>
        <a href="DispatchController?btAction=logout">Log out</a>
    </button>
</body>
</html>
