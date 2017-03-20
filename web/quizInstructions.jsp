<%-- 
    Document   : quizInstructions
    Created on : 23-Feb-2017, 15:40:51
    Author     : daniellewilliams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file='cssStructure.jsp' %>

<!DOCTYPE html>
<html>
    <head>
        <title>Instructions</title>
        
        <div class="navBar1">
            <ul>
                <li><a>Instructions Page</a></li>
            </ul>
        </div>
            <br>
            <br>
            <div class="centerContent2">
                <iframe id="instructionVideo" width="560" height="315" src="https://www.youtube-nocookie.com/embed/TjPE8M1z9X8?rel=0&amp;controls=0&amp;showinfo=0" frameborder="0" align="middle"></iframe>
                <br>
                <br>
                <h7>Follow the steps below to complete a quiz:</h7>
                <br>
                <h7>Step 1</h7>
                <h5>Click 'Take Quiz' to begin the quiz</h5>
                <h7>Step 2</h7>
                <h5>You will see the question displayed in a box above four possible answers</h5>
                <h7>Step 3</h7>
                <h5>Select your answer and click 'next question' to continue</h5>
                <h7>Step 4</h7>
                <h5>Once you have answered every question on the quiz, click 'Submit'</h5>
                <h7>Step 5</h7>
                <h5>Review your answers before the quiz is finalised, and confirm your submission</h5>
                <h7>Good Luck! <h7>
                <br>
            </div>
            <br>
            <br>
            <button10 onclick="goBack()">Return</button10>

<script>
function goBack() {
    window.history.back();
}
</script>
<br>
<br>
    </body>
</html>
