//********************************************************************
// cliente.java
//********************************************************************

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;

//********************************************************************

public class cliente extends Applet
  {
    Socket servidor;
    InputStream entrada_red;
    OutputStream salida_red;

    String nombre_usuario;
    boolean conectado = false;
    escritor w;

    String linea_tecleada = "";

    String host;

    //----------------------------------------------------------------

    public void init()
      {
        host = getParameter("SERVIDOR");

        if (conectar())
          {
            conectado = true;
            w = new escritor(this);
            w.start();
          }
        else
          {
            nombre_usuario = "* DESCONECTADO *";
            return;
          }
      }

    //----------------------------------------------------------------

    boolean conectar()
      {
        try
          {
            servidor = new Socket(host, 1111);
	  }
        catch (IOException e)
          {
            return false;
 	  }

        nombre_usuario = "" + servidor.getLocalPort();

        try
          {
            entrada_red = servidor.getInputStream();
            salida_red = servidor.getOutputStream ();
          }
        catch (IOException e)
          {
            return false;
	  }

        escribir_salida_red(nombre_usuario + "\n");

        return true;
      }

    //----------------------------------------------------------------

    String leer_entrada_red()
      {
        byte bytes[];
        int numero_de_bytes;

        try
          {
            bytes = new byte[1];
            numero_de_bytes = entrada_red.read(bytes, 0, 1);
            if (numero_de_bytes > 0)
              return(new String(bytes, 0, 0, numero_de_bytes));
            else
              return null;
          }
        catch (IOException e)
          {
            return null;
	  }
      }

    //----------------------------------------------------------------

    void escribir_salida_red(String cadena)
      {
        byte arreglo_bytes[];

        int longitud = cadena.length();
        arreglo_bytes = new byte[longitud];
        cadena.getBytes(0, longitud, arreglo_bytes, 0);

        try
          {
            salida_red.write(arreglo_bytes);
	  }
        catch (IOException e)
          {
            ;
	  }
      }

    //----------------------------------------------------------------

    void cerrar_servidor()
      {
        try
          {
            servidor.close();
	  }
        catch (IOException e)
          {
            ;
          }
      }

    //----------------------------------------------------------------

    public void stop()
      {
        cerrar_servidor();
        w.stop();
      }

    //----------------------------------------------------------------

    public boolean keyDown(Event evt, int key)
      {
        if (key == 27)
          {
            if (conectado)
              {
                conectado = false;
                cerrar_servidor();
              }
          }

        else if (conectado)
          {
            linea_tecleada = linea_tecleada + (char) key;
            if (linea_tecleada.length() > 20)
              linea_tecleada = linea_tecleada.substring(1);
            escribir_salida_red("" + (char) key);
            repaint();
          }

        return true;
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        Font fuente = new Font ("TimesRoman", Font.BOLD, 20);
        g.setFont (fuente);
        g.drawString ("Hola " + nombre_usuario, 100, 50);

        g.setColor (Color.red);
        g.fillRect (100, 100, 300, 50);
        g.setColor (Color.white);
        g.drawRect (100, 100, 300, 50);
        g.drawString (linea_tecleada, 120, 125);

        g.setColor (Color.green);
        g.fillRect (100, 200, 300, 50);
        g.setColor (Color.black);
        g.drawRect (100, 200, 300, 50);
        g.drawString (w.linea_red, 120, 225);
      }
  }

//********************************************************************

class escritor extends Thread
  {
    String linea_red = "";

    cliente c;

    //----------------------------------------------------------------

    public escritor(cliente c)
      {
        this.c = c;
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (true)
          {
            String cadena = c.leer_entrada_red();

            if (cadena == null)
              break;

            linea_red = linea_red + cadena;

            if (linea_red.length() > 20)
              linea_red = linea_red.substring(1);

            c.repaint();
          }

        linea_red = "DESCONECTADO";
        c.repaint();
      }
  }
