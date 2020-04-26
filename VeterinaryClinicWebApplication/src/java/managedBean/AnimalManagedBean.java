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
import model.AnimalModel;
import pojo.Animal;

/**
 *
 * @author David Ferrer
 */
@Named(value = "animalManagedBean")
@RequestScoped
public class AnimalManagedBean {

    //Attributes
    private final AnimalModel model;
    private Animal animal;
    private int filterID;

    /**
     * Creates a new instance of AnimalManagedBean
     */
    public AnimalManagedBean() {
        model = new AnimalModel();
        animal = new Animal();
    }

    /**
     * Returns the list of all stored in DB
     *
     * @return animal list
     */
    public List<Animal> index() {
        try {

            return model.findAll();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Persist new Animal to DB
     *
     * @return animal list
     */
    public String save() {
        try {
            model.save(animal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    
    /**
     * Find Animal by ID
     *
     * @return animal list
     */
    public String findById(){
        try {
            animal = (Animal) model.findById(filterID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

        /**
     * Delete Animal to DB
     *
     * @return Animal list
     */
    public String delete() {
        try {
            model.delete(animal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getMessage() {
        return "signup";
    }

    public int getFilterID() {
        return filterID;
    }

    public void setFilterID(int filterID) {
        this.filterID = filterID;
    }
    
    
    
}
