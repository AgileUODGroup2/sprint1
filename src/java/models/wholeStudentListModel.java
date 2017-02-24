/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lib.database.DatabaseConnection;
/**
 *
 * @author ashawittchen
 */
public class wholeStudentListModel {
    
    public String getWholeStudentList(){
        
    Connection conn = null;
   try{
        DatabaseConnection db = new DatabaseConnection();
        conn = db.connectToDatabase();
        Statement st = conn.createStatement();
        String query = "SELECT * FROM student";
        
        ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
               int Matriculation_Number= rs.getInt("Matriculation_Number");
               String First_Name= rs.getString("First_name");
               String Last_Name= rs.getString("Last_name");
                
               String result = Matriculation_Number + " " + First_Name + " " + Last_Name;
               System.out.println(Matriculation_Number + " " + First_Name + " " + Last_Name);
               return result;
            }
           
            st.close();
           
             }
        catch(SQLException err){
            System.out.println(err.getMessage());
    }finally{
        if (conn != null) {
            try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
        }
    }
    return null;
 }
}
