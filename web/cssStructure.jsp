<%-- 
    Document   : cssStructure
    Created on : 10-Mar-2017, 12:58:09
    Author     : daniellewilliams
--%>
<%@page import="stores.LoggedIn"%>
<%@page import="models.QuizModel"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="stores.Quiz"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/styles.css"%>">
        
        <%LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
    </head>
    <body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="<%=request.getContextPath()%>/index.jsp">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="<%=request.getContextPath()%>/<%="logo123.png"%>" width="115" style="position: absolute; left:0; top: 0;">
        <img src="<%=request.getContextPath()%>/<%="logo123.png"%>" width="115" style="position: absolute; right:0; top: 0;">
    </body>
</html>
