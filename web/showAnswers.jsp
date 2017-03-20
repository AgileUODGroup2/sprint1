<%-- 
    Document   : takeQuiz
    Created on : 22-Feb-2017, 23:53:57
    Author     : erincoey
--%>

<%@page import="models.QuestionModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="stores.QuestionBank"%>
<%@page import="models.QuizModel"%>
<%@page import="stores.Quiz"%>
<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/styles.css"%>">
        <title>Your Results</title>
    </head>
    <body bgcolor="d3dfeb">
      <%
            String[] studentAnswers = (String[]) request.getAttribute("StudentAnswers");
            String[] rightAnswers = (String[]) request.getAttribute("RightAnswers");
            int[] questionIDs = (int[]) request.getAttribute("QuestionIDs");
        %>
        
   
    
        <div class="navBar">
            <ul>
                <li><a href="<%=request.getContextPath() + "/studentPortal.jsp"%>">QUIZ MASTER </a></li>
             </ul>
        </div>
        <img src="<%=request.getContextPath() + "/logo123.png"%>" width="115" style="position: absolute; left:0; top: 0;">
        <img src="<%=request.getContextPath() + "/logo123.png"%>" width="115" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a>Your Results</a></li>
            </ul>
        </div>
        <br>
        <br>
        <div class="centerContent1">
            <div id ="cc4"><br>
                <h7>  Your Score: <%=request.getAttribute("Score")%> %  </h7><br>
            </div><br><br>
        <%
            QuestionModel qm = new QuestionModel();
            for (int x=0;x<questionIDs.length;x++) {
                QuestionBank q = qm.getQuestion(questionIDs[x]);
            
%> 
<br><br>
<br><h3><%=q.getQuestion()%></h3>
                 <%
                    if(q.HasMedia()){
                        %><img src="<%=request.getContextPath() + "/question-img/" + q.getQuestionID()%>" width="200" style="display: inline-block;">
                 <%
                    }
                    if (rightAnswers[x].equals(studentAnswers[x])) {
                 %>
                 <h8 style="color: green; font-weight: bold;">You Answered: Correct</font></h8><br>
                 <% if (rightAnswers[x].equals("A")){%><h8><font color="green" ><%=q.getA()%></font></h8> <br /><%}
                    else { %><h8><%=q.getA()%></h8> <br />
                    <% } if (rightAnswers[x].equals("B")){%><h8 style ="color: green; font-weight: bold;"><%=q.getB()%></h8> <br /><%}
                    else { %><h8><%=q.getB()%></h8> <br />
                    <% } if (rightAnswers[x].equals("C")){%><h8 style ="color: green; font-weight: bold;"><%=q.getC()%></h8> <br /><%}
                    else { %><h8><%=q.getC()%> </h8><br />
                    <% } if (rightAnswers[x].equals("D")){%><h8 style ="color: green; font-weight: bold;"><%=q.getD()%></h8> <br /><%}
                    else { %><h8><%=q.getD()%></h8> <br />
                  <% }
                    } else { %>
                    
                 <h8 style="color: red; font-weight: bold;">You Answered: Wrong</font></h8> <br />
                 <% if (rightAnswers[x].equals("A")){%><h8 style ="color: green; font-weight: bold;"><%=q.getA()%></h8> <br />
                 <% } else if (studentAnswers[x].equals("A")) { %><h8 style ="color: red; font-weight: bold;"><%=q.getA()%></h8> <br />
                 <% } else { %><h8><%=q.getA()%></h8> <br />
                 <% } if (rightAnswers[x].equals("B")){%><h8 style ="color: green; font-weight: bold;"><%=q.getB()%></h8> <br />
                 <% } else if (studentAnswers[x].equals("B")) { %><h8 style ="color: red; font-weight: bold;"><%=q.getB()%></h8> <br />
                 <% } else { %><h8><%=q.getB()%> </h8><br />
                 <% } if (rightAnswers[x].equals("C")){%><h8 style ="color: green; font-weight: bold;"><%=q.getC()%></h8> <br />
                 <% } else if (studentAnswers[x].equals("C")) { %><h8 style ="color: red; font-weight: bold;"><%=q.getC()%></h8> <br />
                 <% } else { %><h8><%=q.getC()%> </h8> <br />
                 <% } if (rightAnswers[x].equals("D")){%><h8 style ="color: green; font-weight: bold;"><%=q.getD()%></h8> <br />
                 <% }  else if (studentAnswers[x].equals("D")) { %><h8 style ="color: red; font-weight: bold;"><%=q.getD()%></h8> <br />
                 <% } else { %><h8><%=q.getD()%> </h8><br />
                <%  }
}
                %> <h8 style="text-align: center;">Answer Description: <%=q.getAnswerDesc()%> </h8> <%
}
%>
<br>
<br>
<br>

        </div><br>
        <a href="<%=request.getContextPath() + "/studentPortal.jsp"%>"><button id="fifth-button" style="text-align: center; width:25%; background-color: #042356">Return to Student Portal</button></a> <br />
</body>
</html>
