//********************************************************************
// pelota.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class pelota extends Applet implements Runnable
  {
    Graphics g;

    FontMetrics font_metrics;

    int ancho_cadena;
    int alto_cadena;

    String mensaje = "MCGRAW-HILL";
    int tamano_fuente = 20;

    int longitud_mensaje;
    char arreglo_char[];

    int tamano_pelota = 10;
    int valor_demora = 500;

    double decremento;

    int ball_x = 0;
    int ball_y = 0;

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

        parametro = getParameter("DEMORA");
        if (parametro != null)
          valor_demora = Integer.parseInt(parametro);

        int divisiones = 10;
        parametro = getParameter("DIVISIONES");
        if (parametro != null)
          divisiones = Integer.parseInt(parametro);

        decremento = Math.PI / divisiones;

        valor_demora /= divisiones;

        longitud_mensaje = mensaje.length();
        arreglo_char = new char[longitud_mensaje];
        mensaje.getChars(0, longitud_mensaje, arreglo_char, 0);

        Font fuente = new Font("TimesRoman", Font.BOLD, tamano_fuente);
        g.setFont(fuente);

        font_metrics = g.getFontMetrics();

        ancho_cadena = font_metrics.stringWidth(mensaje);
        alto_cadena = font_metrics.getHeight();

        resize(ancho_cadena, alto_cadena*2);
      }

    //----------------------------------------------------------------

    public void start()
      {
        (new Thread(this)).start();
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

    void dibujar_pelota(int x_anterior, int x, int ancho_anterior, int ancho_char)
      {
        if (x < x_anterior)
          return;

        int x1 = x_anterior + ancho_anterior /2;
        int x2 = x + ancho_char/2;
        int x_media = (x1 + x2) / 2;
        int y_media = alto_cadena;
        int radio = x_media - x1;
        int y1 = 0;

        for (double r = Math.PI; r >= 0; r -= decremento)
          {
            g.setColor(Color.lightGray);
            g.fillArc(ball_x, ball_y, tamano_pelota, tamano_pelota, 0, 360);

            x1 = x_media + (int) (Math.cos(r) * radio);
            y1 = y_media - (int) (Math.sin(r) * radio);

            g.setColor(Color.red);
            g.fillArc(x1, y1, tamano_pelota, tamano_pelota, 0, 360);

            ball_x = x1;
            ball_y = y1;

            demora();
          }
      }

    //----------------------------------------------------------------

    public void run()
      {
        int caracter_actual = 0;
        int x = 0;
        int x_anterior = 0;
        int ancho_char = font_metrics.charWidth(arreglo_char[0]);

        while (true)
          {
            g.setColor(Color.red);

            int ancho_anterior = ancho_char;
            ancho_char = font_metrics.charWidth(arreglo_char[caracter_actual]);

            dibujar_pelota(x_anterior, x, ancho_anterior, ancho_char);
            x_anterior = x;

            x += ancho_char;

            if (++caracter_actual == longitud_mensaje)
              {
                caracter_actual = 0;
                x = 0;
              }

            demora();
          }
      }

    //----------------------------------------------------------------

    public void paint(Graphics gc)
      {
        showStatus("Pelota rebotando:  corriendo");
        g.setColor(Color.black);
        g.drawString(mensaje, 0, alto_cadena+tamano_fuente);
      }
  }
