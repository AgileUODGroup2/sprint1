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
@WebServlet(urlPatterns = {"/filterByModule"})
public class filterByModule extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute("QuizType");
        
        LoggedIn staff = (LoggedIn) session.getAttribute("LoggedIn");
        int staffID = staff.getID();
        
        String moduleID = request.getParameter("module");
        
        QuizModel qm = new QuizModel();
        // Refactor to remove diamond operator and simplify instantiation of the LinkedList
        java.util.LinkedList<Quiz> quizList = new java.util.LinkedList<>();
        RequestDispatcher rd = null;
        switch (type) {
            case "Completed Quizzes":
                quizList = qm.getCompletedQuizzesMod(staffID, moduleID);
                rd = request.getRequestDispatcher("/displayQuizzes.jsp");
                break;
            case "Live Quizzes":
                quizList = qm.getLiveQuizzesMod(staffID, moduleID);
                rd = request.getRequestDispatcher("/displayQuizzes.jsp");
                break;
            case "Unfinished Quizzes":
                quizList = qm.getUnfinishedQuizzesMod(staffID, moduleID);
                rd = request.getRequestDispatcher("/displayQuizzes.jsp");
                break;
            default:
                rd = request.getRequestDispatcher("/index.jsp");
                break;
        }
        
        request.setAttribute("QuizList", quizList);
        rd.forward(request, response);
    }
}
