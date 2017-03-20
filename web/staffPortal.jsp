<%-- 
    Document   : staffPortal
    Created on : 17-Feb-2017, 19:46:40
    Author     : daniellewilliams
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file='cssStructure.jsp' %>

<!DOCTYPE html>
<html>
    <head>
       <title>Staff Portal </title>
        <% 
           
            String contextPath = request.getContextPath();
        %>
    
        <div class="navBar1">
            <ul>
                <li><a> Staff Portal - Welcome <%=lg.getFirstName()%>!</a></li>
            </ul>
        </div>
            <br>
            <br>
            <br>
            <div class="centerContent1">
                 
        <div id="cc1">
            <img class="profImage" id="<%="img" + lg.getID()%>" src="<%=contextPath + "/staff-img/" + lg.getID()%>" style="display: inline-block; float:right; width:33%;">
            <h2>Your Profile</h2>
            <h3>Staff ID:&nbsp;<%=lg.getUsername()%></h3>
            <h3>First Name:&nbsp;<%=lg.getFirstName()%></h3>
            <h3>Last Name:&nbsp;<%=lg.getLastName()%></h3><br><Br>

         
       </div>
          
            
           
        <div id="cc2">
             <br>
             <br>
            <a href="editProfile.jsp"><button id="fourth-button">Edit Profile</button></a>
          
            <a href ="staffModules.jsp"><button id="fourth-button">My Modules</button></a>
            
              <a href="logout.jsp"><button id="third-button">Log Out</button></a>
    <br>
            <br>
            <br>
       
          
        </div>
            <br>
            <br>
            <br>
            <br>
        <div id ="cc3">
             <p><h7>My Quizzes </h7></p>
        <p><a href="createQuiz"><button id="sec-button">Create Quiz</button></a></p>
              
        <p><a href="completedQuiz"><button id="sec-button">Completed Quizzes</button></a></p>
                
        <p><a href="liveQuiz"><button id="sec-button">Live Quizzes</button></a></p>
               
        <p><a href="unfinishedQuiz"><button id="sec-button">Unfinished Quizzes</button></a></p>
        
        <p><a href="archived"><button id="sec-button">Archived Quizzes</button></a></p>
                <br>
                <br>
             
        </div>
            <br><br><br>
    
       </div>
           
            <div id="myModal" class="modal">
                        <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>
                        <img class="modal-content" id="modalImage">
         </div>
             <br>
             <br><br>
         <script>
            var modal = document.getElementById('myModal');
            var img = document.getElementById('<%="img" + lg.getID()%>');
            var modalImg = document.getElementById("modalImage");
            img.onclick = function(){
                modal.style.display = "block";
                modalImg.src = this.src;
            }
            var span = document.getElementsByClassName("close")[0];
            span.onclick = function() {
              modal.style.display = "none";
            }
         </script>
         <br>
         <br>
</body>
</html>
