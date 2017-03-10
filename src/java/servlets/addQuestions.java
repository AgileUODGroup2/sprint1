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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.QuizModel;
import stores.QuestionBank;
import stores.Quiz;

/**
 *
 * @author erincoey
 */
@WebServlet(name ="addQuestions", urlPatterns = {"/addQuestions"})
@MultipartConfig(maxFileSize = 16177216)
public class addQuestions extends HttpServlet {
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        
    }

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String submit = request.getParameter("submit");
        String save = request.getParameter("save");
        String addQuestion = request.getParameter("addQuestion");
        String cancel = request.getParameter("cancel");
        
        String question = request.getParameter("question");
        String answerA = request.getParameter("answerA");
        String answerB = request.getParameter("answerB");
        String answerC = request.getParameter("answerC");
        String answerD = request.getParameter("answerD");
        String correctAnswer = request.getParameter("correctAnswer");
        String answerDesc = request.getParameter("answerDesc");
        Part questionMedia = request.getPart("media");
        
        
        System.out.println("Question: "+question+" "+answerA+" "+answerB+" "+answerC+ " " + answerD+ " " + correctAnswer + " "+ answerDesc);
        
        if (submit!=null)
        {
            //send it to the database
            if(question==null)
            {
                HttpSession session = request.getSession();
                java.util.LinkedList<QuestionBank> questionList = (java.util.LinkedList<QuestionBank>) session.getAttribute("QuestionBank");

                String[] array = new String[questionList.size()];
                Part[] parts = new Part[questionList.size()];

                System.out.println("LinkedList contains: ");
                   for(int i=0; i< questionList.size(); i++)
                   {
                     QuestionBank result = questionList.get(i);
                     System.out.println(result.getQuery());

                     array[i] = result.getQuery();
                     if (result.getMedia() == null){
                         parts[i] = null;
                     } else {
                         parts[i] = result.getMedia();
                     }

                  }
                   QuizModel quizModel = new QuizModel();
                   quizModel.addQuestion(array, parts);

             }
            else if(question!=null)
            {
                HttpSession session = request.getSession();
            Quiz quizStore = (Quiz) session.getAttribute("Quiz");
            int quizID = quizStore.getQuizID();
            int questionID = quizStore.getNumberOfQuestions();
            QuestionBank questionBank = new QuestionBank();
            questionBank.setQuestion(question);
            questionBank.setQuizID(quizID);
            questionBank.setQuestionID(questionID);
            questionBank.setA(answerA);
            questionBank.setB(answerB);
            questionBank.setC(answerC);
            questionBank.setD(answerD);
            questionBank.setCorrectAnswer(correctAnswer);
            questionBank.setAnswerDesc(answerDesc);
            
            if (questionMedia != null)
            {
                questionBank.setMedia(questionMedia);
            }

            String test = questionBank.getQuestion();
            System.out.println("Test: "+ test);
            
            java.util.LinkedList<QuestionBank> questionList = (java.util.LinkedList<QuestionBank>) session.getAttribute("QuestionBank");
            
            if(questionList!=null)
            {
                    System.out.println("Question list is not null");
                    questionList.add(questionBank);
                    
            }
            else if (questionList == null)
            {
                System.out.println("Question list is null");
                questionList.add(questionBank);
            }
            
                

                String[] array = new String[questionList.size()];
                Part[] parts = new Part[questionList.size()];

                System.out.println("LinkedList contains: ");
                   for(int i=0; i< questionList.size(); i++)
                   {
                     QuestionBank result = questionList.get(i);
                     System.out.println(result.getQuery());

                     array[i] = result.getQuery();
                     if (result.getMedia() == null){
                         parts[i] = null;
                     } else {
                         parts[i] = result.getMedia();
                     }

                  }
                   QuizModel quizModel = new QuizModel();
                   quizModel.addQuestion(array, parts);
                   System.out.println("Quiz ID: " +quizID);
                   quizModel.updateQuizStatus(quizID);
                   quizModel.UpdateQuestionAmmount(quizID);
            }
            
            Quiz quiz= new Quiz();
            response.sendRedirect("/AC31007Quiz/staffPortal.jsp");
            
        }
        else if (save!=null)
        {
            if(question==null)
            {
                HttpSession session = request.getSession();
                java.util.LinkedList<QuestionBank> questionList = (java.util.LinkedList<QuestionBank>) session.getAttribute("QuestionBank");

                String[] array = new String[questionList.size()];
                Part[] parts = new Part[questionList.size()];

                System.out.println("LinkedList contains: ");
                   for(int i=0; i< questionList.size(); i++)
                   {
                     QuestionBank result = questionList.get(i);
                     System.out.println(result.getQuery());

                     array[i] = result.getQuery();
                     if (result.getMedia() == null){
                         parts[i] = null;
                     } else {
                         parts[i] = result.getMedia();
                     }

                  }
                   QuizModel quizModel = new QuizModel();
                   quizModel.addQuestion(array, parts);
             }
            else if(question!=null)
            {
                HttpSession session = request.getSession();
            Quiz quizStore = (Quiz) session.getAttribute("Quiz");
            int quizID = quizStore.getQuizID();
            int questionID = quizStore.getNumberOfQuestions();
            QuestionBank questionBank = new QuestionBank();
            questionBank.setQuestion(question);
            questionBank.setQuizID(quizID);
            questionBank.setQuestionID(questionID);
            questionBank.setA(answerA);
            questionBank.setB(answerB);
            questionBank.setC(answerC);
            questionBank.setD(answerD);
            questionBank.setCorrectAnswer(correctAnswer);
            questionBank.setAnswerDesc(answerDesc);
            if (questionMedia != null)
            {
                questionBank.setMedia(questionMedia);
            }

            String test = questionBank.getQuestion();
            System.out.println("Test: "+ test);
            
            java.util.LinkedList<QuestionBank> questionList = (java.util.LinkedList<QuestionBank>) session.getAttribute("QuestionBank");
            
            if(questionList!=null)
            {
                    System.out.println("Question list is not null");
                    questionList.add(questionBank);
                    
            }
            else if (questionList == null)
            {
                System.out.println("Question list is null");
                questionList.add(questionBank);
            }
            
                

                String[] array = new String[questionList.size()];
                Part[] parts = new Part[questionList.size()];
                
                System.out.println("LinkedList contains: ");
                   for(int i=0; i< questionList.size(); i++)
                   {
                     QuestionBank result = questionList.get(i);
                     System.out.println(result.getQuery());

                     array[i] = result.getQuery();
                     if (result.getMedia() == null){
                         parts[i] = null;
                     } else {
                         parts[i] = result.getMedia();
                     }

                  }
                   QuizModel quizModel = new QuizModel();
                   quizModel.addQuestion(array, parts);
                   quizModel.UpdateQuestionAmmount(quizID);
            }
            
            Quiz quiz= new Quiz();
            response.sendRedirect("/AC31007Quiz/staffPortal.jsp");
        }
        else if (addQuestion!=null)
        {
            response.sendRedirect("/AC31007Quiz/addQuestions.jsp");
            
            HttpSession session = request.getSession();
            Quiz quizStore = (Quiz) session.getAttribute("Quiz");
            int quizID = quizStore.getQuizID();
            int questionID = quizStore.getNumberOfQuestions();
            QuestionBank questionBank = new QuestionBank();
            questionBank.setQuestion(question);
            questionBank.setQuizID(quizID);
            questionBank.setQuestionID(questionID);
            questionBank.setA(answerA);
            questionBank.setB(answerB);
            questionBank.setC(answerC);
            questionBank.setD(answerD);
            questionBank.setCorrectAnswer(correctAnswer);
            questionBank.setAnswerDesc(answerDesc);
            if (questionMedia != null)
            {
                questionBank.setMedia(questionMedia);
            }

            String test = questionBank.getQuestion();
            System.out.println("Test: "+ test);
            
            java.util.LinkedList<QuestionBank> questionList = (java.util.LinkedList<QuestionBank>) session.getAttribute("QuestionBank");
            
            if(questionList!=null)
            {
                    System.out.println("Question list is not null");
                    questionList.add(questionBank);
                    
            }
            else if (questionList == null)
            {
                System.out.println("Question list is null");
                questionList.add(questionBank);
            }
            
            
        }
        else if(cancel!=null)
        {
            response.sendRedirect("/AC31007Quiz/staffPortal.jsp");
        }
}

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd=request.getRequestDispatcher("addQuestions.jsp");
	    rd.forward(request,response);
    }
}
    
