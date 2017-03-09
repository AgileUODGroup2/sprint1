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
import stores.StudentQuiz;

/**
 *
 * @author viivipursiainen
 */
@WebServlet(name = "orderByDate", urlPatterns = {"/orderByDate"})
public class orderByDate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        int matricNo = Integer.parseInt(request.getParameter("MatricNo"));
        String table = request.getParameter("table");
        
        QuizModel qm = new QuizModel();
        java.util.LinkedList<StudentQuiz> qList = qm.orderByDate(matricNo,table);
        
        session.setAttribute("StudentQuizList", qList);
        RequestDispatcher rd = request.getRequestDispatcher("/displayStudentQuizzes.jsp");
        rd.forward(request, response);
    }

}
