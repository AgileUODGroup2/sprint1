/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import lib.database.DatabaseConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import stores.moduleStudentsEnrolledStore;

/**
 *
 * @author ashawittchen
 */
public class moduleStudentsEnrolledModel {
    
    
  
   public String getStudentsEnrolled(int Module_ID){
        
    
  try{
      DatabaseConnection db = new DatabaseConnection();
        db = new DatabaseConnection();
        Connection conn = db.connectToDatabase();
        Statement st = conn.createStatement();
 
        String query = "SELECT * FROM student_enrolment WHERE Module_ID = 210101";  //change to session variable
        //LinkedList students = new LinkedList();    
        ResultSet rs = st.executeQuery(query);
        String result;
         while (rs.next())               
            {
               int numColumns = rs.getMetaData().getColumnCount();
              
               
            for ( int i = 1 ; i <= numColumns ; i++ ) {
                
                
                if (i == 1){
                    System.out.println("Matriculation_Number " + rs.getInt(i));
                     int Matriculation_Number= rs.getInt("Matriculation_Number");
                    result = Matriculation_Number + " " ;
      
                }
                else{
                    Module_ID = rs.getInt("Module_ID");
                    System.out.println("Module_ID " + rs.getInt(i)); 
                    result = Module_ID + " " ;
                    
                }
              
               System.out.println("Result "+ result);   
            
            /*    
              int Matriculation_Number= rs.getInt("Matriculation_Number");
                System.out.println(Matriculation_Number);
               // add elements to the linked list
              //students.add(Matriculation_Number);
              //System.out.println(students);
              Module_ID = rs.getInt("Module_ID");
              System.out.println(Module_ID);
               // add elements to the linked list
               //students.add(Module_ID);
               //System.out.println(students);
               System.out.println(Matriculation_Number + " "+ Module_ID);
               String result = Matriculation_Number + " " + Module_ID;
               System.out.println(Matriculation_Number + " " + Module_ID);
               System.out.println("Result "+ result);
               
               //System.out.println(students);
           
              
               return result;
             */
            }
            }
            st.close();
           
           
             }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    return null;
 }
        
 /*  public java.util.LinkedList<moduleStudentsEnrolledStore>getStudents() throws SQLException{
        
       db = new DatabaseConnection();
       String query = "SELECT * FROM student_enrolment WHERE Module_ID = 210101";  
       java.util.LinkedList<moduleStudentsEnrolledStore>StudentsEnrolled = new java.util.LinkedList<>();
       try 
           (Connection con = db.connectToDatabase(); 
          
           PreparedStatement ps = con.prepareStatement(query)){
           try (ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                moduleStudentsEnrolledStore s = new moduleStudentsEnrolledStore();
                s.setMatriculation_Number(rs.getInt("Matriculation_Number"));
                s.setModule_ID(rs.getInt("Module_ID"));
                
                StudentsEnrolled.add(s);
                System.out.println(StudentsEnrolled);
            }
           }
    
       
       catch (SQLException ex) {    
            Logger.getLogger(moduleStudentsEnrolledModel.class.getName()).log(Level.SEVERE, null, ex);
        }    
       return StudentsEnrolled;
        
        
    }
   }
 */
}    


