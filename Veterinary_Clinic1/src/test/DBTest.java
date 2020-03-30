/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import java.util.List;
import model.Animal;
import model.AnimalModel;
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
        AnimalModel animalModel = new AnimalModel();

        try {

            /**
             * Test creating, searching and updating a person
             */
//            Person person1 = new Person(1234, "Carlos Paez", 321654, "car@fd", "Calle Falsam123");
//            personModel.create(person1, true);
//            Person person2 = (Person) personModel.findOne("Person", 12345);
//            person2.setNameSurname("Renombrado");
//            personModel.update(person2, true);
//            personModel.delete("Person", 1234);
//            List<Person> persons = personModel.findAll("Person");
//            System.out.println(Arrays.toString(persons.toArray()));
//              System.out.println(personModel.findOne("Person", 12345));
            System.out.println("");
            /**
             * Test creating, searching and updating a client
             */
//            Person person3 = (Person) personModel.findOne("Person", 12345);
//            Client client1 = new Client(person3);
//            clientModel.create(client1, true);            
//            List<Client> clients = clientModel.findAll("Client");
//            System.out.println(Arrays.toString(clients.toArray()));
            System.out.println("");
            /**
             * Test creating, searching and updating an animal
             */
//            Animal animal1 = new Animal(1, "Hachi", "11 meses", "Perro Labrador");
//            animalModel.create(animal1, true);
//            Animal animal2 = (Animal) animalModel.findOne("Animal", 1);
//            animal2.setUpdateVaccines(0);
//            animalModel.update(animal2, true);
            animalModel.delete("Animal", 1);

            List<Animal> animals = animalModel.findAll("Animal");
            System.out.println(Arrays.toString(animals.toArray()));
            System.out.println(animalModel.findOne("Animal", 2));

        } catch (Exception e) {
            e.printStackTrace();
        }

        DBSession.persistenceClose();
    }
}
