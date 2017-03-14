/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AttemptModel;
import models.QuestionModel;
import models.QuizModel;
import models.ResultModel;
import stores.LoggedIn;
import stores.Quiz;

/**
 *
 * @author erincoey
 */
@WebServlet(name = "takeQuiz", urlPatterns = {"/takeQuiz/*"})
public class takeQuiz extends HttpServlet{
    
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
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        String path = request.getContextPath();
        
        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        int matricNo = lg.getID();
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        
        String[] studentAnswers = new String[i];
        int[] qIDs = new int[i];

        for(int x=1; x<=i; x++) {
            studentAnswers[x-1] = request.getParameter("answer"+x);
            qIDs[x-1] = Integer.parseInt(request.getParameter("questionID"+x));
        }
        
        int score = calculateResult(studentAnswers, qIDs);
        
        System.out.println("Score: "+score);
        
        QuizModel qm = new QuizModel();
        qm.addNewAttempt(matricNo, quizID, score, date);
        
        AttemptModel am = new AttemptModel();
        am.addNewAttempt(matricNo, quizID, date, score);
        
        response.sendRedirect(path+"/studentPortal.jsp");
    }
    
    private int calculateResult(String[] studentAnswers, int[] quizIDs) {
        QuestionModel qm = new QuestionModel();
        int questions = quizIDs.length;
        System.out.println("Number of questions: "+questions);
        String[] rightAnswers = qm.getRightAnswers(quizIDs);
        int right = 0;
        for (int i=0; i<questions;i++) {
            if(studentAnswers[i].equals(rightAnswers[i])) {
                right ++;
                System.out.println("How many right: "+right);
            }
        }
        System.out.println("Division: "+(double)right/questions);
        Double answer = new Double((right/questions) * 100);
        System.out.println("Double: "+answer);
        int answer2 = answer.intValue();
        System.out.println("Int: "+answer2);
        return answer2;
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
      
            
            RequestDispatcher rd = request.getRequestDispatcher("/takeQuiz.jsp"); 
            
            request.setAttribute("Quiz", quiz);
            rd.forward(request, response);
        
    }
    
}
