<%-- 
    Document   : editQuiz
    Created on : 17-Feb-2017, 19:50:48
    Author     : daniellewilliams
--%>

<%@page import="stores.QuestionBank"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% 
            QuestionBank ans = (QuestionBank) request.getAttribute("Question");
            String contextPath = request.getContextPath();
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Question</title>
    </head>
    <body>
        <form method="POST" action="edit" enctype="multipart/form-data">
            <label for="question">Question:</label>
            <input id="question" type="text" name="question" value="<%=ans.getQuestion()%>" /> <br /><br />
            <label for="a">A:</label>
            <input id="a" type="text" name="a" value="<%=ans.getA()%>" /> <br /><br />
            <label for="b">B:</label>
            <input id="b" type="text" name="b" value="<%=ans.getB()%>" /> <br /><br />
            <label for="c">C:</label>
            <input id="c" type="text" name="c" value="<%=ans.getC()%>" /> <br /><br />
            <label for="d">D:</label>
            <input id="d" type="text" name="d" value="<%=ans.getD()%>" /> <br /><br />
            <% String right = ans.getAnswer();%>
            Correct answer: <br />
            <input type="radio" name="Answer" value="A" <% if(right.equals("A")) { %>checked<%}%>> A
            <input type="radio" name="Answer" value="B" <% if(right.equals("B")) { %>checked<%}%>> B
            <input type="radio" name="Answer" value="C" <% if(right.equals("C")) { %>checked<%}%>> C
            <input type="radio" name="Answer" value="D" <% if(right.equals("D")) { %>checked<%}%>> D <br /><br />
            <label for="question">Answer Description:</label>
            <input type="text" name="answerDesc" value="<%=ans.getAnswerDesc()%>" /> <br /><br />
            Supplementary Image: </br>
            <input id="media" name="media" style="width: 30%;" type="file" accept=".jpg, .jpeg, .png">
            <a href="<%=request.getContextPath() + "/delete-media/" + ans.getQuestionID()%>">Delete Image</a>
            <br/><br/>
            <input type="hidden" name="QuizID" value="<%=ans.getQuizID()%>" />
            <input type="hidden" name="QuestionID" value="<%=ans.getQuestionID()%>" />
            
            
            <input type="submit" value="Save Changes" />
        </form>
    </body>
</html>
