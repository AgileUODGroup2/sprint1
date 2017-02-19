<%-- 
    Document   : createAQuiz
    Created on : 17-Feb-2017, 19:23:36
    Author     : erincoey
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <title>Create A Quiz</title>
    </head>
    <body>
        <h1> Would you like to create a quiz?</h1>
        <h2> Quiz Details: </h2>
        
        <%
            session.setAttribute( "newQuiz", null );
            String date = new SimpleDateFormat("YYYY-MM-dd").format(new Date()); 
            
        %>
        <div class ="main">
		<form method="post"  action="createQuiz">
                        <label for = "quizName">Quiz Name: </label>
                        <input type="text" name="quizName" id="quizName">
                        <br>
			<label for = "moduleId">Module ID: </label>
                        <input type="text" name="moduleId" id="moduleId" style="width:30%;">
                        <br>
			<label for = "staffName">Staff Name: </label>
                        <input type="text" name="staffName" id="staffName" style="width:30%;">
                        <br>
                        <label for = "date">Date: </label>
                        <input type="text" name="date" id="date" value="<%=date%>"><br>
                        <br>
                        <label for = "available">Available: </label>
                        <input type="radio" name="available" value="Live"> Yes
                        <input type="radio" name="available" value="Unfinished"> No
                        
                        <label for = "numOfQuestions">Number of Questions: </label>
                        <input type="text" name="numOfQuestions" id="numOfQuestions"><br>
                        <br><br><br>
                        
                        
                        <input type="submit" value="Submit" >  
                </form>
			</div>
        
    </body>
</html>
