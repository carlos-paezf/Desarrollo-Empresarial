//********************************************************************
// cartas.java
//********************************************************************

import java.awt.*;

//********************************************************************

public class cartas
  {
    Graphics g;
    Image comodin;

    //----------------------------------------------------------------

    public cartas(Graphics g)
      {
        this.g = g;
      }

    //----------------------------------------------------------------

    public void dibujar_espadas(int x, int y, int w, int h)
      {
        int xx[] = new int[3];
        int yy[] = new int[3];

        xx[0] = x + w/2;  yy[0] = y          ;
        xx[1] = x      ;  yy[1] = y + h/2;
        xx[2] = x + w  ;  yy[2] = y + h/2;

        g.fillPolygon(xx, yy, 3);

        g.fillOval(x    , y+h/3, w/2, h/2);
        g.fillOval(x+w/2, y+h/3, w/2, h/2);

        g.fillRect(x+(w*3)/7, y+h/3, w/7, (h*2)/3);
      }

    //----------------------------------------------------------------

    public void dibujar_treboles(int x, int y, int w, int h)
      {
        g.fillOval(x    , y+(h*2)/5, w/2, h/2);
        g.fillOval(x+w/2, y+(h*2)/5, w/2, h/2);
        g.fillOval(x+w/4, y        , w/2, h/2);

        g.fillRect(x+(w*3)/7, y+h/3, w/7, (h*2)/3);
      }

    //----------------------------------------------------------------

    public void dibujar_corazones(int x, int y, int w, int h)
      {
        int xx[] = new int[3];
        int yy[] = new int[3];

        xx[0] = x + w/2;  yy[0] = y + h  ;
        xx[1] = x      ;  yy[1] = y + h/3;
        xx[2] = x + w  ;  yy[2] = y + h/3;

        g.fillPolygon(xx, yy, 3);

        g.fillOval(x    , y, w/2, h/2);
        g.fillOval(x+w/2, y, w/2, h/2);
      }

    //----------------------------------------------------------------

    public void dibujar_diamantes(int x, int y, int w, int h)
      {
        int xx[] = new int[4];
        int yy[] = new int[4];

        xx[0] = x + w/2;  yy[0] = y + h  ;
        xx[1] = x      ;  yy[1] = y + h/2;
        xx[2] = x + w/2;  yy[2] = y      ;
        xx[3] = x + w  ;  yy[3] = y + h/2;

        g.fillPolygon(xx, yy, 4);
      }

    //----------------------------------------------------------------

    public void dibujar_comodin(int x, int y, int w, int h)
      {
        g.drawImage(comodin, x, y, w, h, null);
      }

    //----------------------------------------------------------------

    public void dibujar(int carta, int x, int y, int w, int h)
      {
        g.setColor(Color.white);
        g.fillRoundRect(x, y, w, h, w/20, h/20);
        Font fuente = new Font("Helvetica", Font.BOLD, h/10);
        g.setFont(fuente);
        FontMetrics font_metrics = g.getFontMetrics();

        String cadena;
        int numero;
        int palo = 4;

        if (carta > 51)
          {
            cadena = "COMODIN";
            g.setColor(Color.green);
          }
        else
          {
            numero = carta % 13;
            palo = carta / 13;

            if (numero == 0)
              cadena = "A";
            else if (numero == 10)
              cadena = "J";
            else if (numero == 11)
              cadena = "Q";
            else if (numero == 12)
              cadena = "K";
            else
              cadena = "" + (numero+1);

            if (palo/2 == 0)
              g.setColor(Color.black);
            else
              g.setColor(Color.red);
          }

        int ancho_cadena = font_metrics.stringWidth(cadena);
        g.drawString(cadena, x+(w*31)/32-ancho_cadena, y+(h*31)/32);
        g.drawString(cadena, x+w/15, y+h/8);

        if (palo == 0)
          dibujar_espadas(x+(w*2)/5, y+(h*2)/5, w/5, h/5);
        else if (palo == 1)
          dibujar_treboles(x+(w*2)/5, y+(h*2)/5, w/5, h/5);
        else if (palo == 2)
          dibujar_corazones(x+(w*2)/5, y+(h*2)/5, w/5, h/5);
        else if (palo == 3)
          dibujar_diamantes(x+(w*2)/5, y+(h*2)/5, w/5, h/5);
        else
          dibujar_comodin(x+w/4, y+h/4, w/2, h/2);
      }
  }
