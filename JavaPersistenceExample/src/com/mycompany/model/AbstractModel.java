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

    public List findAll(String className) throws Exception {
        entityManager.getEntityManagerFactory().getCache().evictAll();
        Query query = entityManager.createNamedQuery(className + ".findAll");
        return query.getResultList();
    }

}
