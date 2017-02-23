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
           <link rel="stylesheet" type="text/css" href="styles.css">
        <title>JSP Page</title>
    </head>
    <body bgcolor="d3dfeb">
         <% LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); %>
         <img src="logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="logo123.png" width="115px" style="position: absolute; right:0; top: 0;">
      <div class="navBar">
          <ul>
                 <li><a href="index.jsp">QUIZ MASTER </a></li>
          </ul>
      </div>
     
       <div class="navBar1">
            <ul>
             <li><a>Your <%String type = (String) session.getAttribute("QuizType");%><%=type%></a></li>
            </ul>
       </div>
            <br>
            <br>
            <br>
            <div class="centerContent1">
                <br>
                <h7> Welcome <%=lg.getFirstName()%>!</h7>
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
        <form method="GET" action="completedQuiz"><input type="submit" value="Reset" /></form>
        <br>
        <%
            } else if (type.equals("Live Quizzes")) {
        %>
        
        <form method="GET" action="liveQuiz"><input type="submit" value="Reset" /></form>
        <br>
        <%
            } else {
        %>
        <form method="GET" action="unfinishedQuiz"><input type="submit" value="Reset" /></form>
        <br>
         <% }java.util.LinkedList<Quiz> quizList = (java.util.LinkedList<Quiz>) request.getAttribute("QuizList");
                if (quizList != null) {
                    Iterator<Quiz> it = quizList.iterator();
                    while(it.hasNext()) {
                        Quiz q = (Quiz) it.next();
%> <a href="result/<%=q.getQuizID() %>"<button id="third-button" style="text-align: left; width:40%;">Quiz Name: <%=q.getQuizName()%> <br> Quiz ID: <%=q.getQuizID()%> <br> Module ID: <%=q.getModuleID()%> <br><br> </button></a><%
                    }
                } else { %>
                <h7>No quizzes to show</h7>
                <%}%>
                <br>
                <br>
            </div>
    </body>
</html>
