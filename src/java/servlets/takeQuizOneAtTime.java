/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.Arrays;
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

        HttpSession session = request.getSession();
        String[] studentAnswers = (String[]) session.getAttribute("StudentAnswers");
        System.out.println("DOGET: student answers :" + Arrays.toString(studentAnswers));
        
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String strQuizID = uri.substring(i+1);
        int quizID = Integer.parseInt(strQuizID);
        System.out.println("Quiz ID: "+quizID);
        display(studentAnswers, request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        QuizModel qm = new QuizModel();
            
        HttpSession session = request.getSession();
        String[] studentAnswers = (String[]) session.getAttribute("StudentAnswers");
        
        //Get ID from page
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        
        //Get total number of questions from page
        int quiz_totalQuestionAmmount = Integer.parseInt(request.getParameter("numOfQuestions"));
        
        //get current question
        int quiz_currentQuestion = Integer.parseInt(request.getParameter("questionNumber"));
        
        //Get all details of current quiz
        Quiz quiz = qm.getQuizDetails(quizID);
        
        String btn = request.getParameter("next");
        String btnFinish = request.getParameter("Finish Quiz");
            
        if(btnFinish == null){
        
            if(btn == null){
                
                ///////////////////////////////
                //BUTTON'S JUMP TO QUESTIONS
                ///////////////////////////////
                
                int jumpQuestion = Integer.parseInt(request.getParameter("jumpQuestion"));
                
                for(int p=0;p<quiz_totalQuestionAmmount;p++){
                    
                    if(jumpQuestion == p){
                        
                        quiz.setCounter(p);
                        
                    }
                    
                }
                
                ////////////////////////////////
                
            }
            else{
                
                ///////////////////////////////
                //BUTTON NEXT QUESTION
                ///////////////////////////////
                
                studentAnswers[quiz_currentQuestion] = request.getParameter("answer");
        
                int incrementQuestionNumber = quiz_currentQuestion;
                
                incrementQuestionNumber ++;
                
                quiz.setCounter(incrementQuestionNumber);
                
                ///////////////////////////////
                
            }
            
            session.setAttribute("Quiz", quiz);
            
            String contextPath = request.getContextPath();
            
            String url = contextPath+ "/takeQuizOneAtTime/"+quizID;
            
            System.out.println(url);
            
            response.sendRedirect(url);

        }
        else{
             
            ///////////////////////////////
            //BUTTON FINISH QUIZ
            ///////////////////////////////
            
            System.out.println("POST - User clicked on button Finish Quiz");
            session.setAttribute("StudentAnswers", studentAnswers);

            ////////////////////////////////
            
        }
    }
   private void display(String[] studentAnswers, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        Quiz quiz = (Quiz) session.getAttribute("Quiz");
        RequestDispatcher rd = request.getRequestDispatcher("/takeQuizOneAtTime.jsp");
        request.setAttribute("Quiz", quiz);
        request.setAttribute("StudentAnswers", studentAnswers);
        
        rd.forward(request, response);
        
    }
    
}

