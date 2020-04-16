//********************************************************************
// estrella.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class estrella extends Applet implements Runnable
  {
    int x = 0;
    int y = 0;

    boolean blink = true;

    int valor_demora = 100;

    //----------------------------------------------------------------

    public void init()
      {
        x = size().width  / 8;
        y = size().height / 8;

        String parametro = getParameter("DEMORA");
        if (parametro != null)
          valor_demora = Integer.parseInt(parametro);
      }

    //----------------------------------------------------------------

    public void start()
      {
        (new Thread(this)).start();
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (true)
          {
            repaint();
            demora();
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

    public void paint(Graphics _g)
      {
        Graphics g = _g.create();

        if (blink)
          {
            g.setColor(Color.red);
            blink = false;
          }
        else
          {
            g.setColor(Color.blue);
            blink = true;
          }

        int ancho  = (size().width  * 3) / 4;
        int alto = (size().height * 3) / 4;

        g.translate(x, y);

        Font fuente = new Font("TimesRoman", Font.BOLD, alto/10);
        g.setFont(fuente);

        FontMetrics font_metrics = g.getFontMetrics();

        g.drawString("Muestras"   ,
                     (ancho-font_metrics.stringWidth ("Muestras")) / 2,
                     alto/2 - alto/20);

        g.drawString("Gratis",
                     (ancho-font_metrics.stringWidth ("Gratis")) / 2,
                     alto/2 + alto/20);

        int puntos_x[] = new int[9];
        int puntos_y[] = new int[9];

        puntos_x[0] =  ancho   / 2;  puntos_y[0] = (alto*9)/10;
        puntos_x[1] = (ancho*3)/ 8;  puntos_y[1] = (alto*5)/ 8;
        puntos_x[2] =  ancho   /10;  puntos_y[2] =  alto   / 2;
        puntos_x[3] = (ancho*3)/ 8;  puntos_y[3] = (alto*3)/ 8;
        puntos_x[4] =  ancho   / 2;  puntos_y[4] =  alto   /10;
        puntos_x[5] = (ancho*5)/ 8;  puntos_y[5] = (alto*3)/ 8;
        puntos_x[6] = (ancho*9)/10;  puntos_y[6] =  alto   / 2;
        puntos_x[7] = (ancho*5)/ 8;  puntos_y[7] = (alto*5)/ 8;
        puntos_x[8] =  ancho   / 2;  puntos_y[8] = (alto*9)/10;

        g.drawPolygon(puntos_x, puntos_y, 9);

        x += (int) (Math.random() * 7) - 3;
        y += (int) (Math.random() * 7) - 3;

        if (x < 0)
          x = 0;

        if (y < 0)
          y = 0;

        if (x > ancho/4)
          x = ancho/4;

        if (y > alto/4)
          y = alto/4;
      }
  }
