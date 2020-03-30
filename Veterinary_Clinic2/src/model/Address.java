/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findOne", query = "SELECT a FROM Address a WHERE a.id_address = :id")
})
public class Address implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private int addressId;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "relation", referencedColumnName = "id_address")
    private Address relation;
    @OneToMany(mappedBy = "id_address", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Address> addresses;
    
    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_address")
    private Person personId;
    
    @ManyToOne
    @JoinColumn(name = "id_medical_treatment", referencedColumnName = "id_address")
    private MedicalTreatment medicalTreatmentId;

    @ManyToOne
    @JoinColumn(name = "id_animal", referencedColumnName = "id_address")
    private StrayAnimal strayAnimal;
    
    public Address() {
    }
    
    public Address(int addressId, String name, Address relation, List<Address> addresses) {
        this.addressId = addressId;
        this.name = name;
        this.relation = relation;
        this.addresses = addresses;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getRelation() {
        return relation;
    }

    public void setRelation(Address relation) {
        this.relation = relation;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public MedicalTreatment getMedicalTreatmentId() {
        return medicalTreatmentId;
    }

    public void setMedicalTreatmentId(MedicalTreatment medicalTreatmentId) {
        this.medicalTreatmentId = medicalTreatmentId;
    }

    public StrayAnimal getStrayAnimal() {
        return strayAnimal;
    }

    public void setStrayAnimal(StrayAnimal strayAnimal) {
        this.strayAnimal = strayAnimal;
    }

    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", name=" + name + ", relation=" + relation.getAddressId() + ", addresses=" + addresses + '}' + "\n";
    }

}
