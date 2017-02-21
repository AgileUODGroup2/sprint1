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
public class Quiz {
    int quizID;
    String moduleID;
    String staffName;
    Date dateCreated;
    String quizName;
    String status;
    int numberOfQuestions;
    private int averageScore;
    private int staffID;

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
    public int getAverageScore() {
        return averageScore;
    }
    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }
    public int getStaffID() {
        return staffID;
    }
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
}
