package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Client;

/**
 *
 * @author Estudiante
 */
public class ClientController {

    private List<Client> clients;

    public ClientController() {
        clients = new ArrayList<>();
    }

    public boolean create(String[] params) {
        try {
            clients.add(new Client(Integer.parseInt(params[Client.ID]), params[Client.NAME], params[Client.PHONENUMBER], params[Client.ADDRESS]));
            return true;
        } catch (NumberFormatException e) {
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
        /*          String clientString = "";          for(Client currentClient : clients){            clientString += currentClient.toString();          }        */
        return Arrays.toString(clients.toArray());
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
        
}
