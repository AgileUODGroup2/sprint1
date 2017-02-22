<%-- 
    Document   : createAQuiz
    Created on : 17-Feb-2017, 19:23:36
    Author     : erincoey
--%>

<%@page import="stores.LoggedIn"%>
<%@page import="models.QuizModel"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css" />
        <title>Create A Quiz</title>
        <%LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
    </head>
    <body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="logo123.png" width="115px" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a> Create Quiz</a></li>
            </ul>
        </div>

        <h2> Quiz Details: </h2>
        
        <%
           String date = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

            String firstName = lg.getFirstName();
            String lastName = lg.getLastName();
            String name = firstName + " " + lastName;

        %>
        <div class ="main">
		<form method="post"  action="createQuiz">
                        <label for = "quizName">Quiz Name: </label>
                        <input type="text" name="quizName" id="quizName">
                        <br>
			<label for = "moduleId">Module ID: </label>
                        <input type="text" name="moduleId" id="moduleId">
                        <br>
			<label for = "staffName">Staff Name: </label>
                        <input type="text" name="staffName" value="<%=name%>" id="staffName" style="width:30%;">
                        <br>
                        <label for = "date">Date: </label>
                        <input type="text" name="date" id="date" value="<%=date%>"><br>
                        <br>
                        <label for = "available">Available: </label>
                        <input type="radio" name="available" value="Live"> Yes
                        <input type="radio" name="available" value="Unfinished" checked> No
                        <br><br><br>
                        
                        
                        <input type="submit" value="Submit" >  
                </form>
			</div>
        
    </body>
</html>
