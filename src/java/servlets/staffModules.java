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
import models.staffModulesModel;

/**
 *
 * @author ashawittchen
 */
@WebServlet(name ="staffModules", urlPatterns = {"/staffModules"})

public class staffModules extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        
    }
    
 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {


    
   
     
    staffModulesModel staffModules = new staffModulesModel(); 
    staffModules.getStaffModules(1);
}
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd=request.getRequestDispatcher("staffModules.jsp");
	    rd.forward(request,response);
    }
    
}
