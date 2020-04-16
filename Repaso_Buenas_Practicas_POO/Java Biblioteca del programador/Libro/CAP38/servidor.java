//********************************************************************
// servidor.java
//********************************************************************

import java.io.*;
import sun.net.*;

//********************************************************************

class servidor extends NetworkServer
  {
    //----------------------------------------------------------------

    public static void main(String args [])
      {
        System.out.println("Esperando usuarios...");

        try
          {
            (new servidor()).startServer(1111);
	  }
        catch (IOException e)
          {
            System.out.println("No se puede iniciar el servidor");
	  }
      }

    //----------------------------------------------------------------

    public void serviceRequest()
      {
        DataInputStream entrada_red = new DataInputStream(clientInput);
        String usuario;

        System.out.println("Conectando al cliente...");

        clientOutput.println("login:");
        clientOutput.flush();

        try
          {
            if ((usuario = entrada_red.readLine()) == null)
              {
                System.out.println("readLine regresó nulo");
                return;
              }
          }
        catch (IOException e)
          {
            System.out.println("Error en readLine");
            return;
          }

        System.out.println("Nombre del usuario: " + usuario);

        clientOutput.println("login exitoso");
        clientOutput.flush();

        System.out.println(usuario + " se ha desconectado");
      }
  }
