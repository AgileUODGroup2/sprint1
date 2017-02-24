/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lib.database.DatabaseConnection;

/**
 *
 * @author erincoey
 */
public class registerModel {
    
    DatabaseConnection db = new DatabaseConnection();

public void register(int Staff_ID,  String First_Name, String Last_Name, String Password ){
    try(Connection conn = db.connectToDatabase()){
        String query = "INSERT INTO staff (Staff_ID, First_Name, Last_Name, Password)"+"values(?,?,?,?)";
        System.out.println("Result: " + Staff_ID + First_Name + Last_Name + Password);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1,Staff_ID);
        preparedStmt.setString(2, First_Name);
        preparedStmt.setString(3,Last_Name);
        preparedStmt.setString(4,Password);
     
        
        preparedStmt.executeUpdate();
    }
    catch(SQLException err){
            System.out.println(err.getMessage());
    }
}

}