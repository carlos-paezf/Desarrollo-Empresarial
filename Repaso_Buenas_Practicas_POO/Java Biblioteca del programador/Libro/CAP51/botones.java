//********************************************************************
// botones.java
//********************************************************************

import java.awt.*;
import java.util.*;

//********************************************************************

class info_boton extends Object
  {
    public String s;
    public int x;
    public int y;
    public int w;
    public int h;

    public info_boton(String s, int x, int y, int w, int h)
      {
        this.s = s;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
      }
  }

//********************************************************************

public class botones
  {
    static Graphics g;
    static boolean primera_vez = true;
    static int total_botones = 0;

    static Vector coordenada;

    static FontMetrics font_metrics;

    static int tamano_fuente = 20;
    static int linea_base =  0;

    //----------------------------------------------------------------

    public botones(Graphics g, String s, int x, int y, int w, int h)
      {
        if (primera_vez)
          {
            primera_vez = false;
            this.g = g;

            Font fuente = new Font("TimesRoman", Font.BOLD, tamano_fuente);
            g.setFont(fuente);
            font_metrics = g.getFontMetrics();
            linea_base = font_metrics.getAscent();

            coordenada = new Vector();
          }

        total_botones++;
        coordenada.addElement(new info_boton(s, x, y, w, h));
        dibujar_elementos();
      }

    //----------------------------------------------------------------

    static void dibujar_elementos()
      {
        for (int i = 0; i < total_botones; i++)
          {
            info_boton b = (info_boton) coordenada.elementAt(i);

            g.setColor(Color.cyan);
            g.fillRect(b.x, b.y, b.w, b.h);

            g.setColor(Color.blue);
            g.drawString(b.s, b.x, b.y + linea_base - 1);

            g.setColor(Color.white);
            g.drawRect(b.x, b.y, b.w, b.h);
          }
      }

    //----------------------------------------------------------------

    static int push_button(int x, int y)
      {
        for (int i = 0; i < total_botones; i++)
          {
            info_boton b = (info_boton) coordenada.elementAt(i);

            if (x > b.x && x < b.x + b.w &&
                y > b.y && y < b.y + b.h)
              {
                g.setColor(Color.black);
                g.fillRect(b.x, b.y, b.w, tamano_fuente);
                g.setColor(Color.white);
                g.drawString(b.s, b.x, b.y + linea_base);

                return (i);
              }
          }

        return (-1);
      }
  }
