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
@WebServlet(name ="addQuestions", urlPatterns = {"/addQuestions"})

public class addQuestions extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        
    }
  
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String submit = request.getParameter("submit");
        String save = request.getParameter("save");
        String addQuestion = request.getParameter("addQuestion");
        
        if (submit!=null)
        {
            response.sendRedirect("/AC31007Quiz/staffPortal.jsp");
        }
        else if (save!=null)
        {
            response.sendRedirect("/AC31007Quiz/staffPortal.jsp");
        }
        else if (addQuestion!=null)
        {
            
            response.sendRedirect("/AC31007Quiz/addQuestions.jsp");
        }
    
}

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd=request.getRequestDispatcher("addQuestions.jsp");
	    rd.forward(request,response);
    }
}
    
