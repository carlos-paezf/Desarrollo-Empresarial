/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name="medicine")
@NamedQueries({
    @NamedQuery(name="Medicine.findAll", query="SELECT m FROM Medicine m"),
    @NamedQuery(name="Medicine.findOne", query="SELECT m FROM Medicine m WHERE m.id_medicine = :id")
})
public class Medicine implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicine")
    private int medicineId;
    @Column(name="barcode")
    private int barcode;
    @Column(name="name_medicine")
    private String nameMedicine;
    @Column(name = "quantity_in_warehouse")
    private int quantityWarehouse;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Column(name= "manufacturer")
    private String manufacturer;

    @OneToMany(mappedBy = "id_medicine", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MedicalTreatment>medicalTreatments; 
    
    public Medicine() {
    }

    public Medicine(int barcode, String nameMedicine, int quantityWarehouse, Date expirationDate, String manufacturer) {
        this.barcode = barcode;
        this.nameMedicine = nameMedicine;
        this.quantityWarehouse = quantityWarehouse;
        this.expirationDate = expirationDate;
        this.manufacturer = manufacturer;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getNameMedicine() {
        return nameMedicine;
    }

    public void setNameMedicine(String nameMedicine) {
        this.nameMedicine = nameMedicine;
    }

    public int getQuantityWarehouse() {
        return quantityWarehouse;
    }

    public void setQuantityWarehouse(int quantityWarehouse) {
        this.quantityWarehouse = quantityWarehouse;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<MedicalTreatment> getMedicalTreatments() {
        return medicalTreatments;
    }

    public void setMedicalTreatments(List<MedicalTreatment> medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }

    @Override
    public String toString() {
        return "Medicine{" + "medicineId=" + medicineId + ", barcode=" + barcode + ", nameMedicine=" + nameMedicine + ", quantityWarehouse=" + quantityWarehouse + ", expirationDate=" + expirationDate + ", manufacturer=" + manufacturer + '}';
    }
    
}
