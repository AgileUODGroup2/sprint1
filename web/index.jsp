<%-- 
    Document   : index
    Created on : 17-Feb-2017, 19:47:52
    Author     : daniellewilliams
--%>

<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/styles.css"%>">
  <body bgcolor=#042356>
     
      <div class="centerContent2">
          
          <br>
          <br>
          <br>
            <h1 style="font-size: 125px;">Quiz Master</h1>
   
   
   <div id="cen1">
       <br>
       <br>
       <img src="homelogo.png" style="display: block; margin: auto; width: 300px;">
   </div>
   
            <div id="cen2" style="margin: auto; display: inline-block;">
                <form method="POST"  action="login">
                <h5 style="font-size: 30px;">Username</h5>
                <input type="text" name="username" style="width:350px; height: 30px; display: block; margin: auto; margin-top: -2.5%;">
                <h5 style="font-size: 30px;">Password</h5>
                <input type="password" name="password" style="width:350px; height: 30px; display: block; margin: auto; margin-top: -2.5%;">
                <br>
                <br>
                <br>
                <input type="submit" value="login" id="second-button" style="display: block; margin: auto; -webkit-appearance: none;">
                </br>
                </form>
                <h5><a href="register.jsp" style="font-size: 25px;">If you do not have an account, please click here to register</a></h5>
           
   </div>
       </div>
</html>
</body>
 
