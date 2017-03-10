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
        int i = uri.lastIndexOf("/");
        String strQuizID = uri.substring(i+1);
        int quizID = Integer.parseInt(strQuizID);
        System.out.println("Quiz ID: "+quizID);
            display(quizID, request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        QuizModel qm = new QuizModel();
        
        String y = request.getParameter("quizID");
        int  quizID = Integer.parseInt(y);
        String z = request.getParameter("questionNo");
        int questionNo = Integer.parseInt(z);
        
        questionNo++;
        System.out.println("New Question No: " + questionNo);
        
        Quiz quiz = qm.getQuizDetails(quizID);
        int oldCounter = quiz.getCounter();
        
        System.out.println("Old Counter: " + oldCounter);
        quiz.setCounter(questionNo);
        
        int newCounter = quiz.getCounter();
        System.out.println("new Counter: " + newCounter);

        String counter = request.getParameter("counter");
        int i = Integer.parseInt(counter);
        
        String[] parameters = new String[i];

        for(int x=1; x<i; x++)
        {
            String parameter = "answer"+x;
            parameters[x] = parameter;
        }
        for(int x=1; x<i; x++)
        {
            System.out.println(parameters[x]);
            System.out.println(request.getParameter(parameters[x]));
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("Quiz", quiz);
        
        String contextPath = request.getContextPath();
        String url = contextPath+ "/takeQuizOneAtTime/"+quizID;
        System.out.println(url);

        
        response.sendRedirect(url);
    }
   private void display(int quizID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        QuizModel qm = new QuizModel();
         HttpSession session = request.getSession();
        Quiz quiz = (Quiz) session.getAttribute("Quiz");
        //System.out.println("Request Counter: " + quiz.getCounter());
        RequestDispatcher rd = request.getRequestDispatcher("/takeQuizOneAtTime.jsp"); 
            
        request.setAttribute("Quiz", quiz);
        rd.forward(request, response);
        
    }
    
}


