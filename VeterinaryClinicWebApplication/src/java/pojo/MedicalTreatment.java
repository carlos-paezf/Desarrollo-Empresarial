package pojo;
// Generated 26/04/2020 02:40:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * MedicalTreatment generated by hbm2java
 */
public class MedicalTreatment  implements java.io.Serializable {


     private Integer idMedicalTreatment;
     private Address address;
     private Medicine medicine;
     private String nameTreatment;
     private Integer turnOfSacrifice;
     private float price;
     private Set invoices = new HashSet(0);
     private Set animals = new HashSet(0);

    public MedicalTreatment() {
    }

	
    public MedicalTreatment(String nameTreatment, float price) {
        this.nameTreatment = nameTreatment;
        this.price = price;
    }
    public MedicalTreatment(Address address, Medicine medicine, String nameTreatment, Integer turnOfSacrifice, float price, Set invoices, Set animals) {
       this.address = address;
       this.medicine = medicine;
       this.nameTreatment = nameTreatment;
       this.turnOfSacrifice = turnOfSacrifice;
       this.price = price;
       this.invoices = invoices;
       this.animals = animals;
    }
   
    public Integer getIdMedicalTreatment() {
        return this.idMedicalTreatment;
    }
    
    public void setIdMedicalTreatment(Integer idMedicalTreatment) {
        this.idMedicalTreatment = idMedicalTreatment;
    }
    public Address getAddress() {
        return this.address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    public Medicine getMedicine() {
        return this.medicine;
    }
    
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    public String getNameTreatment() {
        return this.nameTreatment;
    }
    
    public void setNameTreatment(String nameTreatment) {
        this.nameTreatment = nameTreatment;
    }
    public Integer getTurnOfSacrifice() {
        return this.turnOfSacrifice;
    }
    
    public void setTurnOfSacrifice(Integer turnOfSacrifice) {
        this.turnOfSacrifice = turnOfSacrifice;
    }
    public float getPrice() {
        return this.price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    public Set getInvoices() {
        return this.invoices;
    }
    
    public void setInvoices(Set invoices) {
        this.invoices = invoices;
    }
    public Set getAnimals() {
        return this.animals;
    }
    
    public void setAnimals(Set animals) {
        this.animals = animals;
    }




}


