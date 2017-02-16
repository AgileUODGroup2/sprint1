/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author erincoey
 */
public class DatabaseConnection{
    public static void main(String[] args){
        Connection conn =connectToDatabase();
        
    }
    
    public static Connection connectToDatabase()
    {
        try{
        String host = "silva.computing.dundee.ac.uk";
        String username = "16agileteam2";
        String password = "9374.at2.4739";
        Connection conn = DriverManager.getConnection(host,username,password);
        return conn;
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        return null; 
    }
    
}

