/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import java.util.List;
import model.Address;
import model.AddressModel;
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
        AddressModel addressModel = new AddressModel();

        try {

            System.out.println("");
            /**
             * Test creating, searching and updating a client
             */
//            Address a1 = (Address) addressModel.findOne("Address", 5);
//            
//            Client client = new Client(2, 123456, "Charles", "Ferreira", 547698, "", (List<Address>) a1, 1);
//            personModel.create(client, true);
//            List<Person> clients = personModel.findAll("Person");
//            System.out.println(Arrays.toString(clients.toArray()));
            System.out.println(personModel.findOne("Person", 1));

        } catch (Exception e) {
            e.printStackTrace();
        }

        DBSession.persistenceClose();
    }
}
