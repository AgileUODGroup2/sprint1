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
import java.sql.Statement;
import lib.database.DatabaseConnection;

/**
 *
 * @author erincoey
 */
public class testModel {
    


public String getStaffDetails()
{
    try{
    
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.connectToDatabase();
        System.out.println("Database connected: "+conn);
        Statement st = conn.createStatement();
        String query = "SELECT * from staff";
    
        ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                int staffID = rs.getInt("Staff_ID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String password = rs.getString("Password");
                
                String result =staffID + firstName + lastName + password;
                return result;
            }
        st.close();
    } catch(SQLException err) {
        System.out.println(err.getMessage());
    }
    return null;
}

}