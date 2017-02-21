<%-- 
    Document   : studentResults
    Created on : 17-Feb-2017, 19:49:52
    Author     : daniellewilliams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
