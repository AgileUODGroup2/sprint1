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
        <link rel="stylesheet" type="text/css" href="styles.css" />
        <title>Quiz Master</title>
    </head>
      <body bgcolor="d3dfeb">
         <% LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); %>
        
      <div class="navBar">
          <ul>
                 <li><a href="index.jsp">QUIZ MASTER </a></li>
          </ul>
      </div>
      <img src="logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="logo123.png" width="115px" style="position: absolute; right:0; top: 0;">
        
        
        <div class="navBar1">
             <li><a>Welcome <%=lg.getFirstName()%>!</a></li>
             </div>
             <br>
            <br>
            <br>
            <div class="centerContent1">
                <br>
                <h7>Your <%String type = (String) session.getAttribute("QuizType");%><%=type%></h7>
                <br>
                <br>
        <%
            LoggedIn student = (LoggedIn) session.getAttribute("LoggedIn");
            int matricNo = student.getID();
            user studentUser = new user();
            String path = request.getContextPath();
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
        
        <form method="POST" action="orderByDate">
            <input type="submit" value="Order By Date" />
            <input type="hidden" value="studentcompleted" name="table" />
            <input type="hidden" value="<%=matricNo%>" name="MatricNo" />
        </form>
        <a href="<%=path%>/completedQuizzes"><button id="fifth-button" style="text-align: center; width:25%; background-color: #042356">Reset</button></a> <br />
        <%
            } else if (type.equals("Incomplete Quizzes")) {
        %>
        
        
        <form method="POST" action="orderByDate">
            <input type="submit" value="Order By Date" />
            <input type="hidden" value="studentincomplete" name="table" />
            <input type="hidden" value="<%=matricNo%>" name="MatricNo" />
        </form>
        <a href="<%=path%>/incompleteQuizzes"><button id="fifth-button" style="text-align: center; width:25%; background-color:  #042356">Reset</button></a> <br />
        <%
            } else {
        %>
        
        <form method="POST" action="orderByDate">
            <input type="submit" value="Order By Date" />
            <input type="hidden" value="studentpending" name="table" />
            <input type="hidden" value="<%=matricNo%>" name="MatricNo" />
        </form>
         <a href="<%=path%>/pendingdQuizzes"><button id="fifth-button" style="text-align: center; width:25%; background-color: #042356">Reset</button></a> <br />
             
         <% }   java.util.LinkedList<StudentQuiz> quizList = (java.util.LinkedList<StudentQuiz>) session.getAttribute("StudentQuizList");
                Iterator<StudentQuiz> it = quizList.iterator();
                if (quizList != null && !quizList.isEmpty()) {
                    if (type.equals("Completed Quizzes")) {
                        while(it.hasNext()) {
                            StudentQuiz q = (StudentQuiz) it.next();
         %>
         <br>
                  
              <a href="result/<%=q.getQuizID() %>"><button id="third-button" style="text-align: left; width:40%;">Quiz Name:<%=q.getQuizName()%> <br> Quiz ID:<%=q.getQuizID()%> <br> Module ID:<%=q.getModuleID()%> <br><br> </button></a><%
                            }
                    }
                 
                    else {
                      while(it.hasNext()) {
                            StudentQuiz q = (StudentQuiz) it.next();
            %>
              <br>
            <a href="result/<%=q.getQuizID() %>"><button id="third-button" style="text-align: left; width:40%;">Quiz Name:<%=q.getQuizName()%> <br> Quiz ID:<%=q.getQuizID()%> <br> Module ID:<%=q.getModuleID()%> <br><br> </button></a><%
            
           
                    
                    }
                        
                    }
                    request.setAttribute("StudentQuizList", null);
                } else {%>
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
