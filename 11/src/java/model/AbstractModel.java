/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author David Ferrer
 */
public abstract class AbstractModel {
    
    private final SessionFactory sf;
    private final Session session;
    protected String className;
    
    public AbstractModel(String className){
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        this.className = className;
    }
    
    public List findAll() throws Exception{
        
        //Hibernate Query Language (HQL)
        return session.createQuery(" from " + className ).list();
    }
    
   public Object findById(int id) throws Exception{
       
       //Hibernate Query Language (HQL)
       return session.createQuery(" from " + className + " where id = " + id).uniqueResult();
   }
   
   /*public void create(Object object) throws Exception{
       SessionFactory sf = HibernateUtil.getSessionFactory();
       Session session = sf.openSession();
       
       //Opens transaction
       Transaction tr = session.beginTransaction();
       
       //persist data
       session.save(object);
       
       //Commits transaction
       tr.commit();
       
   }*/
   
   public void save(Object object) throws Exception{
       
       //Opens transaction
       Transaction tr = session.beginTransaction();
       
       //persist data
       session.saveOrUpdate(object);
       
       //Commits transaction
       tr.commit();
       
   }
   
   public void delete(Object object) throws Exception{
       
       //Opens transaction
       Transaction tr = session.beginTransaction();
       
       //persist data
       session.delete(object);
       
       //Commits transaction
       tr.commit();
       
   }
    
}