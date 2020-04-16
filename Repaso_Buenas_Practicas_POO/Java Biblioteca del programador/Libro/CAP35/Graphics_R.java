//********************************************************************
// Graphics_R.java
//********************************************************************

import java.awt.*;

//********************************************************************

public class Graphics_R
  {
    Graphics g;

    int ox = 0;
    int oy = 0;

    double radianes = 0.0;
    double cos = 1.0;
    double sin = 0.0;

    //----------------------------------------------------------------

    public Graphics_R(Graphics g)
      {
        this.g = g.create();
      }

    //----------------------------------------------------------------

    public void origen(int x, int y)
      {
        ox = x;
        oy = y;
      }

    //----------------------------------------------------------------

    public void angulo(double a)
      {
        radianes = (a * 2 * Math.PI) / 360;

        cos = Math.cos(radianes);
        sin = Math.sin(radianes);
      }

    //----------------------------------------------------------------

    public void setColor(Color c)
      {
        g.setColor(c);
      }

    //----------------------------------------------------------------

    int rotate_x(int x, int y)
      {
        return ((int) (ox + x * cos - y * sin));
      }

    //----------------------------------------------------------------

    int rotate_y(int x, int y)
      {
        return ((int) (oy + y * cos + x * sin));
      }

    //----------------------------------------------------------------

    public void drawLine(int x1, int y1, int x2, int y2)
      {
        g.drawLine(rotate_x(x1, y1),
                   rotate_y(x1, y1),
                   rotate_x(x2, y2),
                   rotate_y(x2, y2));
      }

    //----------------------------------------------------------------

    public void fillPolygon(int x[], int y[], int n)
      {
        int nueva_x[] = new int[n];
        int nueva_y[] = new int[n];

        for (int i = 0; i < n; i++)
          {
          nueva_x[i] = rotate_x(x[i], y[i]);
          nueva_y[i] = rotate_y(x[i], y[i]);
          }

        g.fillPolygon(nueva_x, nueva_y, n);
      }
  }
