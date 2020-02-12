/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author David Ferrer
 */
public class ClienteDAO {

    Conexion connnection = new Conexion();
    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> datos = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try {
            conn = connnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cliente c = new Cliente();
                c.setId(resultSet.getInt(1));
                c.setName(resultSet.getString(2));
                c.setNumberPhone(resultSet.getString(3));
                c.setAddress(resultSet.getString(4));
                datos.add(c);
            }
        } catch (Exception e) {
        }
        return datos;
    }

    public int Agregar(Cliente cliente) {
        String sql = "INSER INTO clientes (Nombre, Numero_Celular, Direccion) VALUES (?,?,?)";
        try {
            conn = connnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getName());
            preparedStatement.setString(2, cliente.getNumberPhone());
            preparedStatement.setString(3, cliente.getAddress());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }

    public int Actualizar(Cliente cliente) {
        int r = 0;
        String sql = "UPDATE clientes SET Nombre=?, Numero_Celular=?, Telefono=? WHERE ID=?";
        try {
            conn = connnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
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
            conn= connnection.getConnection();
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }
}
