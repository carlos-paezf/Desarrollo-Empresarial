/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageControllerBean;

import ejb.MedicalTreatmentFacadeLocal;
import entity.Address;
import entity.MedicalTreatment;
import entity.Medicine;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author David Ferrer
 */
@Named(value = "medicalTreatmentManagedBean")
@Dependent
public class MedicalTreatmentManagedBean {

    //Special Attributes
    @EJB
    private MedicalTreatmentFacadeLocal medicalTreatmentFacade;
    //Controller Attributes
    private Integer idMedicalTreatment;
    private Medicine idMedicine;
    private String nameTreatment;
    private Integer turnOfSacrifice;
    private Address placeRealization;
    private float price;
    
    /**
     * Creates a new instance of MedicalTreatmentManagedBean
     */
    public MedicalTreatmentManagedBean() {
        idMedicine = new Medicine();
        placeRealization = new Address();
    }

    public Integer getIdMedicalTreatment() {
        return idMedicalTreatment;
    }

    public void setIdMedicalTreatment(Integer idMedicalTreatment) {
        this.idMedicalTreatment = idMedicalTreatment;
    }

    public Medicine getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(Medicine idMedicine) {
        this.idMedicine = idMedicine;
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

    public Address getPlaceRealization() {
        return placeRealization;
    }

    public void setPlaceRealization(Address placeRealization) {
        this.placeRealization = placeRealization;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    //Controller Methods
    public List<MedicalTreatment> index(){
        return medicalTreatmentFacade.findAll();
    }
    
    public String view(int id){
        MedicalTreatment mt = medicalTreatmentFacade.find(id);
        
        this.idMedicalTreatment = mt.getIdMedicalTreatment();
        idMedicine = mt.getIdMedicine();
        nameTreatment = mt.getNameTreatment();
        turnOfSacrifice = mt.getTurnOfSacrifice();
        placeRealization = mt.getPlaceRealization();
        price = mt.getPrice();
        
        return "form";
    }
    
    public String create(){
        MedicalTreatment mt = new MedicalTreatment();
        
        mt.setNameTreatment(nameTreatment);
        mt.setTurnOfSacrifice(turnOfSacrifice);
        mt.setPlaceRealization(placeRealization);
        mt.setPrice(price);
        
        medicalTreatmentFacade.create(mt);
        
        return "index";
    }
    
    public String update(int id){
        MedicalTreatment mt = medicalTreatmentFacade.find(id);
        
        mt.setNameTreatment(nameTreatment);
        mt.setTurnOfSacrifice(turnOfSacrifice);
        mt.setPlaceRealization(placeRealization);
        mt.setPrice(price);
        
        medicalTreatmentFacade.edit(mt);
        
        return "index";
    }
    
    public String remove(int id){
        MedicalTreatment mt = medicalTreatmentFacade.find(id);
        
        medicalTreatmentFacade.remove(mt);
        
        return "index";
    }
}
