package com.mycompany.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author David Ferrer
 */
public class DBSession {

    private static EntityManager entityManager;

    public static void persistenceCreate() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaPersistenceExamplePU");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    public static EntityManager getEntityManager(){
        return entityManager;
    }
    
    public static void persistenceClose(){
        entityManager.close();
    }

}
