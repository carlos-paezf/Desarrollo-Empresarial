//********************************************************************
// servidor.java
//********************************************************************

import java.io.*;
import sun.net.*;

//********************************************************************

public class servidor extends NetworkServer
  {
    String usuario[];
    DataInputStream entrada_red[];
    PrintStream salida_red[];

    static int contador_clientes = 0;

    //----------------------------------------------------------------

    public static void main(String args[])
      {
        new servidor();
      }

    //----------------------------------------------------------------

    public servidor()
      {
        usuario = new String[2];
        entrada_red = new DataInputStream[2];
        salida_red = new PrintStream[2];

        try
          {
            startServer(1111);
	  }
        catch (Exception e)
          {
            System.out.println("No se puede conectar al servidor");
            return;
          }

        System.out.println("Esperando usuarios...");
      }

    //----------------------------------------------------------------

    public void serviceRequest()
      {
        DataInputStream entrada = new DataInputStream(clientInput);
        PrintStream salida = clientOutput;

        if (contador_clientes >= 2)
          {
            escribir_salida_red(salida, "Ya hay dos clientes");
            return;
          }

        entrada_red[contador_clientes] = entrada;
        salida_red[contador_clientes] = salida;
        usuario[contador_clientes] = leer_entrada_red(entrada);

        System.out.println(usuario[contador_clientes] + " Conectado!");

        (new lector(this, contador_clientes)).start();
        int c = contador_clientes++;

        while (usuario[c] != null)
          try
            {
              Thread.sleep(5000);
	    }
          catch (InterruptedException e)
	    {
              ;
            }
      }

    //----------------------------------------------------------------

    String leer_entrada_red(DataInputStream entrada)
      {
        try
          {
            return entrada.readLine();
	  }
        catch (IOException e)
	  {
            return null;
	  }
      }

    //----------------------------------------------------------------

    void escribir_salida_red(PrintStream salida, String cadena)
      {
        salida.println(cadena);
        salida.flush();
      }
  }

//********************************************************************

class lector extends Thread
  {
    servidor s;
    int indice;

    //----------------------------------------------------------------

    public lector(servidor s, int indice)
      {
        this.s = s;
        this.indice = indice;
      }

    //----------------------------------------------------------------

    public void run()
      {
        setPriority(MIN_PRIORITY);

        s.escribir_salida_red(s.salida_red [indice], "ESPERE...");
        while (s.contador_clientes < 2)
          ;

        s.escribir_salida_red(s.salida_red [indice], "  ADELANTE...");

        while (true)
          {
            String cadena = s.leer_entrada_red(s.entrada_red[indice]);

            if (cadena == null)
              break;

            System.out.println(s.usuario[indice] + ": " + cadena);
            s.escribir_salida_red(s.salida_red[(indice+1)%2], cadena);
          }

        System.out.println(s.usuario[indice] + " se ha desconectado.");
        s.usuario[indice] = null;

        if (--s.contador_clientes == 0)
          System.exit(0);
      }
  }
