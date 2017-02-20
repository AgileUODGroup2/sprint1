<%-- 
    Document   : studentModules
    Created on : 17-Feb-2017, 19:49:36
    Author     : daniellewilliams
--%>

<%@page import="lib.database.DatabaseConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Testing - displaying student details. </p>

        <table border="0" cellpadding="10">
            <thead>
                <tr>
                    <th>Matric No.</th>
                    <th>First Name</th>
                    <th>Last Name</th>

                </tr>
            </thead>
            <tbody>


                <%
                    DatabaseConnection db = new DatabaseConnection();
                    Connection conn = db.connectToDatabase();
                    System.out.println("Database connected: " + conn);
                    Statement st = conn.createStatement();
                    String query = "SELECT * from student";
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                %>
                <tr>
                    <%
                        int matric = rs.getInt("Matriculation_Number");
                        String firstname = rs.getString("First_Name");
                        String lastname = rs.getString("Last_Name");

                    %>
                    <td><%=matric%></td>
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
