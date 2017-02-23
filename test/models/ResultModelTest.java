/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
}
