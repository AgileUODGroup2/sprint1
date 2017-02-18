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
        <title>Register</title>
    </head>
    <body>
        <form method="post" action="register">
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Enter Information Here</th>
                    </tr>
                </thead>
                <tbody>
                        <tr>
                        <td>Staff ID</td>
                        <td><input type="text" name="Staff_ID" value="" /></td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="First_Name" value="" /></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="Last_Name" value="" /></td>
                    </tr>
                  
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="Password" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                       
                    </tr>
                    <tr>
                        
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>