/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asetku.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dhafa
 */
public class ConnectionManager {
    final private static String URL = "jdbc:mysql://localhost:3306/asetku";
    final private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    final private static String USERNAME = "root";
    final private static String PASSWORD = "";
    
    private Connection conn;
    private static ConnectionManager instance;
    
    private ConnectionManager() {
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.err.println("Class not found");
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            // log an exception. fro example:
            System.err.println("Message : " + ex.getMessage());
            System.err.println("Error Code : " + ex.getErrorCode());
            System.err.println("Failed to create the database connection.");
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public static ConnectionManager getInstance() {
        try {
        
            if(instance == null) {
                instance = new ConnectionManager();
            } else if(instance.getConnection().isClosed()) {
                instance = new ConnectionManager();
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode());
            System.err.println(e.getMessage());
        }
        return instance;
    }
}
