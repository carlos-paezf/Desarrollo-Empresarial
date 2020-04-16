//********************************************************************
// Teleimpresor.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class Teleimpresor extends Applet implements Runnable
  {
    Graphics g;

    fuente_8x8 fuente;

    Thread mi_hilo;

    int ancho;
    int alto;

    String cadena_teleimpresor = "Esta es una prueba del teleimpresor";

    int indice;
    int longitud;

    int valor_demora = 200;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();
        fuente = new fuente_8x8(g);

        String parametro = getParameter("DEMORA");
        if (parametro != null)
          valor_demora = Integer.parseInt(parametro);

        parametro = getParameter("MENSAJE");
        if (parametro != null)
          cadena_teleimpresor = parametro;

        por_omision();

        indice = 0;
        longitud = cadena_teleimpresor.length();
      }

    //----------------------------------------------------------------

    void por_omision()
      {
        ancho = size().width;
        alto = size().height;
      }

    //----------------------------------------------------------------

    public void start()
      {
        if (mi_hilo == null)
          {
            mi_hilo = new Thread(this);
            mi_hilo.start();
          }
      }

    //----------------------------------------------------------------

    public boolean keyDown(Event evt, int key)
      {
        if (key == 27)
          stop();

        return true;
      }

    //----------------------------------------------------------------

    public void stop()
      {
        if (mi_hilo != null)
          {
            mi_hilo.stop();
            mi_hilo = null;
          }
      }

    //----------------------------------------------------------------

    void demora()
      {
        try
          {
            Thread.sleep(valor_demora);
          }
        catch (InterruptedException e)
          {
          }
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (mi_hilo != null)
          repaint();
      }

    //----------------------------------------------------------------

    public void update(Graphics _g)
      {
        for (int i = 0; i < 8; i++)
          {
            g.setColor(Color.black);
            g.fillRect(0, 0, ancho, alto);
            g.setColor(Color.red);

            fuente.dibujar_cadena
                     (cadena_teleimpresor.substring(indice, indice+11),
                      0, alto, ancho, alto, 10, i);

            demora();
          }

        if (++indice > longitud-11)
          indice = 0;
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        por_omision();
        update(g);
      }
  }
