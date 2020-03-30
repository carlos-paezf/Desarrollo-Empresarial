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
@Table(name = "medical_treatment")
@NamedQueries({
    @NamedQuery(name="MedicalTreatment.findAll", query="SELECT mt FROM MedicalTreatment mt"),
    @NamedQuery(name="MedicalTreatment.findOne", query="SELECT mt FROM MedicalTreatment mt WHERE mt.id_medical_treatment = :id")
})
public class MedicalTreatment implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medical_treatment")
    private int medicalTreatmentId;
    @ManyToOne
    @JoinColumn(name="id_medicine", referencedColumnName = "id_medical_treatment")
    private Medicine medicineId;
    @Column(name = "name_treatment")
    private String nameTreatment;
    @Column(name="price")
    private float price;
    @Column(name = "turn_of_sacrifice")
    private int turnSacrifice;
    @OneToMany(mappedBy = "medicalTreatmentId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name="place_realization")
    private Address placeRealization;

    @ManyToOne
    @JoinColumn(name="id_animal", referencedColumnName = "id_medical_treatment")
    private Animal animalId;
    
    public MedicalTreatment() {
    }

    public MedicalTreatment(Medicine medicineId, String nameTreatment, float price) {
        this.medicineId = medicineId;
        this.nameTreatment = nameTreatment;
        this.price = price;
    }

    public MedicalTreatment(Medicine medicineId, String nameTreatment, float price, int turnSacrifice, Address placeRealization) {
        this.medicineId = medicineId;
        this.nameTreatment = nameTreatment;
        this.price = price;
        this.turnSacrifice = turnSacrifice;
        this.placeRealization = placeRealization;
    }

    public int getMedicalTreatmentId() {
        return medicalTreatmentId;
    }

    public void setMedicalTreatmentId(int medicalTreatmentId) {
        this.medicalTreatmentId = medicalTreatmentId;
    }

    public Medicine getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Medicine medicineId) {
        this.medicineId = medicineId;
    }

    public String getNameTreatment() {
        return nameTreatment;
    }

    public void setNameTreatment(String nameTreatment) {
        this.nameTreatment = nameTreatment;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTurnSacrifice() {
        return turnSacrifice;
    }

    public void setTurnSacrifice(int turnSacrifice) {
        this.turnSacrifice = turnSacrifice;
    }

    public Address getPlaceRealization() {
        return placeRealization;
    }

    public void setPlaceRealization(Address placeRealization) {
        this.placeRealization = placeRealization;
    }

    public Animal getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Animal animalId) {
        this.animalId = animalId;
    }

    @Override
    public String toString() {
        return "MedicalTreatment{" + "medicalTreatmentId=" + medicalTreatmentId + ", medicineId=" + medicineId.getMedicineId() + ", nameTreatment=" + nameTreatment + ", price=" + price + ", turnSacrifice=" + turnSacrifice + ", placeRealization=" + placeRealization + '}';
    }

}
