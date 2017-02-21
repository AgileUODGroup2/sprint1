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
import stores.StudentResult;

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
    
    public int getQuizAverage(int quizID) {
        int averageScore = 0;
        
        String query = "SELECT AVG(Score) FROM student_quiz WHERE Has_Completed = 'Completed' AND Quiz_ID = ?";
        
        try (Connection con = db.connectToDatabase(); ) {
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, quizID);
            
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    averageScore = rs.getInt("AVG(Score)");
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        
        return averageScore;
    }
    
    public java.util.LinkedList<StudentResult> getQuizResults(int quizID) {
        
        String query = "SELECT"
+	" Quiz.Quiz_ID,"
+	" CONCAT_WS(' ', Student.First_Name, Student.Last_Name) AS Student_Name,"
+	" Student_Quiz.Matriculation_Number,"
+	" Student_Quiz.Has_Completed,"
+	" Student_Quiz.Attempted_Count,"
+	" Student_Quiz.Score,"
+	" Student_Quiz.Date_Completed"
+	" FROM"
+	" Student_Quiz,"
+	" Quiz,"
+	" Student"
+	" WHERE"
+	" Student_Quiz.Quiz_ID=Quiz.Quiz_ID"
+	" AND"
+	" Quiz.Quiz_ID = ?"
+	" AND"
+	" Student.Matriculation_Number = Student_Quiz.Matriculation_Number"
+	" AND"
+	" Has_Completed = 'Completed'"
+	" ORDER BY"
+	" Student_Name, Attempted_Count";
        
        java.util.LinkedList<StudentResult> quizResult = new java.util.LinkedList<>();
        
        try (Connection con = db.connectToDatabase();) {
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, quizID);
            
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    
                    StudentResult result = new StudentResult();
                    result.setMatriculationNumber(rs.getInt("Matriculation_Number"));
                    result.setStudentName(rs.getString("Student_Name"));
                    result.setQuizID(rs.getInt("Quiz_ID"));
                    result.setHasCompleted(true);
                    result.setAttemptedCount(rs.getInt("Attempted_Count"));
                    result.setScore(rs.getInt("Score"));
                    result.setDate(rs.getDate("Date_Completed"));
                    
                    quizResult.add(result);
                }
            }
            
        } catch (SQLException e) {
                System.out.println(e.getMessage());
        }
        return quizResult;
    }
}
