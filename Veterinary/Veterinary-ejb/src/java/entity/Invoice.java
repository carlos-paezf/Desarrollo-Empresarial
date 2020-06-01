/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByIdInvoice", query = "SELECT i FROM Invoice i WHERE i.idInvoice = :idInvoice"),
    @NamedQuery(name = "Invoice.findByExpeditionDate", query = "SELECT i FROM Invoice i WHERE i.expeditionDate = :expeditionDate"),
    @NamedQuery(name = "Invoice.findByTotalPrice", query = "SELECT i FROM Invoice i WHERE i.totalPrice = :totalPrice")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_invoice")
    private Integer idInvoice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expedition_date")
    @Temporal(TemporalType.DATE)
    private Date expeditionDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_price")
    private float totalPrice;
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person idPerson;
    @JoinColumn(name = "id_animal", referencedColumnName = "id_animal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Animal idAnimal;
    @JoinColumn(name = "id_medical_treatment", referencedColumnName = "id_medical_treatment")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MedicalTreatment idMedicalTreatment;

    public Invoice() {
    }

    public Invoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Invoice(Integer idInvoice, Date expeditionDate, float totalPrice) {
        this.idInvoice = idInvoice;
        this.expeditionDate = expeditionDate;
        this.totalPrice = totalPrice;
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(Date expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    public Animal getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Animal idAnimal) {
        this.idAnimal = idAnimal;
    }

    public MedicalTreatment getIdMedicalTreatment() {
        return idMedicalTreatment;
    }

    public void setIdMedicalTreatment(MedicalTreatment idMedicalTreatment) {
        this.idMedicalTreatment = idMedicalTreatment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInvoice != null ? idInvoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.idInvoice == null && other.idInvoice != null) || (this.idInvoice != null && !this.idInvoice.equals(other.idInvoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Invoice[ idInvoice=" + idInvoice + " ]";
    }
    
}
