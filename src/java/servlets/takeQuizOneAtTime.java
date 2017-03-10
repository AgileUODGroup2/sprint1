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
@WebServlet(name = "takeQuizOneAtTime", urlPatterns = {"/takeQuizOneAtTime/*"})
public class takeQuizOneAtTime extends HttpServlet {
    
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
        
        String counter = request.getParameter("counter");
        int i = Integer.parseInt(counter);
        
        String[] parameters = new String[i];

        for(int x=0; x<i; x++)
        {
            String parameter = "answer"+x;
            parameters[x] = parameter;
            System.out.println(parameter);
        }
        for(int x=0; x<i; x++)
        {
            System.out.println(parameters[x]);
            System.out.println(request.getParameter(parameters[x]));
        }
        
        
        
        response.sendRedirect("/AC31007Quiz/studentPortal.jsp");
       
      
    }
   private void display(int quizID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultModel rm = new ResultModel();
        QuizModel qm = new QuizModel();
//        String test;
        
        HttpSession session = request.getSession(true);
        LoggedIn lg =(LoggedIn)session.getAttribute("LoggedIn");
        
//        String button = request.getParameter("button");
//        if (button=="One Question at a time")
//        {
//            test = "oneQuestion";
//            System.out.println("test: " + test);
//            request.setAttribute("test", test);
//        }
//        else if(button == "All questions")
//        {
//            test = "allQuestions";
//            System.out.println("test: " + test);
//            request.setAttribute("test", test);
//        }
        Quiz quiz = qm.getQuizDetails(quizID);
      
            
            RequestDispatcher rd = request.getRequestDispatcher("/takeQuizOneAtTime.jsp"); 
            
            request.setAttribute("Quiz", quiz);
            rd.forward(request, response);
        
    }
    
}


