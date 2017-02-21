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
        <title>Staff Portal </title>
        

        
        <% LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); %>
        
    </head>
    <body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="logo123.png" width="115px" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a> Staff Portal - Welcome <%=lg.getFirstName()%>!</a></li>
            </ul>
        </div>
        
            <div class="centerContent1">
                <br>
        <div id="cc1">
            
            <h2>Your Profile</h2>
            <h3>Staff ID: &nbsp;<%=lg.getUsername()%> </h3>
            <h3>First Name:&nbsp;<%=lg.getFirstName()%></h3>
            <h3>Last Name:&nbsp;<%=lg.getLastName()%></h3>
            <br>
            
       </div>
            
 
        <div id="cc2">
             <br>
           
            <a href="editProfile.jsp"><button id="sec-button">Edit Profile</button></a>
        <form method="get" action="staffModules.jsp">
          
        <button id="sec-button">My Modules</button>
        </form>
              <a href="logout.jsp"><button id="third-button">Log Out</button></a>
            <br>
        
        <br>
         <br>
          <br> 
       
          
        </div>

        <div id ="cc3"
             <p> <h2>Quiz Information </h2></p>
             <a href="createQuiz"><button id="sec-button">Create Quiz</button></a>
              
                <a href="completedQuiz"><button id="sec-button">Completed Quizzes</button></a>
                <br>
                <a href="liveQuiz"><button id="sec-button">Live Quizzes</button></a>
                <br>

                <a href="unfinishedQuiz"><button id="sec-button">Unfinished Quizzes</button></a>
                <br>
                 
                <br>
        </div>
          
         
       </div>
        
    </body>
</html>
