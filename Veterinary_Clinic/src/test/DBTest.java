/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import java.util.List;
import model.Client;
import model.ClientModel;
import model.DBSession;
import model.Person;
import model.PersonModel;

/**
 *
 * @author David Ferrer
 */
public class DBTest {
    
    public static void main(String[] args) {
        
        DBSession.persistenceCreate();
        
        PersonModel personModel = new PersonModel();
        ClientModel clientModel = new ClientModel();
        
        try {
            
            /**
             * Test creating, searching and updating a person
             */
            Person person1 = new Person(1234, "Carlos Paez", 321654, "car@fd", "Calle Falsam123");
            personModel.create(person1, true);
//            List<Person> persons = personModel.findAll("Person");
//            System.out.println(Arrays.toString(persons.toArray()));
//            
//            person1 = new Person(12345, "David Ferrer", 123456, "", "Casa");
//            personModel.update(person1, true);
            
            /**
             * Test creating, searching and updating a person
             */
            Client client1 = new Client(1, person1);
            clientModel.create(client1, true);
            List<Client> clients = clientModel.findAll("Client");
            System.out.println(Arrays.toString(clients.toArray()));
            
        } catch (Exception e) {
        }
        
        DBSession.persistenceClose();
    }
}
