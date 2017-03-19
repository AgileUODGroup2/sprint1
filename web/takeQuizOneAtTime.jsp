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
            int[] qIDs = (int[]) session.getAttribute("QuestionIDs");
            boolean[] flagged = (boolean[]) session.getAttribute("Flagged");
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
                <li><a> Quiz time... good luck!</a></li>
            </ul>
        </div>
        <br>
        <br>
        <div class="centerContent1">
        <form method="post" action="<%=request.getContextPath()%>/takeQuizOneAtTime" style="display: inline-block; margin: auto; text-align: left; width:100%;">
        
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
                qIDs[questionNumber] = q.getQuestionID();
                System.out.println("QuestionID " + q.getQuestionID());
                String sAnswer = studentAnswers[questionNumber];
                System.out.println("The students answer for this question is: " + sAnswer);
            %>
            
                <br>

                <h2><%=q.getQuestion()%></h2>
                <%
                     if(q.HasMedia()){
                         //Adapted source - https://www.w3schools.com/howto/howto_css_modal_images.asp
                 %>
                 
                 <img class="qImage" id="<%="img" + q.getQuestionID()%>" src="<%=request.getContextPath() + "/question-img/" + q.getQuestionID()%>" width="200" style="display: inline-block;">
                
                 <div id="myModal" class="modal">
                        <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>
                        <img class="modal-content" id="modalImage">
                </div>
                 
                 <script>
                    var modal = document.getElementById('myModal');
                    var img = document.getElementById('<%="img" + q.getQuestionID()%>');
                    var modalImg = document.getElementById("modalImage");
                    img.onclick = function(){
                        modal.style.display = "block";
                        modalImg.src = this.src;
                    }
                    var span = document.getElementsByClassName("close")[0];
                    span.onclick = function() {
                      modal.style.display = "none";
                    }
                 </script>
                 <%
                     }
                 %>
                 <br>
                 
                <input type ="hidden" name="quizID" value="<%=quizID%>">
                <input type ="hidden" name="questionNo" value="<%=questionNumber%>">
                <input type ="hidden" name="numOfQuestions" value="<%=numOfQuestions%>">

                <input type="radio" name="answer" value="A" <%if(sAnswer != null && sAnswer.equals("A")){%>checked<%}%>> <h8><%=q.getA()%></h8>
                <br>
                <input type="radio" name="answer" value="B" <%if(sAnswer != null && sAnswer.equals("B")){%>checked<%}%>> <h8><%=q.getB()%></h8>
                <br>
                <input type="radio" name="answer" value="C" <%if(sAnswer != null && sAnswer.equals("C")){%>checked<%}%>> <h8><%=q.getC()%></h8>
                <br>
                <input type="radio" name="answer" value="D" <%if(sAnswer != null && sAnswer.equals("D")){%>checked<%}%>> <h8><%=q.getD()%></h8>
                <br>
                <%
        }
%> 
<input type="hidden" value="<%=questionNumber%>" name="questionNumber"><br>

<input type="checkbox" name="flag" <%if(flagged[questionNumber]){%>checked<%}%>> <h8 style="font-weight: bold;">Flag this question</h8><br>
<br>

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
%><br>
        <input type="submit" value="Next Question" name="next" style="margin: auto;">
        <%
    }
%>
<br>
<input type="submit" value="Save for another time" style="margin: auto;">
<br>
<br>

<div id ="cc5">
    <h3>Flagged Questions </h3>
<%
        Boolean flag;
        for(int i=0;i<numOfQuestions;i++){
            flag = flagged[i];
            %>

            <input type="submit" value="<%=i%><%if(flag == true){%> - FLAGGED<%}%>" name="jumpQuestion" style="margin: auto; display: inline-block; text-align: center; background-color: #042356;">
            <%
            System.out.println("Flagged value for question " + i + " = " + flag);
        }
        System.out.println("");
%>


</form><br>
<br><br>
            </div>
<br>
<br><br><br>
             
     </div>
    </body>
</html>
