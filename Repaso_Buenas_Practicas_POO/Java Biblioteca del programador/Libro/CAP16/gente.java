//********************************************************************
// gente.java
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

public class gente extends Applet
  {
    final int NUM_GENTE = 9;

    Graphics g;
    Image fondo;
    Image gente_gif[];
    String descripcion[];

    boolean imagen_cargada = false;

    int filas_max;
    int ancho;
    int alto;
    int elemento_actual = 0;

    FontMetrics font_metrics;
    Coordenada coordenada[];

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        Font fuente = new Font("TimesRoman", Font.BOLD, 15);
        g.setFont(fuente);

        font_metrics = g.getFontMetrics();

        fondo = getImage(getCodeBase(), "fondo.gif");

        ancho = size().width;
        alto = size().height;

        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, ancho, alto, this);

        gente_gif = new Image[NUM_GENTE];
        for (int i = 0; i < NUM_GENTE; i++)
          gente_gif[i] = getImage(getCodeBase(), "gente" + (i+1) + ".gif");

        traer_descripciones_y_coordenadas();

        filas_max = (NUM_GENTE + 4) / 5;
      }

    //----------------------------------------------------------------

    void traer_descripciones_y_coordenadas()
      {
        descripcion = new String[NUM_GENTE];

        descripcion[0] = new String("Reagan");
        descripcion[1] = new String("Carter");
        descripcion[2] = new String("Ford");
        descripcion[3] = new String("Nixon");
        descripcion[4] = new String("Johnson");
        descripcion[5] = new String("Kennedy");
        descripcion[6] = new String("Eisenhower");
        descripcion[7] = new String("Truman");
        descripcion[8] = new String("Roosevelt");

        coordenada = new Coordenada[NUM_GENTE];

        coordenada[0] = new Coordenada(118, 102);
        coordenada[1] = new Coordenada(243, 27);
        coordenada[2] = new Coordenada(361, 103);
        coordenada[3] = new Coordenada(496, 150);
        coordenada[4] = new Coordenada(525, 28);
        coordenada[5] = new Coordenada(49, 238);
        coordenada[6] = new Coordenada(171, 220);
        coordenada[7] = new Coordenada(305, 282);
        coordenada[8] = new Coordenada(474, 290);
      }

    //----------------------------------------------------------------

    public boolean mouseMove(Event evt, int x, int y)
      {
        int posicion_mouse = 0;
        boolean borrado = false;

        for (int i = 0; i < NUM_GENTE; i++)
          {
            if (x > coordenada[i].x && x < coordenada[i].x + 100 &&
                y > coordenada[i].y && y < coordenada[i].y + 100)
              {
                posicion_mouse = i + 1;
                if (elemento_actual != posicion_mouse)
                  {
                    if (elemento_actual != 0)
                      {
                        borrar_nombre(elemento_actual - 1);
                        borrado = true;
                      }
                    dibujar_nombre(i);
                    break;
                  }
              }
          }

        if (!borrado && elemento_actual != 0 &&
            elemento_actual != posicion_mouse)
          borrar_nombre(elemento_actual - 1);

        elemento_actual = posicion_mouse;

        if (elemento_actual == 0)
          showStatus("");
        else
          showStatus("Persona " + elemento_actual);

        return true;
      }

    //----------------------------------------------------------------

    void borrar_nombre(int indice)
      {
        int ancho_string = font_metrics.stringWidth(descripcion [indice]);
        int alto_string = font_metrics.getHeight();

        int x2 = coordenada[indice].x +  50 - ancho_string/2;
        int y2 = coordenada[indice].y + 100;

        Graphics g2;
        g2 = g.create();

        g2.clipRect(x2, y2-alto_string, ancho_string, alto_string);

        g2.drawImage(fondo, 0, 0, ancho, alto, null);

        g2.drawImage(gente_gif[indice], coordenada[indice].x,
                     coordenada[indice].y, this);
      }

    //----------------------------------------------------------------

    void dibujar_nombre(int indice)
      {
        g.setColor(Color.black);

        int ancho_string = font_metrics.stringWidth(descripcion[indice]);
        int alto_string = font_metrics.getHeight();

        int x2 = coordenada[indice].x +  50 - ancho_string/2;
        int y2 = coordenada[indice].y + 100;

        g.fillRect(x2, y2-alto_string, ancho_string, alto_string);

        g.setColor(Color.yellow);
        g.drawString(descripcion [indice], x2, y2-font_metrics.getDescent ());
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int ancho, int alto)
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

    public void paint(Graphics g)
      {
        if (!imagen_cargada)
          showStatus("Gente:  cargando imagen");

        else
          {
            g.drawImage(fondo, 0, 0, ancho, alto, this);
            g.setColor(Color.white);

            for (int i = 0; i < NUM_GENTE; i++)
              {
                g.drawImage(gente_gif[i], coordenada[i].x,
                            coordenada[i].y, this);

                g.drawRect(coordenada[i].x, coordenada[i].y,
                           100, 100);
              }
          }
      }
  }
