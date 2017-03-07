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
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Question</title>
    </head>
    <body>
        <form method="POST" action="edit">
            <%=ans.getQuestion()%>
            <input type="text" name="question" /> <br /><br />
            <%=ans.getA()%>
            <input type="text" name="a" /> <br /><br />
            <%=ans.getB()%>
            <input type="text" name="b" /> <br /><br />
            <%=ans.getC()%>
            <input type="text" name="c" /> <br /><br />
            <%=ans.getD()%>
            <input type="text" name="d" /> <br /><br />
            <% String right = ans.getAnswer();%>
            <input type="radio" name="Answer" value="A" <% if(right.equals("A")) { %>checked<%}%>> A
            <input type="radio" name="Answer" value="B" <% if(right.equals("B")) { %>checked<%}%>> B
            <input type="radio" name="Answer" value="C" <% if(right.equals("C")) { %>checked<%}%>> C
            <input type="radio" name="Answer" value="D" <% if(right.equals("D")) { %>checked<%}%>> D <br /><br />
            <%=ans.getAnswerDesc()%>
            <input type="text" name="Answer_Desc" /> <br /><br />
            
            <input type="submit" value="Save Changes" />
        </form>
    </body>
</html>
