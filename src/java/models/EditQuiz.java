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
import stores.QuestionBank;

/**
 *
 * @author glenmorrison
 */
public class EditQuiz {
    
    DatabaseConnection db = new DatabaseConnection();
    
    //
    public void AlterQuizTitle(String question, int questionID, int quizID) throws SQLException{
        try(Connection conn = db.connectToDatabase()){
            //Create and prepare query
            String query = "UPDATE Question_Bank SET Question=? WHERE Question_ID=? AND Quiz_ID=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            //Inputs to Query
            preparedStmt.setString(1, question);
            preparedStmt.setInt(2, questionID);
            preparedStmt.setInt(3, quizID);
            
            //Execute Query
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    //Pass (A, B, C, D) depending on what answer is being changed
    public void AlterQuestionAnswers(char option, String edit, int questionID, int quizID) throws SQLException{
        try(Connection conn = db.connectToDatabase()){
            
            //Create and prepare query
            String query = "UPDATE Question_Bank SET " + option + "=? WHERE Question_ID=? AND Quiz_ID=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            //Inputs to Query
            preparedStmt.setString(1, edit);
            preparedStmt.setInt(2, questionID);
            preparedStmt.setInt(3, quizID);
            
            //Execute Query
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    //
    public void AlterQuestionAnswersDescription(String desc, int questionID, int quizID) throws SQLException{
        try(Connection conn = db.connectToDatabase();){
            //Create and prepare query
            String query = "UPDATE Question_Bank SET Answer_Desc=? WHERE Question_ID=? AND Quiz_ID=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            //Inputs to Query
            preparedStmt.setString(1, desc);
            preparedStmt.setInt(2, questionID);
            preparedStmt.setInt(3, quizID);
            
            //Execute Query
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    //
    public void AlterQuestionAnswer(String answer, int questionID, int quizID) throws SQLException{
        try(Connection conn = db.connectToDatabase(); ) {
            //Create and prepare query
            String query = "UPDATE Question_Bank SET Answer=? WHERE Question_ID=? AND Quiz_ID=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            //Inputs to Query
            preparedStmt.setString(1, answer);
            preparedStmt.setInt(2, questionID);
            preparedStmt.setInt(3, quizID);
            
            //Execute Query
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    //
    public void DeleteQuestion(int questionID, int quizID){
        try(Connection conn = db.connectToDatabase();) {
            //Create and prepare query
            String query =  "DELETE FROM Question_Bank " +
                            "WHERE Question_ID=? " +
                            "AND Quiz_Id=?;";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            //Inputs to Query
            preparedStmt.setInt(1, questionID);
            preparedStmt.setInt(2, quizID);
            
            //Execute Query
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    public void EditWholeQuiz(QuestionBank qBank){
        try(Connection conn = db.connectToDatabase();) {
            //Create and prepare query
            String query =     "UPDATE Question_Bank "
                             + "SET Question=?, A=?, B=?, C=?, D=?, Answer=?, Answer_Desc=? "
                             + "WHERE Question_ID=?;";
            
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            //Inputs to Query
            preparedStmt.setString(1, qBank.getQuestion());
            preparedStmt.setString(2, qBank.getA());
            preparedStmt.setString(3, qBank.getB());
            preparedStmt.setString(4, qBank.getC());
            preparedStmt.setString(5, qBank.getD());
            preparedStmt.setString(6, qBank.getAnswer());
            preparedStmt.setString(7, qBank.getAnswerDesc());
            preparedStmt.setInt(8, qBank.getQuestionID());
            
            //Execute Query
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
}
