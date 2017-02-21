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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import stores.Quiz;

/**
 *
 * @author erincoey
 */
public class QuizModel {
    DatabaseConnection db;

public void createQuiz(String moduleID, String staffName, String dateCreated, String quizName,String available ){
        
    try{
        db = new DatabaseConnection();
        Connection conn = db.connectToDatabase();
        
        String query = "INSERT INTO quiz (Module_ID, Staff_Name, Date_Created, Quiz_Name,Quiz_Status)"+"values(?,?,?,?,?)";
        System.out.println("Result: "+moduleID+staffName+dateCreated+quizName);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, moduleID);
        preparedStmt.setString(2,staffName);
        preparedStmt.setString(3,dateCreated);
        preparedStmt.setString(4,quizName);
        preparedStmt.setString(5,available);
        preparedStmt.executeUpdate();
        conn.close();
        }
    catch(SQLException err){
            System.out.println(err.getMessage());
        }
}

public int getQuizId()
{
    try{
        db = new DatabaseConnection();
        Connection conn = db.connectToDatabase();
        int result =0;
        Statement st = conn.createStatement();
        String query = "SELECT Quiz_ID from quiz";
    
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
        }
    return 0;
            
}

public void addQuestion(String[] array)
{
    
    try{
        db = new DatabaseConnection();
        Connection conn = db.connectToDatabase();
        for (int i =0; i<array.length; i++)
        {
            
            PreparedStatement preparedStmt = conn.prepareStatement(array[i]);
            preparedStmt.executeUpdate();
        }
        
        conn.close();
        }
    catch(SQLException err){
            System.out.println(err.getMessage());
        }
    
}

public java.util.LinkedList<Quiz> getQuizzes(String query) {
    // get staff name!!
    // String staffname = ;
    
    db = new DatabaseConnection();
    java.util.LinkedList<Quiz> quizzes = new java.util.LinkedList<>();
    try (Connection con = db.connectToDatabase();
        PreparedStatement ps = con.prepareStatement(query)) {
        //ps.setDate(1, staffname);
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

public java.util.LinkedList<Quiz> getUnfinishedQuizzes() {
    String query = "SELECT * FROM unfinishedquiz WHERE Staff_Name = ?";
    return getQuizzes(query);
}
public java.util.LinkedList<Quiz> getLiveQuizzes() {
    String query = "SELECT * FROM livequiz WHERE Staff_Name = ?";
    return getQuizzes(query);
}
public java.util.LinkedList<Quiz> getCompletedQuizzes() {
    String query = "SELECT * FROM completedquiz WHERE Staff_Name = ?";
    return getQuizzes(query);
}
}