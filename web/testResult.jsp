<%@page import="java.util.Iterator"%>
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

        java.util.LinkedList<Result> quizResults = (java.util.LinkedList) request.getAttribute("Results"); // All attempts for a specific Matriculation Number
        Quiz quiz = (Quiz) request.getAttribute("Quiz");
        String contextPath = request.getContextPath();
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
                <li><a>Quiz Results</a></li>
            </ul>
        </div>
        <br><br>
        <div class="centerContent1">
      
        
          
         
        <h2>Quiz Profile</h2>
        
        <h3> Quiz ID: <%=quiz.getQuizID()%> </h3>
        <h3> Quiz Name: <%=quiz.getQuizName()%> </h3>
        <h3> Module ID: <%=quiz.getModuleID()%> </h3>
       
     
        
        <div id="cc3">
        <h2>Your Results</h2>
       <%   Result result = new Result();
            if (quizResults != null) {
                    Iterator<Result> it = quizResults.iterator();
                    if (it.hasNext()) 
                    {     
                        Result r = it.next();
            %>
                        <h3>Most Recent Score: <%=r.getScore()%> </h3>
                        <h3>Attempt: <%=r.getAttempts()%></h3>
                        <h3>Date Completed: <%=r.getDate()%></h3>
            <%      } 
                    while(it.hasNext()) {
                        Result r = (Result) it.next(); %>
                        <h4>Score: <%=r.getScore()%>, Attempt: <%=r.getAttempts()%>, Date Completed: <%=r.getDate()%></h4>
        <%                              } 
                    } 
                    else  { 
        %>
                        
                        <p> No results to show <p>
                            
        <%      
                                                    }
        %>
 
        </div>
        <br>
        <br>
      
        <div id="cc3" style="background-color: #d3dfeb;">
           
   
         <a href="/AC31007Quiz/quizInstructions.jsp"><button id="sec-button">Click here for quiz instructions</button></a><br>
            <form method="GET" action="<%=contextPath + "/chooseQuizType/" + quiz.getQuizID()%>"><input type="submit" value="Take Quiz" /></form>
            <br>
            
            <button10 onclick="goBack()">Return to your Quizzes</button10><br>
                <script>
                    function goBack() {
                        window.history.back();
                    
                    }
                    </script>
        </div>
            
        </div>
        
</body>
</html>
