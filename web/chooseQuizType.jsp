<%-- 
    Document   : takeQuiz
    Created on : 22-Feb-2017, 23:53:57
    Author     : erincoey
--%>


<%@page import="stores.Quiz"%>
<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/AC31007Quiz/styles.css">
        <title>Take Quiz</title>
    </head>
      <%
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            String contextPath = request.getContextPath();
            Quiz quiz = (Quiz) session.getAttribute("Quiz");
        %>
        
   
    <body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="/AC31007Quiz/logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="/AC31007Quiz/logo123.png" width="115px" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a> Get ready to start your quiz!</a></li>
            </ul>
        </div>
        <br>
        <br>
        <div class="centerContent1">
            <br>
            <h3 style="text-align: center;"> Please select from the options below </h3><br>
            <h4 style="text-align: center;"> This option will display all questions of the quiz on the same page </h4>
            <form method="GET" action="<%=contextPath + "/takeQuiz/" + quiz.getQuizID()%>"><input type="submit" value="All Questions" /></form><br><br>
            <h4 style="text-align: center;"> This option will display one question at a time </h4>
        <form method="GET" action="<%=contextPath + "/takeQuizOneAtTime/" + quiz.getQuizID()%>"><input type="submit" value="One Question at a time" /></form>
<br>
<br>
<br>
             
        
        </div>
    </body>
</html>