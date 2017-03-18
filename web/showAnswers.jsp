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
        <link rel="stylesheet" type="text/css" href="/AC31007Quiz/styles.css">
        <title>Take Quiz</title>
    </head>
    <body>
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
        <img src="/AC31007Quiz/logo123.png" width="115" style="position: absolute; left:0; top: 0;">
        <img src="/AC31007Quiz/logo123.png" width="115" style="position: absolute; right:0; top: 0;">

        <div class="navBar1">
            <ul>
                <li><a>Your Results</a></li>
            </ul>
        </div>
        <br>
        <br>
        <div class="centerContent1">
            <br>
            <h7> Your Score: <%=request.getAttribute("Score")%> % </h7>
        
        <%
            QuestionModel qm = new QuestionModel();
            for (int x=0;x<questionIDs.length;x++) {
                QuestionBank q = qm.getQuestion(questionIDs[x]);
            
%> 
<br>
<h2><%=q.getQuestion()%></h2>
                 <%
                    if(q.HasMedia()){
                 %><img src="<%=request.getContextPath() + "/question-img/" + q.getQuestionID()%>" width="200" style="display: inline-block;">
                 <%
                    }
                    if (rightAnswers[x].equals(studentAnswers[x])) {
                 %>
                 <font color="green">Correct!</font> <br />
                 <% if (rightAnswers[x].equals("A")){%><font color="green"><%=q.getA()%></font> <br /><%}
                    else { %><%=q.getA()%> <br />
                 <% } if (rightAnswers[x].equals("B")){%><font color="green"><%=q.getB()%></font> <br /><%}
                    else { %><%=q.getB()%> <br />
                 <% } if (rightAnswers[x].equals("C")){%><font color="green"><%=q.getC()%></font> <br /><%}
                    else { %><%=q.getC()%> <br />
                 <% } if (rightAnswers[x].equals("D")){%><font color="green"><%=q.getD()%></font> <br /><%}
                    else { %><%=q.getD()%> <br />
                 <% }
   } else { %>
                 <font color="red">Wrong answer</font> <br/>
                 <% if (rightAnswers[x].equals("A")){%><font color="green"><%=q.getA()%></font> <br />
                 <% } else if (studentAnswers[x].equals("A")) { %><font color="red"><%=q.getA()%></font> <br />
                 <% } else { %><%=q.getA()%> <br />
                 <% } if (rightAnswers[x].equals("B")){%><font color="green"><%=q.getB()%></font> <br />
                 <% } else if (studentAnswers[x].equals("B")) { %><font color="red"><%=q.getB()%></font> <br />
                 <% } else { %><%=q.getB()%> <br />
                 <% } if (rightAnswers[x].equals("C")){%><font color="green"><%=q.getC()%></font> <br />
                 <% } else if (studentAnswers[x].equals("C")) { %><font color="red"><%=q.getC()%></font> <br />
                 <% } else { %><%=q.getC()%> <br />
                 <% } if (rightAnswers[x].equals("D")){%><font color="green"><%=q.getD()%></font> <br />
                 <% }  else if (studentAnswers[x].equals("D")) { %><font color="red"><%=q.getD()%></font> <br />
                 <% } else { %><%=q.getD()%> <br />
                <%  }
}
%> Answer Description: <%=q.getAnswerDesc()%> <br /><br /> <%
}
%>
<br>
<br>
<br>

        </div><br>
        <a href="<%=request.getContextPath() + "/studentPortal.jsp"%>"><button id="fifth-button" style="text-align: center; width:25%; background-color: #042356">Return to Student Portal</button></a> <br />
</body>
</html>
