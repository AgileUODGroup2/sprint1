<%-- 
    Document   : moduleStudentsEnrolled
    Created on : 19-Feb-2017, 16:15:04
    Author     : ashawittchen
--%>

<%@page import="models.moduleStudentsEnrolledModel"%>
<%@page import="stores.moduleStudentsEnrolledStore"%>
<%@page import="servlets.moduleStudentsEnrolled"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><% String list = (String) request.getAttribute("students");%><%=list%></h1>
         <% java.util.LinkedList<moduleStudentsEnrolledStore> studentList = (java.util.LinkedList<moduleStudentsEnrolledStore>) request.getAttribute("StudentList");
            if (studentList != null){
                Iterator<moduleStudentsEnrolledStore> it = studentList.iterator();
                 while(it.hasNext()){
                     moduleStudentsEnrolledStore se = (moduleStudentsEnrolledStore) it.next();
                     %> <%=se.getMatriculation_Number()%>, <%=se.getModule_ID()%> <br> <%
                 }
                   request.setAttribute("studentList",null);
            }
            
          
            
            
            %>
         
              <form method="get" action=wholeStudentList.jsp">
            <button type="submit">Add Student</button>
        </form> 
    </body>
</html>

          