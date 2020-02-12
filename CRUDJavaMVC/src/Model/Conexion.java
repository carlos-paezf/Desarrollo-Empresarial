/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author David Ferrer
 */
public class Conexion {
    
    Connection connection;
    
    public Connection getConnection(){
        String url = "jdbc:mysql://localhost/crudjava";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=(Connection) DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
        }
        return connection;
    }
}
