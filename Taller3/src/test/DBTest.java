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
//            Client client1 = new Client(53, "El renovado", "320", "Calle Falsa 123");
//            clientModel.create(client1, true);
//            List<Client> clients = clientModel.findAll("Client");
//            System.out.println(Arrays.toString(clients.toArray()));
//            System.out.println(clientModel.findOne("Client", 9));
//            clientModel.update(client1, true);
//            clientModel.delete(client1, true);

            /**
             * Proof of creating, searching and updating an invoice
             */
//            Client client2 = new Client(52, "El comprador", "320", "Calle Falsa 123");
//            Invoice invoice = new Invoice(33, "2020-corona", 11111, client2);
//            invoiceModel.create(invoice, true);
//            List<Invoice> invoices = invoiceModel.findAll("Invoice");
//            System.out.println(Arrays.toString(invoices.toArray()));
//            System.out.println(invoiceModel.findOne("Invoice", 5));
//            invoice = new Invoice(33, "Factura del 2020 renovada", 220, client2);
//            invoiceModel.update(invoice, true);
//            invoiceModel.delete(invoice, true);
       
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
