/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author viivipursiainen
 */
@WebServlet(name = "SearchResults", urlPatterns = {"/SearchResults"})
public class DateSearch extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String day1 = request.getParameter("day1");
        String day2 = request.getParameter("day2");
        String month1 = request.getParameter("month1");
        String month2 = request.getParameter("month2");
        String year1 = request.getParameter("year1");
        String year2 = request.getParameter("year2");
        
        String stringDate1 = year1 + "-" + month1 + "-" + day1;
        String stringDate2 = year2 + "-" + month2 + "-" + day2;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date javaDate1;
        java.util.Date javaDate2;
        Date date1, date2;
        date1 = date2 = null;
        
        try {
            javaDate1 = df.parse(stringDate1);
            javaDate2 = df.parse(stringDate2);
            date1 = new Date(javaDate1.getTime());
            date2 = new Date(javaDate2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        ResultModel rm = new ResultModel();
        java.util.LinkedList<Result> resultList = null;
        
            resultList = rm.getResultsForDates(date1, date2);
        
            
        
        RequestDispatcher rd = request.getRequestDispatcher("/quizResults.jsp");
        request.setAttribute("ResultList", resultList);
        rd.forward(request, response);
        
    }

}
