//********************************************************************
// anuncio.java
//********************************************************************

import java.applet.*;
import java.awt.Graphics;
import java.awt.Font;

//********************************************************************

public class anuncio extends Applet implements Runnable
  {
    int x = 0;
    int y = 0;

    int ancho = 0;

    Thread mi_hilo = null;

    String mensaje = "Hola, Java!";
    String nombre_fuente = "TimesRoman";
    int tamano = 10;

    //----------------------------------------------------------------

    public void init()
      {
        por_omision();

        String parametro;

        parametro = getParameter("MENSAJE");
        if (parametro != null)
          mensaje = parametro;

        parametro = getParameter("FUENTE");
        if (parametro != null)
          nombre_fuente = parametro;

        parametro = getParameter("TAMANO");
        if (parametro != null)
          tamano = Integer.parseInt(parametro);
      }

    //----------------------------------------------------------------

    public void start()
      {
        mi_hilo = new Thread(this);
        mi_hilo.start();
      }

    //----------------------------------------------------------------

    void por_omision()
      {
        y = size().height / 2;
        ancho = size().width;
        if (x > ancho)
          x = ancho;
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (true)
          {
            repaint();
            x -= 10;
            if (x < 0)
              x = ancho;

            try
              {
                Thread.sleep(100);
              }
            catch (InterruptedException e)
              {
              }
          }
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        por_omision();
        Font fuente = new Font(nombre_fuente, Font.PLAIN, tamano);
        g.setFont(fuente);
        g.drawString(mensaje, x, y);
      }
  }
