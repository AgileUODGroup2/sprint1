<%-- 
    Document   : createQuiz
    Created on : 17-Feb-2017, 19:48:35
    Author     : daniellewilliams
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
        <h1>Hello World!</h1>
         <%
            LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn");
            %>
       <h2> Hey <%=lg.getUsername()%>, this is your profile! </h2> 
    </body>
</html>
