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
    boolean available;
    boolean completed;
    int numberOfQuestions;
    
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
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
    
    public int getQuizID() {
        return quizID;
    }
    public String setModuleID() {
        return moduleID;
    }
    public String setStaffName() {
        return staffName;
    }
    public Date setDateCreated() {
        return dateCreated;
    }
    public String setQuizName() {
        return quizName;
    }
    public boolean setAvailable() {
        return available;
    }
    public boolean setCompleted() {
        return completed;
    }
    public int setNumberOfQuestions() {
        return numberOfQuestions;
    }
}
