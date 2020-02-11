/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author David Ferrer
 */
public class ClientDAO {
    
    Conexion connnection = new Conexion();
    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    
}
