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
import models.ResultModel;
import stores.Result;

/**
 *
 * @author glenmorrison
 */
@WebServlet(name = "staffOrderByMatNo", urlPatterns = {"/staffOrderByMatNo"})
public class staffOrderByMatNo extends HttpServlet {
    
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
          ResultModel servlet = new ResultModel();
          java.util.LinkedList<Result> resultList;
          resultList = servlet.getResultsByMatriculationDESC();
        
          System.out.println("\n \n Result Lis:" + resultList);
          
          RequestDispatcher rd = request.getRequestDispatcher("/quizResults.jsp");
          request.setAttribute("ResultList", resultList);
          rd.forward(request, response);
          
     }
     
}
