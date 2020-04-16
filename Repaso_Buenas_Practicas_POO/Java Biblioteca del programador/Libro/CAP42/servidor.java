//********************************************************************
// servidor.java
//********************************************************************

import java.io.*;
import java.net.*;

//********************************************************************

class servidor
  {
    ServerSocket socket_servidor;

    String usuario[];
    InputStream entrada_red[];
    OutputStream salida_red[];

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
        entrada_red = new InputStream[2];
        salida_red = new OutputStream[2];

        try
          {
            socket_servidor = new ServerSocket(1111);
          }
        catch (IOException e)
          {
            System.out.println("Error en el socket del servidor");
            return;
          }

        System.out.println("Esperando usuarios...");

        while (true)
          {
            try
              {
                Socket socket = socket_servidor.accept();
                atender_peticion(socket);
              }
            catch (IOException e)
              {
                System.out.println("Excepcion: <" + e + ">");
                break;
              }
          }
      }

    //----------------------------------------------------------------

    public void atender_peticion(Socket socket)
      {
        InputStream entrada;
        OutputStream salida;

        try
          {
            entrada = socket.getInputStream();
            salida = socket.getOutputStream();
          }
        catch (IOException e)
          {
            System.out.println("No puede obtener streams de entrada/salida");
            return;
          }

        if (contador_clientes >= 2)
          {
            escribir_salida_red(salida, "Ya hay dos clientes");
            return;
          }

        entrada_red[contador_clientes] = entrada;
        salida_red[contador_clientes] = salida;
        usuario[contador_clientes] = leer_entrada_linea_red(entrada);

        System.out.println(usuario[contador_clientes] + " Conectado! " +
                           socket.toString());

        (new lector(this, contador_clientes)).start();
        contador_clientes++;
      }

    //----------------------------------------------------------------

    String leer_entrada_linea_red(InputStream entrada)
      {
        String line = "";
        String c;

        c = leer_entrada_red(entrada);
        while ((byte) c.charAt(0) != 10)
          {
            line = line + c;
            c = leer_entrada_red(entrada);
          }

        return line;
      }

    //----------------------------------------------------------------

    String leer_entrada_red(InputStream entrada)
      {
        byte bytes[];
        int numero_de_bytes;

        try
          {
            bytes = new byte[1];
            numero_de_bytes = entrada.read(bytes, 0, 1);
            if (numero_de_bytes > 0)
              return (new String(bytes, 0, 0, numero_de_bytes));
            else
              return null;
          }
        catch (IOException e)
          {
            return null;
	  }
      }

    //----------------------------------------------------------------

    void escribir_salida_red(OutputStream salida, String cadena)
      {
        byte arreglo_bytes[];

        int longitud = cadena.length();
        arreglo_bytes = new byte[longitud];
        cadena.getBytes(0, longitud, arreglo_bytes, 0);

        try
          {
            salida.write(arreglo_bytes);
	  }
        catch (IOException e)
          {
            ;
	  }
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

    public void run ()
      {
        setPriority(MIN_PRIORITY);

        s.escribir_salida_red(s.salida_red[indice], "ESPERE...");
        while (s.contador_clientes < 2)
          ;

        s.escribir_salida_red(s.salida_red[indice], "  ADELANTE...");

        while (true)
          {
            String cadena = s.leer_entrada_red(s.entrada_red[indice]);

            if (cadena == null)
              break;

            System.out.println(s.usuario[indice] + ": " + cadena);
            s.escribir_salida_red(s.salida_red[(indice+1)%2], cadena);
          }

        System.out.println(s.usuario [indice] + " se ha desconectado.");

        if (--s.contador_clientes == 0)
          System.exit(0);
      }
  }
