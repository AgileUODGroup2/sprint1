<%-- 
    Document   : takeQuiz
    Created on : 22-Feb-2017, 23:53:57
    Author     : erincoey
--%>

<%@page import="java.util.Iterator"%>
<%@page import="stores.QuestionBank"%>
<%@page import="models.QuizModel"%>
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
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
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
        
        <%
            QuizModel quizModel = new QuizModel();
            java.util.LinkedList<QuestionBank> questionList = new java.util.LinkedList<>();
            int quizID = quiz.getQuizID();
            
            questionList = quizModel.getQuestionsAndAnswers(quizID);
            
            if (questionList != null) {
                    Iterator<QuestionBank> it = questionList.iterator();
                    while(it.hasNext()) {
                        QuestionBank q = (QuestionBank) it.next();
%> 
            
     
            
            
        <br>
        <br>
        <div class="centerContent1">
            <br><br><br>
            <button id="third-button">* <%=q.getQuestion()%>*</button><br><br>
            <button id="fourth-button">* <%=q.getA()%> *</button><br>
            <button id="fourth-button">* <%=q.getB()%>*</button><br>
            <button id="fourth-button">* <%=q.getC()%> *</button><br>
            <button id="fourth-button">* <%=q.getD()%> *</button><br><br>
            
            <button id="fourth-button" style="width:20%;"> Next Question --> </button>
             <button id="fourth-button" style="width:20%;"> Exit Quiz </button><br><br>
            
            
             <%
                 }
}
%>
        </div>
    </body>
</html>
