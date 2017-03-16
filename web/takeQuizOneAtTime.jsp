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
           
            int quizID = quiz.getQuizID();
            int numOfQuestions = quiz.getNumberOfQuestions();
            int counter = quiz.getCounter();
            System.out.println("Counter: " + counter);
            questionList = quizModel.getQuestionsAndAnswers(quizID);

            int x = 1;

            if (questionList != null) {
                  
                        QuestionBank q = questionList.get(counter);
            
%> 
            
        
            
            
            
            <br>
           
             
                 <h2><%=q.getQuestion()%></h2>
                 <%
                     if(q.HasMedia()){
                         ////Adapted source - https://www.w3schools.com/howto/howto_css_modal_images.asp
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
             <input type ="hidden" name="questionNo" value="<%=counter%>">
             <input type="radio" name="answer<%=x%>" value="A"> <h8><%=q.getA()%></h8>
             <br>
             <input type="radio" name="answer<%=x%>" value="B"> <h8><%=q.getB()%></h8>
             <br>
             <input type="radio" name="answer<%=x%>" value="C"> <h8><%=q.getC()%></h8>
             <br>
             <input type="radio" name="answer<%=x%>" value="D"> <h8><%=q.getD()%></h8>
             <br>
              
             
            
                 <%
                 x++;
}
                //request.setAttribute("Quiz", quiz);
%>
<input type="hidden" value="<%=x%>" name="counter">
<input type="submit" value="Next Question" style="margin: auto;">
<br>
<input type="submit" value="Save" style="margin: auto;">
 </form>
<br>
<br>
<br>
             
        
        </div>
    </body>
</html>
