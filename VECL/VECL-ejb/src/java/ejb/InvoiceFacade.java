/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Invoice;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David Ferrer
 */
@Stateless
public class InvoiceFacade extends AbstractFacade<Invoice> implements InvoiceFacadeLocal {

    @PersistenceContext(unitName = "VECL-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvoiceFacade() {
        super(Invoice.class);
    }
    
}
