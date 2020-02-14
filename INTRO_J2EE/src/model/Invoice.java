package model;

import java.util.Date;

/**
 *
 * @author Estudiante
 */
public class Invoice {

    public static final int ID = 0;
    public static final int INVOICEDATE = 1;
    public static final int TOTALPRICE = 2;
    public static final int CLIENT = 3;

    private int id;
    private String invoiceDate;
    private double totalPrice;
    private Client client;

    public Invoice() {
    }

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
