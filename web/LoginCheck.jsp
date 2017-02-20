<%@page import="lib.database.DatabaseConnection"%>
<%@page import="lib.database.DatabaseConnection"%>
<%@page import="lib.database.DatabaseConnection"%>
<%@page import="lib.database.DatabaseConnection"%>
<%@page import ="java.sql.*" %>
<%@page import ="java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="java.io.*"%>
    <html> 
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Login Check</title> 
        </head> 
        <body> 
            <%
                DatabaseConnection db = new DatabaseConnection();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String checkUser = "select * from staff where Staff_ID=? and Password=?";
            try(Connection theConnection = db.connectToDatabase();
   
                PreparedStatement ps = theConnection.prepareStatement(checkUser);){
                ps.setString(1,request.getParameter("username"));
                ps.setString(2,request.getParameter("password"));
                ResultSet theResult = ps.executeQuery();
                if(theResult.next()){
            response.sendRedirect("index.jsp");
            }
                else
                {
                    response.sendRedirect("failed.jsp");
                }
            }catch(Exception ex){
            System.out.println("Class Not Found");

}
                
%>