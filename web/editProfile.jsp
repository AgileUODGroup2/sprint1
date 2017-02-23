<%-- 
    Document   : editProfile
    Created on : 20-Feb-2017, 15:15:46
    Author     : conormckillop
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Edit Profile</title>
                <%LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
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
                <li><a> Edit Profile - <%=lg.getFirstName()%></a></li>
            </ul>
        </div>
            <br>
            <br>
    
       <div class="centerContent1" style="display: block; text-align: center;"> 
           <br>
           <br>
           <h7>Please update your details and click submit</h7>
           <br>
        <form method="POST" style="display: inline-block; margin: auto; text-align: left;" action="edit-profile/<%=lg.getUsername()%>">
            <br>
            <br>
            <label for ="firstName"> First Name:  </label>
                <input name="firstName" style="width: 30%;" type="text" value=<%=lg.getFirstName()%>
            <br>
                <label for ="lastName"> Last Name: </label>
                <input name="lastName" style="width: 30%;" type="text" value=<%=lg.getLastName()%>
            <br>
                <label for ="password"> Password: </label>
                <input name="password" style="width: 30%;" type="password">
            <br>
            <label for="rePassword"> Confirm Password: </label>
                <input name="rePassword" style="width: 30%;" type="password">
            <% 
                if(lg.isStaff()){
                    %>
                        <input type="hidden" name="isStaff" value="Staff">
                    <%
                }else{
                   %>
                        <input type="hidden" name="isStaff" value="Student">
                   <%
                }
            %>
            <br>
            <br>
            <br>
                <input type="submit" style="float-left: 50%;" value="Submit">
            
        </form>
            <br>
            <br>
       </div>
    </body>
</html>
