<%-- 
    Document   : studentResults
    Created on : 17-Feb-2017, 19:49:52
    Author     : daniellewilliams
--%>

<%@page import="stores.Result"%>
<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Results</title>
        
        <%
            java.util.LinkedList<Result> quizResult = (java.util.LinkedList<Result>) request.getAttribute("Results");
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
        %>
        
    </head>
    <body>
        <h2><%=quiz.getQuizName()%></h2>
        <h3>Class Average is <%=quiz.getAverageScore()%>%</h3>
        <br>
        <h3>Students took quiz: <%=quizResult.size()%></h3>
        
        <table border="1">
        <tbody>
        <tr>
        <td>Matriculation Number</td>
        <td>Name</td>
        <td>Score</td>
        <td>Attempts</td>
        <td>Date Completed</td>
        </tr>
        <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        </tr>
        </tbody>
        </table>
        
    </body>
</html>
