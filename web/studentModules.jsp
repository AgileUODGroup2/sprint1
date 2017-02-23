<%-- 
    Document   : staffModules
    Created on : 17-Feb-2017, 19:51:04
    Author     : daniellewilliams
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="stores.LoggedIn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="lib.database.DatabaseConnection"%>
<%@page import="models.staffModulesModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
                 <link rel="stylesheet" type="text/css" href="styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
   
         <% LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); %>
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
                <li><a> <%=lg.getFirstName()%>'s Modules
                    </a></li>
            </ul>
        </div>
                    <br>
                    <Br>
                    <div class="centerContent1">
                        <br>
                        <h7> Here is a list of your Modules</h7>
                        <br>
                        <br>
        <table border="0" cellpadding="10" style="display: block; margin: auto; margin-left: 30%; font-family: candara; color: white; font-size: 25px;">
            <thead>
                <tr>
                    <th>Module Name</th>
                    <th>Module Title</th>

                </tr>
            </thead>
            <tbody>


                <%
                    
                    int staffID = lg.getID();
                    DatabaseConnection db = new DatabaseConnection();
                    Connection conn = db.connectToDatabase();
                    System.out.println("Database connected: " + conn);
                    String query = "SELECT module.Module_ID, module.Module_Title, student_enrolment.Matriculation_Number FROM module JOIN student_enrolment WHERE module.Module_ID = student_enrolment.Module_ID AND student_enrolment.Matriculation_Number = ?";
                    //Statement st = conn.createStatement();
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setInt(1, staffID);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()) {
                        
                %>
                <tr>
                    <%
                        String firstname = rs.getString("Module_ID");
                        String lastname = rs.getString("Module_Title");

                    %>
                    <td><%=firstname%></td>
                    <td><%=lastname%></td>

                </tr>               

                <%
                    }
                %>
</tbody>
        </table>
<br>
<br>
<button10 onclick="goBack()">Return to Portal</button10>

<script>
function goBack() {
    window.history.back();
}
</script>
<br>
<br>
                    </div>
    </body>
</html>
