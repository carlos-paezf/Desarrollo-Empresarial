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
    @JoinColumn(name = "document_person", referencedColumnName = "document")
    private Person documentPerson;
    
    public Client() {
    }

    public Client(int idClient, Person document) {
        this.idClient = idClient;
        this.documentPerson = document;
    }

    public Client(Person document) {
        this.documentPerson = document;
    }
    
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Person getDocumentPerson() {
        return documentPerson;
    }

    public void setDocumentPerson(Person documentPerson) {
        this.documentPerson = documentPerson;
    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", document=" + documentPerson.getDocument() + '}' + "\n";
    }
    
}
