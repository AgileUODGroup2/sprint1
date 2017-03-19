<%-- 
    Document   : addQuestions
    Created on : 18-Feb-2017, 19:52:26
    Author     : erincoey
--%>

<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file='cssStructure.jsp' %>
<!DOCTYPE html>
<html>

      
        <div class="navBar1">
            <ul>
                <li><a> Create Quiz</a></li>
            </ul>
        </div>
        <br><br>
        
        <div class ="centerContent1">
            
            <% 
                    Quiz quiz = (Quiz) session.getAttribute("Quiz");
            %>
            
		<form method="post"  action="addQuestions" enctype="multipart/form-data">
                        <label for = "question">Question: </label>
                        <input type="text" name="question" id="question">
                        <br>
                        <label for="media"> Upload Image: </label>
                        <input name="media" type="file" accept=".jpg, .jpeg, .png" id="media">
                        <br><br>
			<label for = "answerA">A: </label>
                        <input type="text" name="answerA" id="answerA" >
                        <br>
			<label for = "answerB">B: </label>
                        <input type="text" name="answerB" id="answerB">
                        <br>
                        <label for = "answerC">C: </label>
                        <input type="text" name="answerC" id="answerC" >
                        <br>
                        <label for = "answerD">D: </label>
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
                        <textarea name="answerDesc" cols="70" rows="4" id="answerDesc"></textarea>
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
