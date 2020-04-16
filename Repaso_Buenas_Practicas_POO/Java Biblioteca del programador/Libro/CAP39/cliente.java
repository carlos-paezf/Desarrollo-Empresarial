//********************************************************************
// cliente.java
//********************************************************************

import java.io.*;
import sun.net.*;

//********************************************************************

class cliente extends NetworkClient
  {
    DataInputStream entrada_red;
    PrintStream     salida_red;

    //----------------------------------------------------------------

    public static void main(String args[]) 
      {
        if (args.length < 2)
          {
            System.out.println("Para correr, teclee:\n");
            System.out.println("\tjava cliente <servidor> <nombre de usuario>");
            return;
          }

        System.out.println("Conectando...");

        try
          {
            new cliente(args[0], args[1]);
	  }
        catch (Exception e)
          {
            System.out.println("No se puede crear el cliente de red");
            return;
          }
      }

    //----------------------------------------------------------------

    cliente(String servidor, String nombre_usuario) throws IOException
      {
        super(servidor, 1111);

        if (serverIsOpen ())
          {
            System.out.println("Conectado al servidor");
            entrada_red = new DataInputStream(System.in);
            salida_red = serverOutput;

            salida_red.println(nombre_usuario);

            platicar();
          }
        else
          System.out.println("Error - No se pudo conectar al servidor");
      }

    //----------------------------------------------------------------

    void platicar()
      {
        String cadena;

        System.out.println("Teclee 'SALIR' para terminar");

        while (true)
          {
          cadena = leer_entrada_red();

          if (cadena.equalsIgnoreCase("SALIR"))
            break;

          escribir_salida_red(cadena);
          }

        System.out.print("Desconectando...");
        cerrar_servidor();
        System.out.println("terminado");
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

    //----------------------------------------------------------------

    void cerrar_servidor()
      {
        try
          {
            closeServer();
	  }
        catch (Exception e)
          {
            System.out.println("No se puede cerrar el servidor");
	  }
      }
  }
