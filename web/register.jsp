<%-- 
    Document   : register
    Created on : 17-Feb-2017, 19:48:05
    Author     : daniellewilliams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@ include file='cssStructure.jsp' %>
<!DOCTYPE html>

<title>Register</title>
    <div class="navBar1">
            <ul>
                <li><a>Register</a></li>
            </ul>
    </div>
    <br>
    <br>
    <br>
    <div class="centerContent1" style="display: block; text-align: center;">
        <form method="post" action="register" style="display: inline-block; margin: auto; text-align: left;">
            <br>
            <br>
    <h7> Please enter your staff details below</h7>
    <br>
    <br>
    <label for = "staffID">Staff ID: </label>
    <input type="text" name="staffID" id="staffID" style="width:40%; height: 30px;">
                        <br>
    <label for = "firstName">First Name: </label>
    <input type="text" name="firstName" id="firstName" style="width:40%; height: 30px;">
                        <br>
    <label for = "lastName">Last Name: </label>
    <input type="text" name="lastName" id="lastName" style="width:40%; height: 30px;">
                        <br>
    <label for = "password">Password: </label>
    <input type="password" name="password" id="password" style="width:40%; height: 30px;">
                        <br>    
                        <br>
    <input type="submit" value="Submit" style="margin: auto;"> 
   
    <br>
    <br>
    
        </form>
    </div>
    <br>
    <br>
    
    <button10 onclick="goBack()">Return to Log In</button10>

<script>
function goBack() {
    window.history.back();
}
</script>
    </body>
</html>
 
