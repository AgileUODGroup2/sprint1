/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.user;

/**
 *
 * @author conormckillop
 */
@WebServlet(name = "DisplayProfileImage", urlPatterns = {
        "/staff-img/*",
        "/student-img/*"
})
public class DisplayProfileImage extends HttpServlet{
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String strUserID = uri.substring(i+1);
        int userID = Integer.parseInt(strUserID);
        System.out.println("User ID: "+userID);
        
        String str = uri.substring(uri.indexOf("/") + 1, uri.lastIndexOf("/"));
        
        boolean isStaff = false;
        
        if(str.contains("staff-img")){
            isStaff = true;
        } else if(str.contains("student-img")){
            isStaff = false;
        }
        
        displayImage(isStaff, userID, request, response);

    }
    
    private void displayImage(boolean isStaff, int userID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user us = new user();
        byte byteArray[] = us.getProfileImage(isStaff, userID);
        
        response.setContentType("image");
        OutputStream os = response.getOutputStream();
        os.write(byteArray);
        os.flush();
        os.close();
    }
    
}
