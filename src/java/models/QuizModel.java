/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.util.Collections;
import java.sql.PreparedStatement;
import lib.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.Part;
import stores.QuestionBank;
import stores.Quiz;
import stores.StudentQuiz;

/**
 *
 * @author erincoey
 */
public class QuizModel {
    
    DatabaseConnection db = new DatabaseConnection();

public void createQuiz(String moduleID, String staffName, String dateCreated, String quizName,String available, String staffID ){
    try(Connection conn = db.connectToDatabase()){
        String query = "INSERT INTO quiz (Module_ID, Staff_Name, Date_Created, Quiz_Name,Quiz_Status,Staff_ID)"+"values(?,?,?,?,?,?)";
        System.out.println("Result: "+moduleID+staffName+dateCreated+quizName);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, moduleID);
        preparedStmt.setString(2,staffName);
        preparedStmt.setString(3,dateCreated);
        preparedStmt.setString(4,quizName);
        preparedStmt.setString(5,available);
        preparedStmt.setString(6,staffID);
        preparedStmt.executeUpdate();
    }
    catch(SQLException err){
        System.out.println(err.getMessage());
    }
}

//Call this function to update Num_Of_Questions column 
public void UpdateQuestionAmmount(int quizID){
        try (Connection conn = db.connectToDatabase()) {
            //Create and prepare query
            String query =  "UPDATE Quiz " +
                            "SET Num_Of_Questions=(" +
                            "SELECT COUNT(*) FROM Question_Bank " +
                            "WHERE Quiz.Quiz_ID = Question_Bank.Quiz_ID " +
                            ")"+
                            "WHERE Quiz_ID=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, quizID);
            System.out.println(preparedStmt);

            //Execute Query
            preparedStmt.executeUpdate();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
}

public int getQuizNumberOfQuestions(int quizID)
{
    try(Connection conn = db.connectToDatabase()){
        int result =0;
        Statement st = conn.createStatement();
        String query = "SELECT Num_Of_Questions FROM quiz WHERE Quiz_ID=?;";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, quizID);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){      
        }        
        return result;
    }
    catch(SQLException err){
        System.out.println(err.getMessage());
    }
    return 0;  
}

public int getQuizId()
{
    try(Connection conn = db.connectToDatabase()){
        int result =0;
        try (Statement st = conn.createStatement()) {
            String query = "SELECT * FROM quiz ORDER BY Quiz_ID;";
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                int quizID = rs.getInt("Quiz_ID");
                System.out.println(quizID);
                
                result = quizID; 
            }
        }
            return result;
    }
    catch(SQLException err){
        System.out.println(err.getMessage());
    }
    return 0;
}

public void addQuestion(String[] array, Part[] parts) throws IOException
{
    int i = 0;
    String query = "UPDATE question_bank SET Media = ? WHERE Question_ID = ?";
    Connection conn = db.connectToDatabase();
    
    try{
        // Refactor antiquated for loop to JDK 5 enhanced for loop 
        for (String array1 : array) {
            PreparedStatement preparedStmt = conn.prepareStatement(array1, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.executeUpdate();
            
            ResultSet rs = preparedStmt.getGeneratedKeys();
            int questionID = 0;
            if(rs.next()){
                questionID = rs.getInt(1);
            }
            
            // Insert Image into DB
            preparedStmt = conn.prepareStatement(query);
            if(parts[i] == null){
                // Do nothing
            } else {
                InputStream is = parts[i].getInputStream();
                preparedStmt.setBlob(1, is);
                preparedStmt.setInt(2, questionID);
                preparedStmt.executeUpdate();
            }
            
            i++;
        }
    }
    catch(SQLException err){
        System.out.println(err.getMessage());
    } finally {
            if(conn != null){
                try{
                    conn.close();
                } catch(Exception e){
                    System.out.print(e.getMessage());
                }
            }
        }
}

public java.util.LinkedList<QuestionBank> getQuestionsAndAnswers(int quizID) throws SQLException
{
    db = new DatabaseConnection();
    java.util.LinkedList<QuestionBank> questionList= new java.util.LinkedList<>();
    String query = "SELECT * FROM question_bank WHERE Quiz_ID = ?";
    
    try (Connection conn = db.connectToDatabase();
            PreparedStatement ps = conn.prepareStatement(query))
    {
        ps.setInt(1,quizID);
        try (ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                QuestionBank questions = new QuestionBank();
                questions.setQuizID(rs.getInt("Quiz_ID"));
                questions.setQuestionID(rs.getInt("Question_ID"));
                questions.setQuestion(rs.getString("Question"));
                questions.setA(rs.getString("A"));
                questions.setB(rs.getString("B"));
                questions.setC(rs.getString("C"));
                questions.setD(rs.getString("D"));
                questions.setCorrectAnswer(rs.getString("Answer"));
                questions.setAnswerDesc(rs.getString("Answer_Desc"));
                questionList.add(questions);
                
                if(rs.getBlob("Media") != null){
                    questions.setHasMedia(true);
                }
                
            }
            
             Collections.shuffle(questionList);
            } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return questionList;
    }
    
}
public java.util.LinkedList<Quiz> getQuizzes(String query, int staffID) {
    
    db = new DatabaseConnection();
    java.util.LinkedList<Quiz> quizzes = new java.util.LinkedList<>();
    try (Connection con = db.connectToDatabase();
        PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, staffID);
        try (ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                Quiz q = new Quiz();
                q.setQuizID(rs.getInt("Quiz_ID"));
                q.setQuizName(rs.getString("Quiz_Name"));
                q.setModuleID(rs.getString("Module_ID"));
                q.setDateCreated(rs.getDate("Date_Created"));
            
                quizzes.add(q);
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return quizzes;
}

public java.util.LinkedList<Quiz> getUnfinishedQuizzes(int staffID) {
    String query = "SELECT * FROM unfinishedquiz WHERE Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getLiveQuizzes(int staffID) {
    String query = "SELECT * FROM livequiz WHERE Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getCompletedQuizzes(int staffID) {
    String query = "SELECT * FROM completedquiz WHERE Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getAllQuizzes(int staffID) {
    String query = "SELECT * FROM quiz WHERE Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getUnfinishedQuizzesMod(int staffID, String module) {
    String query = "SELECT * FROM unfinishedquiz WHERE Module_ID ='"+module+"' AND Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getLiveQuizzesMod(int staffID, String module) {
    String query = "SELECT * FROM livequiz WHERE Module_ID ='"+module+"' AND Staff_ID = ?";
    return getQuizzes(query, staffID);
}
public java.util.LinkedList<Quiz> getCompletedQuizzesMod(int staffID, String module) {
    String query = "SELECT * FROM completedquiz WHERE Module_ID ='"+module+"' AND Staff_ID = ?";
    return getQuizzes(query, staffID);
}    

public java.util.LinkedList<Quiz> getArchived(int staffID) {
    String query = "SELECT * FROM archived WHERE Module_ID IN (select Module_ID from staff_enrolment where Staff_ID =?)";
    return getQuizzes(query, staffID);
}



public Quiz getQuizDetails(int quizID) {
        Quiz quiz = new Quiz();
        String query = "SELECT * FROM quiz WHERE Quiz_ID = ?";
        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, quizID);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    
                    quiz.setQuizID(rs.getInt("Quiz_ID"));
                    quiz.setModuleID(rs.getString("Module_ID"));
                    //quiz.setStaffName(rs.getString("Staff_Name")); Not needed?
                    quiz.setDateCreated(rs.getDate("Date_Created"));
                    quiz.setQuizName(rs.getString("Quiz_Name"));
                    quiz.setNumberOfQuestions(rs.getInt("Num_Of_Questions"));
                    quiz.setStatus(rs.getString("Quiz_Status"));
                    quiz.setStaffID(rs.getInt("Staff_ID"));
                    
                }
            }


        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }

        return quiz;

    }

public java.util.LinkedList<StudentQuiz> getStudentQuizzes(String query, int matricNo) {
    
    java.util.LinkedList<StudentQuiz> quizzes = new java.util.LinkedList<>();
    try (Connection con = db.connectToDatabase();
        PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, matricNo);
        try (ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                StudentQuiz q = new StudentQuiz();
                q.setQuizID(rs.getInt("Quiz_ID"));
                q.setQuizName(rs.getString("Quiz_Name"));
                q.setModuleID(rs.getString("Module_ID"));
                q.setDateCreated(rs.getDate("Date_Created"));
                q.setStaffName(rs.getString("Staff_Name"));
                q.setNumberOfQuestions(rs.getInt("Num_Of_Questions"));
                q.setAttempts(rs.getInt("Attempted_Count"));
                q.setScore(rs.getInt("Score"));
                q.setDateCompleted(rs.getDate("Date_Completed"));
            
                quizzes.add(q);
            }
           
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return quizzes;
}

public java.util.LinkedList<StudentQuiz> getCompletedStudentQuizzes(int matricNo) {
    String query = "select * from studentcompleted where Matriculation_Number=? order by Module_ID";
    return getStudentQuizzes(query, matricNo);
}
public java.util.LinkedList<StudentQuiz> getIncompleteStudentQuizzes(int matricNo) {
    String query = "select * from studentincomplete where Matriculation_Number=? order by Module_ID";
    return getStudentQuizzes(query, matricNo);
}
public java.util.LinkedList<StudentQuiz> getPendingStudentQuizzes(int matricNo) {
    String query = "select * from studentpending where Matriculation_Number=? order by Module_ID";
    return getStudentQuizzes(query, matricNo);
}

public java.util.LinkedList<StudentQuiz> getCompletedStudentQuizzesMod(int matricNo, String module) {
    String query = "select * from studentcompleted where Module_ID = '"+module+"' AND Matriculation_Number=?";
    return getStudentQuizzes(query, matricNo);
}
public java.util.LinkedList<StudentQuiz> getIncompleteStudentQuizzesMod(int matricNo, String module) {
    String query = "select * from studentincomplete where Module_ID = '"+module+"' AND Matriculation_Number=?";
    return getStudentQuizzes(query, matricNo);
}
public java.util.LinkedList<StudentQuiz> getPendingStudentQuizzesMod(int matricNo, String module) {
    String query = "select * from studentpending where Module_ID = '"+module+"' AND Matriculation_Number=?";
    return getStudentQuizzes(query, matricNo);
}

public void makeQuizLive(int QuizID){ //change to session variables
    try(Connection conn = db.connectToDatabase()){
        
        String query = "UPDATE quiz set Quiz_Status = 'Live' WHERE Quiz_ID = ?";
        
        PreparedStatement preparedStmt = conn.prepareStatement(query);
       
        preparedStmt.setInt(1,QuizID);
        
        preparedStmt.executeUpdate();
    }
    catch(SQLException err){
            System.out.println(err.getMessage());
    }
 }

public java.util.LinkedList<StudentQuiz> orderByDate(int matricNo, String table){ 
    String query =  "SELECT * FROM "+table+" WHERE Matriculation_Number=? ORDER BY Date_Created DESC";
    return getStudentQuizzes(query, matricNo);
}

public void updateQuizStatus(int quizID, String status)
{
    try(Connection conn = db.connectToDatabase()){
        String query = "UPDATE quiz set Quiz_Status = ? WHERE Quiz_ID = ?";
        
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        
        preparedStmt.setString(1,status);
        preparedStmt.setInt(2,quizID);
        
        preparedStmt.executeUpdate();
    }
    catch(SQLException err){
            System.out.println(err.getMessage());
    }
}

public void updateStudentQuizStatus(int matricNo, int quizID, String status) {
    String query = "UPDATE student_quiz set Has_Completed = ? WHERE Quiz_ID = ? and Matriculation_Number=?";
    
    try(Connection conn = db.connectToDatabase();
            PreparedStatement ps = conn.prepareStatement(query);){
        
        ps.setString(1,status);
        ps.setInt(2,quizID);
        ps.setInt(3,matricNo);
        
        ps.executeUpdate();
    }
    catch(SQLException err){
            System.out.println(err.getMessage());
    }
}

public void addNewAttempt(int matricNo, int quizID, int score, Date date) {
    
    String query = null;
    if (check(matricNo,quizID).equals("Completed")) {
        query = "UPDATE student_quiz SET Score=?, Date_Completed=?, Attempted_Count=Attempted_Count+1 WHERE Matriculation_Number=? AND Quiz_ID=?";
    } else {
        query = "UPDATE student_quiz SET Score=?, Date_Completed=?, Attempted_Count=Attempted_Count+1, Has_Completed='Completed' WHERE Matriculation_Number=? AND Quiz_ID=?";
    }
    
    try (Connection conn = db.connectToDatabase();
            PreparedStatement ps = conn.prepareStatement(query);) {
        ps.setInt(1, score);
        ps.setDate(2, date);
        ps.setInt(3,matricNo);
        ps.setInt(4, quizID);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

private String check(int matricNo, int quizID) {
    String check = "SELECT Has_Completed from student_quiz WHERE Matriculation_Number=? AND Quiz_ID=?";
    
    try (Connection con = db.connectToDatabase();
            PreparedStatement ps = con.prepareStatement(check);) {
        ps.setInt(1,matricNo);
        ps.setInt(2,quizID);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Has_Completed");
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return null;
}
 
}

