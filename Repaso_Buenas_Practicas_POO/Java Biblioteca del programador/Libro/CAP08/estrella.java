//*******************************************************************
// estrella.java
//*******************************************************************

import java.applet.*;
import java.awt.*;

//*******************************************************************

public class estrella extends Applet implements Runnable
  {
    boolean parpadeo = false;

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

            try
              {
                Thread.sleep(500);
              }
            catch (InterruptedException e)
              {
              }
          }
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        if (parpadeo)
          {
            g.setColor(Color.red);
            parpadeo = false;
          }
        else
          {
            g.setColor(Color.blue);
            parpadeo = true;
          }

        int ancho = size().width;
        int alto = size().height;

        Font fuente = new Font("TimesRoman", Font.BOLD, 20);
        g.setFont(fuente);

        FontMetrics font_metrics = g.getFontMetrics();

        g.drawString("Muestras", (ancho-font_metrics.stringWidth("Muestras"))/2,
                     alto/2);
        g.drawString("Gratis", (ancho-font_metrics.stringWidth("Gratis"))/2,
                      alto/2+20);

        g.drawLine(ancho/2, (alto*9)/10, (ancho*3)/8, (alto*5)/8);
        g.drawLine((ancho*3)/8, (alto*5)/ 8, ancho/10, alto/2);
        g.drawLine(ancho/10, alto/2, (ancho*3)/8, (alto*3)/8);
        g.drawLine((ancho*3)/8, (alto*3)/8, ancho/2, alto/10);
        g.drawLine(ancho/2, alto/10, (ancho*5)/8, (alto*3)/8);
        g.drawLine((ancho*5)/8, (alto*3)/8, (ancho*9)/10, alto/2);
        g.drawLine((ancho*9)/10, alto/2, (ancho*5)/8, (alto*5)/8);
        g.drawLine((ancho*5)/8, (alto*5)/8, ancho/2, (alto*9)/10);
      }
  }
