/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/edit", "/edit/*"})
public class editQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoggedIn staff = (LoggedIn) session.getAttribute("LoggedIn");
        int staffID = staff.getID();
        
        QuizModel qm = new QuizModel();
        java.util.LinkedList<Quiz> quizList = qm.getAllQuizzes(staffID);
        
        request.setAttribute("QuizList", quizList);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (int i=0; i< 10; i++) {
            
        }
    }
    
}
