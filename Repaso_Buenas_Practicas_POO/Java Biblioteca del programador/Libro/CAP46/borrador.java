//********************************************************************
// borrador.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class borrador extends Applet
  {
    Graphics g;

    Image imagen_1;
    Image imagen_2;

    int ancho;
    int alto;

    boolean imagen_cargada = false;
    boolean primera_vez = true;

    int tamano_borrador = 50;

    int x_anterior;
    int y_anterior;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        ancho = size().width;
        alto = size().height;

        String parametro = getParameter("TAMANO_BORRADOR");
        if (parametro != null)
          tamano_borrador = Integer.parseInt(parametro);

        imagen_1 = getImage(getCodeBase(), "imagen1.gif");
        imagen_2 = getImage(getCodeBase(), "imagen2.gif");

        Image imagen_fueraPant = createImage(300, 400);
        Graphics CG_fueraPant = imagen_fueraPant.getGraphics();
        CG_fueraPant.drawImage(imagen_1, 0, 0, this);
      }

    //----------------------------------------------------------------

    public boolean mouseMove(Event evt, int x, int y)
      {
        Graphics g2 = g.create();
        g2.setXORMode(Color.white);

        if (primera_vez)
          primera_vez = false;
        else
          g2.drawRect(x_anterior, y_anterior, tamano_borrador, tamano_borrador);

        g2.drawRect(x, y, tamano_borrador, tamano_borrador);
        x_anterior = x;
        y_anterior = y;
        return true;
      }

    //----------------------------------------------------------------

    public boolean mouseDrag(Event evt, int x, int y)
      {
        reemplazar_imagen(x, y);
        return true;
      }

    //----------------------------------------------------------------

    public boolean mouseDown(Event evt, int x, int y)
      {
        reemplazar_imagen(x, y);
        return true;
      }

    //----------------------------------------------------------------

    public void reemplazar_imagen(int x, int y)
      {
        Graphics g2 = g.create();
        g2.setXORMode(Color.white);

        if (!primera_vez)
          {
            g2.drawRect(x_anterior, y_anterior, tamano_borrador, tamano_borrador);
            primera_vez = true;
          }

        g2.setPaintMode();
        g2.clipRect(x, y, tamano_borrador, tamano_borrador);
        g2.drawImage(imagen_2, 0, 0, ancho, alto, this);
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int w, int h)
      {
        if (infoflags == ALLBITS)
          {
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
          showStatus("Borrador:   cargando imagen");

        else
          {
            ancho = size().width;
            alto = size().height;
            primera_vez = true;
            showStatus("Borrador:  terminado");
            g.drawImage(imagen_1, 0, 0, ancho, alto, this);
          }
      }
  }
