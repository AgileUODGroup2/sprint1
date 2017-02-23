<%-- 
    Document   : takeQuiz
    Created on : 22-Feb-2017, 23:53:57
    Author     : erincoey
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Take Quiz</title>
    </head>
     <% LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); %>
        
   
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
                <li><a> Get ready to start your quiz!</a></li>
            </ul>
        </div><br><br>
        <div class="centerContent1">
            <br><br><br>
            <button id="third-button">* Input Question *</button><br><br>
            <button id="fourth-button">* Answer 1 *</button><br>
            <button id="fourth-button">* Answer 2 *</button><br>
            <button id="fourth-button">* Answer 3 *</button><br>
            <button id="fourth-button">* Answer 4 *</button><br><br>
            
            <button id="fourth-button" style="width:20%;"> Next Question --> </button>
             <button id="fourth-button" style="width:20%;"> Exit Quiz </button><br><br>
            
            
        
        </div>
    </body>
</html>
