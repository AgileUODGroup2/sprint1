<%-- 
    Document   : completedQuizzes
    Created on : 19-Feb-2017, 13:55:02
    Author     : viivipursiainen
--%>

<%@page import="stores.LoggedIn"%>
<%@page import="models.user"%>
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
        <h1><%String type = (String) session.getAttribute("QuizType");%><%=type%></h1>
        
        <%
            LoggedIn staff = (LoggedIn) session.getAttribute("LoggedIn");
            int staffID = staff.getID();
            user staffUser = new user();
            java.util.Vector<String> modules = staffUser.getStaffModules(staffID);
        %>
        <form method="POST" action="filterByModule">
            <select name="module">
            <%
                for(int i=0; i<modules.size(); i++) {
                    %> <option value="<%=modules.get(i)%>"><%=modules.get(i)%></option> <%
                }
            %>
            <input type="submit" value="Filter by Module" />
            </select>
        </form>
            
        <%
            if (type.equals("Completed Quizzes")) {
        %>
        <form method="GET" action="completedQuiz"><input type="submit" value="Reset" /></form>
        <%
            } else if (type.equals("Live Quizzes")) {
        %>
        
        <form method="GET" action="liveQuiz"><input type="submit" value="Reset" /></form>
        
        <%
            } else {
        %>
        <form method="GET" action="unfinishedQuiz"><input type="submit" value="Reset" /></form>
         <% }java.util.LinkedList<Quiz> quizList = (java.util.LinkedList<Quiz>) request.getAttribute("QuizList");
                if (quizList != null) {
                    Iterator<Quiz> it = quizList.iterator();
                    while(it.hasNext()) {
                        Quiz q = (Quiz) it.next();
                        %> <%=q.getQuizName()%>, <%=q.getQuizID()%>, <%=q.getModuleID()%> <br> <%
                    }
                } else { %>
                No quizzes to show
                <%}%>
    </body>
</html>
