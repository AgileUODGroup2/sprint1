/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import stores.StudentResult;

/**
 *
 * @author conormckillop
 */
public class ResultModelTest {
    
    int quizID = 4;
    int expResult;
    ResultModel instance = new ResultModel();
    
    public ResultModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        LinkedList<StudentResult> quizResult = instance.getQuizResults(4);
        LinkedList<Integer> result = new LinkedList();
        double average = 0;
        
        Iterator<StudentResult> iterator = quizResult.iterator();
        while(iterator.hasNext()){
            StudentResult currentResult = iterator.next(); 
            result.add(currentResult.getScore());
        }
        
        ListIterator<Integer> intIterator = result.listIterator(result.size());
        while(intIterator.hasPrevious()){
            double sum = intIterator.previous();
            average += sum/result.size();
        }
        DecimalFormat df = new DecimalFormat("#.####");
        System.out.println(average);
        average = Double.valueOf(df.format(average));
        System.out.println(average);
        expResult = (int)average;
        System.out.println(expResult);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getQuizAverage method, of class ResultModel.
     */
    @Test
    public void testGetQuizAverage() {
        System.out.println("getQuizAverage");

        int result = instance.getQuizAverage(quizID);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetQuizResults() {
        System.out.println("getQuizResults");
        
        java.util.LinkedList<StudentResult> quizResult = new java.util.LinkedList<>();
        int numberOfStudents = 4;
        
        quizResult = instance.getQuizResults(quizID);
        
        StudentResult result = quizResult.get(2);
        
        boolean finalResult = false;
        boolean name, matriculationNo, hasCompleted, attempts, score, dateCompleted;
        
        name = "John Smith".equals(result.getStudentName()); 
        System.out.println("Name: " + name);
        System.out.println(result.getStudentName());

        matriculationNo = (1 == result.getMatriculationNumber());
        System.out.println("Matriculation Number: " + matriculationNo);
        
        hasCompleted = (result.getHasCompleted());
        System.out.println("HasCompleted?: " + hasCompleted);
        
        attempts = (46 == result.getAttemptedCount());
        System.out.println("Attempts: " + attempts);
        
        score = (0 == result.getScore());
        System.out.println("Score: " + score);
        
        String dateString = "03/19/2017";
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
        java.util.Date date = null;
        
        try {
            date = (java.util.Date) df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        java.util.Date resultDate = result.getDate();
        
        dateCompleted = (date.equals(resultDate));
        System.out.println("Date: " + dateCompleted);
        
       if ((name == true) && (matriculationNo == true) && (hasCompleted == true) && (attempts == true) && (score == true) && (dateCompleted == true)) {
           finalResult = true;   
       }
       
     assertTrue(finalResult);
        
    }
}
