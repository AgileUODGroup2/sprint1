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
    <body bgcolor="d3dfeb">
    <img src="logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="logo123.png" width="115px" style="position: absolute; right:0; top: 0;">

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
            
            <br>
            <br>
            <div class="centerContent1">
                 
        <div id="cc1">
         <img src="pic1.png" style="display: inline-block; float:right; width:33%;">   
        <h2>Your Profile</h2>
        
        <h3>Matriculation Number: <%=lg.getUsername()%></h3>
        <h3>First Name: <%=lg.getFirstName()%></h3>
        <h3>Last Name: <%=lg.getLastName()%></h3>
        
        </div>
        
         <div id="cc2">
             <br>
             <br>
            <a href="editProfile.jsp"><button id="fourth-button">Edit Profile</button></a>
          
            <a href ="studentModules.jsp"><button id="fourth-button">My Modules</button></a>
            
              <a href="logout.jsp"><button id="third-button">Log Out</button></a>
    <br>
            <br>
            <br>
       
          
        </div>
        
        <br>
        <br>
         <div id="cc3" style="background: black;">
             <br>
             <br>
             
              <p><a href="incompleteQuizzes"><button id="fourth-button" style="Background:red; height: 140px;"> Incomplete Quizzes</button></a></p>
              
        <p><a href="pendingdQuizzes"><button id="fourth-button" style="Background:orange; height: 140px">Pending Quizzes</button></a></p>
                
        <p><a href="completedQuizzes"><button id="fourth-button" style="Background:green; height: 140px">Completed Quizzes</button></a></p>
               
              
                <br>
                <br>
                
                
       
        
         </div>
       
        </div>
    </body>
</html>
