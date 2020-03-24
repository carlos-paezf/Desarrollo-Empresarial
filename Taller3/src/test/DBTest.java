/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import java.util.List;
import model.Client;
import model.ClientModel;
import model.DBSession;
import model.Invoice;
import model.InvoiceModel;

/**
 *
 * @author David Ferrer
 */
public class DBTest {

    public static void main(String[] args) {

        DBSession.persistenceCreate();

        ClientModel clientModel = new ClientModel();
        InvoiceModel invoiceModel = new InvoiceModel();

        try {

            /**
             * Test creating, searching and updating a client
             */
//            Client client1 = new Client("Otro ingreso", "12435", "Malibú");
//            clientModel.create(client1, true);
//            
//            Client client2 = (Client) clientModel.findOne("Client", 2);
//            client1.setName("Intento");
//            clientModel.update(client2, true);
//            
//            clientModel.delete("Client", 63);
//            
//            List<Client> clients = clientModel.findAll("Client");
//            System.out.println(Arrays.toString(clients.toArray()));
//            
//            System.out.println(clientModel.findOne("Client", 2));
            
            
            /**
             * Proof of creating, searching and updating an invoice
             */
//            Client client2 = new Client("El comprador nuevo", "320", "Calle Falsa 123");
//            clientModel.create(client2, true);
//            Invoice invoice = new Invoice("2020-02-21 10:23:50", 12345, client2);
//            invoiceModel.create(invoice, true);
//                        
//            Invoice invoice2 = (Invoice) invoiceModel.findOne("Invoice", 2);
//            invoice2.setTotalPrice(9909);
//            invoiceModel.update(invoice2, true);
//            invoiceModel.delete("Invoice", 8);
//       
//            List<Invoice> invoices = invoiceModel.findAll("Invoice");
//            System.out.println(Arrays.toString(invoices.toArray()));
//            System.out.println(invoiceModel.findOne("Invoice", 2));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
