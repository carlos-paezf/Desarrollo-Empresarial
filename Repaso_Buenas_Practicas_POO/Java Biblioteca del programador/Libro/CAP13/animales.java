//********************************************************************
// animales.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class animales extends Applet
  {
    final int ANIMALES_TOTALES = 9;

    int filas_max;
    boolean imagen_cargada = false;

    int ancho;
    int alto;

    Graphics g;
    Image fondo;
    Image animal_gif[];
    AudioClip clip;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        fondo = getImage(getCodeBase(), "ciberzoo.gif");

        ancho = size().width;
        alto = size().height;

        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, ancho, alto, this);

        animal_gif = new Image[ANIMALES_TOTALES];
        for (int i = 0; i < ANIMALES_TOTALES; i++)
          animal_gif[i] = getImage(getCodeBase(), "animal" + (i+1) + ".gif");

        filas_max = (ANIMALES_TOTALES + 4) / 5;
      }

    //----------------------------------------------------------------

    public boolean mouseDown(Event evt, int x, int y)
      {
        System.out.println("X " + x + " Y " + y);

        if (x < 500 && y < filas_max*100)
          {
            int index = (y/100)*5 + (x/100);
            if (index < ANIMALES_TOTALES)
              {
                String nomb_archivo = "animal" + (index+1) + ".au";
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
            showStatus("Zoológico:  haga clic sobre un animal");
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
          showStatus("Zoológico:  cargando imagen");
        else
          {
          g.drawImage(fondo, 0, 0, ancho, alto, this);

          for (int i = 0; i < filas_max; i++)
            for (int j = i*5; j < Math.min ((i+1)*5, ANIMALES_TOTALES); j++)
              g.drawImage(animal_gif[j], (j-(i*5))*100+ 5, i*100+5, this);
          }
      }
  }
