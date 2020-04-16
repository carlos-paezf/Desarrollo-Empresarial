//********************************************************************
// anuncio.java
//********************************************************************

import java.applet.*;
import java.awt.Graphics;

//********************************************************************

public class anuncio extends Applet implements Runnable
  {
    int x = 0;
    int y = 0;

    int ancho = 0;

    Thread mi_hilo = null;

    //----------------------------------------------------------------

    public void init()
      {
        x = size().width;
        y = size().height / 2;
        ancho = x;
      }

    //----------------------------------------------------------------

    public void start()
      {
        mi_hilo = new Thread(this);
        mi_hilo.start();
      }

    //----------------------------------------------------------------

    public void run()
      {
        while(true)
          {
            repaint();
            x -= 10;
            if(x < 0)
              {
                x = ancho;
              }

            try
              {
                Thread.sleep(100);
                  // La instrucción anterior produce un error de
                  // compilacion sin una instrucción try/catch.
              }
            catch(InterruptedException e)
              {
              }
          }
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        g.drawString("Hola, Java!", x, y);
      }
  }
