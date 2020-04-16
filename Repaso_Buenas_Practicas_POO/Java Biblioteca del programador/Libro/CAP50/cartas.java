//********************************************************************
// cartas.java
//********************************************************************

import java.awt.*;

//********************************************************************

public class cartas
  {
    Graphics g;
    Image comodin;

    int x;
    int y;
    int w;
    int h;
    int numero;
    int palo;

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

        xx[0] = x + w/2;  yy[0] = y      ;
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
        xx[1] = x      ;  yy[1] = y + h/4;
        xx[2] = x + w  ;  yy[2] = y + h/4;

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
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        g.setColor(Color.white);
        g.fillRoundRect(x, y, w, h, w/20, h/20);
        Font fuente = new Font("Helvetica", Font.BOLD, h/10);
        g.setFont(fuente);
        FontMetrics font_metrics = g.getFontMetrics();

        String cadena;
        numero = -1;
        palo = -1;

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

        dibujar_palo(x+w/15, y+h/8,
                  x+(w*31)/32-ancho_cadena, y+(h*31)/32,
                  3 * font_metrics.getHeight () / 2);
      }

    //----------------------------------------------------------------

    void dibujar_palo(int x1, int y1, int x2, int y2, int hh)
      {
        switch (palo)
          {
            case 0:
              dibujar_espadas(x1, y1   , w/10, h/10);
              dibujar_espadas(x2, y2-hh, w/10, h/10);

              switch (numero+1)
                {
                  case 1:
                  case 11:
                  case 12:
                  case 13:
                    dibujar_espadas(x+(w*2)/5, y+(h*2)/5, w/5, h/5);
                    break;

                  case 2:
                    dibujar_espadas(x+(w*2)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 3:
                    dibujar_espadas(x+(w*2)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 4:
                    dibujar_espadas(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 5:
                    dibujar_espadas(x+(w*2)/5, y+(h*2)/5 , w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 6:
                    dibujar_espadas(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 7:
                    dibujar_espadas(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    break;

                  case 8:
                    dibujar_espadas(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*2)/10, w/5, h/5);
                    break;

                  case 9:
                    dibujar_espadas(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*2)/5 , w/5, h/5);
                    break;

                  case 10:
                    dibujar_espadas(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_espadas(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_espadas(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_espadas(x+(w*2)/5, y+(h*2)/10, w/5, h/5);
                    break;
                }

              break;

            case 1:
              dibujar_treboles(x1, y1   , w/10, h/10);
              dibujar_treboles(x2, y2-hh, w/10, h/10);

              switch (numero+1)
                {
                  case 1:
                  case 11:
                  case 12:
                  case 13:
                    dibujar_treboles(x+(w*2)/5, y+(h*2)/5, w/5, h/5);
                    break;

                  case 2:
                    dibujar_treboles(x+(w*2)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 3:
                    dibujar_treboles(x+(w*2)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 4:
                    dibujar_treboles(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 5:
                    dibujar_treboles(x+(w*2)/5, y+(h*2)/5 , w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 6:
                    dibujar_treboles(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 7:
                    dibujar_treboles(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    break;

                  case 8:
                    dibujar_treboles(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*2)/10, w/5, h/5);
                    break;

                  case 9:
                    dibujar_treboles(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*2)/5 , w/5, h/5);
                    break;

                  case 10:
                    dibujar_treboles(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_treboles(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_treboles(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_treboles(x+(w*2)/5, y+(h*2)/10, w/5, h/5);
                    break;
                }

              break;

            case 2:
              dibujar_corazones(x1, y1   , w/10, h/10);
              dibujar_corazones(x2, y2-hh, w/10, h/10);

              switch (numero+1)
                {
                  case 1:
                  case 11:
                  case 12:
                  case 13:
                    dibujar_corazones(x+(w*2)/5, y+(h*2)/5, w/5, h/5);
                    break;

                  case 2:
                    dibujar_corazones(x+(w*2)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 3:
                    dibujar_corazones(x+(w*2)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 4:
                    dibujar_corazones(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 5:
                    dibujar_corazones(x+(w*2)/5, y+(h*2)/5 , w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 6:
                    dibujar_corazones(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 7:
                    dibujar_corazones(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    break;

                  case 8:
                    dibujar_corazones(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*2)/10, w/5, h/5);
                    break;

                  case 9:
                    dibujar_corazones(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*2)/5 , w/5, h/5);
                    break;

                  case 10:
                    dibujar_corazones(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_corazones(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_corazones(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_corazones(x+(w*2)/5, y+(h*2)/10, w/5, h/5);
                    break;
                }

              break;

            case 3:
              dibujar_diamantes(x1, y1   , w/10, h/10);
              dibujar_diamantes(x2, y2-hh, w/10, h/10);

              switch (numero+1)
                {
                  case 1:
                  case 11:
                  case 12:
                  case 13:
                    dibujar_diamantes(x+(w*2)/5, y+(h*2)/5, w/5, h/5);
                    break;

                  case 2:
                    dibujar_diamantes(x+(w*2)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 3:
                    dibujar_diamantes(x+(w*2)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 4:
                    dibujar_diamantes(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 5:
                    dibujar_diamantes(x+(w*2)/5, y+(h*2)/5 , w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 6:
                    dibujar_diamantes(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    break;

                  case 7:
                    dibujar_diamantes(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    break;

                  case 8:
                    dibujar_diamantes(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*4)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*2)/10, w/5, h/5);
                    break;

                  case 9:
                    dibujar_diamantes(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*2)/5 , w/5, h/5);
                    break;

                  case 10:
                    dibujar_diamantes(x+(w*1)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*1)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*1)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*3)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*3)/5, y+(h*7)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*5)/10, w/5, h/5);
                    dibujar_diamantes(x+(w*2)/5, y+(h*2)/10, w/5, h/5);
                    break;
                }

              break;

            default:
              dibujar_comodin(x+w/4, y+h/4, w/2, h/2);
          }
      }
  }
