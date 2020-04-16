//*******************************************************************
// peces.java
//*******************************************************************

import java.applet.*;
import java.awt.*;

//*******************************************************************

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

//*******************************************************************

public class peces extends Applet implements Runnable
  {
    final int TOTAL_PECES = 9;

    Graphics g;
    Image fondo;
    Image pez_gif[];
    Coordenada coordenada[];
    Dimension tamano_pez[];
    int velocidad[];

    boolean imagen_cargada = false;
    boolean fin_carga_todas_imagenes = false;

    Thread mi_hilo;

    int nueva_x;
    int nueva_y;
    int ancho;
    int alto;

    int cargando_pez = 0;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        fondo = getImage(getCodeBase(), "fondo.gif");

        ancho = size().width;
        alto = size().height;

        pez_gif = new Image[TOTAL_PECES];
        for (int i = 0; i < TOTAL_PECES; i++)
          pez_gif[i] = getImage(getCodeBase(), "pez" + (i+1) + ".gif");

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

        traer_coordenadas();

        fin_carga_todas_imagenes = true;
      }

    //----------------------------------------------------------------

    public void start()
      {
        if (mi_hilo == null)
          {
            mi_hilo = new Thread(this);
            mi_hilo.start();
          }
      }

    //----------------------------------------------------------------

    void traer_coordenadas()
      {
        coordenada = new Coordenada[TOTAL_PECES];
        for (int i = 0; i < TOTAL_PECES; i++)
          coordenada[i] = new Coordenada
                          ((int) (Math.random() *
                                  (ancho -tamano_pez[i].width)),
                           (int) (Math.random() *
                                  (alto-tamano_pez[i].height)));

        velocidad = new int[TOTAL_PECES];
        for (int i = 0; i < TOTAL_PECES; i++)
          {
            velocidad[i] = (int) (Math.random() * 41) - 20;
            if (velocidad[i] == 0)
              velocidad[i] = 1;
          }
      }

    //----------------------------------------------------------------

    public void demora ()
      {
        try
          {
            Thread.sleep(1000);
          }
        catch (InterruptedException e)
          {
          }
      }

    //----------------------------------------------------------------

    void dibujar_peces()
      {
        for (int i = 0; i < TOTAL_PECES; i++)
          g.drawImage(pez_gif[i],
                      coordenada[i].x,
                      coordenada[i].y,
                      this);
      }

    //----------------------------------------------------------------

    void mover_pez(int indice)
      {
        Graphics g2;
        g2 = g.create();

        g2.clipRect(coordenada [indice].x, coordenada [indice].y,
                    tamano_pez[indice].width,
                    tamano_pez[indice].height);
        g2.drawImage(fondo, 0, 0, null);

        coordenada[indice].x = nueva_x;
        coordenada[indice].y = nueva_y;

        g.drawImage(pez_gif[indice],
                    coordenada[indice].x,
                    coordenada[indice].y,
                    this);
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (true)
          {
            for (int i = 0; i < TOTAL_PECES; i++)
              {
                nueva_x = coordenada[i].x + velocidad[i];
                nueva_y = coordenada[i].y;

                if (nueva_x < 0)
                  {
                    nueva_x = 0;
                    velocidad[i] *= -1;
                  }

                if (nueva_x > ancho-tamano_pez[i].width)
                  {
                    nueva_x = ancho - tamano_pez[i].width;
                    velocidad[i] *= -1;
                  }

                mover_pez(i);
              }

            dibujar_peces();
            demora();
          }
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int ancho, int alto)
      {
        if (infoflags == ALLBITS)
          {
            imagen_cargada = true;
            showStatus("");
            resize(fondo.getWidth(this),
                   fondo.getHeight(this) + 50);

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
            dibujar_peces();
            g.setColor(Color.blue);
            Font fuente = new Font("TimesRoman", Font.BOLD, 35);
            g.setFont(fuente);
            g.drawString("Acuarios y Peceras",
                         20, alto+40);
          }
      }
  }
