//*******************************************************************
// reloj.java
//*******************************************************************

import java.applet.*;
import java.awt.*;
import java.util.Date;

//*******************************************************************

public class reloj extends Applet implements Runnable
  {
    Graphics g;
    Font fuente;
    FontMetrics font_metrics;
    Thread mi_hilo;

    int ancho;
    int alto;
    Date hora_actual;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        hora_actual = new Date();

        por_omision();
      }

    //----------------------------------------------------------------

    void por_omision()
      {
        ancho  = size().width;
        alto = size().height;

        if (ancho > alto*6)
          ancho = alto*6;
        else
          alto = ancho/6;

        fuente = new Font("TimesRoman", Font.BOLD, alto*3/2);
        g.setFont(fuente);
        font_metrics = g.getFontMetrics();
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

    public void stop()
      {
        mi_hilo.stop();
        mi_hilo = null;
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (mi_hilo != null)
          {
            repaint();

            try
              {
                Thread.sleep(1000);
              }
            catch (InterruptedException e)
              {
              }
          }
      }

    //----------------------------------------------------------------

    public void update(Graphics g)
      {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, ancho, alto);

        hora_actual = new Date();

        int horas   = hora_actual.getHours();
        int minutos = hora_actual.getMinutes();
        int segundos = hora_actual.getSeconds();

        String hora;

        if (horas < 10)
          hora = "0" + horas;
        else
          hora = "" + horas;

        if (minutos < 10)
          hora += ":0" + minutos;
        else
          hora += ":" + minutos;

        if (segundos < 10)
          hora += ":0" + segundos;
        else
          hora += ":" + segundos;

        g.setColor(Color.black);

        g.setFont(fuente);
        g.drawString(hora, 0, alto);
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        por_omision();
        update(g);
      }
  }
