//********************************************************************
// prueba.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class prueba extends Applet
  {
    public void paint(Graphics g)
      {
        Graphics_R g2 = new Graphics_R(g);

        g.setColor(Color.black);

        g2.drawLine(  0,   0, 100,   0);
        g2.drawLine(100,   0, 100, 100);
        g2.drawLine(100, 100,   0, 100);
        g2.drawLine(  0, 100,   0,   0);

        g2.origen(150, 150);
        g2.angulo(45.0);

        g2.drawLine(  0,   0, 100,   0);
        g2.drawLine(100,   0, 100, 100);
        g2.drawLine(100, 100,   0, 100);
        g2.drawLine(  0, 100,   0,   0);

        for (double x = 0.0; x < 360.0; x += 1.0)
          {
            g2.angulo(x);
            g2.drawLine(0, 0, 60, 0);
          }

        g2.origen(100, 100);
        g2.angulo(25.0);

        int x[] = new int[4];
        int y[] = new int[4];

        x[0] =  0;  y[0] =  0;
        x[1] = 50;  y[1] =  0;
        x[2] = 50;  y[2] = 50;
        x[3] =  0;  y[3] = 50;

        g2.setColor(Color.green);
        g2.fillPolygon(x, y, 4);
      }
  }
