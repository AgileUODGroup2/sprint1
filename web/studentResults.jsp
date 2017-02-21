<%-- 
    Document   : studentResults
    Created on : 17-Feb-2017, 19:49:52
    Author     : daniellewilliams
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ListIterator"%>
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
        
        <%
            if(!quizResult.isEmpty()) {
                %>
                    <table border="1">
                    <tbody>
                    <tr>
                        <td>Matriculation Number</td>
                        <td>Name</td>
                        <td>Score</td>
                        <td>Attempts</td>
                        <td>Date Completed</td>
                    </tr>
                <%
                Iterator<StudentResult> iterator = quizResult.iterator();
                while(iterator.hasNext()){
                    StudentResult result = iterator.next(); 
                    %>
                        <tr>
                            <td><%=result.getMatriculationNumber()%></td>
                            <td><%=result.getStudentName()%></td>
                            <td><%=result.getScore()%></td>
                            <td><%=result.getAttemptedCount()%></td>
                            <td><%=result.getDate()%></td>
                        </tr>
                    <%
                }
                %>
                        </tbody>
                        </table>
                <%
            }
        %>
        
        
    </body>
</html>
