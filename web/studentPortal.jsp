<%-- 
    Document   : staffPortal
    Created on : 17-Feb-2017, 19:46:40
    Author     : daniellewilliams
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file='cssStructure.jsp' %>
<!DOCTYPE html>

<title> Student Portal </title>

        <%
            
            String contextPath = request.getContextPath();
        %>
 
  
        <div class="navBar1">
            <ul>
                <li><a> STUDENT PORTAL - Welcome <%=lg.getFirstName()%>!</a></li>
            </ul>
        </div>
            
            <br>
            <br>
            <div class="centerContent1">
                 
        <div id="cc1">
            <%//Adapted source%>
         <img class="profImage" id="<%="img" + lg.getID()%>" src="<%=contextPath + "/student-img/" + lg.getID()%>" style="display: inline-block; float:right; width:33%;">
         
        <h2>Your Profile</h2>
        <h3>Matric Number: <%=lg.getUsername()%></h3>
        <h3>First Name: <%=lg.getFirstName()%></h3>
        <h3>Last Name: <%=lg.getLastName()%></h3>
        <br>
        <br>
        </div>
        
         <div id="cc2">
             <br>
             <br>
            <a href="editProfile.jsp"><button id="fourth-button">Edit Profile</button></a>
          
            <a href ="studentModules.jsp"><button id="fourth-button">My Modules</button></a>
            
              <a href="logout.jsp"><button id="third-button">Log Out</button></a>
    <br>
            <br>
          
       
          
        </div>
        
        
         <div id="cc3" style="background: black; display: block; width: 50%; margin-left: 25%;">
             <p><h7>My Quizzes </h7></p>
     
              <p><a href="incompleteQuizzes"><button id="fourth-button" style="Background:#b71010; width: 195px; height: 195px; font-size: 30px; border-radius: 360px; "> Incomplete Quizzes</button></a></p>
              
               <p><a href="pendingdQuizzes"><button id="fourth-button" style="Background:orange;  width: 195px; height: 195px; font-size: 30px; border-radius: 360px;">Pending Quizzes</button></a></p>
                
               <p><a href="completedQuizzes"><button id="fourth-button" style="Background:green; width: 195px; height: 195px; font-size: 30px; text-decoration: none; border-radius: 360px;">Completed Quizzes</button></a></p>
               
               <br>
        <br>
      
   
        </div>
        
           
            </div>
 
   
        
          <div class="push">
                
              <br>
              <br>
            </div>     
        
        <div id="myModal" class="modal">
                        <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>
                        <img class="modal-content" id="modalImage">
         </div>
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
    </body>
</html>
