/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
    }

}
