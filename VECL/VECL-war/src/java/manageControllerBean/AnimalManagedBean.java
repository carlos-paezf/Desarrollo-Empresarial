/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageControllerBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author David Ferrer
 */
@Named(value = "animalManagedBean")
@RequestScoped
public class AnimalManagedBean {

    /**
     * Creates a new instance of AnimalManagedBean
     */
    public AnimalManagedBean() {
    }
    
}
