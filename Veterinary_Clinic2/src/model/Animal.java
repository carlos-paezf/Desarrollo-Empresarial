/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "animal")
@NamedQueries({
    @NamedQuery(name="Animal.findAll", query="SELECT an FROM Animal an"),
    @NamedQuery(name="Animal.findOne", query="SELECT an FROM Animal an WHERE an.id_animal = :id")
})
public class Animal implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_animal")
    private int idAnimal;
    @Column(name= "update_vaccines")
    private int updateVaccines;
    @Column(name = "name")
    private String name;
    @Column(name="age")
    private String age;
    @Column(name="species")
    private String species;
    @Column(name="breed")
    private String breed;
    @ManyToOne
    @JoinColumn(name ="id_medical_treatment", referencedColumnName = "id_animal")
    private MedicalTreatment treatmentId;
    @Column(name = "admission_date")
    @Temporal(TemporalType.DATE)
    private Date admissionDate;
    @Column(name = "departure_date")
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Column(name= "animalType")
    private int animalType;

    public Animal() {
    }

    public Animal(int updateVaccines, String name, String age, String species, String breed, MedicalTreatment treatmentId, Date admissionDate, Date departureDate, int animalType) {
        this.updateVaccines = updateVaccines;
        this.name = name;
        this.age = age;
        this.species = species;
        this.breed = breed;
        this.treatmentId = treatmentId;
        this.admissionDate = admissionDate;
        this.departureDate = departureDate;
        this.animalType = animalType;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int isUpdateVaccines() {
        return updateVaccines;
    }

    public void setUpdateVaccines(int updateVaccines) {
        this.updateVaccines = updateVaccines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public MedicalTreatment getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(MedicalTreatment treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getAnimalType() {
        return animalType;
    }

    public void setAnimalType(int animalType) {
        this.animalType = animalType;
    }

    @Override
    public String toString() {
        return "Animal{" + "idAnimal=" + idAnimal + ", updateVaccines=" + updateVaccines + ", name=" + name + ", age=" + age + ", species=" + species + ", breed=" + breed + ", treatmentId=" + treatmentId.getMedicalTreatmentId() + ", admissionDate=" + admissionDate + ", departureDate=" + departureDate + ", animalType=" + animalType + '}';
    }
  
}
