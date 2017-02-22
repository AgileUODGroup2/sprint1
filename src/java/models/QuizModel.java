/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import lib.database.DatabaseConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import stores.LoggedIn;
import stores.Quiz;
import stores.StudentQuiz;

/**
 *
 * @author erincoey
 */
public class QuizModel {
    DatabaseConnection db;

public void createQuiz(String moduleID, String staffName, String dateCreated, String quizName,String available, String staffID ){

    Connection conn = null;
    
    try{
        db = new DatabaseConnection();
        conn = db.connectToDatabase();
        
        String query = "INSERT INTO quiz (Module_ID, Staff_Name, Date_Created, Quiz_Name,Quiz_Status,Staff_ID)"+"values(?,?,?,?,?,?)";
        System.out.println("Result: "+moduleID+staffName+dateCreated+quizName);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, moduleID);
        preparedStmt.setString(2,staffName);
        preparedStmt.setString(3,dateCreated);
        preparedStmt.setString(4,quizName);
        preparedStmt.setString(5,available);
        preparedStmt.setString(6,staffID);
        preparedStmt.executeUpdate();
    }
    catch(SQLException err){
        System.out.println(err.getMessage());
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }
}

//Call this function to update Num_Of_Questions column 
public void UpdateQuestionAmmount(int quizID){
    db = new DatabaseConnection();
        try (Connection conn = db.connectToDatabase()) {

            //Create and prepare query
            String query =  "UPDATE Quiz " +
                            "SET Num_Of_Questions=(" +
                            "SELECT COUNT(*) FROM Question_Bank " +
                            "WHERE Quiz.Quiz_ID = Question_Bank.Quiz_ID " +
                            ")"+
                            "WHERE Quiz_ID=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, quizID);
            System.out.println(preparedStmt);

            //Execute Query
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
}

public int getQuizNumberOfQuestions(int quizID)
{
    Connection conn = null;
    try{
        db = new DatabaseConnection();
        conn = db.connectToDatabase();
        int result =0;
        Statement st = conn.createStatement();
        String query = "SELECT Num_Of_Questions FROM quiz WHERE Quiz_ID=?;";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, quizID);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){      
        }
        st.close();
        return result;
    }
    catch(SQLException err){
        System.out.println(err.getMessage());
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }

    return 0;  
}

public int getQuizId()
{
    Connection conn = null;
    try{
        db = new DatabaseConnection();
        conn = db.connectToDatabase();
        int result =0;
        Statement st = conn.createStatement();
        String query = "SELECT * FROM quiz ORDER BY Quiz_ID;";

        ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                int quizID = rs.getInt("Quiz_ID");
                System.out.println(quizID);
                
                result = quizID; 
            }

            st.close();
            return result;
             }
        catch(SQLException err){
            System.out.println(err.getMessage());
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }
    
    return 0;
}

public void addQuestion(String[] array)
{
    Connection conn = null;
    try{
        db = new DatabaseConnection();
        conn = db.connectToDatabase();
        for (int i =0; i<array.length; i++)
        {
            
            PreparedStatement preparedStmt = conn.prepareStatement(array[i]);
            preparedStmt.executeUpdate();
        }
        
        conn.close();
        }
    catch(SQLException err){
            System.out.println(err.getMessage());
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }
    
}

public java.util.LinkedList<Quiz> getQuizzes(String query, int staffID) {
    
    db = new DatabaseConnection();
    java.util.LinkedList<Quiz> quizzes = new java.util.LinkedList<>();
    try (Connection con = db.connectToDatabase();
        PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, staffID);
        try (ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                Quiz q = new Quiz();
                q.setQuizID(rs.getInt("Quiz_ID"));
                q.setQuizName(rs.getString("Quiz_Name"));
                q.setModuleID(rs.getString("Module_ID"));
                q.setDateCreated(rs.getDate("Date_Created"));
            
                quizzes.add(q);
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return quizzes;
}

public java.util.LinkedList<Quiz> getUnfinishedQuizzes(int staffID) {
    String query = "SELECT * FROM unfinishedquiz WHERE Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getLiveQuizzes(int staffID) {
    String query = "SELECT * FROM livequiz WHERE Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getCompletedQuizzes(int staffID) {
    String query = "SELECT * FROM completedquiz WHERE Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getAllQuizzes(int staffID) {
    String query = "SELECT * FROM quiz WHERE Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getUnfinishedQuizzesMod(int staffID, String module) {
    String query = "SELECT * FROM unfinishedquiz WHERE Module_ID ='"+module+"' AND Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getLiveQuizzesMod(int staffID, String module) {
    String query = "SELECT * FROM livequiz WHERE Module_ID ='"+module+"' AND Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getCompletedQuizzesMod(int staffID, String module) {
    String query = "SELECT * FROM completedquiz WHERE Module_ID ='"+module+"' AND Staff_ID = ?";
    return getQuizzes(query, staffID);
}

public Quiz getQuizDetails(int quizID) {

        DatabaseConnection db = new DatabaseConnection();
        
        Quiz quiz = new Quiz();
        String query = "SELECT * FROM quiz WHERE Quiz_ID = ?";
        
        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, quizID);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    
                    quiz.setQuizID(rs.getInt("Quiz_ID"));
                    //quiz.setModuleID(rs.getInt("Module_ID")); Servlet error when un-commented?
                    //quiz.setStaffName(rs.getString("Staff_Name")); Not needed?
                    quiz.setDateCreated(rs.getDate("Date_Created"));
                    quiz.setQuizName(rs.getString("Quiz_Name"));
                    quiz.setNumberOfQuestions(rs.getInt("Num_Of_Questions"));
                    quiz.setStatus(rs.getString("Quiz_Status"));
                    quiz.setStaffID(rs.getInt("Staff_ID"));
                    
                }
            }


        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }

        return quiz;

    }

public java.util.LinkedList<StudentQuiz> getStudentQuizzes(String query, int matricNo) {
    
    db = new DatabaseConnection();
    java.util.LinkedList<StudentQuiz> quizzes = new java.util.LinkedList<>();
    try (Connection con = db.connectToDatabase();
        PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, matricNo);
        try (ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                StudentQuiz q = new StudentQuiz();
                q.setQuizID(rs.getInt("Quiz_ID"));
                q.setQuizName(rs.getString("Quiz_Name"));
                q.setModuleID(rs.getString("Module_ID"));
                q.setDateCreated(rs.getDate("Date_Created"));
                q.setStaffName(rs.getString("Staff_Name"));
                q.setNumberOfQuestions(rs.getInt("Num_Of_Questions"));
                q.setAttempts(rs.getInt("Attempted_Count"));
                q.setScore(rs.getInt("Score"));
                q.setDateCompleted(rs.getDate("Date_Completed"));
            
                quizzes.add(q);
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return quizzes;
}

public java.util.LinkedList<StudentQuiz> getCompletedStudentQuizzes(int matricNo) {
    String query = "select * from studentcompleted where Matriculation_Number=? order by Module_ID";
    return getStudentQuizzes(query, matricNo);
}
public java.util.LinkedList<StudentQuiz> getIncompleteStudentQuizzes(int matricNo) {
    String query = "select * from studentincomplete where Matriculation_Number=? order by Module_ID";
    return getStudentQuizzes(query, matricNo);
}
public java.util.LinkedList<StudentQuiz> getPendingStudentQuizzes(int matricNo) {
    String query = "select * from studentpending where Matriculation_Number=? order by Module_ID";
    return getStudentQuizzes(query, matricNo);
}

public java.util.LinkedList<StudentQuiz> getCompletedStudentQuizzesMod(int matricNo, String module) {
    String query = "select * from studentcompleted where Module_ID = '"+module+"' AND Matriculation_Number=?";
    return getStudentQuizzes(query, matricNo);
}
public java.util.LinkedList<StudentQuiz> getIncompleteStudentQuizzesMod(int matricNo, String module) {
    String query = "select * from studentincomplete where Module_ID = '"+module+"' AND Matriculation_Number=?";
    return getStudentQuizzes(query, matricNo);
}
public java.util.LinkedList<StudentQuiz> getPendingStudentQuizzesMod(int matricNo, String module) {
    String query = "select * from studentpending where Module_ID = '"+module+"' AND Matriculation_Number=?";
    return getStudentQuizzes(query, matricNo);
}

public void makeQuizLive(int QuizID){ //change to session variables
    Connection conn = null;
     try{
        db = new DatabaseConnection();
        conn = db.connectToDatabase();
        
        String query = "UPDATE quiz set Quiz_Status = 'Live' WHERE Quiz_ID = ?";
        
        PreparedStatement preparedStmt = conn.prepareStatement(query);
       
        preparedStmt.setInt(1,QuizID);
        
        preparedStmt.executeUpdate();
        conn.close();
        }
    catch(SQLException err){
            System.out.println(err.getMessage());
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }
 }

public java.util.LinkedList<Quiz> getFilterByRecent(){ 
    
    
   db = new DatabaseConnection();
        Connection conn = db.connectToDatabase();
       java.util.LinkedList<Quiz> recentFilter = new java.util.LinkedList<>();
       
     try{
       
        
        String query =  "SELECT * FROM quiz WHERE Quiz_Status = 'Live' ORDER BY Date_Created";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
      
       try (ResultSet rs = preparedStmt.executeQuery()){
           while (rs.next()){
            Quiz rq = new Quiz();
            rq.setQuizID(rs.getInt("Quiz_ID"));
            recentFilter.add(rq);
            rq.setModuleID(rs.getString("Module_ID"));
            recentFilter.add(rq);
            rq.setStaffName(rs.getString("Staff_Name"));
            recentFilter.add(rq);
            rq.setDateCreated(rs.getDate("Date_Created"));
            recentFilter.add(rq);
            rq.setQuizName(rs.getString("Quiz_Name"));
            recentFilter.add(rq);
            rq.setNumberOfQuestions(rs.getInt("Num_Of_Questions"));
            recentFilter.add(rq);
            rq.setStatus(rs.getString("Quiz_Status"));
            recentFilter.add(rq);
            rq.setStaffID(rs.getInt("Staff_ID"));
            recentFilter.add(rq);
            
           }
       }
       
        }
    catch(SQLException err){
            System.out.println(err.getMessage());
              
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }
  
      return recentFilter;
}

public void updateQuizStatus(int quizID)
{
    Connection conn = null;
    try{
        db = new DatabaseConnection();
        conn = db.connectToDatabase();
 
        String query = "UPDATE quiz set Quiz_Status = 'Completed' WHERE Quiz_ID = ?";
        
        PreparedStatement preparedStmt = conn.prepareStatement(query);
       
        preparedStmt.setInt(1,quizID);
        
        preparedStmt.executeUpdate();
        conn.close();
        }
    catch(SQLException err){
            System.out.println(err.getMessage());
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }
}
public Quiz getFilterByRecentQuiz(){ 
 
            
            Quiz rq = new Quiz();
  db = new DatabaseConnection();
        
        
      String query =  "SELECT * FROM quiz WHERE Quiz_Status = 'Live' ORDER BY Date_Created";
    
     try {Connection conn = db.connectToDatabase();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
       try (ResultSet rs = preparedStmt.executeQuery()){
           while (rs.next()){
            
            rq.setQuizID(rs.getInt("Quiz_ID"));
            rq.setModuleID(rs.getString("Module_ID"));
            rq.setStaffName(rs.getString("Staff_Name"));
            rq.setDateCreated(rs.getDate("Date_Created"));
            rq.setQuizName(rs.getString("Quiz_Name"));
            rq.setNumberOfQuestions(rs.getInt("Num_Of_Questions"));
            rq.setStatus(rs.getString("Quiz_Status"));
            rq.setStaffID(rs.getInt("Staff_ID"));
       
        }
            
           }
       }
       
      
    catch(SQLException err){
            System.out.println(err.getMessage());
              
        }
  
      return rq;
    }

 
}

