/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Part;
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
        String query = "UPDATE Question_Bank SET Question=? WHERE Question_ID=? AND Quiz_ID=?";
        try(Connection conn = db.connectToDatabase();
                PreparedStatement preparedStmt = conn.prepareStatement(query);){
            preparedStmt.setString(1, question);
            preparedStmt.setInt(2, questionID);
            preparedStmt.setInt(3, quizID);
            
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    //Pass (A, B, C, D) depending on what answer is being changed
    public void AlterQuestionAnswers(char option, String edit, int questionID, int quizID) throws SQLException{
        String query = "UPDATE Question_Bank SET " + option + "=? WHERE Question_ID=? AND Quiz_ID=?";
        try(Connection conn = db.connectToDatabase();
                PreparedStatement preparedStmt = conn.prepareStatement(query);){
            preparedStmt.setString(1, edit);
            preparedStmt.setInt(2, questionID);
            preparedStmt.setInt(3, quizID);
            
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    public void AlterQuestionAnswersDescription(String desc, int questionID, int quizID) throws SQLException{
        String query = "UPDATE Question_Bank SET Answer_Desc=? WHERE Question_ID=? AND Quiz_ID=?";
        try(Connection conn = db.connectToDatabase();
                PreparedStatement preparedStmt = conn.prepareStatement(query);){
            preparedStmt.setString(1, desc);
            preparedStmt.setInt(2, questionID);
            preparedStmt.setInt(3, quizID);
            
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    public void AlterQuestionAnswer(String answer, int questionID, int quizID) throws SQLException{
        String query = "UPDATE Question_Bank SET Answer=? WHERE Question_ID=? AND Quiz_ID=?";
        try(Connection conn = db.connectToDatabase();
                PreparedStatement preparedStmt = conn.prepareStatement(query);) {
            preparedStmt.setString(1, answer);
            preparedStmt.setInt(2, questionID);
            preparedStmt.setInt(3, quizID);
            
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    public void EditWholeQuiz(QuestionBank qBank){
        String query =     "UPDATE Question_Bank "
                + "SET Question=?, A=?, B=?, C=?, D=?, Answer=?, Answer_Desc=? "
                + "WHERE Question_ID=?;";
        try(Connection conn = db.connectToDatabase();
                PreparedStatement preparedStmt = conn.prepareStatement(query);) {
            preparedStmt.setString(1, qBank.getQuestion());
            preparedStmt.setString(2, qBank.getA());
            preparedStmt.setString(3, qBank.getB());
            preparedStmt.setString(4, qBank.getC());
            preparedStmt.setString(5, qBank.getD());
            preparedStmt.setString(6, qBank.getAnswer());
            preparedStmt.setString(7, qBank.getAnswerDesc());
            preparedStmt.setInt(8, qBank.getQuestionID());
            
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    public void updateQuestionMedia(int questionID, Part questionImage) throws IOException{
        
        String query = "UPDATE question_bank SET Media = ? WHERE Question_ID = ?";
        
        // Java image to MySQL database query based on code found here: http://www.thejavaprogrammer.com/save-retrieve-image-mysql-database-using-servlet-jsp/
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {
            if (questionImage != null){
                InputStream is = questionImage.getInputStream();
                ps.setBlob(1, is);
                ps.setInt(2, questionID);
            
                ps.executeUpdate();
            } else {
                ps.setNull(1, java.sql.Types.BLOB);
                ps.setInt(2, questionID);
                
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
    
}
