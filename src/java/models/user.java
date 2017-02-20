/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lib.database.DatabaseConnection;

/**
 *
 * @author nathanmcmahon
 */
public class user {
    
    
public String login(String username, String password){
    
     DatabaseConnection db = new DatabaseConnection();
     Connection conn = db.connectToDatabase();
     
     String checkStaff = "select * from staff where Staff_ID=? and Password=?";
     String checkStudent = "select * from student where Matriculation_Number=? and Password=?";
     try{       
        PreparedStatement ps = conn.prepareStatement(checkStaff);
        PreparedStatement ps2 = conn.prepareStatement(checkStudent);
        ps.setString(1,username);
        ps.setString(2,password);
        ps2.setString(1,username);
        ps2.setString(2,password);
        ResultSet theResult = ps.executeQuery();
        ResultSet theResult2 = ps2.executeQuery();
        if(theResult.next()){
            conn.close();
            System.out.println("Staff logged in");
            return "Staff";
        } else if(theResult2.next()) {
            conn.close();
            System.out.println("Student logged in");
            return"Student";
        } else {
            conn.close();
            System.out.println("No one logged in");
            return "failed";
        }
     }
     catch(SQLException e) {
         System.out.println(e.getMessage());
     }
     return null;
}
}

