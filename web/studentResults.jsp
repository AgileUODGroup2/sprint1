<%-- 
    Document   : studentResults
    Created on : 17-Feb-2017, 19:49:52
    Author     : daniellewilliams
--%>

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
        <link rel="stylesheet" type="text/css" href="/AC31007Quiz/styles.css">
        <title>Student Results</title>
        
        <%
            java.util.LinkedList<StudentResult> quizResult = (java.util.LinkedList<StudentResult>) request.getAttribute("Results");
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
            
            int[] tempDivide = {2, 6, 5, 3, 5, 5, 4, 3, 3, 4};
            
            Gson gsonResults = new Gson();
            
            //Serialisation
            //String json = gsonResults.toJson(quiz.getGradeDivide());
            String json = gsonResults.toJson(tempDivide);
        %>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            
            var parsed = JSON.parse('<%=json%>');
            var arr = $.map(parsed, function(el) { return el});

            google.charts.load('current', {'packages':['corechart']});
            
            google.charts.setOnLoadCallback(drawChart);
            
            function drawChart() {

              var data = new google.visualization.DataTable();
              data.addColumn('string', 'Grade');
              data.addColumn('number', 'Students');
              data.addRows([
                ['0-10', arr[0]],
                ['10-20', arr[1]],
                ['20-30', arr[2]],
                ['30-40', arr[3]],
                ['40-50', arr[4]],
                ['50-60', arr[5]],
                ['60-70', arr[6]],
                ['70-80', arr[7]],
                ['80-90', arr[8]],
                ['90-100', arr[9]]
              ]);

              var options = {'width':800,
                              hAxis: {
                                  title: 'Score (%)',
                                  textStyle: {
                                      fontSize: 10
                                  }
                              },
                              vAxis: {
                                  title: 'Number of Students',
                                  textStyle: {
                                      fontSize: 10
                                  }
                              },
                             'height':700};

              var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
              chart.draw(data, options);
            }
    </script>


        <%String type = (String) session.getAttribute("QuizType");%>
    </head>
    
        
    <body bgcolor="d3dfeb">
        <div class="navBar">
            <ul>
                <li><a href="/AC31007Quiz/index.jsp">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="/AC31007Quiz/logo123.png" width="115px" style="position: absolute; left:0; top: 0;">
        <img src="/AC31007Quiz/logo123.png" width="115px" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a><%=quiz.getQuizName()%> </a></li>
            </ul>
        </div>
        
        
           
        <%
                if(type == "Completed Quizzes")
                {
                    %>
                    <br>
                    <br>
                    <div id="cc1">  
                        <h2>Quiz Profile</h2>

                        <h3>Quiz ID: <%=quiz.getQuizID()%></h3>
                        <h3>Date Created: <%=quiz.getDateCreated()%></h3>
                        <h3>Number of Q's: <%=quiz.getNumberOfQuestions()%></h3>
                        <h3>Quiz Status: <%=quiz.getStatus()%></h3>
                        <br>
                        <h3>Class Average: <%=quiz.getAverageScore()%></h3>

                        </div>
                        <br>
                        <br>
                        <br>
                        <br>
                        <div id="cc2">
                        <a href="editProfile.jsp"><button id="fourth-button">Edit Quiz</button></a>

                                    <a href ="studentModules.jsp"><button id="fourth-button">Make Live</button></a>

                                    <a href="logout.jsp"><button id="third-button">Filter</button></a>
                        </div>
                    
                    <%
                }
                else if (type == "Live Quizzes")
                {
                                %>

                    
                        <br>
                        <br>
                        
                            
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
        <a href="editProfile.jsp"><button id="fourth-button">Edit Quiz</button></a>
            
                      <a href="logout.jsp"><button id="third-button">Filter</button></a>
        </div>
        <div id="table">
       <%
                        if(!quizResult.isEmpty()) {
                            %>
                                <table border="1">
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
        <div id="graph">
        
        <div id="chart_div"> </div>         
        </div>
                    
                            <%
                        }
   
                }
                else if(type == "Unfinished Quizzes")
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
                        
                    <a href="editProfile.jsp"><button id="fourth-button">Edit Quiz</button></a>
            
                    <a href="logout.jsp"><button id="third-button">Filter</button></a>
                    </div>
                    <%
                }
        %>
         
    
    </body>
</html>
