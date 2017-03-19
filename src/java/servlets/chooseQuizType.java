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
 * @author erincoey
 */
@WebServlet(name = "chooseQuizType", urlPatterns = {"/chooseQuizType/*"})
public class chooseQuizType extends HttpServlet{

    
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    }
    
    
   private void display(int quizID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuizModel qm = new QuizModel();
        Quiz quiz = qm.getQuizDetails(quizID);
        
        RequestDispatcher rd = request.getRequestDispatcher("/chooseQuizType.jsp"); 
        quiz.setCounter(0);
        HttpSession session = request.getSession();
            
        int i = quiz.getNumberOfQuestions();
        String[] studentAnswers = new String[i];
        boolean[] flagged = new boolean[i];
        int[] qIDs = new int[i];
        
        session.setAttribute("Quiz", quiz);
        session.setAttribute("StudentAnswers", studentAnswers);
        session.setAttribute("QuestionIDs", qIDs);
        session.setAttribute("Flagged", flagged);
        rd.forward(request, response);
    }
    

 
}
