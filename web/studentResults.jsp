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

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Students');
        data.addRows([
          ['0 - 10', arr[0]],
          ['10 - 20', arr[1]],
          ['20 - 30', arr[2]],
          ['30 - 40', arr[3]],
          ['40 - 50', arr[4]],
          ['50 - 60', arr[5]],
          ['60 - 70', arr[6]],
          ['70 - 80', arr[7]],
          ['80 - 90', arr[8]],
          ['90 - 100', arr[9]]
        ]);

        // Set chart options
        var options = {'title':'Student Grade',
                       'width':800,
                       'height':700};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
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
                                    <div id="cc1">  
                        <h2>Quiz Profile</h2>

                        <h3>Quiz ID: <%=quiz.getQuizID()%></h3>
                        <h3>Module ID: <%=quiz.getModuleID()%></h3>
                        <h3>Date Created: <%=quiz.getDateCreated()%></h3>
                        <h3>Number of Q's: <%=quiz.getNumberOfQuestions()%></h3>
                        <h3>Quiz Status: <%=quiz.getStatus()%></h3>


                        </div>
                        <a href="editProfile.jsp"><button id="fourth-button">Edit Quiz</button></a>

                                    <a href ="studentModules.jsp"><button id="fourth-button">Make Live</button></a>

                                    <a href="logout.jsp"><button id="third-button">Filter</button></a>
                    
                    <%
                }
                else if (type == "Live Quizzes")
                {
                                %>
                       
                      
                      
                                <div id="chart_div" style ="align-content: center; margin:0;"> </div>

                        <h3>Class Average is <%=quiz.getAverageScore()%>%</h3>
                        
                         <div id="cc1">  
        <h2>Quiz Profile</h2>
        
        <h3>Quiz ID: <%=quiz.getQuizID()%></h3>
        <h3>Module ID: <%=quiz.getModuleID()%></h3>
        <h3>Date Created: <%=quiz.getDateCreated()%></h3>
        <h3>Number of Q's: <%=quiz.getNumberOfQuestions()%></h3>
        <h3>Quiz Status: <%=quiz.getStatus()%></h3>
       
        
        </div>
        <a href="editProfile.jsp"><button id="fourth-button">Edit Quiz</button></a>
            
                      <a href="logout.jsp"><button id="third-button">Filter</button></a>   
                    <br>

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
                            <%
                        }
        
                }
                else if(type == "Incomplete Quizzes")
                {
                    %>
                    <a href="editProfile.jsp"><button id="fourth-button">Edit Quiz</button></a>
            
                    <a href="logout.jsp"><button id="third-button">Filter</button></a> 
                    <%
                }
        %>
         
    
    </body>
</html>
