/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

/**
 *
 * @author viivi
 */
public class Answer {
    private int matricNo;
    private int questionID;
    private String answer;
    
    public Answer() {
        
    }
    
    public void setMatricNo(int matricNo) {
        this.matricNo = matricNo;
    }
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public int getMatricNo() {
        return matricNo;
    }
    public int getQuestionID() {
        return questionID;
    }
    public String getAnswer() {
        return answer;
    }
}
