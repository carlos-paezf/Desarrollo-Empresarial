/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.ClientController;
import model.BDConnection;
import model.Client;

/**
 *
 * @author Estudiante
 */
public class ClientTests {
    public static void main(String[] args) {
        BDConnection connection = new BDConnection();
        
        ClientController cliController = new ClientController(connection);
        String[] params = new String[4];
        params[Client.ID] = "1";
        params[Client.NAME] = "Andres";
        params[Client.PHONE_NUMBER] = "311";
        params[Client.ADDRESS] = "calle falsa 123";
        if(cliController.create(params))
            System.out.println(cliController.list());
        else
            System.err.println("No funcionó");
        
        
    }
}
