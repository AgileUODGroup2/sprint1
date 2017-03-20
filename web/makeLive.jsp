<%-- 
    Document   : specificQuiz
    Created on : 20-Feb-2017, 20:04:47
    Author     : ashawittchen
--%>
<%@page import="stores.LoggedIn"%>
<%@page import="stores.Quiz"%>
<%@page import="models.QuizModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/styles.css"%>">
        <title>Make Live</title>
    </head>
     <body bgcolor="d3dfeb">
    <img src="<%=request.getContextPath() + "/logo123.png"%>" width="115" style="position: absolute; left:0; top: 0;">
        <img src="<%=request.getContextPath() + "/logo123.png"%>" width="115" style="position: absolute; right:0; top: 0;">

        <%
                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                Quiz quiz = (Quiz) request.getAttribute("Quiz");
            %>
        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER</a></li>

            </ul>
        </div>

        <div class="navBar1">
            <ul>
                <li><a> Make Live </a></li>
            </ul>
        </div>
        <%
            
            QuizModel makeLive = new QuizModel();
            System.out.println("Make live: " + quiz.getQuizID());
             makeLive.makeQuizLive(quiz.getQuizID()); // change to session variables
        %>
           <h2> Your quiz is now live! </h2>
           <h2> Students will now be able to take this specific quiz and results will now begin to show </h2>
   </body>
</html>
</html>
