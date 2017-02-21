/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;
import java.sql.*;

/**
 *
 * @author viivipursiainen
 */
public class StudentQuiz {
    int quizID;
    String moduleID;
    String staffName;
    Date dateCreated;
    String quizName;
    String status;
    int numberOfQuestions;
    private String completed;
    private int attempts;
    private int score;
    private Date dateCompleted;
    
    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }
    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
    public void setCompleted(String completed) {
        this.completed = completed;
    }
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
    
    public int getQuizID() {
        return quizID;
    }
    public String getModuleID() {
        return moduleID;
    }
    public String getStaffName() {
        return staffName;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public String getQuizName() {
        return quizName;
    }
    public String getStatus() {
        return status;
    }
    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }
    public String getCompleted() {
        return completed;
    }
    public int getAttempts() {
        return attempts;
    }
    public int getScore() {
        return score;
    }
    public Date getDateCompleted() {
        return dateCompleted;
    }
}
