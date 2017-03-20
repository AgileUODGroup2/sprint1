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
import models.AnswerModel;
import models.AttemptModel;
import models.QuestionModel;
import models.QuizModel;
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
        int i = uri.lastIndexOf('/');
        String strQuizID = uri.substring(i+1);
        int quizID = Integer.parseInt(strQuizID);
        display(quizID, request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String counter = request.getParameter("counter");
        int i = Integer.parseInt(counter);
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        
        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        int matricNo = lg.getID();
        
        String[] studentAnswers = new String[i];
        int[] qIDs = new int[i];
        
        QuizModel qm = new QuizModel();
        if(qm.getStudentStatus(matricNo, quizID).equals("Incomplete")) {
            AnswerModel ansM = new AnswerModel();
            ansM.deleteAnswers(qIDs, matricNo);
        }

        for(int x=1; x<=i; x++) {
            studentAnswers[x-1] = request.getParameter("answer"+x);
            qIDs[x-1] = Integer.parseInt(request.getParameter("questionID"+x));
        }
        
        String submit = request.getParameter("submit");
        if (submit.equals("Save for another time")) {
            save(request,response,studentAnswers,qIDs, matricNo);
        } else {
            submit(request,response,studentAnswers,qIDs, matricNo);
        }
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response, String[] studentAnswers, int[] qIDs, int matricNo) throws ServletException, IOException {
        AnswerModel am = new AnswerModel();
        
        for (int j=0; j<studentAnswers.length;j++) {
            if (studentAnswers[j] != null) {
                am.storeAnswer(studentAnswers[j],qIDs[j],matricNo);
            }
        }
        
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        QuizModel qm = new QuizModel();
        qm.updateStudentQuizStatus(matricNo, quizID, "Incomplete");
        RequestDispatcher rd = request.getRequestDispatcher("/studentPortal.jsp");
        rd.forward(request, response);
    }
    
    private void submit(HttpServletRequest request, HttpServletResponse response, String[] studentAnswers, int[] qIDs, int matricNo) throws ServletException, IOException {
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        
        QuestionModel questionM = new QuestionModel();
        String[] rightAnswers = questionM.getRightAnswers(qIDs);
        
        int score = calculateResult(studentAnswers, rightAnswers);
        
        QuizModel qm = new QuizModel();
        qm.addNewAttempt(matricNo, quizID, score, date);
        Quiz quiz = qm.getQuizDetails(quizID);
        
        AttemptModel am = new AttemptModel();
        am.addNewAttempt(matricNo, quizID, date, score);
        
        if(qm.getStudentStatus(matricNo, quizID).equals("Incomplete")) {
            qm.updateStudentQuizStatus(matricNo, quizID, "Completed");
        }
        
        request.setAttribute("StudentAnswers",studentAnswers);
        request.setAttribute("RightAnswers",rightAnswers);
        request.setAttribute("QuestionIDs",qIDs);
        request.setAttribute("Quiz", quiz);
        request.setAttribute("Score", score);
        
        RequestDispatcher rd = request.getRequestDispatcher("/showAnswers.jsp");
        rd.forward(request, response);
    }
    
    private int calculateResult(String[] studentAnswers, String[] rightAnswers) {
        int questions = rightAnswers.length;
        int right = 0;
        for (int i=0; i<questions;i++) {
            if(studentAnswers[i] != null && studentAnswers[i].equals(rightAnswers[i])) {
                right ++;
            }
        }
        int answer = right*100/questions;
        return answer;
    }
    
   private void display(int quizID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuizModel qm = new QuizModel();
        
        HttpSession session = request.getSession();
        LoggedIn lg =(LoggedIn)session.getAttribute("LoggedIn");
        
        Quiz quiz = qm.getQuizDetails(quizID);
        
        RequestDispatcher rd = request.getRequestDispatcher("/takeQuiz.jsp");
        request.setAttribute("Quiz", quiz);
        rd.forward(request, response);
    }
    
}
