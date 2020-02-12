/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Ferrer
 */
public class ClienteDAO {
    
    Conexion conectar = new Conexion();
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    DefaultTableModel model; 
    
    public ArrayList listar(){
        ArrayList<Cliente>datos = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try {
            connection = conectar.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt(1));
                cliente.setName(resultSet.getString(2));
                cliente.setNumberPhone(resultSet.getString(3));
                cliente.setAddress(resultSet.getString(4));
                datos.add(cliente);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public int agregar(Cliente cliente){
        String sql = "INSER INTO clientes (Nombre, Numero_Celular, Direccion) VALUES (?,?,?)";
         try {
            connection = conectar.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getName());
            preparedStatement.setString(2, cliente.getNumberPhone());
            preparedStatement.setString(3, cliente.getAddress());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }
    
    public int actualizar(Cliente cliente) {
        int r = 0;
        String sql = "UPDATE clientes SET Nombre=?, Numero_Celular=?, Telefono=? WHERE ID=?";
        try {
            connection = conectar.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getName());
            preparedStatement.setString(2, cliente.getNumberPhone());
            preparedStatement.setString(3, cliente.getAddress());
            preparedStatement.setInt(4, cliente.getId());
            r = preparedStatement.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return r;
    }
    
    public void eliminar(int id){
        String sql = "DELETE FROM clientes WHERE ID="+id+"";
        try {
            connection= conectar.getConnection();
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }
}
