<%-- 
    Document   : moduleStudentsEnrolled
    Created on : 19-Feb-2017, 16:15:04
    Author     : ashawittchen
--%>

<%@page import="models.moduleStudentsEnrolledModel"%>
<%@page import="stores.moduleStudentsEnrolledStore"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            moduleStudentsEnrolledModel studentsEn = new moduleStudentsEnrolledModel();
           String result = studentsEn.getStudentsEnrolled(210101);
            
          
            
            
            %>
          <h1> <%=result%> </h1>
              <form method="get" action=wholeStudentList.jsp">
            <button type="submit">Add Student</button>
        </form> 
    </body>
</html>
