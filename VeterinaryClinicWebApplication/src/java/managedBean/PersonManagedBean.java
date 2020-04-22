/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.PersonModel;
import pojo.Person;

/**
 *
 * @author David Ferrer
 */
@Named(value = "personManagedBean")
@RequestScoped
public class PersonManagedBean {

    //Attributes
    private final PersonModel model;
    
    /**
     * Creates a new instance of ClientManagedBean
     */
    public PersonManagedBean() {
        model = new PersonModel();
    }
    
    /**
     * Returns the list of all stored in DB
     * @return client list
     */
    public List<Person> index(){
        try {
            
            return model.findAll();
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
}
