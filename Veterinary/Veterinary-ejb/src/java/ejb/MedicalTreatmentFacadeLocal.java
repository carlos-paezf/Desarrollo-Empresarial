/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.MedicalTreatment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Ferrer
 */
@Local
public interface MedicalTreatmentFacadeLocal {

    void create(MedicalTreatment medicalTreatment);

    void edit(MedicalTreatment medicalTreatment);

    void remove(MedicalTreatment medicalTreatment);

    MedicalTreatment find(Object id);

    List<MedicalTreatment> findAll();

    List<MedicalTreatment> findRange(int[] range);

    int count();
    
}
