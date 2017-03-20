<%-- 
    Document   : studentResults
    Created on : 17-Feb-2017, 19:49:52
    Author     : daniellewilliams
--%>

<%@page import="java.util.Arrays"%>
<%@page import="java.util.stream.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ListIterator"%>
<%@page import="stores.StudentResult"%>
<%@page import="stores.Quiz"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/styles.css"%>">
        <title>Student Results</title>
       
        <%
            String contextPath = request.getContextPath();
            java.util.LinkedList<StudentResult> quizResult = (java.util.LinkedList<StudentResult>) request.getAttribute("Results");
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
            
            Gson gsonResults = new Gson();
            
            int[] tempDivide = {0, 1, 2, 3, 4, 4, 3, 2, 1, 0};
            int totalStudents = Arrays.stream(tempDivide).sum();
            String json = gsonResults.toJson(tempDivide);
            //String json = gsonResults.toJson(quiz.getGradeDivide());
            //int totalStudents = Arrays.stream(quiz.getGradeDivide()).sum();
        %>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            
            var parsed = JSON.parse('<%=json%>');
            var arr = $.map(parsed, function(el) { return el});

            google.charts.load('current', {'packages':['bar']});
            google.charts.setOnLoadCallback(drawChart);
            
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Grade (%)', 'Students'],
                    ['0 - 10%', arr[0]],
                    ['10 - 20%', arr[1]],
                    ['20 - 30%', arr[2]],
                    ['30 - 40%', arr[3]],
                    ['40 - 50%', arr[4]],
                    ['50 - 60%', arr[5]],
                    ['60 - 70%', arr[6]],
                    ['70 - 80%', arr[7]],
                    ['80 - 90%', arr[8]],
                    ['90 - 100%', arr[9]]
                ]);
                
                var options = {
                    chart: {
                       title: 'Grade Distribution',
                       subtitle: 'Percentage Grade for <%=totalStudents%> Students on Module: <%=quiz.getModuleID()%>',
                    },
                    width: 800,
                    height: 800,
                    backgroundColor:'#d3dfeb',
                    chartArea: {
                        backgroundColor:'#d3dfeb',
                    },
                    vAxis: {
                        textStyle: {
                            color: "#d3dfeb",
                        },
                        gridlines: {
                            color: "#d3dfeb",
                        },
                        baselineColor: "#d3dfeb",
                    },
                };
                
                var chart = new google.charts.Bar(document.getElementById('chart_div'));
                chart.draw(data, google.charts.Bar.convertOptions(options));

            }
            
            
    </script>


        <%String type = (String) session.getAttribute("QuizType");%>
    </head>
    
        
    <body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="<%=request.getContextPath() + "/index.jsp"%>">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="<%=request.getContextPath() + "/logo123.png"%>" width="115" style="position: absolute; left:0; top: 0;">
        <img src="<%=request.getContextPath() + "/logo123.png"%>" width="115" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a>Quiz Name: <%=quiz.getQuizName()%> </a></li>
            </ul>
        </div>
           <br>
           <br>
         <div class="centerContent1">

          
        <%
                if(type == "Completed Quizzes")
                {
                    %>
               
                 
                    <div id="cc1">  
                        <h2>Quiz Profile</h2>

                        <h3>Quiz ID: <%=quiz.getQuizID()%></h3>
                        <h3>Date Created: <%=quiz.getDateCreated()%></h3>
                        <h3>Number of Q's: <%=quiz.getNumberOfQuestions()%></h3>
                        <h3>Quiz Status: <%=quiz.getStatus()%></h3>
                        <h3>Class Average: <%=quiz.getAverageScore()%></h3>

                        </div>
                      
                   
                        <div id="cc2">
                            <br>
                            <br>
                            <br>
                         <form method="GET" action=<%=contextPath + "/makeLive/" + quiz.getQuizID()%>><input type="submit" value="Make Live" /></form>
                         <form method="GET" action=<%=contextPath + "/displayQuestionsAndAnswers/" + quiz.getQuizID()%>><input type="submit" value="View Q's and A's" /></form>
                        </div>
         </div>
                    
                    <%
                }
                else if (type == "Live Quizzes")
                {
                                %>

                    
                    
                        
                            
        <div id="cc1">  
        <h2>Quiz Profile</h2>
        
        <h3>Quiz ID: <%=quiz.getQuizID()%></h3>
        <h3>Date Created: <%=quiz.getDateCreated()%></h3>
        <h3>Number of Q's: <%=quiz.getNumberOfQuestions()%></h3>
        <h3>Quiz Status: <%=quiz.getStatus()%></h3>
        <br>
        <h3>Class Average: <%=quiz.getAverageScore()%></h3>
       
        
        </div> 
        
        <div id="cc2">
            <br><br>
            
                    <% 
            if (!type.equals("Unfinished Quizzes")) { 
        %>

        <form method="POST" action="<%=contextPath%>/SearchResults">
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
            for(int i=2005;i<=2017;i++) {
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
            for(int i=2005;i<=2016;i++) {
            %> <option value="<%=i%>"><%=i%></option> <%
            } %><option value="2017" selected="selected">2017</option>
            </select>
            <input type="hidden" name="QuizID" value="<%=quiz.getQuizID()%>" />
            <input type="submit" value="Filter" />
        </form>
        <%
            }
            %>
        <form method="GET" action=<%=contextPath + "/displayQuestionsAndAnswers/" + quiz.getQuizID()%>><input type="submit" value="View Q's and A's" /></form>
        </div>
        <br>
        <br>
    
        <div id="cc3">
       <%
                        if(!quizResult.isEmpty()) {
                            %>
                                <table border="0" cellpadding="10" style=" margin: auto; font-family: candara; color: white; font-size: 25px; background-color:#042356;">
                                <tbody>
                                <tr>
                                    <td>Matriculation Number</td>
                                    <td>Name</td>
                                    <td>Score</td>
                                    <td>Attempts</td>
                                    <td>Date Completed</td>
                                </tr>
                            <%
                            Iterator<StudentResult> iterator = quizResult.iterator();
                            while(iterator.hasNext()){
                                StudentResult result = iterator.next(); 
                                %>
                                    <tr>
                                        <td><%=result.getMatriculationNumber()%></td>
                                        <td><%=result.getStudentName()%></td>
                                        <td><%=result.getScore()%></td>
                                        <td><%=result.getAttemptedCount()%></td>
                                        <td><%=result.getDate()%></td>
                                    </tr>
                                <%
                            }
                            %>
                                    </tbody>
                                    </table>
                                    
        </div>
        <div id="chart_div"></div>
                    
                            <%
                        }
   
                }
                else if (type == "Archived Quizzes")
{
         %>
         <div id="cc1">  
        <h2>Quiz Profile</h2>
        
        <h3>Quiz ID: <%=quiz.getQuizID()%></h3>
        <h3>Date Created: <%=quiz.getDateCreated()%></h3>
        <h3>Number of Q's: <%=quiz.getNumberOfQuestions()%></h3>
        <h3>Quiz Status: <%=quiz.getStatus()%></h3>
        <br>
       
        
        </div>
        
        <div id="cc2">
                        
                     <form method="GET" action=<%=contextPath + "/displayQuestionsAndAnswers/" + quiz.getQuizID()%>><input type="submit" value="View Q's and A's" /></form>
                    </div>
        <%
}
       
        
        
           else if (type == "Unfinished Quizzes")
{
         %>
         <div id="cc1">  
        <h2>Quiz Profile</h2>
        
        <h3>Quiz ID: <%=quiz.getQuizID()%></h3>
        <h3>Date Created: <%=quiz.getDateCreated()%></h3>
        <h3>Number of Q's: <%=quiz.getNumberOfQuestions()%></h3>
        <h3>Quiz Status: <%=quiz.getStatus()%></h3>
        <br>
       
        
        </div>
        
        <div id="cc2">
                        
                     <form method="GET" action=<%=contextPath + "/displayQuestionsAndAnswers/" + quiz.getQuizID()%>><input type="submit" value="View Q's and A's" /></form>
                    </div>

                     <%
                         }

%>
    </body>
</html>
