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
<%@ include file='cssStructure.jsp' %>
       
<!DOCTYPE html>
<html>
    <head>
       <div class="navBar1">
            <ul>
             <li><a>Welcome <%=lg.getFirstName()%>!</a></li>
            </ul>
       </div>
            <br>
            <br>
            <br>
            <div class="centerContent1">
                <br>
                <h7> Your <%String type = (String) session.getAttribute("QuizType");%><%=type%></h7>
                <br>
        <%
            LoggedIn staff = (LoggedIn) session.getAttribute("LoggedIn");
            int staffID = staff.getID();
            user staffUser = new user();
            java.util.Vector<String> modules = staffUser.getStaffModules(staffID);
        %>
        <form method="POST" action="filterByModule">
        
     
            
            <select name="module">
                <br>
            <%
                for(int i=0; i<modules.size(); i++) {
                    %> <option value="<%=modules.get(i)%>"><%=modules.get(i)%></option> <%
                } 
            %>
            <br>
            <input type="submit" value="Filter by Module" />
            </select>
</form>
            <br>
               
        <%
            if (type.equals("Completed Quizzes")) {
        %>
        <form method="GET" action="completedQuiz"><input type="submit" value="Reset" />
            
        </form>
        <br>
        <%
            } else if (type.equals("Live Quizzes")) {
        %>
        
        <form method="GET" action="liveQuiz"><input type="submit" value="Reset" /></form>
        <br>
        <%
            } else if (type.equals("Unfinished Quizzes")){
        %>
        <form method="GET" action="unfinishedQuiz"><input type="submit" value="Reset" /></form>
        <br>
        <%
            }  if (type.equals("Archived Quizzes")) {
        %>
        <form method="GET" action="archived"><input type="submit" value="Reset" /></form>
        <br>
        
         <% }java.util.LinkedList<Quiz> quizList = (java.util.LinkedList<Quiz>) request.getAttribute("QuizList");
                if (quizList != null) {
                    Iterator<Quiz> it = quizList.iterator();
                    while(it.hasNext()) {
                        Quiz q = (Quiz) it.next();
%> <a href="result/<%=q.getQuizID() %>"<button id="third-button" style="text-align: left; width:40%;">Quiz Name: <%=q.getQuizName()%> <br>Date Created: <%=q.getDateCreated()%><br> Quiz ID: <%=q.getQuizID()%> <br> Module ID: <%=q.getModuleID()%> <br><br> </button></a><%
                    }
                } else { %>
                <h7>No quizzes to show</h7>
                <%}%>
                <br>
                <br>
            </div>
                <br>
                <br>
                 <button10 onclick="goBack()">Return to Portal</button10>
                <script>
                    function goBack() {
                        window.history.back();
                    
                    }
                    </script>
    </body>
</html>
