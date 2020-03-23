/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author David Ferrer
 */
public abstract class AbstractModel {

    private EntityManager entityManager;

    public AbstractModel() {
        entityManager = DBSession.getEntityManager();
    }

    /**
     * Gets all DB Data
     *
     * @param className
     * @return
     * @throws Exception
     */
    public List findAll(String className) throws Exception {
        entityManager.getEntityManagerFactory().getCache().evictAll();
        Query jpaql = entityManager.createNamedQuery(className + ".findAll");
        return jpaql.getResultList();
    }

    public Object findOne(String className, int id) throws Exception {
        entityManager.getEntityManagerFactory().getCache().evictAll();
        Query jpaql = entityManager.createNamedQuery(className + ".findOne")
                .setParameter("id", id);
                
        return jpaql.getSingleResult();
    }

    public void create(Object object, boolean isFinal) throws Exception {
        if (!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();
        entityManager.persist(object);
        if (isFinal)
            entityManager.getTransaction().commit();
    }

    public void update(Object object, boolean isFinal) throws Exception {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.merge(object);
        if (isFinal) {
            entityManager.getTransaction().commit();
        }
    }

    public void delete(Object object, boolean isFinal) throws Exception {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(object);
    }

}
