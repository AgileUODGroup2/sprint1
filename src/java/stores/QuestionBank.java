/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import javax.servlet.http.Part;

/**
 *
 * @author erincoey
 */
public class QuestionBank {
    int questionID;
    int quizID;
    String question;
    String a;
    String b;
    String c;
    String d;
    String answer;
    String answerDesc;
    Part media;
    
    public void QuestionBank(){
        
    }
    
    public String getQuery(){
        String query = "INSERT INTO question_bank (Quiz_ID, Question, A, B, C, D, Answer, Answer_Desc) VALUES ("+quizID +",'" +question + "','"+ a + "','"+ b + "','" + c+ "','" + d+ "','" + answer+ "','" + answerDesc+"');";
        return query;
    }
    public void setQuestionID(int questionID){
        this.questionID=questionID;
    }
    public int getQuestionID(){
        return questionID;
    }
    
    public void setQuizID(int quizID){
        this.quizID=quizID;
    }
    
    public int getQuizID()
    {
        return quizID;
    }
    
    public void setQuestion(String question){
        this.question=question;
    }
    
    public String getQuestion()
    {
        return question;
    }
    
    public void setA(String a){
        this.a=a;
    }
    
    public String getA()
    {
        return a;
    }
    
    public void setB(String b){
        this.b=b;
    }
    
    public String getB()
    {
        return b;
    }
    
    public void setC(String c){
        this.c=c;
    }
    
    public String getC()
    {
        return c;
    }
    
    public void setD(String d){
        this.d=d;
    }
    
    public String getD()
    {
        return d;
    }
    
    public void setCorrectAnswer(String answer){
        this.answer=answer;
    }
    
    public String getAnswer()
    {
        return answer;
    }
    
    public void setAnswerDesc(String answerDesc){
        this.answerDesc=answerDesc;
    }
    
    public String getAnswerDesc()
    {
        return answerDesc;
    }

    public Part getMedia() {
        return media;
    }

    public void setMedia(Part media) {
        this.media = media;
    }
    
}
