/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import lib.database.DatabaseConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import models.registerModel;

/**
 *
 * @author glenmorrison
 */
public class userTest {
    DatabaseConnection db;
    user instance = new user();
    
    public userTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    //This is run before the test
    @Before
    public void setUp() {
        
        //Register a new test/fake user
        registerModel model = new registerModel();
        model.register(89898989, "JUnit_Test_Name", "JUnit_Test_LastName", "JUnit_Test_Password");
        
    }
    
    //This is run after the test
    @After
    public void tearDown() throws SQLException {
        
        //connect
        db = new DatabaseConnection();
        
        try(Connection conn = db.connectToDatabase()) {
            
            //Create and prepare query
            String query =  "DELETE FROM Staff WHERE Password=?;";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            //Sort Inputs
            preparedStmt.setString(1, "JUnit_Test_Password");
            
            //Execute
            preparedStmt.executeUpdate();
            
        }
        
    }

    /**
     * Test of login method, of class user.
     */
    @Test
    public void testLogin() {
        
        System.out.println("TEST: Login");
        
        boolean state = false;
        
        String result = instance.login("89898989", "JUnit_Test_Password");
        
        if("Staff".equals(result) || "Student".equals(result))
            state = true;
        
        assertTrue("Something wrong with Login/Register", state);
    }
    
    public void testUpdateFirstName(){
        
        System.out.println("TEST: Update Last Name");
        
        boolean state;
        
        instance.updateLastName(true, 89898989, "TestChangeSecondName");
        
        String[] testDetails = instance.getStaffDetails(89898989);
                
        state = "TestChangeSecondName".equals(testDetails[1]);
        
        assertTrue("Something wrong with Changing second name", state);
    }
    
}