<%-- 
    Document   : specificQuiz
    Created on : 20-Feb-2017, 20:04:47
    Author     : ashawittchen
--%>
<%@page import="stores.Quiz"%>
<%@page import="models.QuizModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Live</title>
    </head>
    <body>
        <%
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
            QuizModel makeLive = new QuizModel();
            System.out.println("Make live: " + quiz.getQuizID());
             makeLive.makeQuizLive(quiz.getQuizID()); // change to session variables
        %>
           <h2> Your quiz is now live! </h2>
           <h2> Students will now be able to take this specific quiz and results will now begin to show </h2>
   </body>
</html>
</html>
