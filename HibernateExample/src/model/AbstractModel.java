/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

/**
 *
 * @author andresforero
 */
public abstract class AbstractModel {
    
    protected String className;
    
    public AbstractModel(String className){
        this.className = className;
    }
    
    public List findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        //Hibernate Query Language (HQL)
        return session.createQuery(" from " + className ).list();
    }
    
   public Object findById(int id) throws Exception{
       SessionFactory sf = HibernateUtil.getSessionFactory();
       Session session = sf.openSession();
       
       //Hibernate Query Language (HQL)
       return session.createQuery(" from " + className + " where id = " + id).uniqueResult();
   }
    
public void create(Object object) throws Exception{
SessionFactory sf = HibernateUtil.getSessionFactory();
       Session session = sf.openSession();

//Open Transaction

}
}
