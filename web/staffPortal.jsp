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
        <link rel="stylesheet" type="text/css" href="styles.css">
        <title> Staff Portal </title>
        

        
        <% LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); %>
        
    </head>
    <body bgcolor="d3dfeb">


        <div class="navBar">
            <ul>
                  
                <li><a href="index.jsp">QUIZ MASTER </a></li>
                 
            </ul>
        </div>
        <img src="logo123.png" width="150px" style="position: absolute; left:0; top:0;">

        <div class="navBar1">
            <ul>
                <li><a> Staff Portal - Welcome <%=lg.getFirstName()%>!</a></li>
            </ul>
        </div>

  <form method="get" action="staffModules.jsp">
            <button type="submit">My Modules</button>
        </form>

        <a href="createQuiz"><button>Create Quiz</button></a>
        <br>
        <a href="completedQuiz"><button id="sec-button">Completed Quizzes</button></a>
        <br>
        <a href="liveQuiz"><button id="sec-button">Live Quizzes</button></a>
        <br>
       
        <a href="unfinishedQuiz"><button id="sec-button">Unfinished Quizzes</button></a>
        <br>
        <a href="logout.jsp"><button id="third-button">Log Out</button></a>
       
        <h2>Profile</h2>
        <h4>Staff ID: </h4><p><%=lg.getUsername()%><p>
        <h4>First Name: </h4><p><%=lg.getFirstName()%></p>
        <h4>Last Name: </h4><p><%=lg.getLastName()%></p>
        
    </body>
</html>
