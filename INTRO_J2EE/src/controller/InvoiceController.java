/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Client;
import model.Invoice;

/**
 *
 * @author David Ferrer
 */
public class InvoiceController {
    
    private List<Invoice>invoices;

    public InvoiceController() {
        invoices = new ArrayList<>();
    }
    
    public boolean create(Object[] params) {
        try {
            invoices.add(new Invoice(
                    (Integer)params[Invoice.ID], 
                    (String)params[Invoice.INVOICEDATE], 
                    (Double)params[Invoice.TOTALPRICE], 
                    (Client)params[Invoice.CLIENT]));
            return true;
        } catch (NumberFormatException e) {
            //TO DO: Show error message
            return false;
        }
    }
    
    public boolean delete(int id){
        try {
            invoices.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String list(){
        return Arrays.toString(invoices.toArray());
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    
}
