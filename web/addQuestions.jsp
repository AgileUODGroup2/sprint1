<%-- 
    Document   : addQuestions
    Created on : 18-Feb-2017, 19:52:26
    Author     : erincoey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <title>Add Questions</title>
    </head>
    <body>
        <h1>Add Questions</h1>
        
        <div class ="main">
		<form method="post"  action="addQuestions">
                        <label for = "question">Question: </label>
                        <input type="text" name="question" id="question">
                        <br><br>
			<label for = "answerA">a.: </label>
                        <input type="text" name="answerA" id="answerA">
                        <br>
			<label for = "answerB">b.: </label>
                        <input type="text" name="answerB" id="answerB">
                        <br>
                        <label for = "answerC">c: </label>
                        <input type="text" name="answerC" id="answerC" ><br>
                        <br>
                        <label for = "answerD">d: </label>
                        <input type="text" name="answerD" id="answerD">
                        <br><br><br>
                        
                        <input type="submit" value="Submit" name ="submit" >  
                        <br>
                        <input type="submit" value="Save" name="save"> 
                        <br> 
                        <input type="submit" value="Add Another Question" name="addQuestion" > 
                   
                </form>
			</div>
    </body>
</html>
