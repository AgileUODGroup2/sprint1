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
            
            PreparedStatement preparedStmt2 = conn.prepareStatement(query); //2nd statement needed to delete the row after password changes from the testUpdatePassword()
            
            //Sort Inputs
            preparedStmt.setString(1, "JUnit_Test_Password");
            
            preparedStmt2.setString(1, "TestChangePassword");
            
            //Execute
            preparedStmt.executeUpdate();
            
            preparedStmt2.executeUpdate();
            
        }
        
    }
      

    /**
     * Test of login method, of class user.
     */
    @Test
    public void testLogin() {
        
        System.out.println("\nTEST: Login");
        
        boolean state = false;
        
        String result = instance.login("89898989", "JUnit_Test_Password");
        
        if("Staff".equals(result) || "Student".equals(result))
            state = true;
        
        assertTrue("Something wrong with Login/Register", state);
    }
    
     /**
     * Test update last name, of class user.
     */
     @Test
    public void testUpdateLastName(){
        
        System.out.println("\nTEST: Update Last Name");
        
        boolean state;
        
        instance.updateLastName(true, 89898989, "TestChangeSecondName");
        
        String[] testDetails = instance.getStaffDetails(89898989);
        
        state = "TestChangeSecondName".equals(testDetails[1]);
        
        assertTrue("Something wrong with Changing second name", state);
    }
    
    @Test 
    public void testUpdateFirstName() {
      
        System.out.println("\nTest: Update First Name");
        
        boolean state;
        
        instance.updateFirstName(true, 89898989, "TestChangeFirstName");
        
       String[] test = instance.getStaffDetails(89898989);
      
       state = "TestChangeFirstName".equals(test[0]);
       
       assertTrue("Update first name test passed", state);
 
    }
    
    @Test 
    public void testUpdatePassword(){
     
      System.out.println("\nTest: Update Password");
        
        boolean state;
        
        instance.updatePassword(true, 89898989, "TestChangePassword");
        
       String[] test = instance.getStaffDetails(89898989);
       state = "TestChangePassword".equals(test[2]);
       
       System.out.println("State: " + state);
       assertTrue("Update password test passed", state);
    
    }
    
    @Test
    public void testGetStudentDetails() throws SQLException{
        
        System.out.println("\nTest: Get Student Details");
        
            
        boolean firstname;
        boolean lastname;
        boolean state = false;
        
        
      instance.getStudentDetails(1);
        
       String[] test = instance.getStudentDetails(1);
  
      firstname = "John".equals(test[0]);
      lastname = "Smith".equals(test[1]);
  
 
      if ((firstname == true) && (lastname == true))
      {
          state = true;
      }
      assertTrue("true", state);
    }
  
    @Test
    public void testGetStaffDetails(){
        
         System.out.println("\nTest: Get Staff Details");
      
        boolean firstname;
        boolean lastname;
        boolean state = false;
        
        
      instance.getStaffDetails(1);
        
       String[] test = instance.getStaffDetails(1);
  
      firstname = "Rachel".equals(test[0]);
      lastname = "Menzies".equals(test[1]);
  
 
      if ((firstname == true) && (lastname == true))
      {
          state = true;
      }
      assertTrue("true", state);
    }
    
    @Test
    public void testGetStaffModules() {
        
         System.out.println("\nTest: Get Staff Modules");
        
       boolean module1;
       boolean module2; 
       boolean state = false;
        
       java.util.Vector <String> modulesTest = instance.getStaffModules(1);
       
       module1 = "AC22006".equals(modulesTest.get(0));
       module2 = "AC31009".equals(modulesTest.get(1));
      
      if ((module1 == true) && (module2 == true))
      {
          state = true;
      }
      
      System.out.println("state: " + state);
      assertTrue("true", state);
 
    }
    
     @Test
    public void testGetStudentModules() {
        
         System.out.println("\nTest: Get Staff Modules");
        
       boolean module1;
       boolean module2; 
       boolean state = false;
        
       java.util.Vector <String> modulesTest = instance.getStudentModules(2);
       
       module1 = "AC11002".equals(modulesTest.get(0));
       module2 = "AC21007".equals(modulesTest.get(1));
      
      if ((module1 == true) && (module2 == true))
      {
          state = true;
      }
      
      System.out.println("state: " + state);
      assertTrue("true", state);
 
    }
        
   
}
  
    
 
    
    
    
  
  
