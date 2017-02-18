<%-- 
    Document   : quizResults
    Created on : 17-Feb-2017, 19:50:37
    Author     : daniellewilliams
--%>

<%@page import="java.util.Iterator"%>
<%@page import="stores.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
            <% java.util.LinkedList<Result> resultList = (java.util.LinkedList<Result>) request.getAttribute("ResultList");
                if (resultList != null) {
                    Iterator<Result> it = resultList.iterator();
                    while(it.hasNext()) {
                        Result r = (Result) it.next();
                    %> <%=r.getMatricNo()%>, <%=r.getQuizID()%>, <%=r.getAttempts()%>, <%=r.getCompleted()%>, <%=r.getScore()%>, <%=r.getDate()%> <%
                    }
                    request.setAttribute("ResultList", null);
                } else {
                    %>
            
            
<form method="POST" action="SearchResults">
    <select name="day1">
    <% for(int i=1;i<=31;i++) {
	%> <option value="<%=i%>"><%=i%></option> <%
	} %>
    </select>

    <select name="month1">
    <%
    String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    for(int i=1;i<=12;i++) {
    %> <option value="<%=i%>"><%=months[i-1]%></option> <%
    } %>
    </select>
    <select name="year1">
    <%
    for(int i=1995;i<=2017;i++) {
    %> <option value="<%=i%>"><%=i%></option> <%
    } %>
    </select>
    <br />
    <select name="day2">
    <% for(int i=1;i<=31;i++) {
	%> <option value="<%=i%>"><%=i%></option> <%
	} %>
    </select>

    <select name="month2">
    <%
    for(int i=1;i<=12;i++) {
    %> <option value="<%=i%>"><%=months[i-1]%></option> <%
    } %>
    </select>
    <select name="year2">
    <%
    for(int i=1995;i<=2017;i++) {
    %> <option value="<%=i%>"><%=i%></option> <%
    } %>
    </select>
    <input type="submit" value="Search" />
</form>
            <% }%>
            
            
            
            
            
    </body>
</html>
