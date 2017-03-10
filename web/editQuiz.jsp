<%-- 
    Document   : editQuiz
    Created on : 17-Feb-2017, 19:50:48
    Author     : daniellewilliams
--%>

<%@page import="stores.QuestionBank"%>
<%@ include file='cssStructure.jsp' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
             <title>Edit Question</title>
             
        <% 
            QuestionBank ans = (QuestionBank) request.getAttribute("Question");
            String contextPath = request.getContextPath();
        %>
        
        <div class="navBar1">
            <ul>
                <li><a> Edit Question</a></li>
            </ul>
        </div>
        <br><br>
    
        <div class="centerContent1" style="display: block; text-align: center;"><br>
        <form method="POST" action="edit" enctype="multipart/form-data" style="display: block; margin: auto; text-align: left;">
            <label for="question">Question:</label>
            <input id="question" type="text" name="question" value="<%=ans.getQuestion()%>" style="width: 40%;" /> 
            <br /><br />
            <label for="a">A:</label>
            <input id="a" type="text" name="a" value="<%=ans.getA()%>" style="width: 40%;" /> 
            <br /><br />
            <label for="b">B:</label>
            <input id="b" type="text" name="b" value="<%=ans.getB()%>" style="width: 40%;" /> 
            <br /><br />
            <label for="c">C:</label>
            <input id="c" type="text" name="c" value="<%=ans.getC()%>" style="width: 40%;" /> 
            <br /><br />
            <label for="d">D:</label>
            <input id="d" type="text" name="d" value="<%=ans.getD()%>" style="width: 40%;" /> 
            <br /><br />
            
            <% String right = ans.getAnswer();%>
            <label for="Answer">Correct Answer:</label>
           
            <input type="radio" name="Answer" value="A" <% if(right.equals("A")) { %>checked<%}%>> A
            <input type="radio" name="Answer" value="B" <% if(right.equals("B")) { %>checked<%}%>> B
            <input type="radio" name="Answer" value="C" <% if(right.equals("C")) { %>checked<%}%>> C
            <input type="radio" name="Answer" value="D" <% if(right.equals("D")) { %>checked<%}%>> D <br><br><br><br>
            
            <label for="question">Answer Description:</label>
            <input type="text" name="answerDesc" value="<%=ans.getAnswerDesc()%>" style="width: 40%;"/> <br /><br />
            
            
            Supplementary Image: </br>
            <input id="media" name="media" style="width: 30%;" type="file" accept=".jpg, .jpeg, .png">
            <a href="<%=request.getContextPath() + "/delete-media/" + ans.getQuestionID()%>">Delete Image</a>
            <br/><br/>
            <input type="hidden" name="QuizID" value="<%=ans.getQuizID()%>" />
            <input type="hidden" name="QuestionID" value="<%=ans.getQuestionID()%>" />
            
            
            <input type="submit" value="Save Changes" /><br>
        </form>
</div>
    </body>
</html>
