/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class ClientDAO {

    private ResultSet resultSet;
    private BDConnection connection;

    public ClientDAO(BDConnection connection) {
        this.connection = connection;
    }

    /**
     * Gets all DB clients
     *
     * @return clientList
     */
    public List<Client> findAll() {
        List<Client> clientList = new ArrayList<>();

        //Sets SQL sentence
        String sql = "Select * from client";

        try {
            //Checks if connection was stablished
            if (connection.setConnection()) {
                Statement st = connection.getConnection().createStatement();
                resultSet = st.executeQuery(sql);

                //Iterates result set to parse data
                while (resultSet.next()) {
                    clientList.add(new Client(
                            resultSet.getInt("id"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("name"),
                            resultSet.getString("address"))
                    );
                }

                //Closes current connection
                st.close();
                connection.closeConnection();

            } else {
                System.out.println("No se pudo conectar ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientList;
    }

    /**
     * Gets all DB clients
     *
     * @param id
     * @return clientList
     */
    public Client findOne(int id) {
        String sql = "SELECT * FROM client WHERE id = " + id;
        try {
            //Checks if connection was stablished
            if (connection.setConnection()) {
                Statement st = connection.getConnection().createStatement();
                resultSet = st.executeQuery(sql);
                resultSet.next();

                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address")
                );

                st.close();
                connection.closeConnection();

                return client;

            } else {
                System.out.println("No se pudo conectar");

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets all DB clients
     *
     * @param name
     * @param phoneNumber
     * @param address
     * @return
     */
    public boolean create(String name, String phoneNumber, String address) {
        String sql = "INSERT INTO client(name, phone_number, address) values('"
                + name + "','"
                + phoneNumber + "','"
                + address + "');";
        try {
            //Checks if connection was stablished
            if (connection.setConnection()) {
                Statement st = connection.getConnection().createStatement();

                int resposenCode = st.executeUpdate(sql);

                st.close();
                connection.closeConnection();

                if (resposenCode == 1) {
                    return true;
                } else {
                    return false;
                }

            } else {
                System.out.println("No se pudo conectar");

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets all DB clients
     *
     * @param id
     * @param name
     * @param phoneNumber
     * @param address
     * @return
     */
    public boolean update(int id, String name, String phoneNumber, String address) {
        String sql = "UPDATE client SET name = '"
                + name + "', phone_number = '"
                + phoneNumber + "', address = '"
                + address + "' WHERE  id = " + id;
        try {
            //Checks if connection was stablished
            if (connection.setConnection()) {
                Statement st = connection.getConnection().createStatement();

                int resposenCode = st.executeUpdate(sql);

                st.close();
                connection.closeConnection();

                if (resposenCode == 1) {
                    return true;
                } else {
                    return false;
                }

            } else {
                System.out.println("No se pudo conectar");

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
        /**
     * Gets all DB clients
     *
     * @param id
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM invoice WHERE ID=" + id + "";
        try {
            if (connection.setConnection()) {
                Statement st= connection.getConnection().createStatement();
                int resposenCode = st.executeUpdate(sql);
                st.close();
                connection.closeConnection();

                if (resposenCode == 1) {
                    return true;
                } else {
                    return false;
                }

            } else {
                System.out.println("No se pudo conectar");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
