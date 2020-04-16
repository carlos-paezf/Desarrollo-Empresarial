/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClientModel;
import model.InvoiceModel;
import model.ProductModel;
import pojo.Client;
import pojo.Invoice;
import pojo.Product;

/**
 *
 * @author andresforero
 */
public class DBTest {
    public static void main(String[] args) {
        try {
            ClientModel cm = new ClientModel();
            ProductModel pm = new ProductModel();
            InvoiceModel im = new InvoiceModel();
            
            //Get client by id
            Client client = (Client) cm.findById(2);
            
            System.out.println("Cliente por id: " + client);
            
            //List of all clients 
            List<Client> clients = cm.findAll();
            System.out.println("Todos los Clientes: " + Arrays.toString(clients.toArray()));
            
            //List of all products
            List<Product> prods = pm.findAll();
            System.out.println("Todos los Productos: " + Arrays.toString(prods.toArray()));
            
            //List of all products
            List<Invoice> invocies = im.findAll();
            System.out.println("Todas las Facturas: " + Arrays.toString(invocies.toArray()));
            
        } catch (Exception ex) {
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
