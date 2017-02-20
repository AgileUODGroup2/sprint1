package servlets;

import models.ResultModel;
import stores.Result;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import stores.LoggedIn;

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
        int i = uri.lastIndexOf("/");
        String strQuizID = uri.substring(i+1);
        int quizID = Integer.parseInt(strQuizID);
        displayResult(quizID, request, response);

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

        RequestDispatcher rd = request.getRequestDispatcher("/testResult.jsp"); // SET REDIRECT LOCATION!

        ResultModel rm = new ResultModel();

        
        //HttpSession session = request.getSession(true);
        //LoggedIn lg =(LoggedIn)session.getAttribute("LoggedIn");
        
        LoggedIn lg = new LoggedIn();
        
        lg.setUsername("1"); // TEMP - GET THIS FROM SESSION VARIABLE!
        
        int matricNo = Integer.parseInt(lg.getUsername());
        Result quizResult = rm.getQuizResult(matricNo, quizID);

        request.setAttribute("Result", quizResult);
        rd.forward(request, response);
    }

}
