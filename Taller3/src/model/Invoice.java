/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "invoice")
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findOne", query = "SELECT i FROM Invoice i WHERE i.id = :id")
})
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "invoice_date")
    private String invoiceDate;
    @Column(name = "total_price")
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client clientId;

    public Invoice() {
    }

    public Invoice(int id, String invoiceDate, double totalPrice, Client clientId) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.totalPrice = totalPrice;
        this.clientId = clientId;
    }

    public Invoice(String invoiceDate, double totalPrice, Client clientId) {
        this.invoiceDate = invoiceDate;
        this.totalPrice = totalPrice;
        this.clientId = clientId;
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

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", invoiceDate=" + invoiceDate + ", totalPrice=" + totalPrice + ", clientId=" + clientId.getId() + '}' + "\n";
    }

}
