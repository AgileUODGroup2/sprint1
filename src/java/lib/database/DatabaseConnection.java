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

    public DatabaseConnection() {
        String hostname = "jdbc:mysql://silva.computing.dundee.ac.uk:3306/16agileteam2db";
        String username = "16agileteam2";
        String password = "9374.at2.4739";
    }

    public Connection connectToDatabase()
    {
        try
        {
            Connection conn = DriverManager.getConnection(hostname, username, password);
            if (conn != null)
            {
                System.out.println("Connected to database!");
                return conn;
            }
        }
        catch(SQLException ex)
        {
            System.out.println("An error has occured connecting to database.");
            ex.printStackTrace();
            return null;
        }
    }

}

