<%-- 
    Document   : staffPortal
    Created on : 17-Feb-2017, 19:46:40
    Author     : daniellewilliams
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Student Portal </title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        
        <%
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        %>
 
    </head>
    <body bgcolor="9BD5E8">


        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER</a></li>

            </ul>
        </div>

        <div class="navBar1">
            <ul>
                <li><a> STUDENT PORTAL - Welcome <%=lg.getFirstName()%>!</a></li>
            </ul>
        </div>
            
            <br><br><a href="incompleteQuizzes">Incomplete Quizzes</a> <a href="pendingdQuizzes">Pending Quizzes</a> <a href="completedQuizzes">Completed Quizzes</a> 
            <br><br>
            
        <h2>Profile</h2>
        <h4>Matriculation Number: </h4><p><%=lg.getUsername()%><p>
        <h4>First Name: </h4><p><%=lg.getFirstName()%></p>
        <h4>Last Name: </h4><p><%=lg.getLastName()%></p>
        <a href="editProfile.jsp">Edit Profile</a>

    </body>
</html>
