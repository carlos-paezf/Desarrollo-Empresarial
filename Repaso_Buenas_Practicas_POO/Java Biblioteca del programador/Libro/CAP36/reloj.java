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
    fuente_8x8 fuente;

    Thread mi_hilo;

    int ancho;
    int alto;
    Date hora_actual;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        fuente = new fuente_8x8(g);

        hora_actual = new Date();

        por_omision();
      }

    //----------------------------------------------------------------

    void por_omision ()
      {
        ancho  = size().width;
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

    public void update(Graphics _g)
      {
        g.setColor(Color.black);
        g.fillRect(0, 0, ancho, alto);

        hora_actual = new Date();

        int horas   = hora_actual.getHours();
        int minutos = hora_actual.getMinutes();
        int segundos = hora_actual.getSeconds();

        String hora;
        boolean am;

        if (horas < 12)
          am = true;
        else
          {
            am = false;
            if (horas > 12)
              horas -= 12;
          }

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

        if (am)
          hora += "am";
        else
          hora += "pm";

        fuente.poner_color(Color.green);

        fuente.origen(100, 100);
        fuente.angulo(45.0);

        fuente.dibujar_cadena("La hora es:", 0, 50, 300, 50);
        fuente.dibujar_cadena(hora, 0, 100, 300, 50);
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        por_omision();
        update(g);
      }
  }
