//********************************************************************
// cliente.java
//********************************************************************

import java.io.*;
import sun.net.*;

//********************************************************************

class cliente
  {
    static NetworkClient   red;
    static DataInputStream entrada_red;
    static PrintStream     salida_red;

    //----------------------------------------------------------------

    public static void main(String args [])
      {
        System.out.print("Conectando...");
        System.out.flush();

        try
          {
            red = new NetworkClient("tumbolia", 1111);
          }
        catch (IOException e)
          {
            System.out.println("Error al crear el cliente de la red");
            return;
          }

        if (red.serverIsOpen())
          System.out.println("Correcto.");
        else
          {
            System.out.println("error.");
            System.out.println("No se puede conectar al servidor");
            return;
          }

        entrada_red  = new DataInputStream(red.serverInput);
        salida_red = red.serverOutput;

        System.out.println("Esperando el indicador de login...");
        System.out.println("El servidor dice <" + traer_entrada_red() + ">");

        System.out.println("Conectando...");
        salida_red.println("un_cliente");

        System.out.println("Esperando la respuesta...");
        System.out.println("El servidor dice <" + traer_entrada_red() + ">");

        System.out.print("Desconectando...");
        System.out.flush();
        cerrar_servidor();
        System.out.println("Terminado!");
      }

    //----------------------------------------------------------------

    static String traer_entrada_red()
      {
        String string;

        try
          {
            string = entrada_red.readLine();
	  }
        catch (IOException e)
          {
            string = "";
	  }

        return string;
      }

    //----------------------------------------------------------------

    static void cerrar_servidor()
      {
        try
          {
            red.closeServer();
	  }
        catch (Exception e)
          {
            System.out.println("No se puede cerrar el servidor");
	  }
      }
  }
