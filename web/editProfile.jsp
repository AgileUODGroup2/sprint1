<%-- 
    Document   : editProfile
    Created on : 20-Feb-2017, 15:15:46
    Author     : conormckillop
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file='cssStructure.jsp' %>
<!DOCTYPE html>
<html>
    <head>
        <title> Edit Profile </title>
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
        <form method="POST" style="display: inline-block; margin: auto; text-align: left;" action="edit-profile/<%=lg.getUsername()%>" enctype="multipart/form-data">
            <br>
            <br>
            <label for="firstName"> First Name:  </label>
                <input id="firstName" name="firstName" style="width: 30%;" type="text" value=<%=lg.getFirstName()%>
            <br>
                <label for="lastName"> Last Name: </label>
                <input id="lastName" name="lastName" style="width: 30%;" type="text" value=<%=lg.getLastName()%>
            <br>
                <label for="password"> Password: </label>
                <input id="password" name="password" style="width: 30%;" type="password">
            <br>
            <label for="rePassword"> Confirm Password: </label>
                <input id="rePassword" name="rePassword" style="width: 30%;" type="password">
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
            <label for="profileImage"> Upload Profile Image: </label>
                <input id="profileImage" name="profileImage" style="width: 30%;" type="file" accept=".jpg, .jpeg, .png">
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
