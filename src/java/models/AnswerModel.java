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
 * @author viivi
 */
public class AnswerModel {
    DatabaseConnection db = new DatabaseConnection();
    
    
    public String getStudentAnswer(int matricNo, int questionID) {
        String query = "SELECT Student_Answer FROM answer_store WHERE Matriculation_Number=? AND Question_ID=?";
        String answer = null;
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, matricNo);
            ps.setInt(2,questionID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    answer = rs.getString("Student_Answer");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return answer;
    }
    
    public void storeAnswer(String answer, int qID, int matricNo) {
        String query = "INSERT INTO answer_store VALUES (?,?,?)";
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, matricNo);
            ps.setInt(2,qID);
            ps.setString(3, answer);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteAnswers(int[] questionIDs, int matricNo) {
        String query = "DELETE FROM answer_store WHERE Matriculation_Number=? AND Question_ID=?";
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {
            for (int i=0; i<questionIDs.length; i++) {
                ps.setInt(1,matricNo);
                ps.setInt(2,questionIDs[i]);
                ps.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
