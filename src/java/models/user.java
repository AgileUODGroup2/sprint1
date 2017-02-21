/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lib.database.DatabaseConnection;

/**
 *
 * @author nathanmcmahon
 */
public class user {
    
    
    public String login(String username, String password){

         DatabaseConnection db = new DatabaseConnection();
         Connection conn = db.connectToDatabase();

         String checkStaff = "select * from staff where Staff_ID=? and Password=?";
         String checkStudent = "select * from student where Matriculation_Number=? and Password=?";
         try{       
            PreparedStatement ps = conn.prepareStatement(checkStaff);
            PreparedStatement ps2 = conn.prepareStatement(checkStudent);
            ps.setString(1,username);
            ps.setString(2,password);
            ps2.setString(1,username);
            ps2.setString(2,password);
            ResultSet theResult = ps.executeQuery();
            ResultSet theResult2 = ps2.executeQuery();
            if(theResult.next()){
                conn.close();
                System.out.println("Staff logged in");
                return "Staff";
            } else if(theResult2.next()) {
                conn.close();
                System.out.println("Student logged in");
                return"Student";
            } else {
                conn.close();
                System.out.println("No one logged in");
                return "failed";
            }
         }
         catch(SQLException e) {
             System.out.println(e.getMessage());
         }
         return null;
    }
    
    public void updateFirstName(boolean isStaff, int userID, String firstName){
        
        DatabaseConnection db = new DatabaseConnection();
        
        String query = "";
        
        if(isStaff) {
            query = "UPDATE staff SET First_Name = ? WHERE Staff_ID = ?";
        } else {
            query = "UPDATE student SET First_Name = ? WHERE Matriculation_Number = ?";
        }
        
        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, firstName);
            ps.setInt(2, userID);
            
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
    
    public void updateLastName(boolean isStaff, int userID, String lastName){
        
        DatabaseConnection db = new DatabaseConnection();
        
        String query = "";
        
        if(isStaff) {
            query = "UPDATE staff SET Last_Name = ? WHERE Staff_ID = ?";
        } else {
            query = "UPDATE student SET Last_Name = ? WHERE Matriculation_Number = ?";
        }
        
        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, lastName);
            ps.setInt(2, userID);
            
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
    
    public void updatePassword(boolean isStaff, int userID, String password){
        
        DatabaseConnection db = new DatabaseConnection();
        
        String query = "";
        
        if(isStaff) {
            query = "UPDATE staff SET Password = ? WHERE Staff_ID = ?";
        } else {
            query = "UPDATE student SET Password = ? WHERE Matriculation_Number = ?";
        }
        
        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, password);
            ps.setInt(2, userID);
            
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public String[] getStudentDetails(int matriculationNo) {

        DatabaseConnection db = new DatabaseConnection();
        
        String nameArr[] = new String[2];
        String query = "SELECT First_Name, Last_Name FROM student WHERE Matriculation_Number = ?";
        
        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, matriculationNo);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    
                    nameArr[0] = rs.getString("First_Name");
                    nameArr[1] = rs.getString("Last_Name");
                }
            }


        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }

        return nameArr;

    }

    public String[] getStaffDetails(int staffID) {

        DatabaseConnection db = new DatabaseConnection();
        
        String nameArr[] = new String[2];
        String query = "SELECT First_Name, Last_Name FROM staff WHERE Staff_ID = ?";
        
        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, staffID);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    
                    nameArr[0] = rs.getString("First_Name");
                    nameArr[1] = rs.getString("Last_Name");
                }
            }


        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }

        return nameArr;
        
    }
}
