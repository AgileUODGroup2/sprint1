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
import models.QuizModel;
import stores.Quiz;

/**
 *
 * @author viivipursiainen
 */
@WebServlet(urlPatterns = {"/liveQuizzes.jsp", "/unfinishedQuizzes.jsp", "/completedQuizzes.jsp"})
public class displayQuizzes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String type = uri.substring(i);
        DisplayQuizzes(type, request, response);
    }
    
    private void DisplayQuizzes(String type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuizModel qm = new QuizModel();
        java.util.LinkedList<Quiz> quizList = new java.util.LinkedList<Quiz>();
        RequestDispatcher rd = null;
        switch (type) {
            case "completedQuizzes.jsp":
                quizList = qm.getCompletedQuizzes();
                rd = request.getRequestDispatcher("/completedQuizzes.jsp");
                break;
            case "liveQuizzes.jsp":
                quizList = qm.getLiveQuizzes();
                rd = request.getRequestDispatcher("/liveQuizzes.jsp");
                break;
            case "unfinishedQuizzes.jsp":
                quizList = qm.getUnfinishedQuizzes();
                rd = request.getRequestDispatcher("/unfinishedQuizzes.jsp");
                break;
            default:
                rd = request.getRequestDispatcher("/index.jsp");
                break;
        }
        
        request.setAttribute("QuizList", quizList);
        rd.forward(request, response);
    }
}
