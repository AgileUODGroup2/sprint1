<%-- 
    Document   : completedQuizzes
    Created on : 19-Feb-2017, 13:55:02
    Author     : viivipursiainen
--%>

<%@page import="models.user"%>
<%@page import="stores.LoggedIn"%>
<%@page import="stores.StudentQuiz"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%String type = (String) session.getAttribute("QuizType");%>
        <title>Quiz Master - <%=type%></title>
    </head>
    <body>
        <h1><%=type%></h1>
        
        <%
            LoggedIn student = (LoggedIn) session.getAttribute("LoggedIn");
            int matricNo = student.getID();
            user studentUser = new user();
            java.util.Vector<String> modules = studentUser.getStudentModules(matricNo);
        %>
        <form method="POST" action="filterByModuleStudent">
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
        <form method="GET" action="completedQuizzes"><input type="submit" value="Reset" /></form>
        <%
            } else if (type.equals("Incomplete Quizzes")) {
        %>
        
        <form method="GET" action="incompleteQuizzes"><input type="submit" value="Reset" /></form>
        
        <%
            } else {
        %>
        <form method="GET" action="pendingdQuizzes"><input type="submit" value="Reset" /></form>
        
        
         <% }   java.util.LinkedList<StudentQuiz> quizList = (java.util.LinkedList<StudentQuiz>) session.getAttribute("StudentQuizList");
                Iterator<StudentQuiz> it = quizList.iterator();
                if (quizList != null && !quizList.isEmpty()) {
                    if (type.equals("Completed Quizzes")) {
                        while(it.hasNext()) {
                            StudentQuiz q = (StudentQuiz) it.next();
         %>
         
         
         <table><tr><td><%=q.getModuleID()%></td><td><a href="result/<%=q.getQuizID()%>"><%=q.getQuizName()%></a></td></tr>
             <tr><td></td><td><%=q.getStaffName()%></td></tr</table><br>
             
             
             <%         }
                    } else {
                      while(it.hasNext()) {
                            StudentQuiz q = (StudentQuiz) it.next();
            %>

            <table><tr><td><%=q.getModuleID()%></td><td><a href="edit/<%=q.getQuizID()%>"><%=q.getQuizName()%></a></td></tr>
             <tr><td></td><td><%=q.getStaffName()%></td></tr</table><br>
            
            <%          }
                    }
                    request.setAttribute("StudentQuizList", null);
                } else {%>
                No quizzes to show
                <%}%>
    </body>
</html>
