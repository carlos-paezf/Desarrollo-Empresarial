/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.MedicineModel;
import pojo.Medicine;

/**
 *
 * @author David Ferrer
 */
@Named(value = "medicineManagedBean")
@RequestScoped
public class MedicineManagedBean {

    //Attributes
    private final MedicineModel model;
    private Medicine medicine;
    private int filterID;
    
    /**
     * Creates a new instance of MedicineManagedBean
     */
    public MedicineManagedBean() {
        model = new MedicineModel();
        medicine = new Medicine();
    }
    
    /**
     * Returns the list of all stored in DB
     *
     * @return medicine list
     */
    public List<Medicine> index() {
        try {

            return model.findAll();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Persist new Medicine to DB
     *
     * @return medicine list
     */
    public String save() {
        try {
            model.save(medicine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    
    /**
     * Find Medicine by ID
     *
     * @return Medicine list
     */
    public String findById(){
        try {
            medicine = (Medicine) model.findById(filterID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

        /**
     * Delete Medicine to DB
     *
     * @return medicine list
     */
    public String delete() {
        try {
            model.delete(medicine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getFilterID() {
        return filterID;
    }

    public void setFilterID(int filterID) {
        this.filterID = filterID;
    }
    
}
