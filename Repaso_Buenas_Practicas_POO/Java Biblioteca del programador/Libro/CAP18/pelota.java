//*******************************************************************
// pelota.java
//*******************************************************************

import java.applet.*;
import java.awt.*;

//*******************************************************************

public class pelota extends Applet implements Runnable
  {
    Graphics g;

    FontMetrics font_metrics;
    AudioClip clip;

    int ancho_string;
    int alto_string;

    String mensaje = "MCGRAW-HILL";
    int tamano_fuente = 20;

    int longitud_mensaje;
    char arreglo_char [];

    int tamano_pelota = 10;
    int demora_pelota = 300;
    int demora_letra = 500;

    double decremento;

    int x_pelota = 0;
    int y_pelota = 0;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        String parametro;

        parametro = getParameter("MENSAJE");
        if (parametro != null)
          mensaje = parametro;

        parametro = getParameter("TAMANO_FUENTE");
        if (parametro != null)
          tamano_fuente = Integer.parseInt(parametro);

        parametro = getParameter("TAMANO_PELOTA");
        if (parametro != null)
          tamano_pelota = Integer.parseInt(parametro);

        parametro = getParameter("DEMORA_PELOTA");
        if (parametro != null)
          demora_pelota = Integer.parseInt(parametro);

        parametro = getParameter("DEMORA_LETRA");
        if (parametro != null)
          demora_letra = Integer.parseInt(parametro);

        int divisiones = 10;
        parametro = getParameter("DIVISIONES");
        if (parametro != null)
          divisiones = Integer.parseInt(parametro);

        decremento = Math.PI / divisiones;

        demora_pelota /= divisiones;

        longitud_mensaje = mensaje.length();
        arreglo_char = new char[longitud_mensaje];
        mensaje.getChars(0, longitud_mensaje, arreglo_char, 0);

        Font fuente = new Font("TimesRoman", Font.BOLD, tamano_fuente);
        g.setFont(fuente);

        font_metrics = g.getFontMetrics();

        ancho_string = font_metrics.stringWidth(mensaje);
        alto_string = font_metrics.getHeight();

        clip = getAudioClip(getCodeBase(), "sonido.au");

        resize(ancho_string, alto_string*2);
      }

    //----------------------------------------------------------------

    public void start()
      {
        (new Thread(this)).start();
      }

    //----------------------------------------------------------------

    void demora_para_pelota ()
      {
        try
          {
            Thread.sleep(demora_pelota);
          }
        catch (InterruptedException e)
          {
          }
      }

    //----------------------------------------------------------------

    void demora_entre_letras()
      {
        try
          {
            Thread.sleep(demora_letra);
          }
        catch (InterruptedException e)
          {
          }
      }

    //----------------------------------------------------------------

    void dibujar_pelota(int x_anterior, int x, int ancho_anterior, int ancho_char)
      {
        if (x < x_anterior)
          return;

        int x1 = x_anterior + ancho_anterior /2;
        int x2 = x + ancho_char/2;
        int x_media = (x1 + x2) / 2;
        int y_media = alto_string;
        int radio = x_media - x1;
        int y1;

        x_media -= tamano_pelota / 2;

        for (double r = Math.PI; r >= 0; r -= decremento)
          {
            g.setColor(Color.lightGray);
            g.fillArc(x_pelota, y_pelota, tamano_pelota, tamano_pelota, 0, 360);

            x1 = x_media + (int) (Math.cos(r) * radio);
            y1 = y_media - (int) (Math.sin(r) * radio);

            g.setColor(Color.red);
            g.fillArc(x1, y1, tamano_pelota, tamano_pelota, 0, 360);

            x_pelota = x1;
            y_pelota = y1;

            demora_para_pelota();
          }

        clip.play();
      }

    //---------------------------------------------------------------

    void escribir_mensaje()
      {
        g.setColor(Color.black);
        g.drawString(mensaje, 0, alto_string+tamano_fuente);
      }

    //----------------------------------------------------------------

    void redibujar_caracter(int x, int caracter_actual,
                          int ancho_anterior, int ancho_char)
      {
        g.setColor(Color.red);
        g.drawChars(arreglo_char, caracter_actual, 1, x,
                    alto_string+tamano_fuente);

        g.setColor(Color.lightGray);
        g.fillRect(0, 0, ancho_anterior, tamano_fuente);

        g.copyArea(x, alto_string, ancho_char, tamano_fuente,
                   -x, -alto_string);
      }

    //----------------------------------------------------------------

    public void run()
      {
        int caracter_actual = 0;
        int x = 0;
        int x_anterior = 0;
        int ancho_char = font_metrics.charWidth(arreglo_char[caracter_actual]);

        while (true)
          {
            escribir_mensaje();

            g.setColor(Color.red);

            int ancho_anterior = ancho_char;
            ancho_char = font_metrics.charWidth(arreglo_char[caracter_actual]);

            dibujar_pelota(x_anterior, x, ancho_anterior, ancho_char);
            redibujar_caracter(x, caracter_actual,
                             ancho_anterior, ancho_char);
            x_anterior = x;

            x += ancho_char;

            if (++caracter_actual == longitud_mensaje)
              {
                caracter_actual = 0;
                x = 0;
              }

            demora_entre_letras();
          }
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        showStatus("Pelota rebotando:  corriendo");
        escribir_mensaje();
      }
  }
