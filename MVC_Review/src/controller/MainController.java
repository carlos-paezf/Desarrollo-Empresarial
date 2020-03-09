/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public abstract class MainController {
    protected List<Object> objectList;

    public MainController() {
        objectList = new ArrayList<>();
    }
    
    public abstract boolean create(Object[] params);
    public abstract boolean update(int index, Object[] params);
    public abstract boolean index();
    
    public boolean delete(int id){
        try {
            objectList.remove(id);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String list(){
        return Arrays.toString(objectList.toArray());
    }

    public List<Object> getObjectList() {
        return objectList;
    }
    
    
}
