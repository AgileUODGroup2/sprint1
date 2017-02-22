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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     <p>Displaying Modules staff are enrolled on </p>

        <table border="0" cellpadding="10">
            <thead>
                <tr>
                    <th>Module Name</th>
                    <th>Module Title</th>

                </tr>
            </thead>
            <tbody>


                <%
                    LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); 
                    int staffID = lg.getID();
                    DatabaseConnection db = new DatabaseConnection();
                    Connection conn = db.connectToDatabase();
                    System.out.println("Database connected: " + conn);
                    String query = "SELECT module.Module_ID, module.Module_Title, staff_enrolment.Staff_ID FROM module JOIN staff_enrolment WHERE module.Module_ID = staff_enrolment.Module_ID AND staff_enrolment.Staff_ID = ?";
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
    </body>
</html>
