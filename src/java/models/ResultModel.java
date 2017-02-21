/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.sql.Connection;
import java.sql.*;
import lib.database.DatabaseConnection;
import stores.Result;

/**
 *
 * @author viivipursiainen
 */
public class ResultModel {
    DatabaseConnection db = new DatabaseConnection();
    
    public java.util.LinkedList<Result> getResultsForDates(Date date1, Date date2) {
        java.util.LinkedList<Result> results = new java.util.LinkedList<>();
        
        String query = "SELECT * FROM student_quiz WHERE (Date_Completed BETWEEN ? AND ?)";
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, date1);
            ps.setDate(2,date2);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Result res = new Result();
                    res.setMatricNo(rs.getInt("Matriculation_Number"));
                    res.setQuizID(rs.getInt("Quiz_ID"));
                    res.setCompleted(rs.getBoolean("Has_Completed"));
                    res.setAttempts(rs.getInt("Attempted_Count"));
                    res.setScore(rs.getInt("Score"));
                    res.setDate(rs.getDate("Date_Completed"));
                    
                    results.add(res);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
    
    //Glen - Returns all quiz results by Matriculation_Number in descending order
    public java.util.LinkedList<Result> getResultsByMatriculationDESC() {
        
        java.util.LinkedList<Result> results = new java.util.LinkedList<>();
        String query = "SELECT * FROM Student_Quiz ORDER BY Matriculation_Number DESC";
          
        DatabaseConnection dConn = new DatabaseConnection();
        
        try (Connection conn = dConn.connectToDatabase()) {
            
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            try (ResultSet set = preparedStmt.executeQuery()) {
                while(set.next()) {
                    Result res = new Result();
                    res.setMatricNo(set.getInt("Matriculation_Number"));
                    res.setQuizID(set.getInt("Quiz_ID"));
                    res.setCompleted(set.getBoolean("Has_Completed"));
                    res.setAttempts(set.getInt("Attempted_Count"));
                    res.setScore(set.getInt("Score"));
                    res.setDate(set.getDate("Date_Completed"));
                    results.add(res);
                 }
            }
               
        } catch(SQLException err) {
            System.out.println(err.getMessage());
        }
        
        return results;
    }
    public java.util.LinkedList<Result> getResultsByModule(String moduleID) {
        java.util.LinkedList<Result> results = new java.util.LinkedList<>();
        
        String query = "SELECT * FROM student_quiz WHERE Module_ID = ? AND Staff_ID = ?";
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, moduleID);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Result res = new Result();
                    res.setMatricNo(rs.getInt("Matriculation_Number"));
                    
                    results.add(res);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
    

    public Result getQuizResult(int matriculationNo, int quizID) {

        Result studentResult = new Result();

        String query = "SELECT * FROM student_quiz WHERE Quiz_ID = ? AND Matriculation_Number = ?";

        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, quizID);
            ps.setInt(2, matriculationNo);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {

                    studentResult.setQuizID(rs.getInt("Quiz_ID"));
                    studentResult.setCompleted(rs.getBoolean("Has_Completed"));
                    studentResult.setAttempts(rs.getInt("Attempted_Count"));
                    studentResult.setScore(rs.getInt("Score"));
                    studentResult.setDate(rs.getDate("Date_Completed"));
                }
            }


        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }

        return studentResult;

    }
}
