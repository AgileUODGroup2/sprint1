<%-- 
    Document   : specificQuiz
    Created on : 20-Feb-2017, 20:04:47
    Author     : ashawittchen
--%>
<%@page import="models.QuizModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Specific Quiz</title>
    </head>
    <body>
       <button type="button" onclick="<%
           
    
        QuizModel ml = new QuizModel();
            ml.makeQuizLive(10); // change to session variable
   
            %>alert('The quiz is now live')">Make Live</button>
       
           
    
      
   </body>
</html>
</html>
