/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbtest;

import com.mycompany.model.Client;
import com.mycompany.model.ClientModel;
import com.mycompany.model.DBSession;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Ferrer
 */
public class DBTest {

    public static void main(String[] args) {
        //DBSession dBSession = new DBSession();
        DBSession.persistenceCreate();
        ClientModel clientModel = new ClientModel();
        try {
            Client c = new Client(9, "El nuevo", "320", "Calle Falsa 123");
            clientModel.create(c, true);
            List<Client> clients = clientModel.findAll("Client");
            System.out.println(Arrays.toString(clients.toArray()));
            System.err.println(clientModel.findOne("Client", 9));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
