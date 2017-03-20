/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
import stores.Result;
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
    
    @Test
    public void testGetResultsForDates() {
        java.sql.Date date1 = java.sql.Date.valueOf("2017-11-01");
        java.sql.Date date2 = java.sql.Date.valueOf("2017-11-03");
        java.util.LinkedList<StudentResult> answers = instance.getResultsForDates(date1, date2, 14);
        
        assertEquals(1, answers.size());
        assertEquals(5, answers.getFirst().getMatriculationNumber());
        assertEquals(14, answers.getFirst().getQuizID());
    }
    
    @Test
    public void testGetQuizResult() {
        java.util.LinkedList<Result> answers = instance.getQuizResult(1, 4);
        
        assertEquals(1, answers.getLast().getAttempts());
        assertEquals(4, answers.getFirst().getQuizID());
        assertNotNull(answers.getFirst().getDate());
        assertNotNull(answers.getFirst().getScore());
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
        
        
        quizResult = instance.getQuizResults(4);
        
        StudentResult result = quizResult.get(1);
        
        boolean finalResult = false;
        boolean name, matriculationNo, hasCompleted, attempts, score, dateCompleted;
        
        name = "John Smith".equals(result.getStudentName()); 
        System.out.println("Name: " + name);
        System.out.println(result.getStudentName());

        matriculationNo = (1 == result.getMatriculationNumber());
        System.out.println("Matriculation Number: " + matriculationNo);
        
        hasCompleted = (result.getHasCompleted());
        System.out.println("HasCompleted?: " + hasCompleted);
        
        attempts = (6 == result.getAttemptedCount());
        System.out.println("Attempts: " + attempts);
        
        score = (0 == result.getScore());
        System.out.println("Score: " + score);
        
        java.sql.Date date = java.sql.Date.valueOf("2017-03-20");
        
        java.sql.Date resultDate = result.getDate();
        
        dateCompleted = (date.equals(resultDate));
        System.out.println("Date: " + dateCompleted);
        
       if ((name == true) && (matriculationNo == true) && (hasCompleted == true) && (attempts == true) && (score == true) && (dateCompleted == true)) {
           finalResult = true;   
       }
       
     assertTrue(finalResult);
        
    }
}

