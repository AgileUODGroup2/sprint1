/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import lib.database.DatabaseConnection;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author erincoey
 */
public class QuizModel {
    

public void createQuiz(int quizID, int moduleID, String staffName, String dateCreated, String quizName ){
        
    try{
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.connectToDatabase();
        
        String query = "INSERT INTO quiz (Quiz_ID, Module_ID, Staff_Name, Date_Created, Quiz_Name)"+"values(?,?,?,?,?)";
        System.out.println("Result: "+quizID+moduleID+staffName+dateCreated+quizName);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1,quizID);
        preparedStmt.setInt(2, moduleID);
        preparedStmt.setString(3,staffName);
        preparedStmt.setString(4,dateCreated);
        preparedStmt.setString(5,quizName);
        
        preparedStmt.executeUpdate();
        conn.close();
        }
    catch(SQLException err){
            System.out.println(err.getMessage());
        }
}

}