/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name="person")
@NamedQueries({
    @NamedQuery(name="Person.findAll", query="SELECT p FROM Person p"),
    @NamedQuery(name="Person.findOne", query="SELECT p FROM Person p WHERE p.document = :id")
})
public class Person implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="document")
    private int document;
    @Column(name="name_surname")
    private String nameSurname;
    @Column(name="phone_number")
    private int phoneNumber;
    @Column(name="e-mail")
    private String email;
    @Column(name="address")
    private String address;

    public Person() {
    }

    public Person(int document, String nameSurname, int phoneNumber, String email, String address) {
        this.document = document;
        this.nameSurname = nameSurname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" + "document=" + document + ", nameSurname=" + nameSurname + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + '}' + "\n" ;
    }
    
}
