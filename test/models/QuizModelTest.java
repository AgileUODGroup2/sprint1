/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import lib.database.DatabaseConnection;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stores.QuestionBank;
import stores.Quiz;
import models.QuizModel;
import stores.StudentQuiz;
import java.util.Iterator; 

/**
 *
 * @author ashawittchen
 */
public class QuizModelTest {
    
    DatabaseConnection db; 
    QuizModel qm = new QuizModel();
    
    
    public QuizModelTest() {
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
    public void tearDown() throws SQLException {
     
        //connect
        db = new DatabaseConnection();
        
        try(Connection conn = db.connectToDatabase()) {
            
            //Create and prepare query
            String query =  "DELETE FROM quiz WHERE Quiz_Name = ?;";
            
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            PreparedStatement preparedStmt2 = conn.prepareStatement(query);
            
            //Sort Inputs
            preparedStmt.setString(1, "TestQuiz");
            preparedStmt2.setString(1, "UpdateQuestionAmount");
            
            //Execute
            preparedStmt.executeUpdate();
            preparedStmt2.executeUpdate();
        }
      
    }
    
    /**
     * Test for Create a Quiz
     */
   
    @Test 
    public void testCreateQuiz() throws SQLException{
        
        boolean expected = true;
        boolean result;
        Quiz q = new Quiz();
        
        System.out.println("\nTest: Create a quiz");
        qm.createQuiz("AC31009", "Rachel", "2014-01-01", "TestQuiz", "TestStatus", "1");
        //connect
        db = new DatabaseConnection();
         java.util.LinkedList<Quiz> quizzes = new java.util.LinkedList<>();
        try(Connection conn = db.connectToDatabase()) {
            
            //Create and prepare query
            String query =  "SELECT * FROM quiz WHERE Quiz_Name = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
       
            //Sort Inputs
            preparedStmt.setString(1, "TestQuiz");
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
              
                q.setQuizID(rs.getInt("Quiz_ID"));
                System.out.println("Quiz ID: " + q.getQuizID());
                q.setQuizName(rs.getString("Quiz_Name"));
                System.out.println("Quiz Name: " + q.getQuizName());
            
                quizzes.add(q);
                
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    
     }
     
       result = "TestQuiz".equals(q.getQuizName());
       System.out.println("Result: "+ result);
       assertEquals("Should equal true", expected, result);
       
   }
   
    
   /**
    * Test for updating the question amount for a quiz 
    */ 
   /* @Test
    public void testUpdateQuestionAmmount(){ //come back to this test!!!
        
    Quiz q = new Quiz();
    
    System.out.println("\nTest: Update question amount");
    
    qm.createQuiz("AC31009", "Rachel", "2014-01-01", "UpdateQuestionAmount", "TestStatus", "1");
     
      db = new DatabaseConnection();
         java.util.LinkedList<Quiz> quizzes = new java.util.LinkedList<>();
        try(Connection conn = db.connectToDatabase()) {
            
            //Create and prepare query
            String query =  "SELECT * FROM quiz WHERE Quiz_Name = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
       
            //Sort Inputs
            preparedStmt.setString(1, "UpdateQuestionAmount");
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
              
                q.setQuizID(rs.getInt("Quiz_ID"));
                System.out.println("Quiz ID: " + q.getQuizID());
                q.setQuizName(rs.getString("Quiz_Name"));
                System.out.println("Quiz Name: " + q.getQuizName());
            
                quizzes.add(q);
                
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    
     }
        
        qm.UpdateQuestionAmmount(q.getQuizID());
        
    }
    */
    
   /*
    * Test for getting the number of questions in a quiz
    */
    @Test
    public void testGetQuizNumberOfQuestions(){
        
        boolean state;
        Quiz q = new Quiz();
        qm.getQuizNumberOfQuestions(1);
        
        System.out.println("\nTest: Get Number of Question in a quiz");    
           
        db = new DatabaseConnection();
        java.util.LinkedList<Quiz> quizzes = new java.util.LinkedList<>();
        try(Connection conn = db.connectToDatabase()) {
            
            //Create and prepare query
            String query =  "SELECT Num_Of_Questions FROM quiz WHERE Quiz_ID=?;";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
       
            //Sort Inputs
            preparedStmt.setInt(1, 1);
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
              
               q.setNumberOfQuestions(rs.getInt("Num_Of_Questions"));
                System.out.println("Number of question in quiz: " + q.getNumberOfQuestions());
                 quizzes.add(q);
                
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    
     }
     state = ( 5 == q.getNumberOfQuestions());
     System.out.println("State: "+state);
     assertTrue("true", state);
      
   }
      
   /**
    * Test of to get Quiz ID
    */ 
    @Test
    public void testGetQuizID()  {
        
        int count = 0;
        int result = 18;
        Quiz q = new Quiz();
      
        System.out.println("\nTest: get Quiz ID ");
        
        //boolean result;
        boolean expected = true;
        boolean state;
        //int result = qm.getQuizId();
         db = new DatabaseConnection();
        java.util.LinkedList<Quiz> quizzes = new java.util.LinkedList<>();
        try(Connection conn = db.connectToDatabase()) {
            
            //Create and prepare query
            String query =  "SELECT * FROM quiz ORDER BY Quiz_ID;";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
              
               q.setQuizID(rs.getInt("Quiz_ID"));
               System.out.println("Quiz ID: " + q.getQuizID());
               count++;
               quizzes.add(q);
                
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    
     }
        
      state = (result == count);         
      System.out.println(count);
      System.out.println("State: " + state);
      assertEquals("Should equal true", expected, state);
      System.out.println(" "); 
    }

   /**
    * Test for randomising questions in quizzes 
    */
   @Test 
   public void testGetQuestionsAndAnswers()throws SQLException {
       
       boolean result = false;
       boolean expected = true;
       
        System.out.println("\nTest: Randomising Questions ");
        qm.getQuestionsAndAnswers(4);
        LinkedList<QuestionBank> QBtest = qm.getQuestionsAndAnswers(4);
        int[] test = new int[5];
        int count = 0;
                if (QBtest != null) {
                    Iterator<QuestionBank> it = QBtest.iterator();
                    while(it.hasNext()) {
                        QuestionBank q = (QuestionBank) it.next();
                        System.out.println("Quiz ID: " + q.getQuizID());
                        System.out.println("Question ID: " + q.getQuestionID());
                        test[count]=q.getQuestionID();
                        count++;
                        System.out.println(q.getQuestion());
                        System.out.println(q.getA());
                        System.out.println(q.getB());
                        System.out.println(q.getC());
                        System.out.println(q.getC());
                        System.out.println(q.getAnswer());
                        System.out.println(q.getAnswerDesc());                 
                    }
                }
                System.out.println("test array" + test[0]);
                System.out.println("test array" + test[1]);
                System.out.println("test array" + test[2]);
                System.out.println("test array" + test[3]);
                System.out.println("test array" + test[4]);
                
               if ((test[0] == 1) && (test[1] == 2) && (test[2] == 3) && (test[3] == 4) && (test[4] == 5)){
                   result = false;
               }
               else {
                   
                     result = true;
               }
         
               System.out.println("result: " + result);
               assertEquals("Should equal true", expected, result);
               System.out.println(" ");
 } 

    /**
     * Test for archived quizzes
     */  
     @Test 
    public void testGetQuizzes()throws SQLException { // Specifically get archived quizzes test
        
        boolean expected;
        boolean result = true;
        int[] archivedTest = new int[2];
        int count = 0;
        boolean quizID1;
        boolean quizID2;
        
    
      System.out.println("\nTest: Get Archived Quizzes");
      qm.getQuizzes("SELECT * FROM archived WHERE Module_ID IN (select Module_ID from staff_enrolment where Staff_ID =?)",1);

      java.util.LinkedList<Quiz> quizzesTest = qm.getQuizzes("SELECT * FROM archived WHERE Module_ID IN (select Module_ID from staff_enrolment where Staff_ID =?)",1);
        if (quizzesTest != null) {
            Iterator<Quiz> it = quizzesTest.iterator();
                while(it.hasNext()) {
                    Quiz q = (Quiz) it.next();
                    System.out.println("Quiz Name: " + q.getQuizName());
                    System.out.println("Date Created: " + q.getDateCreated());
                    System.out.println("Quiz ID: " + q.getQuizID());
                    archivedTest[count]=q.getQuizID();
                    count++;
                    System.out.println("Module ID: " + q.getModuleID());          
                }
        }
     System.out.println("Count 1:  " + archivedTest[0]);      
     quizID1 = ( 8 == archivedTest[0]);
     System.out.println("quizID1: " + quizID1);
     System.out.println("Count 2:  " + archivedTest[1]);  
     quizID2 = ( 25 == archivedTest[1]);
     System.out.println("quizID2: " + quizID2);
    
     if (quizID1 == quizID2){
        expected = true;
        
     }
     else {
        expected = false; 
     }
     assertEquals("Should equal true", expected, result);
     System.out.println(" ");
    
   }
    
    /**
     * Test for getQuizDetails
     */
    @Test
    public void testGetQuizDetails(){
        System.out.println("\nTest: Get Quiz Details");
        
        boolean QuizID, ModuleID, DateCreated, QuizName, NumberOfQuestions, Status, Quiz, StaffID; 
            
        Quiz q =  qm.getQuizDetails(1);
        
        System.out.println("Quiz ID: "+q.getQuizID());
        System.out.println("Module ID: "+q.getModuleID());
        System.out.println("Date Created: "+q.getDateCreated()); 
        System.out.println("Quiz Name: "+q.getQuizName());
        System.out.println("Number of questions: "+q.getNumberOfQuestions());
        System.out.println("Status: "+q.getStatus());
        System.out.println("Quiz ID: "+q.getQuizID());
        System.out.println("Staff ID: "+q.getStaffID());
                
           
    }
}

   
    

  
    
  


    
