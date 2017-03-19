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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stores.QuestionBank;
import stores.Quiz;

/**
 *
 * @author erincoey
 */
public class EditQuizTest {
    
    DatabaseConnection db = new DatabaseConnection();
    EditQuiz instance = new EditQuiz();
    
    public EditQuizTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    
        //register a new fake question
    
       Connection conn = db.connectToDatabase();
       try{
       
       String query = "INSERT INTO question_bank (Question_ID,Quiz_ID,Question,A,B,C,D,Answer,Answer_Desc,Media) VALUES (172,4,'What is the number of our team?','Team 1','Team 2','Team 3','Team 4', 'B','We are team 2 for agile software development module', null);";
       PreparedStatement preparedStmt = conn.prepareStatement(query);
       preparedStmt.executeUpdate();
       
       }
        
       catch(SQLException err){
            System.out.println(err.getMessage());
       }
       try{
       conn.close();
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
       }
    
    @After
    public void tearDown() {
    
       Connection conn = db.connectToDatabase();
       try{
       
       String query = "DELETE FROM question_bank WHERE Question_ID = 172";
       PreparedStatement preparedStmt = conn.prepareStatement(query);
       preparedStmt.executeUpdate();
       }
        
       catch(SQLException err){
            System.out.println(err.getMessage());
            
        
       }
       try{
       conn.close();
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
        
    }

    /**
     * Test of AlterQuizTitle method, of class EditQuiz.
     * @throws java.sql.SQLException
     */
    @Test
    public void testAlterQuizTitle() throws SQLException {
       
        System.out.println("");
        System.out.println("Test for Alter Quiz Title");
        
        boolean state;
        
        instance.AlterQuizTitle("Who is our Scrum Master?", 172, 4);
        
        String query = "SELECT Question FROM question_bank WHERE Question_ID = 172;";
        String question = null;
        Connection conn = db.connectToDatabase();
        PreparedStatement preparedStmt = conn.prepareStatement(query);
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
                question = rs.getString("Question");
                System.out.println("Question from database: " + question);
                System.out.println("Question Expected: Who is our Scrum Master?");
              }
          
        state = "Who is our Scrum Master?".equals(question);
        
        assertTrue("Something wrong with Updating Question", state);
        
        System.out.println("Test " + state);
          }       
          conn.close();
    }
    
    @Test
    public void testAlterQuestionAnswers() throws SQLException{
        System.out.println("");
        System.out.println("Test for Alter Question Answers");
        
        boolean state, state1, state2, state3;
        
        instance.AlterQuestionAnswers('A',"Asha", 172,4);
        instance.AlterQuestionAnswers('B',"Conor", 172,4);
        instance.AlterQuestionAnswers('C',"Danielle", 172,4);
        instance.AlterQuestionAnswers('D',"Erin", 172,4);
        
        String query = "SELECT * FROM question_bank WHERE Question_ID = 172;";
        String a = null;
        String b = null;
        String c = null;
        String d = null;
        
        Connection conn = db.connectToDatabase();
        PreparedStatement preparedStmt = conn.prepareStatement(query);
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
                a = rs.getString("A");
                b = rs.getString("B");
                c = rs.getString("C");
                d = rs.getString("D");
                System.out.println("Answers from database: " + a +" " + b + " " + c + " " + d);
                System.out.println("Answers Expected: Asha, Conor, Danielle, Erin");
              }

        state = "Asha".equals(a);
        assertTrue("Something wrong with Updating Answers", state);
        
        state1 = "Conor".equals(b);
        assertTrue("Something wrong with Updating Answers", state1);
        
        state2 = "Danielle".equals(c);
        assertTrue("Something wrong with Updating Answers", state2);
        
        state3 = "Erin".equals(d);
        assertTrue("Something wrong with Updating Answers", state3);
        
        System.out.println("Test " + state);
          }     
          conn.close();
    }
    
    @Test
    public void testAlterAnswerDescription() throws SQLException {
       
        System.out.println("");
        System.out.println("Test for Alter Answer Description");
        
        boolean state;
        
        instance.AlterQuestionAnswersDescription("Erin is our scrum master", 172, 4);
        
        String query = "SELECT Answer_Desc FROM question_bank WHERE Question_ID = 172;";
        
        String answerDescription = null;
        Connection conn = db.connectToDatabase();
        PreparedStatement preparedStmt = conn.prepareStatement(query);
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
                answerDescription = rs.getString("Answer_Desc");
                System.out.println("Answer description from database: " + answerDescription);
                System.out.println("Answer description Expected: Erin is our scrum master");
              }
          
        state = "Erin is our scrum master".equals(answerDescription);
        
        assertTrue("Something wrong with Updating Answer Description", state);
        
        System.out.println("Test " + state);
          }     
          conn.close();
    
    }
    
    @Test
    public void testAlterQuestionAnswer() throws SQLException {
       
        System.out.println("");
        System.out.println("Test for Alter Question Answer");
        
        boolean state;
        
        instance.AlterQuestionAnswer("D", 172, 4);
        
        String query = "SELECT Answer FROM question_bank WHERE Question_ID = 172;";
        
        String answer = null;
        Connection conn = db.connectToDatabase();
        PreparedStatement preparedStmt = conn.prepareStatement(query);
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
                answer = rs.getString("Answer");
                System.out.println("Answer from database: " + answer);
                System.out.println("Answer Expected: D");
              }
          
        state = "D".equals(answer);
        
        assertTrue("Something wrong with Updating Answer", state);
        
        System.out.println("Test " + state);
          }       
          conn.close();
    
    }
    
    @Test
    public void testEditWholeQuiz() throws SQLException {
       
        System.out.println("");
        System.out.println("Test for Editing whole quiz");
        
        boolean state, state1, state2, state3, state4, state5, state6;
        QuestionBank questionBank = new QuestionBank();
        questionBank.setQuestionID(172); 
        questionBank.setQuestion("How many team members do we have?");
        questionBank.setA("5");
        questionBank.setB("6");
        questionBank.setC("7");
        questionBank.setD("8");
        questionBank.setCorrectAnswer("C");
        questionBank.setAnswerDesc("We have 7 team members");        
        instance.EditWholeQuiz(questionBank);
        
        String query = "SELECT * FROM question_bank WHERE Question_ID = 172;";
        
        String question = null;
        String a = null;
        String b = null;
        String c = null;
        String d = null;
        String answer = null;
        String answerDesc = null;
        
        Connection conn = db.connectToDatabase();
        PreparedStatement preparedStmt = conn.prepareStatement(query);
            
          try (ResultSet rs = preparedStmt.executeQuery()) {   
              while(rs.next()) {
                question = rs.getString("Question");
                a = rs.getString("A");
                b = rs.getString("B");
                c = rs.getString("C");
                d = rs.getString("D");
                answer = rs.getString("Answer");
                answerDesc = rs.getString("Answer_Desc");
                
                System.out.println("Answer from database: " + question + " " + a + " " + b + " " + c + " " + d + " " + answer + " " + answerDesc);
                System.out.println("Expected: How many team members do we have? 5 6 7 8 C We have 7 team members");
              }
          
        state = "How many team members do we have?".equals(question);
        state1 = "5".equals(a);
        state2 = "6".equals(b);
        state3 = "7".equals(c);
        state4 = "8".equals(d);
        state5 = "C".equals(answer);
        state6 = "We have 7 team members".equals(answerDesc);
        
        assertTrue("Something wrong with Updating Whole Quiz", state);
        
        System.out.println("Test " + state);
          }       
          conn.close();
    
    }
    
    }

    

