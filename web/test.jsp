<%-- 
    Document   : test
    Created on : 16-Feb-2017, 19:08:00
    Author     : erincoey
--%>

<%@page import="models.testModel"%>
<%@page import="java.sql.Connection"%>
<%@page import="lib.database.DatabaseConnection"%>
<%@page import="stores.testStore"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
    </head>
    <body>
        <h1>Testing </h1>
        
        <%
            testModel test = new testModel();
            String result = test.getStaffDetails();
    
            testStore ts = new testStore();
            ts.setResult(result);

            String string1 = ts.getResult();
        %>
            <h1> <%=string1%> </h1>
    </body>
</html>
