<%-- 
    Document   : register
    Created on : 17-Feb-2017, 19:48:05
    Author     : daniellewilliams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <body bgcolor=#d3dfeb>
                <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Register</title>
    </head>
        <div class="navBar">
            <ul>
               <li><a href="index.jsp">QUIZ MASTER</a></li>
             </ul>
        </div>
        <img src="logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="logo123.png" width="115px" style="position: absolute; right:0; top: 0;">
    </div>
    <div class="navBar1">
            <ul>
                <li><a>Register</a></li>
            </ul>
    </div>
    <br>
    <div class="centerContent1">
        <form method="post" action="register" style="max-width: 100%; display: blobk;">
            <br>
    <h6> Please enter the details of the staff member you wish to register in the form below</h6>
    <br>
    </div>
    <div class="cent1">
    <h8><label1 for = "staffID">Staff ID: </label1></h8>
    <input type="text" name="staffID" id="staffID" style="width:250px; height: 30px;">
                        <br>
    <h8><label1 for = "firstName">First Name: </label1></h8>
    <input type="text" name="firstName" id="firstName" style="width:250px; height: 30px;">
                        <br>
    <h8><label1 for = "lastName">Last Name: </label1></h8>
    <input type="text" name="lastName" id="lastName" style="width:250px; height: 30px;">
                        <br>
    <h8><label1 for = "password">Password: </label1></h8>
    <input type="password" name="password" id="password" style="width:250px; height: 30px;">
                        <br>    
                        <br>
    <input type="submit" value="Submit" style="margin: auto;"> 
        </form>
    </div>
    </body>
</html>
 
