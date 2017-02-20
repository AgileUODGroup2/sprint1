<%@ page import="stores.Result" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result Page</title>

    <%

        Result quizResult = (Result) request.getAttribute("Result");

    %>

</head>
<body>
<h1>Quiz (<%=quizResult.getQuizID()%>) Result:</h1>
<h3>Score: <%=quizResult.getScore()%></h3>
<h3>Attempts: <%=quizResult.getAttempts()%></h3>
<h3>Date Completed: <%=quizResult.getDate()%></h3>
</body>
</html>