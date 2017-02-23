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

/**
 *
 * @author ashawittchen
 */
public class registerModelTest {
    
    DatabaseConnection db;
   
      
   
    public registerModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         registerModel rM = new registerModel();
         rM.register(111100000,"JUnit_Test_First_Name","JUnit_Test_Last_Name", "JUnit_Test_Password");
    }
    
   @After
    public void tearDown() throws SQLException {
        db = new DatabaseConnection();
        try (Connection con = db.connectToDatabase()) {
            
            //Create query
            String query = "DELETE FROM Staff WHERE Staff_ID =?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setInt(1, 110);
            
            preparedStmt.executeUpdate();
            
            con.close();
        }
        
    }
    

    /**
     * Test of register method, of class registerModel.
     */
    @Test
    public void testRegister() {
        System.out.println("\nTest: Register");
        
        boolean state = false;
       registerModel nU = new registerModel();
       nU.register(110,"Testing","testing", "testingPassword");
       if ("Staff".equals(nU)){
           state = true;
       
       
       assertTrue("Register worked and test passed", state); 
       }

       
        
    }
    
}
