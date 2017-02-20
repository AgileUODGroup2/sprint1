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
    <body bgcolor="9BD5E8">


        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER</a></li>

            </ul>
        </div>

        <div class="navBar1">
            <ul>
                <li><a> STAFF PORTAL - Welcome <%=lg.getFirstName()%>!</a></li>
            </ul>
        </div>

  <form method="get" action="staffModules.jsp">
            <button type="submit">My Modules</button>
        </form>

        <a href="createQuiz.jsp"><button>Create Quiz</button></a>
        <br>
        <a href="completedQuiz"><button>Completed Quizzes</button></a>
        <br>
        <a href="liveQuiz"><button>Live Quizzes</button></a>
        <br>
        <a href="unfinishedQuiz"><button>Unfinished Quizzes</button></a>
        <br>
        <a href="logout.jsp"><button>Log Out</button></a>
       
        <h2>Profile</h2>
        <h4>Staff ID: </h4><p><%=lg.getUsername()%><p>
        <h4>First Name: </h4><p><%=lg.getFirstName()%></p>
        <h4>Last Name: </h4><p><%=lg.getLastName()%></p>
        <a href="editProfile.jsp">Edit Profile</a>
        
    </body>
</html>
