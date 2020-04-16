//********************************************************************
// LED.java
//********************************************************************

import java.awt.*;

//********************************************************************

public class LED
  {
    private Graphics g;

    private int puntos_x[];
    private int puntos_y[];

    private int dx = 5;
    private int dy = 10;

    private int digitos[][] =
      {
        {1, 1, 1, 1, 1, 1, 0},     // 0
        {0, 0, 0, 0, 1, 1, 0},     // 1
        {1, 0, 1, 1, 0, 1, 1},     // 2
        {1, 0, 0, 1, 1, 1, 1},     // 3
        {0, 1, 0, 0, 1, 1, 1},     // 4
        {1, 1, 0, 1, 1, 0, 1},     // 5
        {1, 1, 1, 1, 1, 0, 1},     // 6
        {1, 0, 0, 0, 1, 1, 0},     // 7
        {1, 1, 1, 1, 1, 1, 1},     // 8
        {1, 1, 0, 1, 1, 1, 1},     // 9
      };

    //----------------------------------------------------------------

    public LED(Graphics g)
      {
        this.g = g;

        puntos_x = new int[6];
        puntos_y = new int[6];
      }

    //----------------------------------------------------------------

    public void dibujar_cadena(String string, int x, int y, int w, int h)
      {
        int longitud_cadena = string.length();
        char arreglo_char[] = new char[longitud_cadena];
        string.getChars(0, longitud_cadena, arreglo_char, 0);

        w /= longitud_cadena;

        int ancho_char  = (w * 7) / 8;
        int char_alto = (h * 7) / 8;

        for (int i = 0; i < longitud_cadena; i++)
          {
            dibujar_char(arreglo_char[i], x, y, ancho_char, char_alto);
            x += w;
          }
      }

    //----------------------------------------------------------------

    private void dibujar_char(char c, int x, int y, int w, int h)
      {
        switch (c)
          {
            case ':':
              g.fillRect(x+2*w/dx, y-(h  )/4, w/dx, h/dy);
              g.fillRect(x+2*w/dx, y-(h*3)/4, w/dx, h/dy);
              break;

            default:
              int indice = Character.digit(c, 10);
              for (int i = 0; i < 7; i++)
                if (digitos[indice][i] == 1)
                  switch (i)
                    {
                      case 0:  arriba    (x  , y-h  , w   , h/dy); break;
                      case 1:  izquierda   (x  , y-h/2, w/dx, h/ 2); break;
                      case 2:  izquierda   (x  , y    , w/dx, h/ 2); break;
                      case 3:  abajo (x  , y    , w   , h/dy); break;
                      case 4:  right  (x+w, y    , w/dx, h/ 2); break;
                      case 5:  right  (x+w, y-h/2, w/dx, h/ 2); break;
                      case 6:  centro (x  , y-h/2, w   , h/dy); break;
                    }
              break;
          }
      }

    //----------------------------------------------------------------

    private void arriba(int x, int y, int w, int h)
      {
        puntos_x[0] = x        ;  puntos_y[0] = y  ;
        puntos_x[1] = x+w      ;  puntos_y[1] = y  ;
        puntos_x[2] = x+(w*7)/8;  puntos_y[2] = y+h;
        puntos_x[3] = x+(w  )/8;  puntos_y[3] = y+h;

        g.fillPolygon(puntos_x, puntos_y, 4);
      }

    //----------------------------------------------------------------

    private void centro(int x, int y, int w, int h)
      {
        puntos_x[0] = x          ;  puntos_y[0] = y    ;
        puntos_x[1] = x+(w  )/8  ;  puntos_y[1] = y-h/2;
        puntos_x[2] = x+(w*7)/8  ;  puntos_y[2] = y-h/2;
        puntos_x[3] = x+w        ;  puntos_y[3] = y    ;
        puntos_x[4] = x+(w*7)/8  ;  puntos_y[4] = y+h/2;
        puntos_x[5] = x+(w  )/8  ;  puntos_y[5] = y+h/2;

        g.fillPolygon(puntos_x, puntos_y, 6);
      }

    //----------------------------------------------------------------

    private void abajo(int x, int y, int w, int h)
      {
        puntos_x[0] = x        ;  puntos_y[0] = y  ;
        puntos_x[1] = x+w      ;  puntos_y[1] = y  ;
        puntos_x[2] = x+(w*7)/8;  puntos_y[2] = y-h;
        puntos_x[3] = x+(w  )/8;  puntos_y[3] = y-h;

        g.fillPolygon(puntos_x, puntos_y, 4);
      }

    //----------------------------------------------------------------

    private void izquierda(int x, int y, int w, int h)
      {
        puntos_x[0] = x  ;  puntos_y[0] = y        ;
        puntos_x[1] = x  ;  puntos_y[1] = y-h      ;
        puntos_x[2] = x+w;  puntos_y[2] = y-(h*7)/8;
        puntos_x[3] = x+w;  puntos_y[3] = y-(h  )/8;

        g.fillPolygon(puntos_x, puntos_y, 4);
      }

    //----------------------------------------------------------------

    private void right(int x, int y, int w, int h)
      {
        puntos_x[0] = x  ;  puntos_y[0] = y        ;
        puntos_x[1] = x  ;  puntos_y[1] = y-h      ;
        puntos_x[2] = x-w;  puntos_y[2] = y-(h*7)/8;
        puntos_x[3] = x-w;  puntos_y[3] = y-(h  )/8;

        g.fillPolygon(puntos_x, puntos_y, 4);
      }
  }
