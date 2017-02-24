/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.user;

@WebServlet(name = "EditProfile", urlPatterns = {
        "/edit-profile/*"
})
public class EditProfile extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String strUserID = uri.substring(i+1);
        int userID = Integer.parseInt(strUserID);
        
        String contextPath = request.getContextPath();
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        
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
        
        if (!firstName.equals("")){
            us.updateFirstName(isStaff, userID, firstName);
        }
        if (!lastName.equals("")){
            us.updateLastName(isStaff, userID, lastName);
        }
        if (password.equals(rePassword) && !password.equals("")){
            us.updatePassword(isStaff, userID, password);
        }
        
        
        response.sendRedirect(contextPath);
        
    }
    
}
