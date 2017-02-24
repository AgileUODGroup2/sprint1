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
import stores.StudentQuiz;

/**
 *
 * @author viivipursiainen
 */
@WebServlet(urlPatterns = {"/completedQuizzes", "/incompleteQuizzes", "/pendingdQuizzes"})
public class displayStudentQuizzes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String type = uri.substring(i+1);
        DisplayQuizzes(type, request, response);
    }
    
    private void DisplayQuizzes(String type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoggedIn user = (LoggedIn) session.getAttribute("LoggedIn");
        int staffID = user.getID();
        
        QuizModel qm = new QuizModel();
        java.util.LinkedList<StudentQuiz> quizList = new java.util.LinkedList<StudentQuiz>();
        RequestDispatcher rd = null;
        switch (type) {
            case "completedQuizzes":
                quizList = qm.getCompletedStudentQuizzes(staffID);
                rd = request.getRequestDispatcher("/displayStudentQuizzes.jsp");
                session.setAttribute("QuizType", "Completed Quizzes");
                break;
            case "pendingdQuizzes":
                quizList = qm.getPendingStudentQuizzes(staffID);
                rd = request.getRequestDispatcher("/displayStudentQuizzes.jsp");
                session.setAttribute("QuizType", "Pending Quizzes");
                break;
            case "incompleteQuizzes":
                quizList = qm.getIncompleteStudentQuizzes(staffID);
                rd = request.getRequestDispatcher("/displayStudentQuizzes.jsp");
                session.setAttribute("QuizType", "Incomplete Quizzes");
                break;
            default:
                rd = request.getRequestDispatcher("/index.jsp");
                break;
        }
        
        session.setAttribute("StudentQuizList", quizList);
        rd.forward(request, response);
    }
}