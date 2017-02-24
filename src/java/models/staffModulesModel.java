/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import lib.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author ashawittchen
 */
public class staffModulesModel {
    
    public String getStaffModules(int Staff_ID){
        
    Connection conn = null;
   try{
        DatabaseConnection db = new DatabaseConnection();
        conn = db.connectToDatabase();
        Statement st = conn.createStatement();
        
        String query = "SELECT * FROM staff_enrolment WHERE Staff_ID = 1";
        ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
               Staff_ID= rs.getInt("Staff_ID");
               int  Module_ID= rs.getInt("Module_ID");
                
               String result = Staff_ID + " " + Module_ID;
               System.out.println(Staff_ID + " " + Module_ID);
               return result;
            }
           
            st.close();
           
             }
        catch(SQLException err){
            System.out.println(err.getMessage());
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }
    return null;
 }
}
