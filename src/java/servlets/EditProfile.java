/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.user;

@WebServlet(name = "EditProfile", urlPatterns = {
        "/edit-profile/*"
})
@MultipartConfig(maxFileSize = 16177216)
public class EditProfile extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf('/');
        String strUserID = uri.substring(i+1);
        int userID = Integer.parseInt(strUserID);
        
        String contextPath = request.getContextPath();
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        
        Part profileImage = request.getPart("profileImage");
        
        boolean isStaff;
        
        if(request.getParameter("isStaff").equals("Staff"))
        {
            isStaff = true;
            contextPath = contextPath + "/staffPortal.jsp";
        }else{
            isStaff = false;
            contextPath = contextPath + "/studentPortal.jsp";
        }
        
        user us = new user();
        
        if (!firstName.isEmpty()){
            us.updateFirstName(isStaff, userID, firstName);
        }
        if (!lastName.isEmpty()){
            us.updateLastName(isStaff, userID, lastName);
        }
        if (password.equals(rePassword) && !password.isEmpty()){
            us.updatePassword(isStaff, userID, password);
        }
        if (profileImage != null){
            us.updateProfileImage(isStaff, userID, profileImage);
        }
        
        
        response.sendRedirect(contextPath);
        
    }
    
}
