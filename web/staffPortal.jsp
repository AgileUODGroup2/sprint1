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
        <title> Staff Portal </title>
        <link rel="stylesheet" type="text/css" href="styles.css">

    </head>
    <body bgcolor="9BD5E8">


        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER</a></li>

            </ul>
        </div>

        <div class="navBar1">
            <ul>
                <li><a> STAFF PORTAL - Welcome Rachel (insert name)</a></li>
            </ul>
        </div>

        <a href="createQuiz.jsp"><button>Create Quiz</button></a>
        <br>
        <a href="completedQuiz"><button>Completed Quizzes</button></a>
        <br>
        <a href="liveQuiz"><button>Live Quizzes</button></a>
        <br>
        <a href="unfinishedQuiz"><button>Unfinished Quizzes</button></a>
        <br>
        <a href="logout.jsp"><button>Log Out</button></a>
    
        <% LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); %>
       
        <h2> Hey <%=lg.getUsername()%>, this is your profile! </h2>  
        
        <%lg.getUsername(); 
                
         System.out.println(lg.getUsername());%>
    </body>
</html>
