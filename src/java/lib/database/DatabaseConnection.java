/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.database;
import java.sql.*;

/**
 *
 * @author erincoey
 */
public class DatabaseConnection{
    
    public Connection connectToDatabase()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        String host = "jdbc:mysql://134.36.36.37:3306/16agileteam2db";
        String username = "16agileteam2";
        String password = "9374.at2.4739";
        Connection conn = DriverManager.getConnection(host,username,password);
        return conn;
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
            return null; 
        }
        catch(Exception e) {
            System.out.println("Connection to database failed");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}

