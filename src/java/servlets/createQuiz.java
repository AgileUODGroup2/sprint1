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
import models.QuizModel;

/**
 *
 * @author erincoey
 */
@WebServlet(name ="createQuiz", urlPatterns = {"/createQuiz"})

public class createQuiz extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        
    }
  
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    String quizName = request.getParameter("quizName");
    String quizID = request.getParameter("quizId");
    String moduleID = request.getParameter("moduleId");
    String staffName = request.getParameter("staffName");
    String dateCreated = request.getParameter("date");
//    String available = request.getParameter("available");
//    String numOfQuestions = request.getParameter("numOfQuestions");
    
    int quizIDnew = Integer.parseInt(quizID);
    int moduleIDnew = Integer.parseInt(moduleID);
  
    
    System.out.println("Result: "+quizID+moduleID+staffName+dateCreated+quizName);
    QuizModel quiz = new QuizModel();
    quiz.createQuiz(quizIDnew, moduleIDnew,staffName, dateCreated ,quizName );

    
}

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd=request.getRequestDispatcher("createAQuiz.jsp");
	    rd.forward(request,response);
    }
}
