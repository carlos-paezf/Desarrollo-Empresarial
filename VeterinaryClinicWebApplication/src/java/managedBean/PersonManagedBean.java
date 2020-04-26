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
    private Person person;
    private int filterID;

    /**
     * Creates a new instance of PersonManagedBean
     */
    public PersonManagedBean() {
        model = new PersonModel();
        person = new Person();
    }

    /**
     * Returns the list of all stored in DB
     *
     * @return person list
     */
    public List<Person> index() {
        try {

            return model.findAll();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Persist new Person to DB
     *
     * @return person list
     */
    public String save() {
        try {
            model.save(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    
    /**
     * Find Person by ID
     *
     * @return person list
     */
    public String findById(){
        try {
            person = (Person) model.findById(filterID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

        /**
     * Delete Person to DB
     *
     * @return person list
     */
    public String delete() {
        try {
            model.delete(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getFilterID() {
        return filterID;
    }

    public void setFilterID(int filterID) {
        this.filterID = filterID;
    }
    
    
}
