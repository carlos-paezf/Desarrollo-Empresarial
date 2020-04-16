package pojo;
// Generated 16/04/2020 05:01:36 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Person generated by hbm2java
 */
public class Person  implements java.io.Serializable {


     private Integer idPerson;
     private Address address;
     private Animal animal;
     private int document;
     private String name;
     private String surname;
     private String phoneNumber;
     private String email;
     private Integer professionalCard;
     private String medicalSpeciality;
     private Integer workShift;
     private Integer arrivalTurn;
     private int personType;
     private Set animals = new HashSet(0);
     private Set invoices = new HashSet(0);

    public Person() {
    }

	
    public Person(Address address, int document, String name, String surname, String phoneNumber, int personType) {
        this.address = address;
        this.document = document;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.personType = personType;
    }
    public Person(Address address, Animal animal, int document, String name, String surname, String phoneNumber, String email, Integer professionalCard, String medicalSpeciality, Integer workShift, Integer arrivalTurn, int personType, Set animals, Set invoices) {
       this.address = address;
       this.animal = animal;
       this.document = document;
       this.name = name;
       this.surname = surname;
       this.phoneNumber = phoneNumber;
       this.email = email;
       this.professionalCard = professionalCard;
       this.medicalSpeciality = medicalSpeciality;
       this.workShift = workShift;
       this.arrivalTurn = arrivalTurn;
       this.personType = personType;
       this.animals = animals;
       this.invoices = invoices;
    }
   
    public Integer getIdPerson() {
        return this.idPerson;
    }
    
    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }
    public Address getAddress() {
        return this.address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    public Animal getAnimal() {
        return this.animal;
    }
    
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    public int getDocument() {
        return this.document;
    }
    
    public void setDocument(int document) {
        this.document = document;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getProfessionalCard() {
        return this.professionalCard;
    }
    
    public void setProfessionalCard(Integer professionalCard) {
        this.professionalCard = professionalCard;
    }
    public String getMedicalSpeciality() {
        return this.medicalSpeciality;
    }
    
    public void setMedicalSpeciality(String medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }
    public Integer getWorkShift() {
        return this.workShift;
    }
    
    public void setWorkShift(Integer workShift) {
        this.workShift = workShift;
    }
    public Integer getArrivalTurn() {
        return this.arrivalTurn;
    }
    
    public void setArrivalTurn(Integer arrivalTurn) {
        this.arrivalTurn = arrivalTurn;
    }
    public int getPersonType() {
        return this.personType;
    }
    
    public void setPersonType(int personType) {
        this.personType = personType;
    }
    public Set getAnimals() {
        return this.animals;
    }
    
    public void setAnimals(Set animals) {
        this.animals = animals;
    }
    public Set getInvoices() {
        return this.invoices;
    }
    
    public void setInvoices(Set invoices) {
        this.invoices = invoices;
    }




}


