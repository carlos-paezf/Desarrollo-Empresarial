/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageControllerBean;

import ejb.AnimalFacadeLocal;
import ejb.InvoiceFacadeLocal;
import ejb.PersonFacadeLocal;
import entity.Address;
import entity.Animal;
import entity.Invoice;
import entity.Person;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author David Ferrer
 */
@Named(value = "personManagedBean")
@RequestScoped
public class PersonManagedBean {
    
    //Special Attributes
    @EJB
    private PersonFacadeLocal personFacade;
    //Controller Attributes
    private Integer idPerson;
    private int document;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Address idAddress;
    private Integer professionalCard;
    private String medicalSpeciality;
    private Integer workShift;
    private Integer arrivalTurn;
    private Animal idRescuedPet;
    private int personType;
    private String totalName;


    /**
     * Creates a new instance of PersonManagedBean
     */
    public PersonManagedBean() {
        idAddress = new Address();
        idRescuedPet = new Animal();
    }
    
    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Address idAddress) {
        this.idAddress = idAddress;
    }

    public Integer getProfessionalCard() {
        return professionalCard;
    }

    public void setProfessionalCard(Integer professionalCard) {
        this.professionalCard = professionalCard;
    }

    public String getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(String medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }

    public Integer getWorkShift() {
        return workShift;
    }

    public void setWorkShift(Integer workShift) {
        this.workShift = workShift;
    }

    public Integer getArrivalTurn() {
        return arrivalTurn;
    }

    public void setArrivalTurn(Integer arrivalTurn) {
        this.arrivalTurn = arrivalTurn;
    }

    public Animal getIdRescuedPet() {
        return idRescuedPet;
    }

    public void setIdRescuedPet(Animal idRescuedPet) {
        this.idRescuedPet = idRescuedPet;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }
    
    //Controller methods
    public List<Person> index(){
        return personFacade.findAll();
    }
    
    public String view(int id){
        Person p = personFacade.find(id);
        
        this.idPerson = p.getIdPerson();
        document = p.getDocument();
        name = p.getName();
        surname = p.getSurname();
        phoneNumber = p.getPhoneNumber();
        email = p.getEmail();
        idAddress = p.getIdAddress();
        professionalCard = p.getProfessionalCard();
        medicalSpeciality = p.getMedicalSpeciality();
        workShift = p.getWorkShift();
        arrivalTurn = p.getArrivalTurn();
        personType = p.getPersonType();
        
        return "form";
    }
    
    public String create(){
        Person p = new Person();
        
        p.setDocument(document);
        p.setName(name);
        p.setSurname(surname);
        p.setPhoneNumber(phoneNumber);
        p.setEmail(email);
        p.setIdAddress(idAddress);
        p.setProfessionalCard(professionalCard);
        p.setMedicalSpeciality(medicalSpeciality);
        p.setWorkShift(workShift);
        p.setArrivalTurn(arrivalTurn);
        p.setIdRescuedPet(idRescuedPet);
        p.setPersonType(0);
        
        personFacade.create(p);
        
        //return "view"+"/"+p.getIdPerson();
        return "index";
    }
    
    public String update(int id){
        Person p = personFacade.find(id);
        
        p.setDocument(document);
        p.setName(name);
        p.setSurname(surname);
        p.setPhoneNumber(phoneNumber);
        p.setEmail(email);
        p.setIdAddress(idAddress);
        p.setProfessionalCard(professionalCard);
        p.setMedicalSpeciality(medicalSpeciality);
        p.setWorkShift(workShift);
        p.setArrivalTurn(arrivalTurn);
        p.setIdRescuedPet(idRescuedPet);
        p.setPersonType(0);
        
        personFacade.edit(p);
        
        return "index";
    }
    
    public String remove(int id){
        Person p = personFacade.find(id);
        
        personFacade.remove(p);
        
        return "index";
    }

    public String getTotalName() {
        return totalName = name.concat(surname);
    }
    
    
}
