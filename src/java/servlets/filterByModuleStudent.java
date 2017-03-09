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
import stores.StudentQuiz;

/**
 *
 * @author viivipursiainen
 */
@WebServlet(urlPatterns = {"/filterByModuleStudent"})
public class filterByModuleStudent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute("QuizType");
        
        LoggedIn student = (LoggedIn) session.getAttribute("LoggedIn");
        int matricNo = student.getID();
        
        String moduleID = request.getParameter("module");
        
        QuizModel qm = new QuizModel();
        // Refactor to remove diamond operator and simplify instantiation of the LinkedList
        java.util.LinkedList<StudentQuiz> quizList = new java.util.LinkedList<>();
        RequestDispatcher rd = null;
        switch (type) {
            case "Completed Quizzes":
                quizList = qm.getCompletedStudentQuizzesMod(matricNo, moduleID);
                rd = request.getRequestDispatcher("/displayStudentQuizzes.jsp");
                break;
            case "Pending Quizzes":
                quizList = qm.getPendingStudentQuizzesMod(matricNo, moduleID);
                rd = request.getRequestDispatcher("/displayStudentQuizzes.jsp");
                break;
            case "Incomplete Quizzes":
                quizList = qm.getIncompleteStudentQuizzesMod(matricNo, moduleID);
                rd = request.getRequestDispatcher("/displayStudentQuizzes.jsp");
                break;
            default:
                rd = request.getRequestDispatcher("/index.jsp");
                break;
        }
        
        session.setAttribute("StudentQuizList", quizList);
        rd.forward(request, response);
    }
}
