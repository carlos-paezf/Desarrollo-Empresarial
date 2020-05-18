/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageControllerBean;

import ejb.MedicineFacadeLocal;
import entity.Medicine;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author David Ferrer
 */
@Named(value = "medicineManagedBean")
@Dependent
public class MedicineManagedBean {

    //Special Attributes
    @EJB
    private MedicineFacadeLocal medicineFacade;
    //Controller Attributes
    private Integer idMedicine;
    private int barcode;
    private String nameMedicine;
    private int quantityInWarehouse;
    private Date expirationDate;
    private String manufacturer;
    
    /**
     * Creates a new instance of MedicineManagedBean
     */
    public MedicineManagedBean() {
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
    
    //Controller Methods
    public List<Medicine> index(){
        return medicineFacade.findAll();
    }
    
    public String view(int id){
        Medicine m = medicineFacade.find(id);
        
        idMedicine = m.getIdMedicine();
        barcode = m.getBarcode();
        nameMedicine = m.getNameMedicine();
        quantityInWarehouse = m.getQuantityInWarehouse();
        expirationDate = m.getExpirationDate();
        manufacturer = m.getManufacturer();
        
        return "form";
    }
    
    public String create(){
        Medicine m = new Medicine();
        
        m.setBarcode(barcode);
        m.setNameMedicine(nameMedicine);
        m.setQuantityInWarehouse(quantityInWarehouse);
        m.setExpirationDate(expirationDate);
        m.setManufacturer(manufacturer);
        
        medicineFacade.create(m);
        
        return "index";
    }
    
    public String update(int id){
        Medicine m = medicineFacade.find(id);
        
        m.setBarcode(barcode);
        m.setNameMedicine(nameMedicine);
        m.setQuantityInWarehouse(quantityInWarehouse);
        m.setExpirationDate(expirationDate);
        m.setManufacturer(manufacturer);
        
        medicineFacade.edit(m);
        
        return "index";
    }
    
    public String remove(int id){
        Medicine m = medicineFacade.find(id);
        
        medicineFacade.remove(m);
        
        return "index";
    }
}
