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
            Quiz quiz = (Quiz) session.getAttribute("Quiz");
            String[] studentAnswers = (String[]) session.getAttribute("StudentAnswers");
        %>
        
   
    <body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="<%=request.getContextPath() + "/studentPortal.jsp"%>">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="/AC31007Quiz/logo123.png" width="115" style="position: absolute; left:0; top: 0;">
        <img src="/AC31007Quiz/logo123.png" width="115" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a> Get ready to start your quiz!</a></li>
            </ul>
        </div>
        <br>
        <br>
        <div class="centerContent1">
        <form method="post" action="<%=request.getContextPath()%>/takeQuizOneAtTime" style="display: inline-block; margin: auto; text-align: left;">
        
            <%
            QuizModel quizModel = new QuizModel();
            java.util.LinkedList<QuestionBank> questionList = new java.util.LinkedList<>();
           
            //ID of current quiz
            int quizID = quiz.getQuizID();
            
            //numbers of questions in current quiz
            int numOfQuestions = quiz.getNumberOfQuestions();
            
            //current question the student is on
            int questionNumber = quiz.getCounter();
            
            //return full list of questions
            questionList = quizModel.getQuestionsAndAnswers(quizID);
            
            if (questionList != null) {
                
                QuestionBank q = questionList.get(questionNumber);
                String sAnswer = studentAnswers[questionNumber];
                System.out.println("The students answer for this question is: " + sAnswer);
            %>
            
                <br>

                <h2><%=q.getQuestion()%></h2>
                <input type ="hidden" name="quizID" value="<%=quizID%>">
                <input type ="hidden" name="questionNo" value="<%=questionNumber%>">
                <input type ="hidden" name="numOfQuestions" value="<%=numOfQuestions%>">

                <%if(sAnswer == "A"){%>
                    <%System.out.println(sAnswer + " Doesn't equal A");%>
                    <input type="radio" name="answer" value="A" checked> <h8><%=q.getA()%></h8>
                    <br>
                <%}else{%>
                    <input type="radio" name="answer" value="A"> <h8><%=q.getA()%></h8>
                    <br>
                <%}%>

                <%if(sAnswer == "B"){%>
                    <input type="radio" name="answer" value="B" checked> <h8><%=q.getB()%></h8>
                    <br>
                <%}else{%>
                    <input type="radio" name="answer" value="B"> <h8><%=q.getB()%></h8>
                    <br>
                <%}%>

                <%if(sAnswer == "C"){%>
                    <input type="radio" name="answer" value="C" checked> <h8><%=q.getC()%></h8>
                    <br>
                <%}else{%>
                    <input type="radio" name="answer" value="C"> <h8><%=q.getC()%></h8>
                    <br>
                <%}%>

                <%if(sAnswer == "D"){%>
                    <input type="radio" name="answer" value="D" checked> <h8><%=q.getD()%></h8>
                    <br>
                <%}else{%>
                    <input type="radio" name="answer" value="D"> <h8><%=q.getD()%></h8>
                    <br>
                <%}%>

                <%
        }
%> 
<input type="hidden" value="<%=questionNumber%>" name="questionNumber">

<%
    System.out.println("question number = " + questionNumber + " numOfQuestions = " + numOfQuestions);
    if(questionNumber == -1 + numOfQuestions)
    {
        %>
        <input type="submit" value="Finish Quiz" name="Finish Quiz" style="margin: auto;">
        <%
    }
    else
    {
        %>
        <input type="submit" value="Next Question" name="next" style="margin: auto;">
        <%
    }
%>
<br>
<input type="submit" value="Save" style="margin: auto;">
<br>
<%
        for(int i=0;i<numOfQuestions;i++){
            %>
            <input type="submit" value="<%=i%>" name="jumpQuestion" style="margin: auto;">
            <%
        }

            System.out.println("");
%>
 </form>
<br>
<br>
<br>
             
        
        </div>
    </body>
</html>
