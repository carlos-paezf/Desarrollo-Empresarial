/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "medicine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicine.findAll", query = "SELECT m FROM Medicine m"),
    @NamedQuery(name = "Medicine.findByIdMedicine", query = "SELECT m FROM Medicine m WHERE m.idMedicine = :idMedicine"),
    @NamedQuery(name = "Medicine.findByBarcode", query = "SELECT m FROM Medicine m WHERE m.barcode = :barcode"),
    @NamedQuery(name = "Medicine.findByNameMedicine", query = "SELECT m FROM Medicine m WHERE m.nameMedicine = :nameMedicine"),
    @NamedQuery(name = "Medicine.findByQuantityInWarehouse", query = "SELECT m FROM Medicine m WHERE m.quantityInWarehouse = :quantityInWarehouse"),
    @NamedQuery(name = "Medicine.findByExpirationDate", query = "SELECT m FROM Medicine m WHERE m.expirationDate = :expirationDate"),
    @NamedQuery(name = "Medicine.findByManufacturer", query = "SELECT m FROM Medicine m WHERE m.manufacturer = :manufacturer")})
public class Medicine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_medicine")
    private Integer idMedicine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "barcode")
    private int barcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name_medicine")
    private String nameMedicine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity_in_warehouse")
    private int quantityInWarehouse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "manufacturer")
    private String manufacturer;
    @OneToMany(mappedBy = "idMedicine", fetch = FetchType.LAZY)
    private List<MedicalTreatment> medicalTreatmentList;

    public Medicine() {
    }

    public Medicine(Integer idMedicine) {
        this.idMedicine = idMedicine;
    }

    public Medicine(Integer idMedicine, int barcode, String nameMedicine, int quantityInWarehouse, Date expirationDate, String manufacturer) {
        this.idMedicine = idMedicine;
        this.barcode = barcode;
        this.nameMedicine = nameMedicine;
        this.quantityInWarehouse = quantityInWarehouse;
        this.expirationDate = expirationDate;
        this.manufacturer = manufacturer;
    }

    public Integer getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(Integer idMedicine) {
        this.idMedicine = idMedicine;
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

    public int getQuantityInWarehouse() {
        return quantityInWarehouse;
    }

    public void setQuantityInWarehouse(int quantityInWarehouse) {
        this.quantityInWarehouse = quantityInWarehouse;
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

    @XmlTransient
    public List<MedicalTreatment> getMedicalTreatmentList() {
        return medicalTreatmentList;
    }

    public void setMedicalTreatmentList(List<MedicalTreatment> medicalTreatmentList) {
        this.medicalTreatmentList = medicalTreatmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedicine != null ? idMedicine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicine)) {
            return false;
        }
        Medicine other = (Medicine) object;
        if ((this.idMedicine == null && other.idMedicine != null) || (this.idMedicine != null && !this.idMedicine.equals(other.idMedicine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Medicine[ idMedicine=" + idMedicine + " ]";
    }
    
}
