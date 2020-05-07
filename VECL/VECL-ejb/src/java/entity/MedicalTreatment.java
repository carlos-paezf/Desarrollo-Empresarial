/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "medical_treatment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicalTreatment.findAll", query = "SELECT m FROM MedicalTreatment m"),
    @NamedQuery(name = "MedicalTreatment.findByIdMedicalTreatment", query = "SELECT m FROM MedicalTreatment m WHERE m.idMedicalTreatment = :idMedicalTreatment"),
    @NamedQuery(name = "MedicalTreatment.findByNameTreatment", query = "SELECT m FROM MedicalTreatment m WHERE m.nameTreatment = :nameTreatment"),
    @NamedQuery(name = "MedicalTreatment.findByTurnOfSacrifice", query = "SELECT m FROM MedicalTreatment m WHERE m.turnOfSacrifice = :turnOfSacrifice"),
    @NamedQuery(name = "MedicalTreatment.findByPrice", query = "SELECT m FROM MedicalTreatment m WHERE m.price = :price")})
public class MedicalTreatment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_medical_treatment")
    private Integer idMedicalTreatment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name_treatment")
    private String nameTreatment;
    @Column(name = "turn_of_sacrifice")
    private Integer turnOfSacrifice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @JoinColumn(name = "place_realization", referencedColumnName = "id_address")
    @ManyToOne(fetch = FetchType.LAZY)
    private Address placeRealization;
    @JoinColumn(name = "id_medicine", referencedColumnName = "id_medicine")
    @ManyToOne(fetch = FetchType.LAZY)
    private Medicine idMedicine;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTreatment", fetch = FetchType.LAZY)
    private List<Animal> animalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedicalTreatment", fetch = FetchType.LAZY)
    private List<Invoice> invoiceList;

    public MedicalTreatment() {
    }

    public MedicalTreatment(Integer idMedicalTreatment) {
        this.idMedicalTreatment = idMedicalTreatment;
    }

    public MedicalTreatment(Integer idMedicalTreatment, String nameTreatment, float price) {
        this.idMedicalTreatment = idMedicalTreatment;
        this.nameTreatment = nameTreatment;
        this.price = price;
    }

    public Integer getIdMedicalTreatment() {
        return idMedicalTreatment;
    }

    public void setIdMedicalTreatment(Integer idMedicalTreatment) {
        this.idMedicalTreatment = idMedicalTreatment;
    }

    public String getNameTreatment() {
        return nameTreatment;
    }

    public void setNameTreatment(String nameTreatment) {
        this.nameTreatment = nameTreatment;
    }

    public Integer getTurnOfSacrifice() {
        return turnOfSacrifice;
    }

    public void setTurnOfSacrifice(Integer turnOfSacrifice) {
        this.turnOfSacrifice = turnOfSacrifice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Address getPlaceRealization() {
        return placeRealization;
    }

    public void setPlaceRealization(Address placeRealization) {
        this.placeRealization = placeRealization;
    }

    public Medicine getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(Medicine idMedicine) {
        this.idMedicine = idMedicine;
    }

    @XmlTransient
    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedicalTreatment != null ? idMedicalTreatment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicalTreatment)) {
            return false;
        }
        MedicalTreatment other = (MedicalTreatment) object;
        if ((this.idMedicalTreatment == null && other.idMedicalTreatment != null) || (this.idMedicalTreatment != null && !this.idMedicalTreatment.equals(other.idMedicalTreatment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MedicalTreatment[ idMedicalTreatment=" + idMedicalTreatment + " ]";
    }
    
}
