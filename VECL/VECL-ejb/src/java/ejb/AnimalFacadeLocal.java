/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Animal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Ferrer
 */
@Local
public interface AnimalFacadeLocal {

    void create(Animal animal);

    void edit(Animal animal);

    void remove(Animal animal);

    Animal find(Object id);

    List<Animal> findAll();

    List<Animal> findRange(int[] range);

    int count();
    
}
