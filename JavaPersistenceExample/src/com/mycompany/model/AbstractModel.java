/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author David Ferrer
 */
public class AbstractModel {

    private EntityManager entityManager;

    public AbstractModel() {
        entityManager = DBSession.getEntityManager();
    }

    /**
     * Gets all DB data
     * @param className
     * @return
     * @throws Exception 
     */
    
    public List findAll(String className) throws Exception {
        entityManager.getEntityManagerFactory().getCache().evictAll();
        Query query = entityManager.createNamedQuery(className + ".findAll");
        return query.getResultList();
    }
    
    public Object findOne(String className, int id) throws Exception{
        entityManager.getEntityManagerFactory().getCache().evictAll();
        Query jpaql = entityManager.createNamedQuery(className + ".findOne")
                .setParameter("id", id);                
        return jpaql.getSingleResult();
    }
    
    public void create(Object obj, boolean isFinal) throws Exception{
        if(!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();        
        entityManager.persist(obj);        
        if(isFinal)
            entityManager.getTransaction().commit();
    }

}
