/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lib.database.DatabaseConnection;
import stores.QuestionBank;

/**
 *
 * @author viivipursiainen
 */
public class QuestionModel {
    
    DatabaseConnection db = new DatabaseConnection();
    
    public QuestionBank getQuestion(int questionID) {
        db = new DatabaseConnection();
        QuestionBank question = new QuestionBank();
        question.setQuestionID(questionID);
        String query = "SELECT * FROM question_bank WHERE Question_ID = ?";
        
        try (Connection conn = db.connectToDatabase();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1,questionID);
            try (ResultSet rs = ps.executeQuery();) {
                while(rs.next()) {
                    question.setQuizID(rs.getInt("Quiz_ID"));
                    question.setQuestion(rs.getString("Question"));
                    question.setA(rs.getString("A"));
                    question.setB(rs.getString("B"));
                    question.setC(rs.getString("C"));
                    question.setD(rs.getString("D"));
                    question.setCorrectAnswer(rs.getString("Answer"));
                    question.setAnswerDesc(rs.getString("Answer_Desc"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return question;
    }
    
    public byte[] getQuestionMedia(int questionID) {
        
        Connection con = db.connectToDatabase();
        
        String query = "SELECT Media FROM question_bank WHERE Question_ID = ?";
        
        // Java image to MySQL database query based on code found here: http://www.thejavaprogrammer.com/save-retrieve-image-mysql-database-using-servlet-jsp/
        try {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, questionID);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    
                    Blob blob = rs.getBlob("Media");
                    byte byteArray[] = blob.getBytes(1, (int)blob.length());
                    
                    return byteArray;
                    
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if(con != null){
                try{
                    con.close();
                } catch(Exception e){
                    System.out.print(e.getMessage());
                }
            }
        }
        
        //CHANGE THIS
        return null;
        
    }
    
    public String[] getRightAnswers(int[] quizIDs) {
        String[] rightAnswers = new String[quizIDs.length];
        
        String query = "SELECT Answer FROM question_bank WHERE Question_ID = ?";
        
        try (Connection conn = db.connectToDatabase()) {
            for (int i=0; i<quizIDs.length; i++) {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, quizIDs[i]);
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    rightAnswers[i] = rs.getString("Answer");
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        
        return rightAnswers;
    }
    
}
