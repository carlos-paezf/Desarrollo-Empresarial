package model;

import java.util.Date;

/**
 *
 * @author Estudiante
 */
public class Invoice {
    
    private int id;
    private Date invoiceDate;
    private double totalPrice;
    private Client client;

    public Invoice() {
    }

    public Invoice(int id, Date invoiceDate, double totalPrice, Client client) {
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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", invoiceDate=" + invoiceDate + ", totalPrice=" + totalPrice + ", client=" + client + '}';
    }
    
}
