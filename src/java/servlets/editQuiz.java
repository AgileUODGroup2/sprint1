/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.EditQuiz;
import models.QuestionModel;
import models.QuizModel;
import stores.QuestionBank;
import stores.Quiz;

/**
 *
 * @author viivipursiainen
 */
@WebServlet(urlPatterns = {"/edit", "/edit/*"})
@MultipartConfig(maxFileSize = 16177216)
public class editQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf("/");
        String strQuestionID = uri.substring(i+1);
        int questionID = Integer.parseInt(strQuestionID);
        
        QuestionModel qm = new QuestionModel();
        QuestionBank question = qm.getQuestion(questionID);
        
        request.setAttribute("Question", question);
        RequestDispatcher rd = request.getRequestDispatcher("/editQuiz.jsp");
        rd.forward(request, response);
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        QuestionBank qBank = new QuestionBank();
        EditQuiz editQuiz = new EditQuiz();
        String contextPath = request.getContextPath();
        
        int questionID = Integer.parseInt(request.getParameter("QuestionID"));
        
        //Set the question ID to the current question
        qBank.setQuestionID(questionID);
        
        //Get parameters to edit
        String question     = request.getParameter("question");
        String a            = request.getParameter("a");
        String b            = request.getParameter("b");
        String c            = request.getParameter("c");
        String d            = request.getParameter("d");
        String answer       = request.getParameter("Answer");
        String answerDesc   = request.getParameter("answerDesc");
        int quizID          = Integer.parseInt(request.getParameter("QuizID"));
        Part questionMedia  = request.getPart("media");
        
        System.out.println("Question: "+question+" A: "+a+" B: "+b+" Answer Desc: "+answerDesc);
        
        
        
        //Fill model with parameters
        qBank.setQuestion(question);
        qBank.setA(a);
        qBank.setB(b);
        qBank.setC(c);
        qBank.setD(d);
        qBank.setCorrectAnswer(answer);
        qBank.setAnswerDesc(answerDesc);
        qBank.setMedia(questionMedia);
        
        //Change question in database
        editQuiz.EditWholeQuiz(qBank);
        
        if(questionMedia != null){
            editQuiz.updateQuestionMedia(questionID, questionMedia);
        }
        
        response.sendRedirect(contextPath+"/displayQuestionsAndAnswers/"+quizID);
    }
    


    
}
