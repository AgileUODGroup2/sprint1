/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.QuizModel;
import stores.Quiz;

/**
 *
 * @author ashawittchen
 */
@WebServlet(name ="mostRecentQuiz", urlPatterns = {"/mostRecentQuiz"})
public class mostRecentQuiz extends HttpServlet{
    
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
     String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String type = uri.substring(i+1);
        displayRecentQuizzes(type,request, response);
        
        
        
    }
    

 //@Override    
private void displayRecentQuizzes(String type,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
   QuizModel drq = new QuizModel();
   drq.getFilterByRecentQuiz();

QuizModel rL = new QuizModel();
        java.util.LinkedList<Quiz> recentList = new java.util.LinkedList<Quiz>();
        recentList = rL.getFilterByRecent();
                RequestDispatcher rd = request.getRequestDispatcher("/mostRecentQuiz.jsp");
                request.setAttribute("RecentList",recentList);    
                rd.forward(request,response);
  
   
    }
   

    
}

