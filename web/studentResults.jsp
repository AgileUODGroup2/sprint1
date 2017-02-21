<%-- 
    Document   : studentResults
    Created on : 17-Feb-2017, 19:49:52
    Author     : daniellewilliams
--%>

<%@page import="stores.StudentResult"%>
<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Results</title>
        
        <%
            java.util.LinkedList<StudentResult> quizResult = (java.util.LinkedList<StudentResult>) request.getAttribute("Results");
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
            
            StudentResult studTest = quizResult.get(1);
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
        <td><%=studTest.getMatriculationNumber()%></td>
        <td><%=studTest.getStudentName()%></td>
        <td><%=studTest.getScore()%></td>
        <td><%=studTest.getAttemptedCount()%></td>
        <td><%=studTest.getDate()%></td>
        </tr>
        </tbody>
        </table>
        
    </body>
</html>
