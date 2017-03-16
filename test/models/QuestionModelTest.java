/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Part;
import lib.database.DatabaseConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author viivipursiainen
 */
public class QuestionModelTest {
    
    public QuestionModelTest() { }
    
    @Before
    public void setUp() throws IOException {
        QuizModel qm = new QuizModel();
        String query1 = "INSERT INTO question_bank (Question_ID, Quiz_ID, Question, A, B, C, D, Answer, Answer_Desc) VALUES (200,1,'Test?','Yes','No','Maybe','Dunno','A','Description')"; 
        String query2 = "INSERT INTO question_bank (Question_ID, Quiz_ID, Question, A, B, C, D, Answer, Answer_Desc) VALUES (201,1,'Test?','Yes','No','Maybe','Dunno','B','Description')"; 
        String query3 = "INSERT INTO question_bank (Question_ID, Quiz_ID, Question, A, B, C, D, Answer, Answer_Desc) VALUES (202,1,'Test?','Yes','No','Maybe','Dunno','C','Description')"; 
        String query4 = "INSERT INTO question_bank (Question_ID, Quiz_ID, Question, A, B, C, D, Answer, Answer_Desc) VALUES (203,1,'Test?','Yes','No','Maybe','Dunno','D','Description')"; 
        String[] array = {query1, query2, query3, query4 };
        Part[] parts = new Part[4];
        
        qm.addQuestion(array,parts);
    }
    
    @After
    public void tearDown() throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        String query = "DELETE FROM question_bank WHERE Question_ID IN (200,201,202,203)";
        
        try (Connection conn = db.connectToDatabase();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }

    @Test
    public void testGetRightAnswers() {
        QuestionModel questionM = new QuestionModel();
        int[] questionIDs = {200,201,202,203};
        String[] answers = {"A","B","C","D"};
        String[] rightAnswers = questionM.getRightAnswers(questionIDs);
        
        assertArrayEquals(rightAnswers, answers);
    }
}
