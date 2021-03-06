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
import javax.servlet.http.Part;
import models.EditQuiz;
import models.QuestionModel;
import stores.QuestionBank;

/**
 *
 * @author viivipursiainen
 */
@WebServlet(urlPatterns = {"/edit", "/edit/*", "/delete/*", "/delete-media/*"})
@MultipartConfig(maxFileSize = 16177216)
public class editQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf('/');
        String strQuestionID = uri.substring(i+1);
        int questionID = Integer.parseInt(strQuestionID);
        
        String command = uri.substring(uri.indexOf('/') + 1, uri.lastIndexOf('/'));
        command = command.substring(command.lastIndexOf('/') + 1);
        
        if(command.contains("edit")){
            
            QuestionModel qm = new QuestionModel();
            QuestionBank question = qm.getQuestion(questionID);
        
            request.setAttribute("Question", question);
            RequestDispatcher rd = request.getRequestDispatcher("/editQuiz.jsp");
            
            rd.forward(request, response);
        } else if(command.matches("delete")){
            // Insert code for deleting a Quiz (From question_bank and quiz tables)
        } else if(command.matches("delete-media")){
            EditQuiz q = new EditQuiz();
            Part tempPart = null;
            q.updateQuestionMedia(questionID, tempPart);
            response.sendRedirect(request.getContextPath() + "/edit/" + questionID);
        }
        
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
        
        //Change question in database
        editQuiz.EditWholeQuiz(qBank);
        
        if(questionMedia.getSize() != 0){
            qBank.setMedia(questionMedia);
            qBank.setHasMedia(true);
            editQuiz.updateQuestionMedia(questionID, questionMedia);
        }
        
        response.sendRedirect(contextPath+"/displayQuestionsAndAnswers/"+quizID);
    }
    


    
}
