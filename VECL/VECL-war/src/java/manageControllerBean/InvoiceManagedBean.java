/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageControllerBean;

import ejb.InvoiceFacadeLocal;
import entity.Animal;
import entity.Invoice;
import entity.MedicalTreatment;
import entity.Person;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author David Ferrer
 */
@Named(value = "invoiceManagedBean")
@Dependent
public class InvoiceManagedBean {

    //Special Attributes
    @EJB
    private InvoiceFacadeLocal invoiceFacade;
    //Controller Attributes
    private Integer idInvoice;
    private Date expeditionDate;
    private float totalPrice;
    private Animal idAnimal;
    private Person idPerson;
    private MedicalTreatment idMedicalTreatment;

    /**
     * Creates a new instance of InvoiceManagedBean
     */
    public InvoiceManagedBean() {
        idAnimal = new Animal();
        idPerson = new Person();
        idMedicalTreatment = new MedicalTreatment();
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

    public Animal getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Animal idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    public MedicalTreatment getIdMedicalTreatment() {
        return idMedicalTreatment;
    }

    public void setIdMedicalTreatment(MedicalTreatment idMedicalTreatment) {
        this.idMedicalTreatment = idMedicalTreatment;
    }

    //Controller Methods
    public List<Invoice> index() {
        return invoiceFacade.findAll();
    }

    public String view(int id) {
        Invoice i = invoiceFacade.find(id);

        this.idInvoice = i.getIdInvoice();
        idPerson = i.getIdPerson();
        idAnimal = i.getIdAnimal();
        expeditionDate = i.getExpeditionDate();
        idMedicalTreatment = i.getIdMedicalTreatment();
        totalPrice = i.getTotalPrice();

        return "form";
    }

    public String create() {
        Invoice i = new Invoice();

        i.setIdAnimal(idAnimal);
        i.setExpeditionDate(expeditionDate);
        i.setIdMedicalTreatment(idMedicalTreatment);
        i.setTotalPrice(totalPrice);

        invoiceFacade.create(i);

        return "index";
    }

    public String update(int id) {
        Invoice i = invoiceFacade.find(id);

        i.setIdPerson(idPerson);
        i.setIdAnimal(idAnimal);
        i.setExpeditionDate(expeditionDate);
        i.setIdMedicalTreatment(idMedicalTreatment);
        i.setTotalPrice(totalPrice);

        invoiceFacade.edit(i);

        return "index";
    }
    
    public String remove(int id){
        Invoice i = invoiceFacade.find(id);
        
        invoiceFacade.remove(i);
        
        return "index";
    }
}
