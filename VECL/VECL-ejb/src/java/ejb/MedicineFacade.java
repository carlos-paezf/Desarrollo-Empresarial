/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Medicine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David Ferrer
 */
@Stateless
public class MedicineFacade extends AbstractFacade<Medicine> implements MedicineFacadeLocal {

    @PersistenceContext(unitName = "VECL-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicineFacade() {
        super(Medicine.class);
    }
    
}
