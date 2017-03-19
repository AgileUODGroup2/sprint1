/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            //PreparedStatement preparedStmt2 = conn.prepareStatement(query);
            
            //Sort Inputs
           preparedStmt.setString(1, "TestQuiz");
           // preparedStmt2.setString(1, "UpdateQuestionAmount");
            
            //Execute
           preparedStmt.executeUpdate();
            //preparedStmt2.executeUpdate();
           conn.close();
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
             conn.close();
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    
     }
     
       result = "TestQuiz".equals(q.getQuizName());
       System.out.println("Result: "+ result);
       assertEquals("Should equal true", expected, result);
       
   }
    
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
          conn.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    
     }
     state = ( 5 == q.getNumberOfQuestions());
     System.out.println("State: "+state);
     assertTrue("true", state);
      
   }
      
   /**
    * Test to get Quiz ID
    */ 
    @Test
    public void testGetQuizID()  {
        
        int count = 0;
        int result = 17;
        Quiz q = new Quiz();
      
        System.out.println("\nTest: get Quiz ID ");
        
        //boolean result;
        boolean expected = true;
        boolean state;
       
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
        conn.close();  
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
    * Test for Get questions and answers 
    */
   @Test 
   public void testGetQuestionsAndAnswers()throws SQLException {
       
       boolean result;
       boolean expected = true;
       
        System.out.println("\nTest: Get Questions and Answsers ");
        qm.getQuestionsAndAnswers(4);
        LinkedList<QuestionBank> QBtest = qm.getQuestionsAndAnswers(4);
        int[] test = new int[6];
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
                System.out.println("test array" + test[5]);
                
                
               if ((test[0] == 1) && (test[1] == 2) && (test[2] == 3) && (test[3] == 4) && (test[4] == 5) && (test[5] == 6)){
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
    * Test for GetQuizzes
    */
   @Test
   public void testGetQuizzes(){
       
        boolean expected = true;
        boolean unfinResult, liveResult, completedResult, allResult, archivedResult;
        boolean quiz1, quiz2, quiz3, quiz4,quiz5,quiz6, quiz7;
        int [] unfinishedQuizzes = new int[2];
        int [] liveQuiz = new int[5];
        int [] completedQuiz = new int[1];
        int [] allQuizzes = new int[7];
        int [] archivedQuiz = new int[1];
        int ccount = 0, lcount = 0, uncount = 0, allcount = 0, archcount = 0;
        boolean state; 
         
        System.out.println("\nTest: Get Quizzes");
      
        //completed Quiz
      java.util.LinkedList<Quiz> unfinished = qm.getQuizzes("SELECT * FROM unfinishedquiz WHERE Staff_ID = ?",1);
      if (unfinished != null){
          Iterator<Quiz> it = unfinished.iterator();
          while(it.hasNext()){
              Quiz un = (Quiz) it.next();
              unfinishedQuizzes[uncount] = un.getQuizID();
              System.out.println("Unfinished Quiz"+uncount + un.getQuizID());
              uncount++; 
          }
      }
      
       if (uncount == 2){
           
           unfinResult = true;
       }
       else{
           unfinResult = false;
       }
       
       System.out.println("unfinResult: " + unfinResult);
      
       //Live Quizzes
       java.util.LinkedList<Quiz> Live = qm.getQuizzes("SELECT * FROM livequiz WHERE Staff_ID = ?",3);
      if (Live != null){
          Iterator<Quiz> it = Live.iterator();
          while(it.hasNext()){
              Quiz lQ = (Quiz) it.next();
             liveQuiz[lcount] = lQ.getQuizID();
              System.out.println("Live Quiz"+lcount + lQ.getQuizID());
              lcount++; 
          }
      }
       System.out.println("live count 0: " + liveQuiz[0]);
       quiz1 = (1 == liveQuiz[0]);
        System.out.println(quiz1);
       System.out.println("live count 1: " + liveQuiz[1]); 
       quiz2 = (3 == liveQuiz[1]);
        System.out.println(quiz2);
         System.out.println("live count 2: " + liveQuiz[2]); 
       quiz3 = (4 == liveQuiz[2]);
        System.out.println(quiz3);
       System.out.println("live count 3: " + liveQuiz[3]); 
       quiz4 = (13 == liveQuiz[3]);
        System.out.println(quiz4);
         System.out.println("live count 4: " + liveQuiz[4]); 
       quiz5 = (15 == liveQuiz[4]);
        System.out.println(quiz5);
  
       
       if ((quiz1 == true) && (quiz2 == true)){
           
           liveResult = true;
       }
       else{
           liveResult = false;
       }
        System.out.println("liveResult: " + liveResult);
      
       // Completed Quizzes 
     java.util.LinkedList<Quiz> completed = qm.getQuizzes("SELECT * FROM completedquiz WHERE Staff_ID = ?",1);
      if (completed != null){
          Iterator<Quiz> it = completed.iterator();
          while(it.hasNext()){
               Quiz cQ = (Quiz) it.next();
              completedQuiz[ccount] = cQ.getQuizID();
              System.out.println("Completed Quiz: "+ccount + " " + cQ.getQuizID());
              ccount++; 
          }
      }
       System.out.println("Completed count 0: " + completedQuiz[0]);
       quiz1 = (291 == completedQuiz[0]);
        System.out.println(quiz1);
  
       if (quiz1 != true){
           completedResult = false;
       }
       else{

           completedResult = true;
       }
        System.out.println("CompletedResult: " + completedResult);
     
        
     //get all quizzes
     java.util.LinkedList<Quiz> allQuiz = qm.getQuizzes("SELECT * FROM quiz WHERE Staff_ID = ?",1);
      if (allQuiz != null){
          Iterator<Quiz> it = allQuiz.iterator();
          while(it.hasNext()){
               Quiz aQ = (Quiz) it.next();
              allQuizzes[allcount] = aQ.getQuizID();
              System.out.println("All Quiz: "+allcount + " " + aQ.getQuizID());
              allcount++; 
          }
      }
       System.out.println("All count 0: " + allQuizzes[0]);
       quiz1 = (5 == allQuizzes[0]);
        System.out.println(quiz1);
        
        System.out.println("All count 1: " + allQuizzes[1]);
       quiz2 = (8 == allQuizzes[1]);
        System.out.println(quiz2);
        
        System.out.println("All count 2: " + allQuizzes[2]);
       quiz3 = (9 == allQuizzes[2]);
        System.out.println(quiz3);
        
       System.out.println("All count 3: " + allQuizzes[3]);
       quiz4 = (10 == allQuizzes[3]);
        System.out.println(quiz3);
        
       System.out.println("All count 4: " + allQuizzes[4]);
       quiz5 = (13 == allQuizzes[4]);
       System.out.println(quiz5);
        
       System.out.println("All count 5: " + allQuizzes[5]);
       quiz6 = (291 == allQuizzes[5]);
       System.out.println(quiz6);
       
       System.out.println("All count 6: " + allQuizzes[6]);
       quiz7 = (332 == allQuizzes[6]);
       System.out.println(quiz7);
  
       allResult = ((quiz1 == true) && (quiz2 == true) && (quiz3 == true) && (quiz4 == true) && (quiz5 == true) && (quiz6 == true) && (quiz7 == true));
        System.out.println("AllResult: " + allResult);  
        
  //get Archived quizzes
      java.util.LinkedList<Quiz> archivedQuizzes = qm.getQuizzes("SELECT * FROM archived WHERE Module_ID IN (select Module_ID from staff_enrolment where Staff_ID =?)",1);
        if (archivedQuizzes != null) {
            Iterator<Quiz> it = archivedQuizzes.iterator();
                while(it.hasNext()) {
                    Quiz archQ = (Quiz) it.next();
                    System.out.println("Quiz Name: " + archQ.getQuizName());
                    System.out.println("Date Created: " + archQ.getDateCreated());
                    System.out.println("Quiz ID: " + archQ.getQuizID());
                    archivedQuiz[archcount]=archQ.getQuizID();
                    archcount++;
                    System.out.println("Module ID: " + archQ.getModuleID());          
                }
        }
     System.out.println("Count 1:  " + archivedQuiz[0]);      
     quiz1 = ( 8 == archivedQuiz[0]);
         
   if (quiz1 = true){
        archivedResult = true;
        
     }
     else {
        archivedResult = false; 
     }
  
     state = ((unfinResult == true) && (liveResult == true) && (completedResult == true)&& (allResult == true) && (archivedResult == true));  
     assertEquals("Should equal true", expected, state);
     System.out.println(" ");
       
 }

   /**
    * Test for GetQuizzes Mod 
    */
   @Test
   public void testGetQuizzesMod(){
       
        boolean expected = true;
        boolean unfinModResult, liveModResult, completedModResult;
        boolean quiz1, quiz2;
        int [] unfinishedModQuizzes = new int[2];
        int [] liveModQuiz = new int[2];
        int [] completedModQuiz = new int[1];
        int ccount = 0, lcount = 0, uncount = 0;
        boolean state;
        String moduleUn = "AC31009";
        String moduleL = "AC22006";
        String moduleC = "AC31009";
         
        System.out.println("\nTest: Get Quizzes Mod");
      
        //completed Student Quiz
      java.util.LinkedList<Quiz> unfinishedMod = qm.getQuizzes("SELECT * FROM unfinishedquiz WHERE Module_ID ='"+moduleUn+"' AND Staff_ID = ?",1);
      if (unfinishedMod != null){
          Iterator<Quiz> it = unfinishedMod.iterator();
          while(it.hasNext()){
              Quiz unMod = (Quiz) it.next();
              unfinishedModQuizzes[uncount] = unMod.getQuizID();
              System.out.println("Unfinished Quiz"+uncount + unMod.getQuizID());
              uncount++; 
          }
      }
  
       
       if (uncount == 2){
           
           unfinModResult = true;
       }
       else{
           unfinModResult = false;
       }
       
       System.out.println("unfinModResult: " + unfinModResult);
      
       //Live Quizzes Mod
       java.util.LinkedList<Quiz> liveMod = qm.getQuizzes("SELECT * FROM livequiz WHERE Module_ID ='"+moduleL+"' AND Staff_ID = ?",1);
      if (liveMod != null){
          Iterator<Quiz> it = liveMod.iterator();
          while(it.hasNext()){
              Quiz lMod = (Quiz) it.next();
             liveModQuiz[lcount] = lMod.getQuizID();
              System.out.println("Live Mod Quiz"+lcount + lMod.getQuizID());
              lcount++; 
          }
      }
       System.out.println("live count 0: " + liveModQuiz[0]);
       quiz1 = (9 == liveModQuiz[0]);
        System.out.println(quiz1);
       System.out.println("live count 1: " + liveModQuiz[1]); 
       quiz2 = (10 == liveModQuiz[1]);
        System.out.println(quiz2);
         
       
       if ((quiz1 == true) && (quiz2 == true)){
           
           liveModResult = true;
       }
       else{
           liveModResult = false;
       }
        System.out.println("liveModResult: " + liveModResult);
      
       // Completed Mod Quizzes 
     java.util.LinkedList<Quiz> completedMod = qm.getQuizzes("SELECT * FROM completedquiz WHERE Module_ID ='"+moduleC+"' AND Staff_ID = ?",1);
      if (completedMod != null){
          Iterator<Quiz> it = completedMod.iterator();
          while(it.hasNext()){
               Quiz cMod = (Quiz) it.next();
              completedModQuiz[ccount] = cMod.getQuizID();
              System.out.println("Completed Mod Quiz: "+ccount + " " + cMod.getQuizID());
              ccount++; 
          }
      }
       System.out.println("Completed count 0: " + completedModQuiz[0]);
       quiz1 = (291 == completedModQuiz[0]);
        System.out.println(quiz1);
  
       if (quiz1 != true){
           completedModResult = false;
       }
       else{

           completedModResult = true;
       }
        System.out.println("CompletedResult: " + completedModResult);
     
     state = ((unfinModResult == true) && (liveModResult == true) && (completedModResult == true));  
     assertEquals("Should equal true", expected, state);
     System.out.println(" ");
        
   }
   
    /**
     * Test for getQuizDetails
     */
    @Test
    public void testGetQuizDetails(){
        System.out.println("\nTest: Get Quiz Details");
        
        boolean QuizID, ModuleID, DateCreated, QuizName, NumberOfQuestions, QuizStatus, StaffID; 
        boolean expected = true;
        boolean result;
            
        Quiz q =  qm.getQuizDetails(1);
        
        QuizID = (1 == q.getQuizID()); 
        System.out.println("QuizID: " + QuizID);

        ModuleID = "AC31007".equals(q.getModuleID());
        System.out.println("ModuleID: " + ModuleID);
        
        QuizName = "Week 1 Revision".equals(q.getQuizName());
        System.out.println("Quiz Name: " + QuizName);
        
        NumberOfQuestions = (5 == q.getNumberOfQuestions());
        System.out.println("Number of questions: " + NumberOfQuestions);
        
        QuizStatus = "Live".equals(q.getStatus());
        System.out.println("Status: " + QuizStatus);
        
        StaffID = (3 == q.getStaffID());
        System.out.println("StaffID: " + StaffID);
        
       if ((QuizID == true) && (ModuleID == true) && (QuizName == true) && (NumberOfQuestions == true) && (QuizStatus == true) && (StaffID == true)) {
           
           result = true;   
       }
       else 
       {
           result = false; 
       }
       
     assertEquals("Should equal true", expected, result);
     System.out.println(" ");
       
           
    }
    
    /**
     * Test for getStudentQuizzes
     */
    @Test 
    public void testGetStudentQuizzes(){
        
        boolean expected = true;
        boolean compResult, incompResult, pendResult;
        boolean quiz1, quiz2, quiz3, quiz4;
        int [] completedQuiz = new int[4];
        int [] incompleteQuiz = new int[2];
        int [] pendingQuiz = new int[4];
        int ccount = 0;
        int incount = 0;
        int pcount = 0;
        boolean state; 
         
        System.out.println("\nTest: Get Student Quizzes");
      
        //completed Student Quiz
      java.util.LinkedList<StudentQuiz> StudentCompleted = qm.getStudentQuizzes("select * from studentcompleted where Matriculation_Number=? order by Module_ID",1);
      if (StudentCompleted != null){
          Iterator<StudentQuiz> it = StudentCompleted.iterator();
          while(it.hasNext()){
              StudentQuiz cQ = (StudentQuiz) it.next();
              completedQuiz[ccount] = cQ.getQuizID();
              System.out.println("completed Quiz"+ccount + cQ.getQuizID());
              ccount++; 
          }
      }
  
       
       if (ccount == 4){
           
           compResult = true;
       }
       else{
           compResult = false;
       }
       
       System.out.println("compResult: " + compResult);
      
       //Incompleted Student Quiz
       java.util.LinkedList<StudentQuiz> StudentIncomplete = qm.getStudentQuizzes("select * from studentincomplete where Matriculation_Number=? order by Module_ID",9);
      if (StudentIncomplete != null){
          Iterator<StudentQuiz> it = StudentIncomplete.iterator();
          while(it.hasNext()){
              StudentQuiz iQ = (StudentQuiz) it.next();
              incompleteQuiz[incount] = iQ.getQuizID();
              System.out.println("Incomplete Quiz"+incount + iQ.getQuizID());
              incount++; 
          }
      }
       System.out.println("Incomplete count 0: " + incompleteQuiz[0]);
       quiz1 = (14 == incompleteQuiz[0]);
        System.out.println(quiz1);
       System.out.println("Incompleted count 1: " + incompleteQuiz[1]); 
       quiz2 = (15 == incompleteQuiz[1]);
        System.out.println(quiz2);
  
       
       if ((quiz1 == true) && (quiz2 == true)){
           
           incompResult = true;
       }
       else{
           incompResult = false;
       }
        System.out.println("incompResult: " + incompResult);
      
       // Pending student Quiz 
     java.util.LinkedList<StudentQuiz> StudentPending = qm.getStudentQuizzes("select * from studentpending where Matriculation_Number=? order by Module_ID",13);
      if (StudentPending != null){
          Iterator<StudentQuiz> it = StudentPending.iterator();
          while(it.hasNext()){
              StudentQuiz pQ = (StudentQuiz) it.next();
              pendingQuiz[pcount] = pQ.getQuizID();
              System.out.println("pending QuizP: "+pcount + " " + pQ.getQuizID());
              pcount++; 
          }
      }
       
  
       if (pcount != 4){
           pendResult = false;
       }
       else{

           pendResult = true;
       }
        System.out.println("pendResult: " + pendResult);
        
       if ((compResult == true) && (incompResult == true) && (pendResult == true)){
           state = true;
       }
       else {
           state = false; 
       }
       
       
       
     assertEquals("Should equal true", expected, state);
     System.out.println(" ");
       
    }
    
    /**
     * Test for get student quizzes mod  
     */
    @Test 
    public void testGetStudentQuizzesMod(){
        
        boolean expected = true;
        boolean compResult, incompResult, pendResult;
        boolean quiz1, quiz2, quiz3, quiz4;
        int [] completedQuizMod = new int[1];
        int [] incompleteQuizMod = new int[1];
        int [] pendingQuizMod = new int[4];
        int ccount = 0;
        int incount = 0;
        int pcount = 0;
        boolean state;
       String modulec = "AC32007";
       String modulei = "AC31007";
       String modulep = "AC31007";
         
        System.out.println("\nTest: Get Student Quizzes Mod ");
      
        
        
        //completed Student Quiz Mod
      java.util.LinkedList<StudentQuiz> StudentCompletedMod = qm.getStudentQuizzes("select * from studentcompleted where Module_ID = '"+modulec+"' AND Matriculation_Number=?",1);
      if (StudentCompletedMod != null){
          Iterator<StudentQuiz> it = StudentCompletedMod.iterator();
          while(it.hasNext()){
              StudentQuiz cQ = (StudentQuiz) it.next();
              completedQuizMod[ccount] = cQ.getQuizID();
              System.out.println("completed Quiz"+ccount + cQ.getQuizID());
              ccount++; 
          }
      }
  
       
       if (ccount == 1){
           
           compResult = true;
       }
       else{
           compResult = false;
       }
       
       System.out.println("compResult: " + compResult);
      
       //Incompleted Student Quiz Mod
       java.util.LinkedList<StudentQuiz> StudentIncompleteMod = qm.getStudentQuizzes("select * from studentincomplete where Module_ID = '"+modulei+"' AND Matriculation_Number=?",9);
      if (StudentIncompleteMod != null){
          Iterator<StudentQuiz> it = StudentIncompleteMod.iterator();
          while(it.hasNext()){
              StudentQuiz iQ = (StudentQuiz) it.next();
              incompleteQuizMod[incount] = iQ.getQuizID();
              System.out.println("Incomplete Quiz"+incount + iQ.getQuizID());
              incount++; 
          }
      }
       System.out.println("Incomplete count 0: " + incompleteQuizMod[0]);
      // quiz1 = (14 == incompleteQuiz[0]);
       // System.out.println(quiz1);
      // quiz2 = (15 == incompleteQuiz[1]);
      //  System.out.println(quiz2);
  
       
       if (incount == 1){
           
           incompResult = true;
       }
       else{
           incompResult = false;
       }
        System.out.println("incompResult: " + incompResult);
      
       // Pending student Quiz Mod
     java.util.LinkedList<StudentQuiz> StudentPendingMod = qm.getStudentQuizzes("select * from studentpending where Module_ID = '"+modulep+"' AND Matriculation_Number=?",13);
      if (StudentPendingMod != null){
          Iterator<StudentQuiz> it = StudentPendingMod.iterator();
          while(it.hasNext()){
              StudentQuiz pQ = (StudentQuiz) it.next();
              pendingQuizMod[pcount] = pQ.getQuizID();
              System.out.println("pending QuizP: "+pcount + " " + pQ.getQuizID());
              pcount++; 
          }
      }
       
  
       if (pcount != 3){
           pendResult = false;
       }
       else{

           pendResult = true;
       }
        System.out.println("pendResult: " + pendResult);
        
       if ((compResult == true) && (incompResult == true) && (pendResult == true)){
           state = true;
       }
       else {
           state = false; 
       }
       
       
       
     assertEquals("Should equal true", expected, state);
     System.out.println(" ");
       
    }
    
    @Test
    public void testAddNewAttempt(){
        
        // Code to be used later
        /**int matricNo = 1181180;
        int quizID = 15;
        int score = 100;
        Date date;
        
        String dateString = "2017/03/06";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        
        try {
            date = (Date) df.parse(dateString);
            String newDateString = df.format(dateString);
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        qm.addNewAttempt(matricNo, quizID, score, date);**/
        
        
        
    }
    
    @Test
    public void testAddNewAttempt(){
        
        System.out.println("addNewAttempt");
        
        /**int matricNo = 1181180;
        int quizID = 15;
        int score = 100;
        Date date;
        
        String dateString = "2017/03/06";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        
        try {
            date = (Date) df.parse(dateString);
            String newDateString = df.format(dateString);
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        qm.addNewAttempt(matricNo, quizID, score, date);**/
        
    }
}

