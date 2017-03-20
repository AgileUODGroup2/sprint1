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
import models.QuestionModel;
import models.user;

/**
 *
 * @author conormckillop
 */
@WebServlet(name = "DisplayImage", urlPatterns = {
        "/staff-img/*",
        "/student-img/*",
        "/question-img/*"
})
@MultipartConfig(maxFileSize = 16177216)
public class DisplayImage extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        int i = uri.lastIndexOf('/');
        String strID = uri.substring(i+1);
        int imageID = Integer.parseInt(strID);
        
        String command = uri.substring(uri.indexOf('/') + 1, uri.lastIndexOf('/'));
        command = command.substring(command.lastIndexOf('/') + 1);
        
        boolean isStaff = false;
        
        if(command.matches("staff-img")){
            isStaff = true;
            displayProfileImage(isStaff, imageID, request, response);
        } else if(command.matches("student-img")){
            isStaff = false;
            displayProfileImage(isStaff, imageID, request, response);
        } else if(command.matches("question-img")){
            displayQuestionImage(imageID, request, response);
        }
    }
    
    private void displayProfileImage(boolean isStaff, int userID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user us = new user();
        byte byteArray[] = us.getProfileImage(isStaff, userID);
        
        if(byteArray == null){
            response.sendRedirect("profile-image.png");
        } else {
            response.setContentType("image");
            OutputStream os = response.getOutputStream();
            os.write(byteArray);
            os.flush();
            os.close();
        }
    }
    
    private void displayQuestionImage(int questionID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionModel qm = new QuestionModel();
        byte byteArray[] = qm.getQuestionMedia(questionID);
        
        response.setContentType("image");
        OutputStream os = response.getOutputStream();
        os.write(byteArray);
        os.flush();
        os.close();
    }
    
}
