<%-- 
    Document   : studentResults
    Created on : 17-Feb-2017, 19:49:52
    Author     : daniellewilliams
--%>

<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Results</title>
        
        <%
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
        %>
        
    </head>
    <body>
        <h2><%=quiz.getQuizName()%></h2>
        <h3>Class Average is <%=quiz.getAverageScore()%>%</h3>
    </body>
</html>
