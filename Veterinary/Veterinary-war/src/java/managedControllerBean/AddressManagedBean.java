/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedControllerBean;

import ejb.AddressFacadeLocal;
import entity.Address;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author David Ferrer
 */
@Named(value = "addressManagedBean")
@Dependent
public class AddressManagedBean {

    //Special Attributes
    @EJB
    private AddressFacadeLocal addressFacade;
    //Controller Attributes
    private Integer idAddress;
    private String name;
    private Address relation;

    /**
     * Creates a new instance of AddressManagedBean
     */
    public AddressManagedBean() {
        relation = new Address();
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getRelation() {
        return relation;
    }

    public void setRelation(Address relation) {
        this.relation = relation;
    }

    //Controller Methods
    public List<Address> index(){
        return addressFacade.findAll();
    }
    
    public String view(int id){
        Address a = addressFacade.find(id);
        
        this.idAddress = a.getIdAddress();
        name = a.getName();
        relation = a.getRelation();
        
        return "form";
    }
    
    public String create(){
        Address a = new Address();
        
        a.setName(name);
        a.setRelation(relation);
        
        addressFacade.create(a);
        
        return "index";
    }
    
    public String update(int id){
        Address a = addressFacade.find(id);
        
        a.setName(name);
        a.setRelation(relation);
        
        return "index";
    }
    
    public String remove(int id){
        Address a = addressFacade.find(id);
        
        addressFacade.remove(a);
        
        return "index";
    }
    
}
