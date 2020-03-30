/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT p FROM Person p WHERE p.personType = 1"),
    @NamedQuery(name = "Client.findOne", query = "SELECT p FROM Person p  WHERE p.arrival_turn = :id")
})
public class Client extends Person {

    @Column(name = "arrival_turn")
    private int arrivalTurn;

    public Client() {
    }

    public Client(int arrivalTurn, int document, String name, String surname, int phoneNumber, String email, List<Address> addressId, int personType) {
        super(document, name, surname, phoneNumber, email, addressId, personType);
        this.arrivalTurn = arrivalTurn;
    }

    public int getArrivalTurn() {
        return arrivalTurn;
    }

    public void setArrivalTurn(int arrivalTurn) {
        this.arrivalTurn = arrivalTurn;
    }

    @Override
    public String toString() {
        return "Client{" + "arrivalTurn=" + arrivalTurn + '}';
    }

}
