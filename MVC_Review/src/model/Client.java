/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Estudiante
 */
public class Client {

    //Const
    public static final int ID = 0;
    public static final int PHONE_NUMBER = 2;
    public static final int NAME = 1;
    public static final int ADDRESS = 3;

    private int id;
    private String name;
    private String phoneNumber;
    private String address;

    public Client() {
    }

    public Client(int id, String name, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address + '}';
    }

 

}
