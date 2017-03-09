<%-- 
    Document   : addQuestions
    Created on : 18-Feb-2017, 19:52:26
    Author     : erincoey
--%>

<%@page import="stores.LoggedIn"%>
<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css" />
        <title>Add Questions</title>
    </head>
     <%LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
   
    <body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="index.jsp">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="logo123.png" width="115" style="position: absolute; left:0; top: 0;">
        <img src="logo123.png" width="115" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a> Create Quiz - Add Questions</a></li>
            </ul>
        </div>

        
        <div class ="main">
            
            <% 
                    Quiz quiz = (Quiz) session.getAttribute("Quiz");
            %>
            
		<form method="post"  action="addQuestions" enctype="multipart/form-data">
                        <label for = "question">Question: </label>
                        <input type="text" name="question" id="question">
                        <br>
                        <label for="media"> Upload Image: </label>
                        <input name="media" type="file" accept=".jpg, .jpeg, .png">
                        <br><br>
			<label for = "answerA">a: </label>
                        <input type="text" name="answerA" id="answerA" >
                        <br>
			<label for = "answerB">b: </label>
                        <input type="text" name="answerB" id="answerB">
                        <br>
                        <label for = "answerC">c: </label>
                        <input type="text" name="answerC" id="answerC" >
                        <br>
                        <label for = "answerD">d: </label>
                        <input type="text" name="answerD" id="answerD">
                        <br>
                        
                        <label for = "correctAnswer" style ="width:200px;">Correct Answer: </label>
                        <br>
                        <input type="radio" name="correctAnswer" value="A" checked> A
                        <input type="radio" name="correctAnswer" value="B"> B
                        <input type="radio" name="correctAnswer" value="C"> C
                        <input type="radio" name="correctAnswer" value="D"> D
                        <br>
                        <br>

                        <label for = "answerDesc">Answer Description: </label>
                        <textarea name="answerDesc" cols="70" rows="4"></textarea>
                        <br>
                        <br>
                        <br>
                         
                        <input type="submit" value="Add Another Question" name="addQuestion" > 
                        <br>
                        <input type="submit" value="Submit" name ="submit" >  
                        <br>
                        <input type="submit" value="Save" name="save"> 
                        <br> 
                        <input type="submit" value="Cancel" name="cancel" > 
                   
                </form>
			</div>
    </body>
</html>
