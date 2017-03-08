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
        //QuizModel qm = new QuizModel();
        
        
    }
    
    @After
    public void tearDown() {
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
                System.out.println(" ");
               if ((test[0] == 1) && (test[1] == 2) && (test[2] == 3) && (test[3] == 4) && (test[4] == 5)){
                   result = false;
               }
               else {
                   
                     result = true;
               }
         
               System.out.println("result: " + result);
               assertEquals("Should equal true", expected, result);
 }    
}

   
    

  
    
  


    
