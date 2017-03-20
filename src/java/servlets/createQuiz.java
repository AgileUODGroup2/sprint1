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
import stores.LoggedIn;
import stores.QuestionBank;
import stores.Quiz;

/**
 *
 * @author erincoey
 */
@WebServlet(name ="createQuiz", urlPatterns = {"/createQuiz"})

public class createQuiz extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        
    }
  
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    String quizName = request.getParameter("quizName");
    String moduleID = request.getParameter("moduleId");
    String staffName = request.getParameter("staffName");
    String dateCreated = request.getParameter("date");
    String available = request.getParameter("available");
    String numOfQuestions = request.getParameter("numOfQuestions");
    
    HttpSession session = request.getSession();
    LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn"); 
    String staffID = lg.getUsername();
    
    System.out.println("Result: "+moduleID+staffName+dateCreated+quizName+available+numOfQuestions);
    QuizModel quizModel = new QuizModel();
    quizModel.createQuiz(moduleID,staffName,dateCreated,quizName,available,staffID);
    

    Quiz quiz= new Quiz();
    int quizID = quizModel.getQuizId();
    quiz.setQuizID(quizID);
    session.setAttribute("Quiz", quiz);
        
    java.util.LinkedList<QuestionBank> questionList = new java.util.LinkedList();
    session.setAttribute("QuestionBank", questionList);

    response.sendRedirect(request.getContextPath() + "/addQuestions.jsp");
    
}

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd=request.getRequestDispatcher("createQuiz.jsp");
	    rd.forward(request,response);
    }
}
  
