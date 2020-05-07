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
@Table(name = "animal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a"),
    @NamedQuery(name = "Animal.findByIdAnimal", query = "SELECT a FROM Animal a WHERE a.idAnimal = :idAnimal"),
    @NamedQuery(name = "Animal.findByUpdateVaccines", query = "SELECT a FROM Animal a WHERE a.updateVaccines = :updateVaccines"),
    @NamedQuery(name = "Animal.findByName", query = "SELECT a FROM Animal a WHERE a.name = :name"),
    @NamedQuery(name = "Animal.findByAge", query = "SELECT a FROM Animal a WHERE a.age = :age"),
    @NamedQuery(name = "Animal.findBySpecies", query = "SELECT a FROM Animal a WHERE a.species = :species"),
    @NamedQuery(name = "Animal.findByBreed", query = "SELECT a FROM Animal a WHERE a.breed = :breed"),
    @NamedQuery(name = "Animal.findByAdmissionDate", query = "SELECT a FROM Animal a WHERE a.admissionDate = :admissionDate"),
    @NamedQuery(name = "Animal.findByDepartureDate", query = "SELECT a FROM Animal a WHERE a.departureDate = :departureDate"),
    @NamedQuery(name = "Animal.findByAnimalType", query = "SELECT a FROM Animal a WHERE a.animalType = :animalType")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_animal")
    private Integer idAnimal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "update_vaccines")
    private boolean updateVaccines;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "age")
    private String age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "species")
    private String species;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "breed")
    private String breed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "admission_date")
    @Temporal(TemporalType.DATE)
    private Date admissionDate;
    @Column(name = "departure_date")
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "animalType")
    private int animalType;
    @OneToMany(mappedBy = "idRescuedPet", fetch = FetchType.LAZY)
    private List<Person> personList;
    @JoinColumn(name = "found_in", referencedColumnName = "id_address")
    @ManyToOne(fetch = FetchType.LAZY)
    private Address foundIn;
    @JoinColumn(name = "id_client_owner", referencedColumnName = "id_person")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person idClientOwner;
    @JoinColumn(name = "id_treatment", referencedColumnName = "id_medical_treatment")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MedicalTreatment idTreatment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnimal", fetch = FetchType.LAZY)
    private List<Invoice> invoiceList;

    public Animal() {
    }

    public Animal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Animal(Integer idAnimal, boolean updateVaccines, String age, String species, String breed, Date admissionDate, int animalType) {
        this.idAnimal = idAnimal;
        this.updateVaccines = updateVaccines;
        this.age = age;
        this.species = species;
        this.breed = breed;
        this.admissionDate = admissionDate;
        this.animalType = animalType;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public boolean getUpdateVaccines() {
        return updateVaccines;
    }

    public void setUpdateVaccines(boolean updateVaccines) {
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Address getFoundIn() {
        return foundIn;
    }

    public void setFoundIn(Address foundIn) {
        this.foundIn = foundIn;
    }

    public Person getIdClientOwner() {
        return idClientOwner;
    }

    public void setIdClientOwner(Person idClientOwner) {
        this.idClientOwner = idClientOwner;
    }

    public MedicalTreatment getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(MedicalTreatment idTreatment) {
        this.idTreatment = idTreatment;
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
        hash += (idAnimal != null ? idAnimal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.idAnimal == null && other.idAnimal != null) || (this.idAnimal != null && !this.idAnimal.equals(other.idAnimal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Animal[ idAnimal=" + idAnimal + " ]";
    }
    
}
