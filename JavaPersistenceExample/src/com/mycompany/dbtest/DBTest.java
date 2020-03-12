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
         DBSession dBSession = new DBSession();
         dBSession.persistenceCreate();
         ClientModel clientModel = new ClientModel();
         try {
             List<Client> clients = clientModel.findAll("Client");
             System.out.println(Arrays.toString(clients.toArray()));
         } catch (Exception ex) {
             ex.printStackTrace();
         }
    }
}
