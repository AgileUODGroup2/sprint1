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
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
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
    
    @BeforeClass
    public static void setUpClass() {
        String q1 = "INSERT INTO answer_store VALUES (1,400,'A')";
        String q2 = "INSERT INTO answer_store VALUES (1,401,'A')";
        String q3 = "INSERT INTO answer_store VALUES (1,402,'A')";
        String q4 = "INSERT INTO answer_store VALUES (1,403,'B')";
        String[] qs = {q1,q2,q3,q4};
        DatabaseConnection db = new DatabaseConnection();
        
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
    
    @AfterClass
    public static void tearDownClass() {
        String query = "DELETE FROM answer_store WHERE Question_ID IN (400,401,402,403,404) AND Matriculation_Number=1";
        DatabaseConnection db = new DatabaseConnection();
        System.out.println("Deleting all the questions");
        
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
        instance.storeAnswer("A", 404, 1);
        String result = null;
        
        String check = "SELECT Student_Answer FROM answer_store WHERE Matriculation_Number=1 AND Question_ID=404";
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(check);
                ResultSet rs = ps.executeQuery();) {
            if (rs.next()) {
                result = rs.getString("Student_Answer");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        assertEquals("A", result);
    }
}
