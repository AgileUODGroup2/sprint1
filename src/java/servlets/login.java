/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.user;
import stores.LoggedIn;

/**
 *
 * @author nathanmcmahon
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sess = request.getSession(true);
        RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
	rd.forward(request,response);
        
     
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println(username);
    HttpSession session = request.getSession();
    LoggedIn lg= new LoggedIn();
    lg.setLoggedin();
    lg.setUsername(username);
    
    System.out.println(lg.getUsername());
    
    session.setAttribute("LoggedIn", lg);
    
    user us = new user();
    String result = us.login(username, password);
    System.out.println("Here is the result: "+result);
    if (result.equals ("Staff"))
    {
        lg.setAsStaff(true);
        String[] name = us.getStaffDetails(Integer.parseInt(username));
        lg.setFirstName(name[0]);
        lg.setLastName(name[1]);
        response.sendRedirect("staffPortal.jsp");
    }
    else if (result.equals ("Student"))
    {
        String[] name = us.getStudentDetails(Integer.parseInt(username));
        lg.setFirstName(name[0]);
        lg.setLastName(name[1]);
        response.sendRedirect("studentPortal.jsp");
    }
    else
    {
            response.sendRedirect("failedLogin");
    }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
