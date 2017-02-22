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
        
        
        <style>

            .chart div {
              font: 10px sans-serif;
              background-color: steelblue;
              text-align: right;
              padding: 3px;
              margin: 1px;
              color: white;
            }

        </style>
        
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

        
    </head>
    <body>
        <h2><%=quiz.getQuizName()%></h2>
        <h3>Class Average is <%=quiz.getAverageScore()%>%</h3>
        
        <div id="chart_div"></div>
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
        %>
        
        
    </body>
</html>
