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
@WebServlet(name ="makeLive", urlPatterns = {"/makeLive"})
public class makeLive extends HttpServlet{

@Override    
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    
   Quiz quiz = (Quiz) request.getAttribute("Quiz");
   QuizModel makeLive = new QuizModel();
   System.out.println("Make live: " + quiz.getQuizID());
    makeLive.makeQuizLive(quiz.getQuizID()); // change to session variables
    
    response.sendRedirect("makeLive.jsp");
}
   
    
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        RequestDispatcher rd=request.getRequestDispatcher("makeLive.jsp");
	 rd.forward(request,response);
    }
    
}

