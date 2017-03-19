/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
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
        boolean[] flagged = (boolean[]) session.getAttribute("Flagged");
        int[] qIDs = (int[]) session.getAttribute("QuestionIDs");
        System.out.println("DO GET: student answers :" + Arrays.toString(studentAnswers));
        System.out.println("DO GET: question ID's :" + Arrays.toString(qIDs));
        System.out.println("DO GET: flagged questions :" + Arrays.toString(flagged));
        
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String strQuizID = uri.substring(i+1);
        int quizID = Integer.parseInt(strQuizID);
        display(studentAnswers, request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuizModel qm = new QuizModel();
            
        HttpSession session = request.getSession();
        String[] studentAnswers = (String[]) session.getAttribute("StudentAnswers");
        int[] qIDs = (int[]) session.getAttribute("QuestionIDs");
        boolean[] flagged = (boolean[]) session.getAttribute("Flagged");
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        
        int matricNo = lg.getID();
        int quizID = Integer.parseInt(request.getParameter("quizID")); //Get ID from page
        int quiz_totalQuestionAmmount = Integer.parseInt(request.getParameter("numOfQuestions"));//Get total number of questions from page
        int quiz_currentQuestion = Integer.parseInt(request.getParameter("questionNumber"));//get current question
        Quiz quiz = qm.getQuizDetails(quizID);//Get all details of current quiz
        String flag = request.getParameter("flag");
        
        String btn = request.getParameter("next");
        String btnFinish = request.getParameter("Finish Quiz");
        String btnSave = request.getParameter("Save");
        
        System.out.println("parameter flag == " + flag);
        
        flagged[quiz_currentQuestion] = flag != null && flag.equals("on");
            
        if(btnFinish == null){
            if(btn == null){
                //BUTTON'S JUMP TO QUESTIONS
                String jumpQuestion = request.getParameter("jumpQuestion");
                for(int p=0;p<quiz_totalQuestionAmmount;p++){
                    if(jumpQuestion != null && jumpQuestion.contains("" + p))
                        quiz.setCounter(p);
                }
            }
            else if(btnSave != null){
                //BUTTON SAVE
                Save(request,response,studentAnswers,qIDs, matricNo);
            }
            else{
                //BUTTON NEXT QUESTION
                //request.setAttribute("Flagged",flagged);
                studentAnswers[quiz_currentQuestion] = request.getParameter("answer");
                int incrementQuestionNumber = quiz_currentQuestion;
                incrementQuestionNumber ++;
                quiz.setCounter(incrementQuestionNumber);
            }
            session.setAttribute("Quiz", quiz);
            String contextPath = request.getContextPath();
            String url = contextPath+ "/takeQuizOneAtTime/"+quizID;
            System.out.println(url);
            response.sendRedirect(url);
        }
        else{
            studentAnswers[quiz_currentQuestion] = request.getParameter("answer");
            System.out.println("*******     FINAL ANSWERS FOR QUIZ: student answers :" + Arrays.toString(studentAnswers));
            System.out.println("*** QUIZ ID's FOR QUIZ ANSWERS: question ID's :" + Arrays.toString(qIDs));
            Submit(request,response,studentAnswers,qIDs, matricNo);
        }
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
    
    private void Submit(HttpServletRequest request, HttpServletResponse response, String[] studentAnswers, int[] qIDs, int matricNo) throws ServletException, IOException {
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
            AnswerModel ansM = new AnswerModel();
            ansM.deleteAnswers(qIDs, matricNo);
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
    
    private void Save(HttpServletRequest request, HttpServletResponse response, String[] studentAnswers, int[] qIDs, int matricNo) throws ServletException, IOException {
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
    
    private void display(String[] studentAnswers, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Quiz quiz = (Quiz) session.getAttribute("Quiz");
        RequestDispatcher rd = request.getRequestDispatcher("/takeQuizOneAtTime.jsp");
        request.setAttribute("Quiz", quiz);
        request.setAttribute("StudentAnswers", studentAnswers);
        rd.forward(request, response);
    }
   
}

