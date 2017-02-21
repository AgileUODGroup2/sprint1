/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.sql.Date;

/**
 *
 * @author conormckillop
 */
public class StudentResult {
    
    private String  studentName;
    private int     matriculationNumber;
    private int     quizID;
    private boolean hasCompleted;
    private int     attemptedCount;
    private int     score;
    private Date    date;
    
    //SET
    public void setStudentName(String studentName){
        this.studentName=studentName;
    }
    public void setMatriculationNumber(int matriculationNumber){
        this.matriculationNumber=matriculationNumber;
    }
    public void setHasCompleted(boolean hasCompleted){
        this.hasCompleted=hasCompleted;
    }
    public void setAttemptedCount(int attemptedCount){
        this.attemptedCount=attemptedCount;
    }
    public void setScore(int score){
        this.score=score;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }
    
    //GET
    
    public String getStudentName() {
        return studentName;
    }
    public int getMatriculationNumber() {
        return matriculationNumber;
    }
    public int getQuizID() {
        return quizID;
    }
    public boolean getHasCompleted() {
        return hasCompleted;
    }
    public int getAttemptedCount() {
        return attemptedCount;
    }
    public int getScore() {
        return score;
    }
    public Date getDate() {
        return date;
    }
    
}
