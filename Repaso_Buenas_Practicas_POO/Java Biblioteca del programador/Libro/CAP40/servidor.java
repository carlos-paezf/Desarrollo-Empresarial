//********************************************************************
// servidor.java
//********************************************************************

import java.io.*;
import sun.net.*;

//********************************************************************

class servidor extends NetworkServer
  {
    DataInputStream entrada_red;
    PrintStream salida_red;

    //----------------------------------------------------------------

    public static void main(String args[])
      {
        new servidor();
      }

    //----------------------------------------------------------------

    public servidor()
      {
        try
          {
            startServer(1111);
	  }
        catch (Exception e)
          {
            System.out.println("No se puede iniciar el servidor");
            return;
          }

        System.out.println("Esperando usuarios...");
      }

    //----------------------------------------------------------------

    public void serviceRequest()
      {
        entrada_red = new DataInputStream(clientInput);
        salida_red = clientOutput;

        String usuario = leer_entrada_red();
        System.out.println(usuario + " Conectado!");

        while (true)
          {
            String cadena;

            if ((cadena = leer_entrada_red()) == null)
              break;

            System.out.println(usuario + ": " + cadena);
            escribir_salida_red(cadena);
          }

        System.out.println(usuario + " se ha desconectado.");
      }

    //----------------------------------------------------------------

    String leer_entrada_red()
      {
        try
          {
            return entrada_red.readLine();
	  }
        catch (IOException e)
          {
            return null;
	  }
      }

    //----------------------------------------------------------------

    void escribir_salida_red(String cadena)
      {
        salida_red.println(cadena);
        salida_red.flush();
      }
  }
