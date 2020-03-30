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
@Table(name = "animal")
@NamedQueries({
    @NamedQuery(name="Animal.findAll", query="SELECT a FROM Animal a"),
    @NamedQuery(name="Animal.findOne", query="SELECT a FROM Animal a WHERE a.idAnimal = :id")
})
public class Animal implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAnimal")
    private int idAnimal;
    @Column(name= "update_vaccines")
    private int updateVaccines;
    @Column(name = "name")
    private String name;
    @Column(name="age")
    private String age;
    @Column(name="breed")
    private String breed;

    public Animal() {
    }

    public Animal(int updateVaccines, String name, String age, String breed) {
        this.updateVaccines = updateVaccines;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int isUpdateVaccines() {
        return updateVaccines;
    }

    public void setUpdateVaccines(int updateVaccines) {
        this.updateVaccines = updateVaccines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Animal{" + "idAnimal=" + idAnimal + ", updateVaccines=" + updateVaccines + ", name=" + name + ", age=" + age + ", breed=" + breed +  '}' + "\n";
    }
    
    
}
