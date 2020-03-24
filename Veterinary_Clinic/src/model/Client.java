/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author David Ferrer
 */
@Entity
@Table(name = "client")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findOne", query = "SELECT c FROM Client c WHERE c.id_client = :id")
})
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private int idClient;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "document_client_fk", referencedColumnName = "document")
    private Person document;
    
    public Client() {
    }

    public Client(int idClient, Person document) {
        this.idClient = idClient;
        this.document = document;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Person getDocument() {
        return document;
    }

    public void setDocument(Person document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", document=" + document.getDocument() + '}';
    }
    
}
