<%-- 
    Document   : displayQuestionsAnswers
    Created on : 23-Feb-2017, 15:16:52
    Author     : erincoey
--%>

<%@page import="java.util.Iterator"%>
<%@page import="stores.QuestionBank"%>
<%@page import="models.QuizModel"%>
<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> View Questions and Answers </title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/styles.css"%>">
        
        <%
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
        %>
 
    </head>
    <body bgcolor="d3dfeb">
    <img src="<%=request.getContextPath() + "/logo123.png"%>" width="115" style="position: absolute; left:0; top: 0;">
    <img src="<%=request.getContextPath() + "/logo123.png"%>" width="115" style="position: absolute; right:0; top: 0;">

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
        <div class="centerContent1">   
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
        
        </div>
        <div id="cc3">
        <%
            QuizModel quizModel = new QuizModel();
            String contextPath = request.getContextPath();
            java.util.LinkedList<QuestionBank> quizList = (java.util.LinkedList<QuestionBank>) quizModel.getQuestionsAndAnswers(quiz.getQuizID());
                if (quizList != null) {
                    Iterator<QuestionBank> it = quizList.iterator();
                    while(it.hasNext()) {
                        QuestionBank q = (QuestionBank) it.next();
%> 
                    <h2> Question: <%=q.getQuestion() %>  <a href="<%=contextPath%>/edit/<%=q.getQuestionID()%>">EDIT</a></h2>
                    <% if(q.HasMedia()){
                        %>
                        <img src="<%=contextPath + "/question-img/" + q.getQuestionID()%>" width="200" style="display: inline-block;">
                    <%}%>
                    <h4>A: <%=q.getA()%></h4>
                    <h4>B: <%=q.getB()%></h4>
                    <h4>C: <%=q.getC()%></h4>
                    <h4>D: <%=q.getD() %></h4>
                    <h4>Answer: <%=q.getAnswer()%></h4>
                    <h4>Answer Description  : <%=q.getAnswerDesc()%></h4>
                    <br><Br>
           <%
                    }
                } else { %>
                <h7>No quizzes to show</h7>
                <br>
                <%}%>
        </div>
        <br>
        </div><br>
        
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript">
        $(document).ready(function () {

                //min font size
                var min=9; 	

                //max font size
                var max=70;	

                //grab the default font size
                var reset = $('p').css('fontSize'); 

                //font resize these elements
                var elm = $('h3, h2, h4');  

                //set the default font size and remove px from the value
                var size = str_replace(reset, 'px', ''); 

                //Increase font size
                $('a.fontSizePlus').click(function() {

                        //if the font size is lower or equal than the max value
                        if (size<=max) {

                                //increase the size
                                size++;

                                //set the font size
                                elm.css({'fontSize' : size});
                        }

                        //cancel a click event
                        return false;	

                });

                $('a.fontSizeMinus').click(function() {

                        //if the font size is greater or equal than min value
                        if (size>=min) {

                                //decrease the size
                                size--;

                                //set the font size
                                elm.css({'fontSize' : size});
                        }

                        //cancel a click event
                        return false;	

                });

                //Reset the font size
                $('a.fontReset').click(function () {

                        //set the default font size	
                         elm.css({'fontSize' : reset});		
                });

        });

        //A string replace function
        function str_replace(haystack, needle, replacement) {
                var temp = haystack.split(needle);
                return temp.join(replacement);
        }
        </script>

<a href="#" class="fontSizePlus">A+</a> | 
<a href="#" class="fontReset">Reset</a> | 
<a href="#" class="fontSizeMinus">A-</a>

<p class="intro"> HELLO WORLD</p>


<script type="text/javascript" src="http://www.queness.com/js/bsa.js"></script>

<br>
    </body>
</html>
