<%-- 
    Document   : wholeStudentList
    Created on : 19-Feb-2017, 16:42:55
    Author     : ashawittchen
--%>

<%@page import="models.wholeStudentListModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
           wholeStudentListModel studentList = new wholeStudentListModel();
            String result = studentList.getWholeStudentList();
            
            
            %>
          <h1> <%=result%> </h1>
              <form method="get" action=wholeStudentList.jsp">
            <button type="submit">Add Student</button>
        </form> 
    </body>
</html>
