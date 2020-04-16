//********************************************************************
// cliente.java
//********************************************************************

import java.awt.*;
import java.io.*;
import sun.net.*;

//********************************************************************

public class cliente extends Frame
  {
    public static void main(String args[])
      {
        if (args.length < 2)
          {
            System.out.println("Para correr, teclee:\n");
            System.out.println("\tjava cliente <servidor> <nombre de usuario>");
            return;
          }

        new cliente(args[0], args[1]);
      }

    //----------------------------------------------------------------

    public cliente(String host, String nombre_usuario)
      {
        add ("Center", new red(host, nombre_usuario));
        resize(500, 500);
        show();
      }
  }

//********************************************************************

class red extends Panel
  {
    NetworkClient cliente_red;
    DataInputStream entrada_red;
    PrintStream salida_red;

    String nombre_usuario;
    boolean conectado = false;
    escritor w;

    //----------------------------------------------------------------

    public red(String host, String nombre_usuario)
      {
        this.nombre_usuario = nombre_usuario;

        if (conectar(host, nombre_usuario))
          {
            conectado = true;
            w = new escritor(this);
            w.start();
          }
        else
          {
            System.out.println("No se pudo conectar");
            return;
          }
      }

    //----------------------------------------------------------------

    boolean conectar(String host, String nombre_usuario)
      {
        System.out.println("Conectando al usuario " + nombre_usuario +
                           " a " + host);

        try
          {
            cliente_red = new NetworkClient(host, 1111);
	  }
        catch (Exception e)
          {
            System.out.println("Error...no se pudo conectar al servidor <" +
                               e + ">");
            return false;
          }

        if (cliente_red.serverIsOpen ())
          {
            System.out.println("Conectado al servidor");
            entrada_red = new DataInputStream
                                (cliente_red.serverInput);
            salida_red = cliente_red.serverOutput;

            escribir_salida_red(nombre_usuario);

            return true;
          }
        else
          {
            System.out.println("El servidor NO está abierto");
            return false;
          }
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

    void escribir_salida_red (String cadena)
      {
        salida_red.println(cadena);
        salida_red.flush();
      }

    //----------------------------------------------------------------

    void cerrar_servidor()
      {
        try
          {
            cliente_red.closeServer();
	  }
        catch (Exception e)
          {
            System.out.println("No se puede cerrar el servidor");
	  }
      }

    //----------------------------------------------------------------

    public boolean keyDown(Event evt, int key)
      {
        if (key == 27)
          {
            if (conectado)
              {
                System.out.print("Desconectando...");
                cerrar_servidor();
                System.out.println("terminado");
              }

            System.exit (0);
          }

        else if (conectado)
          escribir_salida_red ("" + (char) key);

        return true;
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        Font fuente = new Font("TimesRoman", Font.BOLD, 20);
        g.setFont(fuente);
        g.drawString("Hola " + nombre_usuario, 100, 100);

        g.setColor(Color.red);
        g.fillRect(100, 200, 300, 50);
        g.setColor(Color.white);
        g.drawRect(100, 200, 300, 50);
        g.drawString(w.linea_red, 120, 225);
      }
  }

//********************************************************************

class escritor extends Thread
  {
    String linea_red = "";

    red n;

    //----------------------------------------------------------------

    public escritor(red n)
      {
        this.n = n;
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (true)
          {
            String caracter = n.leer_entrada_red();
            linea_red = linea_red + caracter;

            if (linea_red.length () > 20)
              linea_red = caracter;

            n.repaint();
            System.out.println("String <" + caracter + ">");
          }
      }
  }
