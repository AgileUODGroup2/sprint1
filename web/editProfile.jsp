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
        <title>Edit Profile</title>
                <%LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
    </head>
    <body>
        <h3><%=lg.getUsername()%> - Edit Profile</h3>
        
        <form method="POST" action="edit-profile/<%=lg.getUsername()%>">
            <label>
                First Name
                <input name="firstName" type="text" value=<%=lg.getFirstName()%>>
            </label>
            <label>
                Last Name
                <input name="lastName" type="text" value=<%=lg.getLastName()%>>
            </label>
            <label>
                Password
                <input name="password" type="password">
            </label>
            <label>
                Confirm Password
                <input name="rePassword" type="password">
            </label>
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
            <button onclick="goBack()">Cancel</button>
            <script>function goBack(){ window.history.back();}</script>
    </body>
</html>
