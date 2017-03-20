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
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author viivi
 */
public class AnswerModelTest {
    AnswerModel instance = new AnswerModel();
    DatabaseConnection db = new DatabaseConnection();
    
    public AnswerModelTest() {
    }
    
    @Before
    public void setUp() {
        String q1 = "INSERT INTO answer_store VALUES (1,400,'A')";
        String q2 = "INSERT INTO answer_store VALUES (1,401,'A')";
        String q3 = "INSERT INTO answer_store VALUES (1,402,'A')";
        String q4 = "INSERT INTO answer_store VALUES (1,403,'B')";
        String[] qs = {q1,q2,q3,q4};
        
        try (Connection con = db.connectToDatabase()) {
            for (String q : qs) {
                try (PreparedStatement ps = con.prepareStatement(q)) {
                    ps.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        String query = "DELETE FROM answer_store WHERE Question_ID IN (400,401,402,403,404) AND Matriculation_Number=1";
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testGetStudentAnswer() {
        assertEquals(instance.getStudentAnswer(1, 403), "B");
    }
    
    @Test
    public void testDeleteAnswers() {
        int[] qIDs = { 400, 401, 402 };
        
        instance.deleteAnswers(qIDs, 1);
        
        assertEquals(instance.getStudentAnswer(1, 400), null);
        assertEquals(instance.getStudentAnswer(1, 401), null);
        assertEquals(instance.getStudentAnswer(1, 402), null);
    }

    @Test
    public void testStoreAnswer() {
        instance.storeAnswer("A", 400, 1);
        String expectedResult = null;
        
        String check = "SELECT Answer FROM student_answer WHERE Matriculation_Number=1 AND Question_ID=404";
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(check);
                ResultSet rs = ps.executeQuery();) {
            expectedResult = rs.getString("Answer");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        assertEquals(expectedResult, "A");
    }
}
