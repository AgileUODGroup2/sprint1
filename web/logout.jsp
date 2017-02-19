<%-- 
    Document   : logout
    Created on : 19-Feb-2017, 23:20:54
    Author     : nathanmcmahon
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>you have successfully logged out</h1>
         <%
            LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn");
            %>
            
         <%
         session.invalidate();
         RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
         rd.forward(request, response);
         %>
                        
    </body>
</html>

