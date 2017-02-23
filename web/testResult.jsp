<%@page import="stores.Quiz"%>
<%@ page import="stores.Result" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/AC31007Quiz/styles.css">
    <title>Results</title>

    <%

        Result quizResult = (Result) request.getAttribute("Result"); // Result of a single quiz for a specific Matriculation Number
        Quiz quiz = (Quiz) request.getAttribute("Quiz");
    %>

</head>
<body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="/AC31007Quiz/index.jsp">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="/AC31007Quiz/logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="/AC31007Quiz/logo123.png" width="115px" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a>Your Quiz </a></li>
            </ul>
        </div>
        <br><br>
        <div class="centerContent1">
      
        
          
         
        <h2>Quiz Profile</h2>
        
        <h3> Quiz ID: <%=quiz.getQuizID()%> </h3>
        <h3> Quiz ID: <%=quiz.getQuizName()%> </h3>
        <h3> Quiz ID: <%=quiz.getModuleID()%> </h3>
       
     
        
        <div id="cc3">
        <h2>Your Results</h2>
       
        <h3>Score: <%=quizResult.getScore()%></h3>
        <h3>Attempts: <%=quizResult.getAttempts()%></h3>
        <h3>Date Completed: <%=quizResult.getDate()%></h3>
 
        </div>
        <br>
        <br>
        <div id="graph">
         <a href="/AC31007Quiz/takeQuiz.jsp"><button id="third-button">Take Quiz</button></a>
        </div>
        </div>
</body>
</html>