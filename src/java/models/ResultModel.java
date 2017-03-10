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
    
    public java.util.LinkedList<StudentResult> getResultsForDates(Date date1, Date date2, int quizID) {
        java.util.LinkedList<StudentResult> results = new java.util.LinkedList<>();
        String query = "SELECT Quiz.Quiz_ID, CONCAT_WS(' ', Student.First_Name, Student.Last_Name) AS Student_Name, student_quiz.Matriculation_Number, " +
                        "Student_Quiz.Has_Completed, Student_Quiz.Attempted_Count, Student_Quiz.Score, Student_Quiz.Date_Completed FROM Student_Quiz, Quiz, Student " +
                        "WHERE Student_Quiz.Quiz_ID=Quiz.Quiz_ID AND Quiz.Quiz_ID = ? AND Student.Matriculation_Number = Student_Quiz.Matriculation_Number " +
                        "AND Has_Completed = 'Completed' AND (student_quiz.Date_Completed BETWEEN ? AND ?) ORDER BY Student_Name";
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, quizID);
            ps.setDate(2,date1);
            ps.setDate(3,date2);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    StudentResult res = new StudentResult();
                    res.setMatriculationNumber(rs.getInt("Matriculation_Number"));
                    res.setStudentName(rs.getString("Student_Name"));
                    res.setQuizID(rs.getInt("Quiz_ID"));
                    res.setHasCompleted(rs.getBoolean("Has_Completed"));
                    res.setAttemptedCount(rs.getInt("Attempted_Count"));
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
    
    
    public java.util.LinkedList<Result> getResultsByMatriculationDESC() {
        java.util.LinkedList<Result> results = new java.util.LinkedList<>();
        String query = "SELECT * FROM Student_Quiz ORDER BY Matriculation_Number DESC";
        DatabaseConnection dConn = new DatabaseConnection();
        try (Connection conn = dConn.connectToDatabase()){
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
    
    public java.util.LinkedList<Result> getQuizResult(int matriculationNo, int quizID) {
        java.util.LinkedList<Result> studentResults = new java.util.LinkedList<>();
        String query = "SELECT * FROM attempts WHERE Quiz_ID = ? AND Matriculation_Number = ? ORDER BY AttemptNo DESC";
        
        System.out.println("Getting attempts for "+quizID+" and "+matriculationNo);
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, quizID);
            ps.setInt(2, matriculationNo);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Result studentResult = new Result();
                    studentResult.setQuizID(rs.getInt("Quiz_ID"));
                    studentResult.setAttempts(rs.getInt("AttemptNo"));
                    studentResult.setScore(rs.getInt("Score"));
                    studentResult.setDate(rs.getDate("Date_Completed"));
                    studentResults.add(studentResult);
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return studentResults;
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
