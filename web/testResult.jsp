<%@ page import="stores.Result" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result Page</title>

    <%

        Result quizResult = (Result) request.getAttribute("Result"); // Result of a single quiz for a specific Matriculation Number

    %>

</head>
<body>
<h1>Quiz (<%=quizResult.getQuizID()%>) Result:</h1>
<h3>Score: <%=quizResult.getScore()%></h3>
<h3>Attempts: <%=quizResult.getAttempts()%></h3>
<h3>Date Completed: <%=quizResult.getDate()%></h3>

<!Below is a test for result checking. Can change - Conor>
        <h1>Enter Quiz ID:</h1>
        <form method="POST" action="result">
            <label>
                <input name="quizID" type="text"/>
            </label>
            <label>
                <input type="submit" value="Submit"/>
            </label>
        </form>
</body>
</html>