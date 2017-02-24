/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.QuizModel;
import models.ResultModel;
import stores.LoggedIn;
import stores.Quiz;

/**
 *
 * @author ashawittchen
 */
@WebServlet(name ="makeLive", urlPatterns = {"/makeLive/*"})
public class makeLive extends HttpServlet{

@Override    
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    
   
}
   
    
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
         String uri = request.getRequestURI();
        System.out.println(uri);
        int i = uri.lastIndexOf("/");
        String strQuizID = uri.substring(i+1);
        int quizID = Integer.parseInt(strQuizID);
        System.out.println("Quiz ID: "+quizID);
        display(quizID, request, response);
    }
 
 private void display(int quizID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultModel rm = new ResultModel();
        QuizModel qm = new QuizModel();

        
        HttpSession session = request.getSession(true);
        LoggedIn lg =(LoggedIn)session.getAttribute("LoggedIn");
        
        Quiz quiz = qm.getQuizDetails(quizID);
      
            
            RequestDispatcher rd = request.getRequestDispatcher("/makeLive.jsp"); // SET CORRECT REDIRECT LOCATION
            
            request.setAttribute("Quiz", quiz);
            rd.forward(request, response);
        
    }
    
}

