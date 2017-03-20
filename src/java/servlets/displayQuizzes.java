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
import stores.LoggedIn;
import stores.Quiz;

/**
 *
 * @author viivipursiainen
 */
@WebServlet(urlPatterns = {"/liveQuiz", "/unfinishedQuiz", "/completedQuiz", "/archived"})
public class displayQuizzes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf('/');
        String type = uri.substring(i+1);
        DisplayQuizzes(type, request, response);
    }
    
    private void DisplayQuizzes(String type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoggedIn user = (LoggedIn) session.getAttribute("LoggedIn");
        int staffID = user.getID();
        
        QuizModel qm = new QuizModel();
        // Refactor to remove diamond operator and simplify instantiation of the LinkedList
        java.util.LinkedList<Quiz> quizList = new java.util.LinkedList<>();
        RequestDispatcher rd = null;
        switch (type) {
            case "completedQuiz":
                quizList = qm.getCompletedQuizzes(staffID);
                rd = request.getRequestDispatcher("/displayQuizzes.jsp");
                session.setAttribute("QuizType", "Completed Quizzes");
                break;
            case "liveQuiz":
                quizList = qm.getLiveQuizzes(staffID);     
                rd = request.getRequestDispatcher("/displayQuizzes.jsp");
                session.setAttribute("QuizType", "Live Quizzes");
                break;
            case "unfinishedQuiz":
                quizList = qm.getUnfinishedQuizzes(staffID);
                rd = request.getRequestDispatcher("/displayQuizzes.jsp");
                session.setAttribute("QuizType", "Unfinished Quizzes");
                break;
            case "archived":
                quizList = qm.getArchived(staffID);
                rd = request.getRequestDispatcher("/displayQuizzes.jsp");
                session.setAttribute("QuizType", "Archived Quizzes");
                
                break;
            default:
                rd = request.getRequestDispatcher("/index.jsp");
                break;
        }
        
        request.setAttribute("QuizList", quizList);
        rd.forward(request, response);
    }
}
