/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.Part;
import lib.database.DatabaseConnection;

/**
 *
 * @author nathanmcmahon
 */
public class user {
    
    DatabaseConnection db = new DatabaseConnection();
    
    public String login(String username, String password){

         String checkStaff = "select * from staff where Staff_ID=? and Password=?";
         String checkStudent = "select * from student where Matriculation_Number=? and Password=?";
         try(Connection conn = db.connectToDatabase();
                 PreparedStatement ps = conn.prepareStatement(checkStaff);
                 PreparedStatement ps2 = conn.prepareStatement(checkStudent);){
            ps.setString(1,username);
            ps.setString(2,password);
            ps2.setString(1,username);
            ps2.setString(2,password);
            try (ResultSet theResult = ps.executeQuery();
                    ResultSet theResult2 = ps2.executeQuery();) {
                if(theResult.next()){
                    System.out.println("Staff logged in");
                    return "Staff";
                } else if(theResult2.next()) {
                    System.out.println("Student logged in");
                    return"Student";
                } else {
                    System.out.println("No one logged in");
                    return "failed";
                }
            }
         }
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
         return null;
    }
    
    public void updateFirstName(boolean isStaff, int userID, String firstName){
        
        String query = "";
        
        if(isStaff) {
            query = "UPDATE staff SET First_Name = ? WHERE Staff_ID = ?";
        } else {
            query = "UPDATE student SET First_Name = ? WHERE Matriculation_Number = ?";
        }
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {

            ps.setString(1, firstName);
            ps.setInt(2, userID);
            
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
    
    public void updateLastName(boolean isStaff, int userID, String lastName){
        
        String query = "";
        
        if(isStaff) {
            query = "UPDATE staff SET Last_Name = ? WHERE Staff_ID = ?";
        } else {
            query = "UPDATE student SET Last_Name = ? WHERE Matriculation_Number = ?";
        }
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {

            ps.setString(1, lastName);
            ps.setInt(2, userID);
            
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
    
    public void updatePassword(boolean isStaff, int userID, String password){
        
        String query = "";
        
        if(isStaff) {
            query = "UPDATE staff SET Password = ? WHERE Staff_ID = ?";
        } else {
            query = "UPDATE student SET Password = ? WHERE Matriculation_Number = ?";
        }
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query); ) {

            ps.setString(1, password);
            ps.setInt(2, userID);
            
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
    
    public void updateProfileImage(boolean isStaff, int userID, Part profileImage) throws IOException{
        
        
        
        String query = "";
        
        if(isStaff) {
            query = "UPDATE staff SET Profile_Image = ? WHERE Staff_ID = ?";
        } else {
            query = "UPDATE student SET Profile_Image = ? WHERE Matriculation_Number = ?";
        }
        
        // Java image to MySQL database query based on code found here: http://www.thejavaprogrammer.com/save-retrieve-image-mysql-database-using-servlet-jsp/
        
        try(Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);){

            if(profileImage != null){
                InputStream is = profileImage.getInputStream();
                ps.setBlob(1, is);
                ps.setInt(2, userID);  
                ps.executeUpdate();
            }else{
                ps.setNull(1, java.sql.Types.BLOB);
                ps.setInt(2, userID);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        
    }
    
    public byte[] getProfileImage(boolean isStaff, int userID) {
        
        Connection con = db.connectToDatabase();
        
        String query = "";
        
        if(isStaff) {
            query = "SELECT Profile_Image FROM staff WHERE Staff_ID = ?";
        } else {
            query = "SELECT Profile_Image FROM student WHERE Matriculation_Number = ?";
        }
        
        // Java image to MySQL database query based on code found here: http://www.thejavaprogrammer.com/save-retrieve-image-mysql-database-using-servlet-jsp/
        try {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userID);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    
                    Blob blob = rs.getBlob("Profile_Image");
                    byte byteArray[] = blob.getBytes(1, (int)blob.length());
                    
                    return byteArray;
                    
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if(con != null){
                try{
                    con.close();
                } catch(Exception e){
                    System.out.print(e.getMessage());
                }
            }
        }
        
        //CHANGE THIS
        return null;
        
    }

    public String[] getStudentDetails(int matriculationNo) throws SQLException {
        
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
        
        String nameArr[] = new String[3];
        String query = "SELECT First_Name, Last_Name, Password FROM staff WHERE Staff_ID = ?";
        
        try (Connection con = db.connectToDatabase(); ) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, staffID);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    
                    nameArr[0] = rs.getString("First_Name");
                    nameArr[1] = rs.getString("Last_Name");
                    nameArr[2] = rs.getString("Password"); 
                    
                   
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return nameArr;
    }

    public java.util.Vector<String> getStaffModules(int staffID) {
        java.util.Vector<String> modules = new java.util.Vector<>();
        String query = "SELECT Module_ID FROM staff_enrolment WHERE Staff_ID = ? GROUP BY Module_ID";
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, staffID);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    modules.add(rs.getString("Module_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return modules;
    }

    public java.util.Vector<String> getStudentModules(int matricNo) {
        java.util.Vector<String> modules = new java.util.Vector<>();
        String query = "SELECT Module_ID FROM student_enrolment WHERE Matriculation_Number = ? GROUP BY Module_ID";
        
        try (Connection con = db.connectToDatabase();
                PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, matricNo);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    modules.add(rs.getString("Module_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return modules;
        
    }
}
