/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Veterinarian.findAll", query = "SELECT p FROM Person p WHERE p.personType = 0"),
    @NamedQuery(name = "Veterinarias.findOne", query = "SELECT p FROM Person p WHERE p.professional_card = :id")
})
public class Veterinarian extends Person {

    @Column(name = "professional_card")
    private int professionalCard;
    @Column(name = "medical_speciality")
    private String medicalSpeciality;
    @Column(name = "work_shift")
    private int workShift;

    public Veterinarian() {
    }

    public Veterinarian(int professionalCard, String medicalSpeciality, int workShift, int document, String name, String surname, int phoneNumber, String email, List<Address> addressId, int personType) {
        super(document, name, surname, phoneNumber, email, addressId, personType);
        this.professionalCard = professionalCard;
        this.medicalSpeciality = medicalSpeciality;
        this.workShift = workShift;
    }

    public int getProfessionalCard() {
        return professionalCard;
    }

    public void setProfessionalCard(int professionalCard) {
        this.professionalCard = professionalCard;
    }

    public String getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(String medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }

    public int getWorkShift() {
        return workShift;
    }

    public void setWorkShift(int workShift) {
        this.workShift = workShift;
    }

    @Override
    public String toString() {
        return "Veterinarian{" + "professionalCard=" + professionalCard + ", medicalSpeciality=" + medicalSpeciality + ", workShift=" + workShift + '}';
    }
    
    

}
