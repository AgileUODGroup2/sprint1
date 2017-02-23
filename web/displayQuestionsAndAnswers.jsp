<%-- 
    Document   : displayQuestionsAnswers
    Created on : 23-Feb-2017, 15:16:52
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
        <title> View Questions and Answers </title>
        <link rel="stylesheet" type="text/css" href="/AC31007Quiz/styles.css">
        
        <%
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
        %>
 
    </head>
    <body bgcolor="d3dfeb">
    <img src="/AC31007Quiz/logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="/AC31007Quiz/logo123.png" width="115px" style="position: absolute; right:0; top: 0;">

        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER</a></li>

            </ul>
        </div>

        <div class="navBar1">
            <ul>
                <li><a> View Questions and Answers </a></li>
            </ul>
        </div>
        <br>
        <br>
           
        <div id="cc1">
            
        <h2>Quiz Profile</h2>
        
        
        <h3> Quiz ID: <%=quiz.getQuizID()%> </h3>
        <h3> Quiz Name: <%=quiz.getQuizName()%> </h3>
        <h3>Module ID: <%=quiz.getModuleID()%></h3>
        <h3>Date Created: <%=quiz.getDateCreated()%></h3>
        <h3>Number of Q's: <%=quiz.getNumberOfQuestions()%></h3>
        </div>
        
        <div id="cc2">
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
        <a href="editProfile.jsp"><button id="fourth-button">Edit Quiz</button></a>
        
        </div>
        <div id="cc3">
        <%
            QuizModel quizModel = new QuizModel();
            java.util.LinkedList<QuestionBank> quizList = (java.util.LinkedList<QuestionBank>) quizModel.getQuestionsAndAnswers(quiz.getQuizID());
                if (quizList != null) {
                    Iterator<QuestionBank> it = quizList.iterator();
                    while(it.hasNext()) {
                        QuestionBank q = (QuestionBank) it.next();
%> 
                    <h2> Question: <%=q.getQuestion() %> </h2>
                    <h4>A: <%=q.getA()%></h4>
                    <h4>B: <%=q.getB()%></h4>
                    <h4>C: <%=q.getC()%></h4>
                    <h4>D: <%=q.getD() %></h4>
                    <h4>Answer: <%=q.getAnswer()%></h4>
                    <h4>Answer Description  : <%=q.getAnswerDesc()%></h4>
                    
           <%
                    }
                } else { %>
                <h7>No quizzes to show</h7>
                <%}%>
        </div>
    </body>
</html>
