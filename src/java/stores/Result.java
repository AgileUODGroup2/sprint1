/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.sql.Date;

/**
 *
 * @author viivipursiainen
 */
public class Result {
    private int matricNo;
    private int quizID;
    private boolean completed;
    private int attempts;
    private int score;
    private Date date;
    
    public void setMatricNo(int matricNo) {
        this.matricNo = matricNo;
    }
    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getMatricNo() {
        return matricNo;
    }
    public int setQuizID() {
        return quizID;
    }
    public boolean setCompleted() {
        return completed;
    }
    public int setAttempts() {
        return attempts;
    }
    public Date getDate() {
        return date;
    }
}
