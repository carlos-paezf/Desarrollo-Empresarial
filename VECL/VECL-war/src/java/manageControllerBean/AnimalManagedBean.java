/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageControllerBean;

import ejb.AnimalFacadeLocal;
import entity.Address;
import entity.Animal;
import entity.MedicalTreatment;
import entity.Person;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author David Ferrer
 */
@Named(value = "animalManagedBean")
@RequestScoped
public class AnimalManagedBean {

    //Special Attributes
    @EJB
    private AnimalFacadeLocal animalFacade;
    //Controller Attributes
    private Integer idAnimal;
    private boolean updateVaccines;
    private String name;
    private String age;
    private String species;
    private String breed;
    private Person idClientOwner;
    private MedicalTreatment idTreatment;
    private Date admissionDate;
    private Date departureDate;
    private Address foundIn;
    private int animalType;
    
    /**
     * Creates a new instance of AnimalManagedBean
     */
    public AnimalManagedBean() {
        idClientOwner = new Person();
        idTreatment = new MedicalTreatment();
        foundIn = new Address();
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public boolean isUpdateVaccines() {
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

    public Address getFoundIn() {
        return foundIn;
    }

    public void setFoundIn(Address foundIn) {
        this.foundIn = foundIn;
    }

    public int getAnimalType() {
        return animalType;
    }

    public void setAnimalType(int animalType) {
        this.animalType = animalType;
    }
    
    //Controller Methods
    public List<Animal> index(){
        return animalFacade.findAll();
    }
    
    public String view(int id){
        Animal a = animalFacade.find(id);
        
        this.idAnimal = a.getIdAnimal();
        updateVaccines = a.getUpdateVaccines();
        name = a.getName();
        age = a.getAge();
        species = a.getSpecies();
        breed = a.getBreed();
        idClientOwner = a.getIdClientOwner();
        idTreatment = a.getIdTreatment();
        admissionDate = a.getAdmissionDate();
        departureDate = a.getDepartureDate();
        foundIn = a.getFoundIn();
        animalType = a.getAnimalType();
        
        return "form";
    }
    
    public String create(){
        Animal a = new Animal();
        
        a.setUpdateVaccines(updateVaccines);
        a.setName(name);
        a.setAge(age);
        a.setSpecies(species);
        a.setBreed(breed);
        a.setIdClientOwner(idClientOwner);
        a.setIdTreatment(idTreatment);
        a.setAdmissionDate(admissionDate);
        a.setDepartureDate(departureDate);
        a.setFoundIn(foundIn);
        a.setAnimalType(0);
        
        animalFacade.create(a);
        
        return "index";
    }
    
    public String update(int id){
        Animal a = animalFacade.find(id);
        
        a.setUpdateVaccines(updateVaccines);
        a.setName(name);
        a.setAge(age);
        a.setSpecies(species);
        a.setBreed(breed);
        a.setIdClientOwner(idClientOwner);
        a.setIdTreatment(idTreatment);
        a.setAdmissionDate(admissionDate);
        a.setDepartureDate(departureDate);
        a.setFoundIn(foundIn);
        a.setAnimalType(animalType);
        
        animalFacade.edit(a);
        
        return "index";
    }
    
    public String remove(int id){
        Animal a = animalFacade.find(id);
        
        animalFacade.remove(a);
        
        return "index";
    }
}
