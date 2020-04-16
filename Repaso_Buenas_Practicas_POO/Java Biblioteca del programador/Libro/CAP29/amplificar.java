//********************************************************************
// amplificar.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class amplificar extends Applet
  {
    Graphics g;

    Image imagen_fueraPant;
    Image fondo;
    int ancho;
    int alto;
    boolean imagen_cargada = false;

    int cuadro_x = 0;
    int cuadro_y = 0;
    int ancho_cuadro = 100;
    int alto_cuadro = 100;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        fondo = getImage(getCodeBase(), "estadio.gif");

        imagen_fueraPant = createImage(640, 400);
        Graphics CG_fueraPant = imagen_fueraPant.getGraphics();
        CG_fueraPant.drawImage(fondo, 0, 0, this);
      }

    //----------------------------------------------------------------

    public boolean mouseMove(Event evt, int x, int y)
      {
        borrar_cuadro(cuadro_x, cuadro_y, x, y);

        cuadro_x = x;
        cuadro_y = y;

        if (cuadro_x > (ancho - ancho_cuadro/2))
          cuadro_x = ancho - ancho_cuadro/2;

        if (cuadro_y > (alto - alto_cuadro/2))
          cuadro_y = alto - ancho_cuadro/2;

        dibujar_cuadro();

        return true;
      }

    //----------------------------------------------------------------

    void dibujar_cuadro()
      {
        Graphics g2;
        g2 = g.create();

        g2.clipRect(cuadro_x, cuadro_y, ancho_cuadro, alto_cuadro);

        g2.drawImage(fondo, -cuadro_x, -cuadro_y,
                     ancho*2, alto*2, null);

        g.setColor(Color.red);
        g.drawRect(cuadro_x, cuadro_y, ancho_cuadro-1, alto_cuadro-1);
      }

    //----------------------------------------------------------------

    void borrar_cuadro(int x_anterior, int y_anterior, int nueva_x, int nueva_y)
      {
      Graphics g2;
      g2 = g.create();

      if (nueva_x <= x_anterior && nueva_y <= y_anterior)
        {
          g2.clipRect(nueva_x, nueva_y+alto_cuadro,
                      ancho_cuadro+x_anterior-nueva_x, y_anterior-nueva_y);
          g2.drawImage(imagen_fueraPant, 0, 0, null);
          g2 = g.create();
          g2.clipRect(nueva_x+ancho_cuadro, nueva_y,
                      x_anterior-nueva_x, alto_cuadro+y_anterior-nueva_y);
          g2.drawImage(imagen_fueraPant, 0, 0, null);
        }
      else if (nueva_x > x_anterior && nueva_y <= y_anterior)
        {
          g2.clipRect(x_anterior, nueva_y+alto_cuadro,
                      ancho_cuadro+nueva_x-x_anterior, y_anterior-nueva_y);
          g2.drawImage(imagen_fueraPant, 0, 0, null);
          g2 = g.create();
          g2.clipRect(x_anterior, nueva_y,
                      nueva_x-x_anterior, alto_cuadro+y_anterior-nueva_y);
          g2.drawImage(imagen_fueraPant, 0, 0, null);
        }
      else if (nueva_x > x_anterior && nueva_y > y_anterior)
        {
          g2.clipRect(x_anterior, y_anterior,
                      ancho_cuadro+nueva_x-x_anterior, nueva_y-y_anterior);
          g2.drawImage(imagen_fueraPant, 0, 0, null);
          g2 = g.create();
          g2.clipRect(x_anterior, y_anterior,
                      nueva_x-x_anterior, alto_cuadro+nueva_y-y_anterior);
          g2.drawImage(imagen_fueraPant, 0, 0, null);
        }
      else // if (nueva_x <= x_anterior && nueva_y > y_anterior)
        {
          g2.clipRect(nueva_x, y_anterior,
                      ancho_cuadro+x_anterior-nueva_x, nueva_y-y_anterior);
          g2.drawImage(imagen_fueraPant, 0, 0, null);
          g2 = g.create();
          g2.clipRect(nueva_x+ancho_cuadro, y_anterior,
                      x_anterior-nueva_x, alto_cuadro+nueva_y-y_anterior);
          g2.drawImage(imagen_fueraPant, 0, 0, null);
        }
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int w, int h)
      {
        if (infoflags == ALLBITS)
          {
            ancho = fondo.getWidth(this);
            alto = fondo.getHeight(this);

            resize(ancho+ancho_cuadro/2, alto+alto_cuadro/2);

            imagen_fueraPant = createImage(ancho  + ancho_cuadro /2,
                                          alto + alto_cuadro/2);

            Graphics CG_fueraPant = imagen_fueraPant.getGraphics();
            CG_fueraPant.setColor(Color.lightGray);
            CG_fueraPant.fillRect(0, 0,
                                  ancho  + ancho_cuadro /2,
                                  alto + alto_cuadro/2);

            CG_fueraPant.drawImage(fondo, ancho_cuadro/4,
                                   alto_cuadro/4, this);

            imagen_cargada = true;
            repaint();

            return false;
          }
        else
          return true;
      }

    //----------------------------------------------------------------

    public void paint(Graphics _g)
      {
        if (!imagen_cargada)
          showStatus("Amplificar:  cargando imagen");

        else
          {
            showStatus("Amplificar:  terminado");
            g.drawImage(fondo, ancho_cuadro/4, alto_cuadro/4, this);
            dibujar_cuadro();
          }
      }
  }
