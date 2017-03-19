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
            <br>
            <% 
                    Quiz quiz = (Quiz) session.getAttribute("Quiz");
            %>
      
		<form method="post"  action="addQuestions" enctype="multipart/form-data" >
                        <label for = "question">Question: </label>
                        <input type="text" name="question" id="question" style="width: 30%;">
                        <br><br><br>
                        <label for="media"> Upload Image: </label>
                        <input name="media" type="file" accept=".jpg, .jpeg, .png" id="media">
                        <br><br>
			<label for = "answerA">A: </label>
                        <input type="text" name="answerA" id="answerA" style="width: 30%;" >
                        <br>
			<label for = "answerB">B: </label>
                        <input type="text" name="answerB" id="answerB" style="width: 30%;">
                        <br>
                        <label for = "answerC">C: </label>
                        <input type="text" name="answerC" id="answerC" style="width: 30%;" >
                        <br>
                        <label for = "answerD">D: </label>
                        <input type="text" name="answerD" id="answerD" style="width: 30%;">
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
                        <textarea name="answerDesc" cols="70" rows="8" id="answerDesc" style="width: 30%;"></textarea>
                        <br>
                        <br>
                        <br>
                         
                        <input type="submit" value="Add Another Question" name="addQuestion"><Br><Br>
                        <div id="cc5" style="background-color: #042356">
                        <input type="submit" value="Submit" name ="submit" style="display: inline-block;" >  
                        <input type="submit" value="Save" name="save" style="display: inline-block;"> 
                      <input type="submit" value="Cancel" name="cancel" style="display: inline-block;"  > 
                   
                </form>
       
            </div>
            <br><br>
			</div>
    </body>
</html>
