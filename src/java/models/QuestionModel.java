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
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1,questionID);
            try (ResultSet rs = ps.executeQuery()) {
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
}
