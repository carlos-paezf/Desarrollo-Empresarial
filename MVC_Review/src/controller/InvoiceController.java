/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.crypto.spec.IvParameterSpec;
import model.Client;
import model.Invoice;

/**
 *
 * @author Estudiante
 */
public class InvoiceController extends MainController{

    public InvoiceController() {
        super();
    }

    @Override
    public boolean create(Object[] params) {
        try {
            objectList.add(new Invoice(
                    new Integer(params[0].toString()), 
                    params[1].toString(), 
                    new Double(params[2].toString()), 
                    (Client) params[3]));
//            objectList.add(new Invoice(
//                    new Integer(params[0].toString()), 
//                    params[1].toString(), 
//                    new Double(params[2].toString()), 
//                    new Integer(params[3].toString())
//            ));
            return true;
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    @Override
    public boolean update(int index, Object[] params) {
        try {
            objectList.set(index, new Invoice(
                    new Integer(params[0].toString()), 
                    params[1].toString(), 
                    new Double(params[2].toString()),
                    (Client) (params[3])
            ));
        
            return true;
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
