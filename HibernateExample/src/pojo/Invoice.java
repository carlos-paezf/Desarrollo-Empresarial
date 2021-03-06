package pojo;
// Generated Apr 3, 2020 9:23:41 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Invoice generated by hbm2java
 */
public class Invoice  implements java.io.Serializable {


     private Integer id;
     private Client client;
     private Date invoiceDate;
     private Double totalPrice;
     private Set products = new HashSet(0);

    public Invoice() {
    }

	
    public Invoice(Client client, Date invoiceDate) {
        this.client = client;
        this.invoiceDate = invoiceDate;
    }
    public Invoice(Client client, Date invoiceDate, Double totalPrice, Set products) {
       this.client = client;
       this.invoiceDate = invoiceDate;
       this.totalPrice = totalPrice;
       this.products = products;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    public Date getInvoiceDate() {
        return this.invoiceDate;
    }
    
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public Double getTotalPrice() {
        return this.totalPrice;
    }
    
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Set getProducts() {
        return this.products;
    }
    
    public void setProducts(Set products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", client=" + client + ", invoiceDate=" + invoiceDate + ", totalPrice=" + totalPrice;
    }




}


