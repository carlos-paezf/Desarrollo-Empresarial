/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author David Ferrer
 */
public class Cliente {
    
    int id;
    String name;
    String numberPhone;
    String address;

    public Cliente(int id, String name, String numberPhone, String address) {
        this.id = id;
        this.name = name;
        this.numberPhone = numberPhone;
        this.address = address;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", name=" + name + ", numberPhone=" + numberPhone + ", address=" + address + '}';
    }
    
}
