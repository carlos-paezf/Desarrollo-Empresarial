//********************************************************************
// animales.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class animales extends Applet
  {
    final int ANIMALES_TOTALES = 9;

    Graphics g;
    Image fondo;
    Image animal_gif[];
    String descripcion[];

    boolean imagen_cargada = false;

    int filas_max;
    int ancho;
    int alto;
    int elemento_actual = 0;

    AudioClip clip;
    FontMetrics font_metrics;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        Font fuente = new Font("TimesRoman", Font.BOLD, 20);
        g.setFont (fuente);

        font_metrics = g.getFontMetrics();

        fondo = getImage(getCodeBase(), "ciberzoo.gif");

        ancho = size().width;
        alto = size().height;

        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, ancho, alto, this);

        animal_gif = new Image[ANIMALES_TOTALES];
        for (int i = 0; i < ANIMALES_TOTALES; i++)
          animal_gif[i] = getImage(getCodeBase(), "animal" + (i+1) + ".gif");

        traer_descripciones();

        filas_max = (ANIMALES_TOTALES + 4) / 5;
      }

    //----------------------------------------------------------------

    void traer_descripciones()
      {
        descripcion = new String[ANIMALES_TOTALES];

        descripcion[0] = new String("Gallina");
        descripcion[1] = new String("Vaca");
        descripcion[2] = new String("Perro");
        descripcion[3] = new String("Burro");
        descripcion[4] = new String("Pato");
        descripcion[5] = new String("Caballo");
        descripcion[6] = new String("Gallo");
        descripcion[7] = new String("Oveja");
        descripcion[8] = new String("Pavo");
      }

    //----------------------------------------------------------------

    public boolean mouseMove(Event evt, int x, int y)
      {
        int donde_click = 0;
        boolean borrado = false;

        if (x < 500 && y < filas_max*100)
          {
            int indice = (y/100)*5 + (x/100);
            if (indice < ANIMALES_TOTALES)
              {
                donde_click = indice + 1;
                if (elemento_actual != donde_click)
                  {
                    if (elemento_actual != 0)
                      {
                      borrar_nombre(elemento_actual - 1);
                      borrado = true;
                      }
                    System.out.println(descripcion[indice]);
                    dibujar_nombre(indice);
                  }
              }
          }

        if (!borrado && elemento_actual != 0 && elemento_actual != donde_click)
          borrar_nombre(elemento_actual - 1);

        elemento_actual = donde_click;

        if (elemento_actual == 0)
          showStatus("");
        else
          showStatus("Animal " + elemento_actual);

        return true;
      }

    //----------------------------------------------------------------

    void borrar_nombre(int indice)
      {
        int ancho_string = font_metrics.stringWidth(descripcion[indice]);
        int alto_string = font_metrics.getHeight();

        int x = (indice%5) * 100;
        int y = (indice/5) * 100;

        int x2 = (x/100)*100 + 50 - ancho_string/2;
        int y2 = ((y+100)/100)*100;

        Graphics g2;
        g2 = g.create();

        g2.clipRect(x2, y2-alto_string,
                    ancho_string, alto_string);

        g2.drawImage(fondo, 0, 0, ancho, alto, null);

        g2.drawImage(animal_gif[indice], x+5, y+5, this);
      }

    //----------------------------------------------------------------

    void dibujar_nombre(int indice)
      {
        int ancho_string = font_metrics.stringWidth(descripcion [indice]);
        int alto_string = font_metrics.getHeight();

        int x = (indice%5) * 100;
        int y = (indice/5) * 100;

        int x2 = (x/100)*100 + 50 - ancho_string/2;
        int y2 = ((y+100)/100)*100;

        g.setColor(Color.black);
        g.fillRect(x2, y2-alto_string, ancho_string, alto_string);

        g.setColor(Color.white);
        g.drawString(descripcion[indice], x2, y2-font_metrics.getDescent());
      }

    //----------------------------------------------------------------

    public boolean mouseDown(Event evt, int x, int y)
      {
        System.out.println("X " + x + " Y " + y);

        if (x < 500 && y < filas_max*100)
          {
            int indice = (y/100)*5 + (x/100);
            if (indice < ANIMALES_TOTALES)
              {
                String nomb_archivo = "animal" + (indice+1) + ".au";
                clip = getAudioClip(getCodeBase(), nomb_archivo);
                System.out.println("Reproduciendo " + nomb_archivo);
                clip.play();
              }
          }

        return true;
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int ancho, int alto)
      {
        if (infoflags == ALLBITS)
          {
            imagen_cargada = true;
            repaint ();
            return false;
          }
        else
          return true;
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        if (!imagen_cargada)
          showStatus("Zoológico:  cargando imagen");
        else
          {
            g.drawImage(fondo, 0, 0, ancho, alto, this);
            g.setColor(Color.white);

            for (int i = 0; i < filas_max; i++)
              for (int j = i*5; j < Math.min ((i+1)*5, ANIMALES_TOTALES); j++)
                {
                  g.drawImage(animal_gif[j], (j-(i*5))*100+ 5, i*100+5, this);

                  g.drawRect((j-(i*5))*100+ 5, i*100+5, 100, 100);
                }
          }
      }
  }
