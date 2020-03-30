/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "animal")
@NamedQuery(name = "StrayAnimal.findAll", query = "SELECT an FROM Animal an WHERE an.animalType = 1")
public class StrayAnimal extends Animal {

    @OneToMany(mappedBy = "animalId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name = "found_in")
    private Address foundIn;

    public StrayAnimal() {
    }

    public StrayAnimal(Address foundIn, int updateVaccines, String name, String age, String species, String breed, MedicalTreatment treatmentId, Date admissionDate, Date departureDate, int animalType) {
        super(updateVaccines, name, age, species, breed, treatmentId, admissionDate, departureDate, animalType);
        this.foundIn = foundIn;
    }

    public Address getFoundIn() {
        return foundIn;
    }

    public void setFoundIn(Address foundIn) {
        this.foundIn = foundIn;
    }

    @Override
    public String toString() {
        return "StrayAnimal{" + "foundIn=" + foundIn + '}';
    }
    
}
