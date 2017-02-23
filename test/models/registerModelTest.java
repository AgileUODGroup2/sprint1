/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
    public void tearDown() {
    }

    /**
     * Test of register method, of class registerModel.
     */
    @Test
    public void testRegister() {
        System.out.println("register: Test");
        
        boolean state = false;
        registerModel nU = new registerModel();
       nU.register(105,"Testing","testing", "testingPassword");
        
       

       
        
    }
    
}
