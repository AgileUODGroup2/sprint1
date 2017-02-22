 <%--
    Document   : mostRecentQuiz
    Created on : 21-Feb-2017, 00:43:05
    Author     : ashawittchen
--%>

<%@page import="java.util.Iterator"%>
<%@page import="models.QuizModel"%>
<%@page import="stores.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Most Recent Quiz</title>
    </head>

    <body>
         <% QuizModel rqs = new QuizModel();
            rqs.getFilterByRecent(); %>
       
    <h1><%String list = (String) request.getAttribute("RecentList");%><%=list%></h1>     
  <% java.util.LinkedList<Quiz>recentList = (java.util.LinkedList<Quiz>) request.getAttribute("RecentList"); 
                if (recentList != null) {
                    Iterator<Quiz> it = recentList.iterator();
                    while(it.hasNext()) {
                        Quiz rq = (Quiz) it.next();
                        %> <%=rq.getQuizID()%>, <%=rq.getModuleID()%>, <%=rq.getStaffName()%>, <%=rq.getDateCreated()%>, <%=rq.getQuizName()%>, <%=rq.getNumberOfQuestions()%>, <%=rq.getStatus()%>, <%=rq.getStaffID()%><br> <%
                    }
                     request.setAttribute("RecentList", null);
                    }   
                else if  (recentList == null) 
                {
%><h1>Empty</h1> <%     
                }
   %>
       
           <% QuizModel rrr = new QuizModel();
          Quiz result =  rrr.getFilterByRecentQuiz(); 
     %>
          <h1><%=result.getQuizID()%>,<%=result.getModuleID()%>, <%=result.getStaffName()%>, <%=result.getDateCreated()%>, <%=result.getQuizName()%>, <%=result.getNumberOfQuestions()%>, <%=result.getStatus()%>, <%=result.getStaffID()%> </h1>
       
          
              
           
    
    </body>
</html>
