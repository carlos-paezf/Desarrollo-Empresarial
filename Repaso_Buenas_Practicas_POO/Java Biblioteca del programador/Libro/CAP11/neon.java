//********************************************************************
// neon.java
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

public class neon extends Applet implements Runnable
  {
    Graphics g;
    FontMetrics font_metrics;
    Image fondo;

    int tamano_focos = 10;
    int espacio_focos = 20;
    int valor_demora = 500;
    int foco_apag = 0;
    Thread mi_hilo = null;

    boolean imagen_cargada = false;
    boolean hilo_corriendo = false;

    String mensaje = "Las Vegas";

    int ancho;
    int alto;
    int longitud_mensaje;
    int string_x;
    int string_y;
    int ancho_string;
    int alto_string;
    char arreglo_char[];

    Coordenada coordenada[];

    int total_focos;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        fondo = getImage(getCodeBase(), "vegas.gif");

        String parametro;

        parametro = getParameter("MENSAJE");
        if (parametro != null)
          mensaje = parametro;

        parametro = getParameter("DEMORA");
        if (parametro != null)
          valor_demora = Integer.parseInt(parametro);

        parametro = getParameter("TAMANO_FOCOS");
        if (parametro != null)
          tamano_focos = Integer.parseInt(parametro);

        parametro = getParameter("ESPACIO_FOCOS");
        if (parametro != null)
          espacio_focos = Integer.parseInt(parametro);

        ancho = size().width;
        alto = size().height;

        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, ancho, alto, this);

        longitud_mensaje = mensaje.length();
        arreglo_char = new char[longitud_mensaje];
        mensaje.getChars(0, longitud_mensaje, arreglo_char, 0);

        Font fuente = new Font("TimesRoman", Font.BOLD, 20);
        g.setFont(fuente);

        font_metrics = g.getFontMetrics();

        ancho_string = font_metrics.stringWidth(mensaje);
        alto_string = font_metrics.getHeight();

        string_x = (ancho + espacio_focos*2 - ancho_string) / 2;
        string_y = alto + espacio_focos*2 + 30;

        resize(ancho+espacio_focos*2, alto+40+espacio_focos*2);

        coordenadas_focos();
      }

    //----------------------------------------------------------------

    public void start()
      {
        if (mi_hilo == null)
          mi_hilo = new Thread(this);
      }

    //----------------------------------------------------------------

    public void corre_hilo()
      {
        if (!hilo_corriendo)
          {
            mi_hilo.start();
            hilo_corriendo = true;
          }
      }

    //----------------------------------------------------------------

    void demora ()
      {
        try
          {
            Thread.sleep(valor_demora);
          }
        catch (InterruptedException e)
          {
          }
      }

    //----------------------------------------------------------------

    void coordenadas_focos()
      {
        total_focos = 2*ancho /espacio_focos +
                      2*alto/espacio_focos + 4;

        coordenada = new Coordenada[total_focos];

        int foco_actual = 0;

        int x;
        int y;
        int fin;

        //------------------------------------------------------------
        // arriba
        //------------------------------------------------------------
        y = (espacio_focos-tamano_focos)/2;
        fin = ancho + espacio_focos;
        for (x = (espacio_focos - tamano_focos) / 2; x < fin; x += espacio_focos)
          coordenada[foco_actual++] = new Coordenada(x, y);

        //------------------------------------------------------------
        // derecha
        //------------------------------------------------------------
        x = ancho + espacio_focos + (espacio_focos-tamano_focos)/2;
        fin = alto + espacio_focos;
        for (y = (espacio_focos - tamano_focos) / 2; y < fin; y += espacio_focos)
          coordenada[foco_actual++] = new Coordenada(x, y);

        //------------------------------------------------------------
        // abajo
        //------------------------------------------------------------
        y = alto + espacio_focos + (espacio_focos-tamano_focos)/2;
        fin = espacio_focos;
        for (x = ancho + espacio_focos + (espacio_focos - tamano_focos)/2;
             x > fin; x -= espacio_focos)
          {
            coordenada[foco_actual++] = new Coordenada(x, y);
          }

        //------------------------------------------------------------
        // izquierda
        //------------------------------------------------------------
        x = (espacio_focos - tamano_focos) / 2;
        fin = (espacio_focos - tamano_focos) / 2;
        for (y = alto + espacio_focos + (espacio_focos - tamano_focos)/2;
             y > fin; y -= espacio_focos)
          {
            coordenada[foco_actual++] = new Coordenada(x, y);
          }
      }

    //----------------------------------------------------------------

    void dibuja_focos()
      {
        int contador_focos = 0;

        g.setColor(Color.red);
        for (int i = 0; i < total_focos; i++)
          {
            if (++contador_focos == 5)
              contador_focos = 0;

            if (contador_focos == foco_apag)
              {
                g.setColor(Color.lightGray);
                g.fillOval(coordenada[i].x, coordenada[i].y, tamano_focos, tamano_focos);
                g.setColor(Color.red);
                g.drawOval(coordenada[i].x, coordenada[i].y, tamano_focos, tamano_focos);
              }
            else
              g.fillOval(coordenada[i].x, coordenada[i].y, tamano_focos, tamano_focos);
          }

        if (++foco_apag == 5)
          foco_apag = 0;
      }

    //----------------------------------------------------------------

    public void run ()
      {
        repaint();

        int caracter_rojo = 0;
        AudioClip clip = getAudioClip(getCodeBase(), "vegas.au");
        clip.loop();

        try
          {
            while (true)
              {
                int x = string_x;

                g.setColor(Color.black);
                g.fillRect(x, alto+10+espacio_focos*2, ancho_string, alto_string);

                for (int i = 0; i < longitud_mensaje; i++)
                  {
                    if (i == caracter_rojo)
                      g.setColor(Color.red);
                    else
                      g.setColor(Color.white);

                    g.drawChars(arreglo_char, i, 1, x, string_y);
                    x += font_metrics.charWidth(arreglo_char[i]);
                  }

                if (++caracter_rojo == longitud_mensaje)
                  caracter_rojo = 0;
                else
                  while (arreglo_char[caracter_rojo] == ' ')
                    caracter_rojo++;

                dibuja_focos();
                demora();
              }
          }
        finally
          {
          }
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h)
      {
      if (infoflags == ALLBITS)
        {
          imagen_cargada = true;
          repaint();

          corre_hilo();

          return false;
        }
      else
        return true;
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        if (!imagen_cargada)
          showStatus("Neon:  cargando imagen");

        else
          {
            showStatus("Neon:  terminado");
            g.drawImage(fondo, espacio_focos, espacio_focos, ancho, alto, null);
          }
      }
  }
