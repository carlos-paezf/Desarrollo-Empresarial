/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
import model.BDConnection;
import model.Client;
import model.ClientDAO;
import model.Invoice;
import model.InvoiceDAO;

/**
 *
 * @author Estudiante
 */
public class BDTest {

    public static void main(String[] args) {
        //Sets BD Connection
        BDConnection bdCon = new BDConnection();
        //Client Map instance
        ClientDAO cdao = new ClientDAO(bdCon);
        //Invoice Map instance
        InvoiceDAO idao = new InvoiceDAO(bdCon);

//        if (cdao.update(7, "Anas", "320", "Calle falsa 123")) {
//            //List<Client> clients = cdao.findAll();
//            Client client = cdao.findOne(7);
//            //for (Client client : clients) {
//            System.out.println(client.toString());
//            //}
//        } else {
//            System.out.println("Fallo inseción");
//        }
       double  tP = 20;
        if (idao.update(2, "4444-12-12 01:20:05", tP, 1)) {
            Invoice invoice = idao.findOne(2);
            System.out.println(invoice.toString());
        } else {
            System.out.println("Fallo inseción");
        }
        
        if(idao.create("1111-12-12 02:30:50", tP, 3)){
            System.out.println("Factura Creada");
            Invoice invoice = idao.findOne(5);
            System.out.println(invoice.toString());
        }else {
            System.out.println("Error de creacion");
        }
        
        if(idao.delete(10)){
            System.out.println("Factura Eliminada");
        }else{
            System.out.println("Error");
        }
    }
}
