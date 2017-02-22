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
            
       <div class="main"> 
        <form method="POST" action="edit-profile/<%=lg.getUsername()%>">
            <label for ="firstName"> First Name:  </label>
                <input name="firstName" type="text" value=<%=lg.getFirstName()%>
            <br>
                <label for ="lastName"> Last Name: </label>
                <input name="lastName" type="text" value=<%=lg.getLastName()%>
            <br>
                <label for ="password"> Password: </label>
                <input name="password" type="password">
            <br>
            <label for="rePassword"> Confirm Password: </label>
                <input name="rePassword" type="password">
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
            <label>
                <input type="submit" value="Submit">
            </label>
        </form>
       </div>
            <button onclick="goBack()">Cancel</button>
            <script>function goBack(){ window.history.back();}</script>
    </body>
</html>
