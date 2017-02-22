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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.registerModel;

/**
 *
 * @author erincoey
 */
@WebServlet(name ="register", urlPatterns = {"/register"})

public class register extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        
    }
  
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    String First_Name = request.getParameter("firstName");
    String Last_Name = request.getParameter("lastName");
    String Password = request.getParameter("password");
    String Staff_ID = request.getParameter("staffID");


    int Staff_IDnew = Integer.parseInt(Staff_ID);

  
    
    System.out.println("Result: " + Staff_ID + First_Name + Last_Name + Password);
    registerModel register = new registerModel();
    register.register(Staff_IDnew, First_Name,Last_Name, Password );
    response.sendRedirect("index.jsp");
    
}

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
	    rd.forward(request,response);
    }
}
