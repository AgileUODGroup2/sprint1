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
import models.wholeStudentListModel;
/**
 *
 * @author ashawittchen
 */

@WebServlet(name ="wholeStudentList", urlPatterns = {"/wholeStudentList"})

public class wholeStudentList extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        
    }
    
 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

   wholeStudentListModel wholeStudentLt = new wholeStudentListModel(); 
   wholeStudentLt.getWholeStudentList();
}
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd=request.getRequestDispatcher("wholeStudentList.jsp");
	    rd.forward(request,response);
    }
    
}
