//********************************************************************
// reloj.java
//********************************************************************

import java.applet.*;
import java.awt.*;
import java.util.Date;

//********************************************************************

class Coordenada
  {
    public int x;
    public int y;

    //----------------------------------------------------------------

    public void poner(int x, int y)
      {
        this.x = x;
        this.y = y;
      }

    //----------------------------------------------------------------

    public Coordenada(int x, int y)
      {
        poner(x, y);
      }
  }

//********************************************************************

public class reloj extends Applet implements Runnable
  {
    Graphics g;
    FontMetrics font_metrics;
    Thread mi_hilo;

    static final double pi   = Math.PI;
    static final double _2pi = 2 * pi;

    int ancho;
    int alto;

    Date hora_actual;

    Coordenada centro;

    int radio = 80;
    int radio_interno;
    int longitud_h;
    int longitud_m;
    int longitud_s;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        centro = new Coordenada(100, 100);
        hora_actual = new Date();

        por_omision();
      }

    //----------------------------------------------------------------

    void por_omision()
      {
        ancho  = size().width;
        alto = size().height;

        if (ancho < alto)
          alto = ancho;
        else
          ancho = alto;

        centro.poner(ancho/2, alto/2);
        radio = (ancho * 8) / 20;
        radio_interno = (radio * 8) / 10;

        longitud_h = (radio * 5) / 10;
        longitud_m = (radio * 7) / 10;
        longitud_s = (radio * 6) / 10;

        Font fuente = new Font("TimesRoman", Font.BOLD, ancho/8);
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
        g.setColor(Color.yellow);
        g.fillOval(centro.x - radio_interno, centro.y - radio_interno,
                   radio_interno * 2, radio_interno * 2);

        hora_actual = new Date();

        int horas   = hora_actual.getHours();
        int minutos = hora_actual.getMinutes();
        int segundos = hora_actual.getSeconds();

        g.setColor(Color.black);
        dibujar_manecillas(horas, minutos, segundos);
      }

    //----------------------------------------------------------------

    void dibujar_manecillas(int h, int m, int s)
      {
        int x = centro.x;
        int y = centro.y;

        double valor_horas = h + m/60.0 + s/3600.0;
        valor_horas *= _2pi / 12.0;

        g.drawLine (x, y,
                    (int) (x + longitud_h * Math.sin(valor_horas)),
                    (int) (y - longitud_h * Math.cos(valor_horas)));

        double valor_minutos = m + s/60.0;
        valor_minutos *= _2pi / 60.0;

        g.drawLine (x, y,
                (int) (x + longitud_m * Math.sin(valor_minutos)),
                (int) (y - longitud_m * Math.cos(valor_minutos)));

        double valor_segundos = s * _2pi / 60.0;

        g.drawLine (x, y, 
                (int) (x + longitud_s * Math.sin(valor_segundos)),
                (int) (y - longitud_s * Math.cos(valor_segundos)));
      }

    //----------------------------------------------------------------

    void dibujar_cadena(String string, int x, int y)
      {
        int ancho_string = font_metrics.stringWidth(string);
        int alto_string = font_metrics.getAscent();

        g.drawString(string, x - ancho_string/2, y + alto_string/2);
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        por_omision();

        g.setColor(Color.black);
        g.fillRect(0, 0, size().width, size().height);

        g.setColor(Color.lightGray);
        g.fillOval(0, 0, ancho, alto);

        g.setColor(Color.black);
        for (int i = 1; i <= 12; i++)
          dibujar_cadena ("" + i,
                  (int) (centro.x + radio * Math.sin(_2pi * i/12)),
                  (int) (centro.y - radio * Math.cos(_2pi * i/12)));
      }
  }
