//*******************************************************************
// audio.java
//*******************************************************************

import java.applet.*;
import java.awt.*;

//*******************************************************************

public class audio extends Applet implements Runnable
  {
    Graphics g;

    Image fondo;
    int ancho;
    int alto;
    boolean imagen_cargada = false;

    int valor_demora = 500;
    Thread mi_hilo = null;
    String mensaje = "Las Vegas";
    boolean hilo_corriendo = false;

    int longitud_mensaje;
    char arreglo_char[];

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        fondo = getImage(getCodeBase(), "bar_de_joe.gif");

        String parametro;

        parametro = getParameter("MENSAJE");
        if (parametro != null)
          mensaje = parametro;

        parametro = getParameter("DEMORA");
        if (parametro != null)
          valor_demora = Integer.parseInt(parametro);

        ancho = size().width;
        alto = size().height;

        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, ancho, alto, this);

        longitud_mensaje = mensaje.length();
        arreglo_char = new char[longitud_mensaje];
        mensaje.getChars(0, longitud_mensaje, arreglo_char, 0);
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

    void demora()
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

    public void run()
      {
        repaint();

        int caracter_rojo = 0;
        AudioClip clip = getAudioClip(getCodeBase(), "bar.au");
        clip.loop();

        Font fuente = new Font("TimesRoman", Font.BOLD, 20);
        g.setFont(fuente);

        FontMetrics font_metrics = g.getFontMetrics();

        int ancho_string = font_metrics.stringWidth(mensaje);
        int alto_string = font_metrics.getHeight();

        while (true)
          {
            int x = (ancho - ancho_string) / 2;

            g.setColor(Color.black);
            g.fillRect(x, alto+10, ancho_string, alto_string);

            for (int i = 0; i < longitud_mensaje; i++)
              {
                if (i == caracter_rojo)
                  g.setColor(Color.red);
                else
                  g.setColor(Color.white);

                g.drawChars(arreglo_char, i, 1, x, alto+30);
                x += font_metrics.charWidth(arreglo_char[i]);
              }

            if (++caracter_rojo == longitud_mensaje)
              caracter_rojo = 0;
            else
              while (arreglo_char[caracter_rojo] == ' ')
                caracter_rojo++;

            demora();
          }
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h)
      {
        if (infoflags == ALLBITS)
          {
            resize(ancho, alto+40);

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
          {
            showStatus("Bar de Joe:  cargando imagen");
          }
        else
          {
            showStatus("Bar de Joe:  terminado");
            g.drawImage(fondo, 0, 0, ancho, alto, null);
          }
      }
  }
