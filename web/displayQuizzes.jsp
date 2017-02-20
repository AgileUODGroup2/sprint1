<%-- 
    Document   : completedQuizzes
    Created on : 19-Feb-2017, 13:55:02
    Author     : viivipursiainen
--%>

<%@page import="java.util.Iterator"%>
<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%String type = (String) request.getAttribute("QuizType");%><%=type%></h1>
        
         <% java.util.LinkedList<Quiz> quizList = (java.util.LinkedList<Quiz>) request.getAttribute("QuizList");
                if (quizList != null) {
                    Iterator<Quiz> it = quizList.iterator();
                    while(it.hasNext()) {
                        Quiz q = (Quiz) it.next();
                        %> <%=q.getQuizName()%>, <%=q.getQuizID()%>, <%=q.getModuleID()%> <br> <%
                    }
                    request.setAttribute("QuizList", null);
                }%>
    </body>
</html>
