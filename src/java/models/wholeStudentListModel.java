/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import lib.database.DatabaseConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author ashawittchen
 */
public class wholeStudentListModel {
    
    public String getWholeStudentList(){
        
    
   try{
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.connectToDatabase();
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
        }
    return null;
 }
}