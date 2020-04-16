//********************************************************************
// amplificar.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class amplificar extends Applet
  {
    Graphics g;

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

        Image imagen_fueraPant = createImage(640, 400);
        Graphics CG_fueraPant = imagen_fueraPant.getGraphics();
        CG_fueraPant.drawImage(fondo, 0, 0, this);
      }

    //----------------------------------------------------------------

    public boolean mouseMove(Event evt, int x, int y)
      {
        borrar_cuadro();

        cuadro_x = x;
        cuadro_y = y;

        if (cuadro_x > ancho)
          cuadro_x = ancho;

        if (cuadro_y > alto)
          cuadro_y = alto;

        dibujar_cuadro();

        return true;
      }

    //----------------------------------------------------------------

    void dibujar_cuadro()
      {
        Graphics g2;
        g2 = g.create();

        g2.clipRect(cuadro_x, cuadro_y, ancho_cuadro, alto_cuadro);

        g2.drawImage(fondo,
                     -cuadro_x+ancho_cuadro/2, -cuadro_y+alto_cuadro/2,
                     ancho*2, alto*2, null);

        g.setColor(Color.red);
        g.drawRect(cuadro_x, cuadro_y, ancho_cuadro-1, alto_cuadro-1);
      }

    //----------------------------------------------------------------

    void borrar_cuadro()
      {
        Graphics g2;
        g2 = g.create();

        g2.clipRect(cuadro_x, cuadro_y, ancho_cuadro, alto_cuadro);

        g2.drawImage(fondo, ancho_cuadro/2, alto_cuadro/2, null);

        if (cuadro_x < ancho_cuadro/2)
          {
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, ancho_cuadro/2, alto+alto_cuadro);
          }

        if (cuadro_y < alto_cuadro/2)
          {
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, ancho+ancho_cuadro, alto_cuadro/2);
          }

        if (cuadro_x > (ancho-ancho_cuadro/2))
          {
            g.setColor(Color.lightGray);
            g.fillRect(ancho+ancho_cuadro/2, 0,
                       ancho_cuadro/2, alto+alto_cuadro);
          }

        if (cuadro_y > (alto-alto_cuadro))
          {
            g.setColor(Color.lightGray);
            g.fillRect(0, alto+alto_cuadro/2,
                       ancho+ancho_cuadro, alto_cuadro/2);
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

            resize(ancho+ancho_cuadro, alto+alto_cuadro);

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
          showStatus("Amplificar:  realizado");
          g.drawImage(fondo, ancho_cuadro/2, alto_cuadro/2, this);
          dibujar_cuadro();
          }
      }
  }
