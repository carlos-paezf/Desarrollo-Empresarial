/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Estudiante
 */
public class BDConnection {
    private Connection connection;

    public BDConnection() {}
    
    public boolean setConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc_review","root","");
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return false;
        }
    }
    
    public boolean closeConnection(){
        try {
            connection.close();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
