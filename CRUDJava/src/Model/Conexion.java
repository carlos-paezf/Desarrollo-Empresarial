/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author David Ferrer
 */
public class Conexion {
    
    Connection connection;
    
    public Conexion() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/CRUDJava", "root", "");
            JOptionPane.showMessageDialog(null, "Conexion establecida...");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
}
