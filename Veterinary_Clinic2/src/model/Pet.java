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
@NamedQueries({
    @NamedQuery(name = "Pet.findAll", query = "SELECT an FROM Animal an WHERE an.animalType = 0 "),
    @NamedQuery(name = "Pet.findOne", query = "SELECT an FROM Animal an WHERE an.idAnimal = :id")
})
public class Pet extends Animal{
    
    @Column(name = "id_client_owner")
    private Person clientOwnerId;

    public Pet() {
    }

    public Pet(Person clientOwnerId, int updateVaccines, String name, String age, String species, String breed, MedicalTreatment treatmentId, Date admissionDate, Date departureDate, int animalType) {
        super(updateVaccines, name, age, species, breed, treatmentId, admissionDate, departureDate, animalType);
        this.clientOwnerId = clientOwnerId;
    }

    public Person getClientOwnerId() {
        return clientOwnerId;
    }

    public void setClientOwnerId(Person clientOwnerId) {
        this.clientOwnerId = clientOwnerId;
    }

    @Override
    public String toString() {
        return "Pet{" + "clientOwnerId=" + clientOwnerId + '}';
    }
    
}
