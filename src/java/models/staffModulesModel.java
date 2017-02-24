/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lib.database.DatabaseConnection;
/**
 *
 * @author ashawittchen
 */
public class staffModulesModel {
    
    DatabaseConnection db = new DatabaseConnection();
    
    public String getStaffModules(int Staff_ID){
        
    try(Connection conn = db.connectToDatabase(); Statement st = conn.createStatement()){
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
    }
    catch(SQLException err){
        System.out.println(err.getMessage());
    }
    return null;
 }
}
