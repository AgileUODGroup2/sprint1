/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import lib.database.DatabaseConnection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author erincoey
 */
public class AttemptModelTest {
    DatabaseConnection db = new DatabaseConnection();
    AttemptModel am = new AttemptModel();
   
    
    public AttemptModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    
    
       
    }
    
    @After
    public void tearDown() {
        
        Connection conn = db.connectToDatabase();
        String query = "DELETE FROM attempts WHERE Quiz_ID = 15 AND Matriculation_Number = 16;";
        
        try(PreparedStatement preparedStmt = conn.prepareStatement(query);)
        {
        preparedStmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    @Test
    public void testAddAttempts() {
        
        System.out.println("Add Attempts Test");
        
        boolean state = false;
        boolean expected = true;
        int quizID = 0;
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        am.addNewAttempt(16,15,date,75);
        
        try(Connection conn  = db.connectToDatabase();)
        {
            String query = "SELECT * FROM attempts WHERE Quiz_ID=? AND Matriculation_Number=?;";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,15);
            preparedStmt.setInt(2,16);
            
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                System.out.println(rs.getInt("Matriculation_Number"));
                 quizID = rs.getInt("Quiz_ID");
                 System.out.println(rs.getInt("Quiz_ID"));
                  System.out.println(rs.getDate("Date_Completed"));
                   System.out.println(rs.getInt("Score"));
            }
            
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        state = (15 == quizID);
        System.out.println("Test" + state);
        assertEquals("Should equal true", expected, state );
        
        
        
        
    }   
}
