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
        <form method="POST" action="edit">
            Question: <%=ans.getQuestion()%> <br />
            <input type="text" name="question" value="<%=ans.getQuestion()%>" /> <br /><br />
            A: <%=ans.getA()%> <br />
            <input type="text" name="a" value="<%=ans.getA()%>" /> <br /><br />
            B: <%=ans.getB()%> <br />
            <input type="text" name="b" value="<%=ans.getB()%>" /> <br /><br />
            C: <%=ans.getC()%> <br />
            <input type="text" name="c" value="<%=ans.getC()%>" /> <br /><br />
            D: <%=ans.getD()%> <br />
            <input type="text" name="d" value="<%=ans.getD()%>" /> <br /><br />
            <% String right = ans.getAnswer();%>
            Right answer: <br />
            <input type="radio" name="Answer" value="A" <% if(right.equals("A")) { %>checked<%}%>> A
            <input type="radio" name="Answer" value="B" <% if(right.equals("B")) { %>checked<%}%>> B
            <input type="radio" name="Answer" value="C" <% if(right.equals("C")) { %>checked<%}%>> C
            <input type="radio" name="Answer" value="D" <% if(right.equals("D")) { %>checked<%}%>> D <br /><br />
            Answer Description: <br />
            <%=ans.getAnswerDesc()%> <br />
            <input type="text" name="answerDesc" value="<%=ans.getAnswerDesc()%>" /> <br /><br />
            <input type="hidden" name="QuizID" value="<%=ans.getQuizID()%>" />
            <input type="hidden" name="QuestionID" value="<%=ans.getQuestionID()%>" />
            
            <input type="submit" value="Save Changes" />
        </form>
    </body>
</html>
