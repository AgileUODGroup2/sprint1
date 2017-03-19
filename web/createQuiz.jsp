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
 <%@ include file='cssStructure.jsp' %>
<!DOCTYPE html>
<html>
  
        <div class="navBar1">
            <ul>
                <li><a> Create Quiz</a></li>
            </ul>
        </div><br><br>

        <%
           String date = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

            String firstName = lg.getFirstName();
            String lastName = lg.getLastName();
            String name = firstName + " " + lastName;

        %>
        
        <script>
                function goBack() {
                    window.history.back();
                }
        </script>
        
            <div class ="centerContent1" style="display: block; text-align: center;">
            <br>
            <br>
            <h2> Please enter the Quiz details </h2>
            <br>
            <br>
		<form method="post"  action="createQuiz" style="display: block; margin: auto; text-align: left;">
                        <label for = "quizName">Quiz Name: </label>
                        <input type="text" name="quizName" id="quizName" style="width: 30%;">
                        <br>
			<label for = "moduleId">Module ID: </label>
                        <input type="text" name="moduleId" id="moduleId" style="width: 30%;">
                        <br>
			<label for = "staffName" >Staff Name: </label>
                        <input type="text" name="staffName" value="<%=name%>" id="staffName" style="width: 30%;">
                        <br>
                        <label for = "date" >Date: </label>
                        <input type="text" name="date" id="date" value="<%=date%>" style="width: 30%;"><br>
                        <br>
                        <label for = "available" >Available: </label>
                        <input type="radio" name="available" value="Live"> <h8>Yes</h8>
                        <input type="radio" name="available" value="Unfinished" checked> <h8>No</h8>
                        <br><br><br>
                        
                        
                        <input type="submit" style="margin: auto;" value="Submit" >  
                        <br>
                        <br>
                </form>
			</div>
                        <br>
                        <br>
                        <button10 onclick="goBack()">Return to Portal</button10>


        
    </body>
</html>
