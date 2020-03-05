/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Estudiante
 */
public class Invoice {
    private int id;
    private String invoiceDate;
    private double totalPrice;
    private Client client;
//    private int clientId;

    public Invoice() {
    }

/*    public Invoice(int id, String invoiceDate, double totalPrice, int clientId) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.totalPrice = totalPrice;
        this.clientId = clientId;
    }
*/
    
public Invoice(int id, String invoiceDate, double totalPrice, Client client) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.totalPrice = totalPrice;
        this.client = client;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

//    public int getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(int clientId) {
//        this.clientId = clientId;
//    }

    
    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", invoiceDate=" + invoiceDate + ", totalPrice=" + totalPrice + "\n"+"  --> client=" + client + '}';
    }

    
    
}
