<%-- 
    Document   : test
    Created on : 16-Feb-2017, 19:08:00
    Author     : erincoey
--%>

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
                    testStore ts = (testStore) session.getAttribute("testStore");
                    String test = ts.getResult();
            %>
            <h1> <%=test%> </h1>
    </body>
</html>
