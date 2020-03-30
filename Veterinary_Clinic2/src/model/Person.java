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
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findOne", query = "SELECT p FROM Person p WHERE p.document = :id")
})
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private int personId;
    @Column(name = "document")
    private int document;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone_number")
    private int phoneNumber;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "personId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name = "id_address")
    private List<Address> addressId;
    @Column(name = "personType")
    private int personType;

    public Person() {
    }

    public Person(int document, String name, String surname, int phoneNumber, String email, List<Address> addressId, int personType) {
        this.document = document;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressId = addressId;
        this.personType = personType;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddressId() {
        return addressId;
    }

    public void setAddressId(List<Address> addressId) {
        this.addressId = addressId;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    @Override
    public String toString() {
        return "Person{" + "idPerson=" + personId + ", document=" + document + ", name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", email=" + email + ", addressId=" + addressId + ", personType=" + personType + '}' + "\n";
    }

}
