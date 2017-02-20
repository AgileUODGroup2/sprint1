<%-- 
    Document   : staffModules
    Created on : 17-Feb-2017, 19:51:04
    Author     : daniellewilliams
--%>

<%@page import="models.staffModulesModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
      <%
            staffModulesModel staffMM = new staffModulesModel();
            String result = staffMM.getStaffModules(1);
            
            
            %>
             <form method="get" action="moduleStudentsEnrolled.jsp">
            <button type="submit"><%=result%></button>
        </form> 
        
           <h1> <%=result%> </h1>

    </body>
</html>
