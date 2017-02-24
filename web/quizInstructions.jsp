<%-- 
    Document   : quizInstructions
    Created on : 23-Feb-2017, 15:40:51
    Author     : daniellewilliams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Instructions</title>
    </head>
    <body>
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
                <li><a>Instructions Page</a></li>
            </ul>
        </div>
            <br>
            <br>
            <div class="centerContent1">
                <br>
                <br>
                <h7>Here you will find instructions on how to successfully complete the quiz</h7>
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
