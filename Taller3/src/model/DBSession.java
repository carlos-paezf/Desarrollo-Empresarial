/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author David Ferrer
 */
public class DBSession {
    
    private static EntityManager entityManager;
    
    public static void persistenceCreate(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Taller3PU");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    public static EntityManager getEntityManager(){
        return entityManager;
    }
    
    public static void persistenceClose(){
        entityManager.close();
    }
}
