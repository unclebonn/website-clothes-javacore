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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <% Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username = "";
                for (Cookie ckie : cookies) {
                    if (ckie.getName().equals("username_cookie")) {
                        username = ckie.getValue();
        %>
        <h1>Welcome <%= username%></h1> 
        <%
                    }
                }
            }
        %>
        <%-- <% RegistrationDTO user_info = (RegistrationDTO) session.getAttribute("USER_NAME");%> --%>
        <%--<h1>Welcome <%= user_info.getfullname() %></h1>--%> 

        <form name="btAction" action="DispatchController">
            <%--<input type="text" name="txtSearch" value="<%= request.getParameter("txtSearch")%>" />--%> 
            <!--using jstl-->
            <input type="text" name="txtSearch" value="${param.txtSearch}" />  
            <input type="submit" value="search" name="btAction" />
        </form>


        <div>
            <button>
                <a href="shopping.jsp">Go shopping</a>
            </button>
        </div>


        <c:set var="txtSearchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty txtSearchValue}">
            <c:set var="result" value="${requestScope.USER_LIST}"/>
            <c:if test="${not empty result}">
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
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchController" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>    
                                    <%--<%= user.getAccount()%>--%>

                                    <input type="text" name="txtAccount" value=${dto.account} />
                                    <input type="hidden" name="lastAccount" value=${dto.account} />
                                </td>
                                <td>
                                    <%--<%= user.getPassword()%>--%>
                                    <input type="text" name="txtPassword" value=${dto.password} />
                                </td>
                                <td>
                                    <%--<%= user.getFullname()%>--%>
                                    <input type="text" name="txtFullname" value=${dto.fullname}  />
                                </td>

                            <input type="hidden" name="txtSearchValue" value=${param.txtSearch} />


                            <c:if test="${dto.role}">
                                <td>
                                    <input type="checkbox" name="isRole" value="ON" checked="checked" />
                                </td>
                            </c:if>
                            <c:if test="${!dto.role}">
                                <td>
                                    <input type="checkbox" name="isRole" value="ON" />
                                </td>
                            </c:if>



                            <%--                            <% if (user.isRole()) {
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
                            %>--%>
                            <!-- This use to prevent user(Admin) to update themself -->

                            <c:set var="user" value="${cookie.username_cookie}" scope="request"/>
                            <%-- ${user.value} --%> : to take the value of cookie
                            <c:if test="${not empty user}">
                                <c:if test="${user.value eq dto.fullname}">
                                    <td>
                                        <input disabled="disabled" type="submit" value="update" name="btAction" />
                                    </td>
                                    <td>
                                        <a style="padding: 1px;border-radius: 2px;text-decoration: none;background-color:rgba(0,0,0,0.4);color:black;pointer-events: none; cursor: default" href="DispatchController?accountID=${dto.account}&txtSearchValue=${param.txtSearch}&btAction=delete">Delete</a>
                                    </td>
                                </c:if>
                                <c:if test="${user.value ne dto.fullname}">
                                    <td>
                                        <input type="submit" value="update" name="btAction" />
                                    </td>
                                    <td>
                                        <a href="DispatchController?accountID=${dto.account}&txtSearchValue=${param.txtSearch}&btAction=delete">Delete</a>
                                    </td>
                                </c:if> 
                            </c:if>




                            <%--         <% if (session.getAttribute("USER_NAME").equals(user.getFullname())) {
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
                                     %> --%>






                            <!--                <td>
                                                <input type="submit" value="update" name="btAction" />
                                            </td>-->

                            <!-- If you want to use the above function 
                                        -- command line 91 - 93 and open the command from line 76 - 90
                            -->

                            </tr>
                        </form>
                    </c:forEach>
                    <%--              <% int count = 1;
                              for (RegistrationDTO user : result) {%>
                              this is not jstl
                              <%   }
                              %> --%>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is match
            </h2>
        </c:if>
    </c:if>

    <%--    <%
            String txtSearchValue = request.getParameter("txtSearch");
            if (txtSearchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("USER_LIST");
                if (result != null) {
        %>  --%>




    <%--  <% } else {
      %>
      <h1>No record found</h1>
      <%
              }
          }%>  --%>


    <button>
        <a href="DispatchController?btAction=logout">Log out</a>
    </button>
</body>
</html>
