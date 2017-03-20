package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
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
import stores.Result;
import stores.StudentResult;

/**
 * Created by cmckillop on 17/02/2017.
 */
@WebServlet(name = "DisplayResult", urlPatterns = {
        "/result",
        "/result/*"
})
public class DisplayResult extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        int i = uri.lastIndexOf('/');
        String strQuizID = uri.substring(i+1);
        int quizID = Integer.parseInt(strQuizID);
        HttpSession session = request.getSession();
        
        if (session.getAttribute("Results") == null) {
            System.out.println("Request attribute Results is null");
            displayResult(quizID, request, response);
        } else {
            ResultModel rm = new ResultModel();
            QuizModel qm = new QuizModel();
            Quiz quiz = qm.getQuizDetails(quizID);
            quiz.setAverageScore(rm.getQuizAverage(quizID));
            
            RequestDispatcher rd = request.getRequestDispatcher("/studentResults.jsp");
            request.setAttribute("Results", session.getAttribute("Results"));
            request.setAttribute("Quiz", quiz);
            session.removeAttribute("Results");
            rd.forward(request, response);
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String strQuizID = request.getParameter("quizID");
        int quizID = Integer.parseInt(strQuizID);
        
        displayResult(quizID, request, response);
        
    }

    /**
     *
     * @param quizID
     * @param request
     * @param response
     */
    private void displayResult(int quizID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultModel rm = new ResultModel();
        QuizModel qm = new QuizModel();

        
        HttpSession session = request.getSession(true);
        LoggedIn lg =(LoggedIn)session.getAttribute("LoggedIn");
        
        Quiz quiz = qm.getQuizDetails(quizID);
        quiz.setAverageScore(rm.getQuizAverage(quizID));
        
        if(lg.isStaff()){
            java.util.LinkedList<StudentResult> quizResult = rm.getQuizResults(quizID);
            quiz.setGradeDivide(calculateGraphGrades(quizResult));
            
            RequestDispatcher rd = request.getRequestDispatcher("/studentResults.jsp");
            
            request.setAttribute("Results", quizResult);
            request.setAttribute("Quiz", quiz);
            rd.forward(request, response);
        }
        else
        {
            int matriculationNo = Integer.parseInt(lg.getUsername());
            java.util.LinkedList<Result> quizResults = rm.getQuizResult(matriculationNo, quizID);
            
            RequestDispatcher rd = request.getRequestDispatcher("/testResult.jsp"); 
            request.setAttribute("Results", quizResults);
            request.setAttribute("Quiz", quiz);
            rd.forward(request, response);
        }
    }
    
    private int[] calculateGraphGrades(java.util.LinkedList<StudentResult> quizResult) {
        
        int tenP, twentyP, thirtyP, fortyP, fiftyP, sixtyP, seventyP, eightyP, ninetyP, oneHP;
        tenP = twentyP = thirtyP = fortyP = fiftyP = sixtyP = seventyP = eightyP = ninetyP = oneHP = 0;
        
        int[] gradeDivide = new int[10];
        
        Iterator<StudentResult> iterator = quizResult.iterator();
        
        while(iterator.hasNext()){
            StudentResult result = iterator.next();
            int score = result.getScore();

            if (score >= 0 && score <=10){
                tenP++;
            } else if (score > 10 && score <=20){
                twentyP++;
            } else if (score > 20 && score <=30){
                thirtyP++;
            } else if (score > 30 && score <=40){
                fortyP++;
            } else if (score > 40 && score <=50){
                fiftyP++;
            } else if (score > 50 && score <=60){
                sixtyP++;
            } else if (score > 60 && score <=70){
                seventyP++;
            } else if (score > 70 && score <=80){
                eightyP++;
            } else if (score > 80 && score <=90){
                ninetyP++;
            } else if (score > 90 && score <=100){
                oneHP++;
            }
        }
        
        for (int i = 0; i < 10; i++){
            switch (i){
                case 0:     gradeDivide[i] = tenP;
                            break;
                case 1:     gradeDivide[i] = twentyP;
                            break;
                case 2:     gradeDivide[i] = thirtyP;
                            break;
                case 3:     gradeDivide[i] = fortyP;
                            break;
                case 4:     gradeDivide[i] = fiftyP;
                            break;
                case 5:     gradeDivide[i] = sixtyP;
                            break;
                case 6:     gradeDivide[i] = seventyP;
                            break;
                case 7:     gradeDivide[i] = eightyP;
                            break;
                case 8:     gradeDivide[i] = ninetyP;
                            break;
                case 9:     gradeDivide[i] = oneHP;
                            break;
            }
        }
        
        System.out.println(Arrays.toString(gradeDivide));
        return gradeDivide;
    }

}
