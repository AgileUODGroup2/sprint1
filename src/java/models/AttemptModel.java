/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lib.database.DatabaseConnection;

/**
 *
 * @author viivipursiainen
 */
public class AttemptModel {
    DatabaseConnection db = new DatabaseConnection();
    
    public void addNewAttempt(int matricNo, int quizID, Date date, int score) {
        String query = "INSERT INTO attempts VALUES (?,?,(SELECT Attempted_Count FROM student_quiz WHERE Matriculation_Number=? AND Quiz_ID=?),?,?);";
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query); ) {
            ps.setInt(1,matricNo);
            ps.setInt(2,quizID);
            ps.setInt(3,matricNo);
            ps.setInt(4,quizID);
            ps.setDate(5,date);
            ps.setInt(6, score);
            ps.execute();
        } catch (SQLException e) {
            e.getMessage();
        }
        
    }
}
