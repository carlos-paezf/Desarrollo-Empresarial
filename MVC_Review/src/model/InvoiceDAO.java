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
 * @author David Ferrer
 */
public class InvoiceDAO {

    private BDConnection connection;
    private ResultSet resultSet;
    private Statement statement;
    private ClientDAO cdao;

    public InvoiceDAO(BDConnection connection) {
        this.connection = connection;
        this.cdao = new ClientDAO(connection);
    }


    /**
     * Gets all DB clients
     *
     * @return clientList
     */
    public List<Invoice> findAll() {
        List<Invoice> invoiceList = new ArrayList<>();
        String sql = "SELECT * FROM invoice";
        try {
            if (connection.setConnection()) {
                statement = connection.getConnection().createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    invoiceList.add(new Invoice(
                            resultSet.getInt("id"),
                            resultSet.getString("invoice_date"),
                            resultSet.getDouble("total_price"),
                            cdao.findOne(resultSet.getInt("client_id")))
                    );
                }
                statement.close();
                connection.closeConnection();

            } else {
                System.out.println("No se pudo conectar ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    /**
     * Gets one DB clients
     *
     * @param id
     */
    public Invoice findOne(int id) {
        String sql = "SELECT * FROM invoice WHERE id = " + id;
        try {
            if (connection.setConnection()) {
                statement = connection.getConnection().createStatement();
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                Invoice invoice = new Invoice(
                        resultSet.getInt("id"),
                        resultSet.getString("invoice_date"),
                        resultSet.getDouble("total_price"),
                        cdao.findOne(resultSet.getInt("client_id"))
                );
                statement.close();
                connection.closeConnection();
                return invoice;

            } else {
                System.out.println("No se pudo conectar");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets all DB clients
     *
     * @param invoiceDate
     * @param totalPrice
     * @param clientId
     * @return
     */
    public boolean create(String invoiceDate, Double totalPrice, int clientId) {
        String sql = "INSERT INTO invoice(invoice_date, total_price, client_id) values('"
                + invoiceDate + "','"
                + totalPrice + "','"
                + clientId + "');";
        try {
            if (connection.setConnection()) {
                statement = connection.getConnection().createStatement();
                int resposenCode = statement.executeUpdate(sql);
                statement.close();
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

    /**
     * Gets all DB clients
     *
     * @param id
     * @param invoiceDate
     * @param totalPrice
     * @param clientId
     * @return
     */
    public boolean update(int id, String invoiceDate, Double totalPrice, int clientId) {
        String sql = "UPDATE invoice SET invoice_date = '"
                + invoiceDate + "', total_price = '"
                + totalPrice + "', client_id = '"
                + clientId + "' WHERE  id = " + id;
        try {
            if (connection.setConnection()) {
                statement = connection.getConnection().createStatement();
                int resposenCode = statement.executeUpdate(sql);
                statement.close();
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
                statement = connection.getConnection().createStatement();
                int resposenCode = statement.executeUpdate(sql);
                statement.close();
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
