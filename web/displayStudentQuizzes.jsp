<%-- 
    Document   : completedQuizzes
    Created on : 19-Feb-2017, 13:55:02
    Author     : viivipursiainen
--%>

<%@page import="stores.StudentQuiz"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%String type = (String) request.getAttribute("QuizType");%>
        <title>Quiz Master - <%=type%></title>
    </head>
    <body>
        <h1><%=type%></h1>
        
         <% java.util.LinkedList<StudentQuiz> quizList = (java.util.LinkedList<StudentQuiz>) request.getAttribute("StudentQuizList");
                if (quizList != null) {
                    Iterator<StudentQuiz> it = quizList.iterator();
                    while(it.hasNext()) {
                        StudentQuiz q = (StudentQuiz) it.next();
         %>
         
         
         <table><tr><td><%=q.getModuleID()%></td><td><a href="<%=q.getQuizID()%>"><%=q.getQuizName()%></a></td></tr>
             <tr><td></td><td><%=q.getStaffName()%></td></tr</table><br>
             
             
             <% }
                    request.setAttribute("StudentQuizList", null);
                }%>
    </body>
</html>
