/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ResultModel;
import stores.StudentResult;

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
        
        HttpSession session = request.getSession();
        
        String path = request.getContextPath();
        
        int quizID = Integer.parseInt(request.getParameter("QuizID"));
        
        if (day1.equals("31") || day2.equals("31")) {
            String[] months = {"4","6","9","11"};
            for (String month : months) {
                if (month.equals(month1)) {
                    day1 = "30";
                }
                if (month.equals(month2)) {
                    day2 = "30";
                }
            }
        }
        if ((month1.equals("2") && (day1.equals("31") || day1.equals("30")))) {
            day1 = "29";
        }
        if ((month2.equals("2") && (day2.equals("31") || day2.equals("30")))) {
            day2 = "29";
        }
        
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
        }
        
        
        ResultModel rm = new ResultModel();
        java.util.LinkedList<StudentResult> resultList;
        resultList = rm.getResultsForDates(date1, date2, quizID);
        
        session.setAttribute("Results", resultList);
        response.sendRedirect(path+"/result/"+quizID);
    }

}
