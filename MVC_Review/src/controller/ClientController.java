/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.BDConnection;
import model.Client;
import model.ClientDAO;

/**
 *
 * @author Estudiante
 */
public class ClientController extends MainController {

    private ClientDAO clientDAO;

    public ClientController(BDConnection connection) {
        super();
        clientDAO = new ClientDAO(connection);
    }

    @Override
    public boolean index() {
        try {
            objectList.addAll(clientDAO.findAll());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean create(Object[] params) {
        try {
            objectList.add(new Client(new Integer(params[Client.ID].toString()), params[Client.PHONE_NUMBER].toString(),
                    params[Client.NAME].toString(), params[Client.ADDRESS].toString()));

            return true;

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean update(int index, Object[] params) {
        try {
            objectList.set(index, new Client(new Integer(params[Client.ID].toString()), params[Client.PHONE_NUMBER].toString(),
                    params[Client.NAME].toString(), params[Client.ADDRESS].toString()));

            return true;

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    /*private List<Client> clients;

    public ClientController() {
        clients = new ArrayList<>();
    }
    
    public boolean create(int id, String phoneNumber, String name, String address/*String[] params){
        try {
            /*clients.add(new Client(Integer.parseInt(params[Client.ID]), params[Client.PHONE_NUMBER], 
                params[Client.NAME], params[Client.ADDRESS]));
            clients.add(new Client(id, phoneNumber, name, address));
            
            return true;
            
        } catch (Exception e) {
            //TO DO: Show error message
            return false;
        }
    }
    
    public boolean update(int indice, int id, String phoneNumber, String name, String address String[] params){
        try {
            /*clients.add(new Client(Integer.parseInt(params[Client.ID]), params[Client.PHONE_NUMBER], 
                params[Client.NAME], params[Client.ADDRESS]));
            clients.add(indice, new Client(id, phoneNumber, name, address));
            
            return true;
            
        } catch (Exception e) {
            //TO DO: Show error message
            return false;
        }
    }
    
    public boolean delete(int id){
        try {
            clients.remove(id);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String list(){
        return Arrays.toString(clients.toArray());
    } 

    public List<Client> getClients() {
        return clients;
    }*/
}
