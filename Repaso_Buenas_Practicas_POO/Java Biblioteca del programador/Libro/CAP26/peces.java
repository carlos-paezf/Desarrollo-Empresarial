//********************************************************************
// peces.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

class Coordenada
  {
    public int x;
    public int y;

    public Coordenada(int x, int y)
      {
        this.x = x;
        this.y = y;
      }
  }

//********************************************************************

public class peces extends Applet
  {
    final int TOTAL_PECES = 9;

    Graphics g;
    Image fondo;
    Image pez_gif[];

    boolean imagen_cargada = false;
    boolean fin_carga_todas_imagenes = false;

    Coordenada coordenada[];
    Dimension tamano_pez[];
    FontMetrics font_metrics;

    int pez_actual = 0;

    int nueva_x;
    int nueva_y;

    int cargando_pez = 0;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        Font fuente = new Font("TimesRoman", Font.BOLD, 15);
        g.setFont(fuente);

        font_metrics = g.getFontMetrics();

        fondo = getImage(getCodeBase(), "fondo.gif");

        int ancho  = size().width;
        int alto = size().height;

        pez_gif = new Image[TOTAL_PECES];
        for (int i = 0; i < TOTAL_PECES; i++)
          pez_gif[i] = getImage(getCodeBase(), "pez" + (i+1) + ".gif");

        traer_coordenadas();

        repaint();

        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, this);

        while (!imagen_cargada)
          ;

        tamano_pez = new Dimension[TOTAL_PECES];
        for (int i = 0; i < TOTAL_PECES; i++)
          {
            cargando_pez = i;
            imagen_cargada = false;
            CGFueraPant.drawImage(pez_gif[i], 0, 0, this);
            while (!imagen_cargada)
              ;
            tamano_pez[i] = new Dimension(pez_gif[i].getWidth(null),
                                         pez_gif[i].getHeight(null));
          }

        fin_carga_todas_imagenes = true;
      }

    //----------------------------------------------------------------

    void traer_coordenadas()
      {
        coordenada = new Coordenada[TOTAL_PECES];

        coordenada[0] = new Coordenada(118, 102);
        coordenada[1] = new Coordenada(243,  27);
        coordenada[2] = new Coordenada(361, 103);
        coordenada[3] = new Coordenada(496, 150);
        coordenada[4] = new Coordenada(500,  28);
        coordenada[5] = new Coordenada( 49, 238);
        coordenada[6] = new Coordenada(171, 220);
        coordenada[7] = new Coordenada(305, 252);
        coordenada[8] = new Coordenada(474, 260);
      }

    //----------------------------------------------------------------

    void draw_peces()
      {
        for (int i = 0; i < TOTAL_PECES; i++)
          g.drawImage(pez_gif[i],
                      coordenada[i].x,
                      coordenada[i].y,
                      this);
      }

    //----------------------------------------------------------------

    public boolean mouseDrag(Event evt, int x, int y)
      {
        if (pez_actual != 0)
          {
            nueva_x = x - tamano_pez[pez_actual-1].width/2;
            nueva_y = y - tamano_pez[pez_actual-1].height/2;

            if (nueva_x < 0)
              nueva_x = 0;

            if (nueva_y < 0)
              nueva_y = 0;

            mover_pez(pez_actual - 1);
          }

        return true;
      }

    //----------------------------------------------------------------

    public boolean mouseUp(Event evt, int x, int y)
      {
        pez_actual = 0;
        return true;
      }

    //----------------------------------------------------------------

    public boolean mouseDown(Event evt, int x, int y)
      {
        for (int i = 0; i < TOTAL_PECES; i++)
          {
          if (x > coordenada[i].x &&
              x < coordenada[i].x + tamano_pez[i].width &&
              y > coordenada[i].y &&
              y < coordenada[i].y + tamano_pez[i].height)
            {
              pez_actual = i + 1;
            }
          }

        return true;
      }

    //----------------------------------------------------------------

    void mover_pez(int indice)
      {
        int x_anterior = coordenada [indice].x;
        int y_anterior = coordenada [indice].y;
        int dx    = nueva_x - x_anterior;
        int dy    = nueva_y - y_anterior;
        int fin   = Math.max(Math.abs(dx), Math.abs(dy));

        for (int i = 0; i < fin; i++)
          {
          int x = x_anterior + dx * i / fin;
          int y = y_anterior + dy * i / fin;

          Graphics g2;
          g2 = g.create();

          g2.clipRect(coordenada [indice].x, coordenada [indice].y,
                      tamano_pez[indice].width,
                      tamano_pez[indice].height);
          g2.drawImage(fondo, 0, 0, null);

          coordenada[indice].x = x;
          coordenada[indice].y = y;

          g.drawImage(pez_gif[indice],
                      coordenada[indice].x,
                      coordenada[indice].y,
                      this);
          }

        draw_peces();
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int ancho, int alto)
      {
        if (infoflags == ALLBITS)
          {
            imagen_cargada = true;
            showStatus("");
            repaint();
            return false;
          }
        else
          return true;
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        if (!fin_carga_todas_imagenes)
          showStatus("Pecera:  cargando imagen " + cargando_pez);

        else
          {
            g.drawImage(fondo, 0, 0, this);
            draw_peces();
          }
      }
  }
