package test;

import controller.ClientController;
import model.Client;

/**
 *
 * @author Estudiante
 */
public class ClientTest {
    
    public static void main(String[] args) {
        
        ClientController clientController = new ClientController();
        
        String[] params = new String[4];
        params[Client.ID] = "1";
        params[Client.NAME] = "DAVID";
        params[Client.PHONENUMBER] = "123456";
        params[Client.ADDRESS] = "ONE MAY";
        if(clientController.create(params)){
            //Excelent
            System.out.println(clientController.list());
        } else {
            //Not Save
            System.out.println("Sin lista de clientes");
        }
        
    }
}
