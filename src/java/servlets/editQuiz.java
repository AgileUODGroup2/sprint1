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
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        QuestionBank qBank = new QuestionBank();
        EditQuiz editQuiz = new EditQuiz(); 
        
        //Get Question ID from the URL
        String uri = request.getRequestURI();               //Get the url
        int i = uri.lastIndexOf("/");                       //Get the position of the last occurence of "/"
        String strQuestionID = uri.substring(i+1);          //Get the string at position one to the left of the last "/"
        int questionID = Integer.parseInt(strQuestionID);   //Convert it to an Integer
        System.out.println("Question ID: " + questionID);
        
        //Set the question ID to the current question
        qBank.setQuestionID(questionID);
        
        //Get parameters to edit
        String question     = request.getParameter("question");
        String a            = request.getParameter("a");
        String b            = request.getParameter("b");
        String c            = request.getParameter("c");
        String d            = request.getParameter("d");
        String answer       = request.getParameter("answer");
        String answerDesc   = request.getParameter("answerDesc");
        
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
        
        RequestDispatcher rd = request.getRequestDispatcher("");
        rd.forward(request, response);
    }
    


    
}
