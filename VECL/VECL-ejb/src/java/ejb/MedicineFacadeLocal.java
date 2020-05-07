/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Medicine;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Ferrer
 */
@Local
public interface MedicineFacadeLocal {

    void create(Medicine medicine);

    void edit(Medicine medicine);

    void remove(Medicine medicine);

    Medicine find(Object id);

    List<Medicine> findAll();

    List<Medicine> findRange(int[] range);

    int count();
    
}
