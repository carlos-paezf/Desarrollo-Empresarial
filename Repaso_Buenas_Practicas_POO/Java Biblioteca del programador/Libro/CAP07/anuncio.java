//********************************************************************
// anuncio.java
//********************************************************************

import java.applet.*;
import java.awt.Graphics;
import java.awt.Font;

//********************************************************************

public class anuncio extends Applet implements Runnable
  {
    int ancho = 0;
    int alto = 0;
    int longitud = 0;
    int demora = 0;

    int tamano = 10;
    String mensaje = "¡Hola, Java!";
    String nombre_fuente = "TimesRoman";

    char arreglo_char[];
    int offset_x[];
    int offset_y[];

    //----------------------------------------------------------------

    public void init()
      {
        String parametro;

        parametro = getParameter("MENSAJE");
        if (parametro != null)
          mensaje = parametro;

        parametro = getParameter("FUENTE");
        if (parametro != null)
          nombre_fuente = parametro;

        parametro = getParameter("TAMANO");
        if (parametro != null)
          tamano = Integer.parseInt(parametro);

        parametro = getParameter("DEMORA");
        if (parametro != null)
          demora = Integer.parseInt(parametro);

        longitud = mensaje.length();
        arreglo_char = new char[longitud];
        mensaje.getChars(0, longitud, arreglo_char, 0);
        offset_x = new int[longitud];
        offset_y = new int[longitud];
      }

    //----------------------------------------------------------------

    public void start()
      {
        (new Thread(this)).start();
      }

    //----------------------------------------------------------------

    void por_omision()
      {
        ancho = size().width;
        alto = size().height;
      }

    //----------------------------------------------------------------

    public void redibujar()
      {
        repaint();

        try
          {
            Thread.sleep(demora);
          }
        catch (InterruptedException e)
          {
          }
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (true)
          {
            //--------------------------------------------------------
            // Subir por el costado izquierdo.
            //--------------------------------------------------------
            for (int y1 = alto - tamano; y1 > tamano;
                y1 -= tamano)
              {
                int j = -1;

                for (int i = 0; i < longitud; i++)
                  {
                    if (y1 + i * tamano > alto - tamano)
                      {
                        if (j < 0)
                          j = i;

                        offset_y[i] = alto - tamano;
                        offset_x[i] = (i - j + 2) * tamano;
                      }
                    else
                      {
                        offset_y[i] = y1 + i * tamano;
                        offset_x[i] = tamano;
                      }
                  }

                redibujar();
              }

            //--------------------------------------------------------
            // Ir a la derecha en la parte superior.
            //--------------------------------------------------------
            for (int x1 = tamano; x1 < ancho; x1 += tamano)
              {
                int j = -1;

                for (int i = 0; i < longitud; i++)
                  {
                    if (x1 - i * tamano < tamano * 2)
                      {
                        if (j < 0)
                          j = i;

                        offset_y[i] = (i - j + 1) * tamano;
                        offset_x[i] = tamano;
                      }
                    else
                      {
                        offset_y[i] = tamano;
                        offset_x[i] = x1 - i * tamano;
                      }
                  }

                redibujar();
              }

            //--------------------------------------------------------
            // Descender por el costado derecho.
            //--------------------------------------------------------
            for (int y1 = tamano * 2; y1 < alto - tamano;
                 y1 += tamano)
              {
                int j = -1;

                for (int i = 0; i < longitud; i++)
                  {
                    if (y1 - i * tamano < tamano)
                      {
                        if (j < 0)
                          j = i;

                        offset_y[i] = tamano;
                        offset_x[i] = ancho - (i - j + 2) * tamano;
                      }
                    else
                      {
                        offset_y[i] = y1 - i * tamano;
                        offset_x[i] = ancho - tamano;
                      }
                  }

                redibujar();
              }

            //--------------------------------------------------------
            // Ir a la izquierda en la parte inferior.
            //--------------------------------------------------------
            for (int x1 = ancho - tamano; x1 > tamano;
                 x1 -= tamano)
              {
                int j = -1;

                for (int i = 0; i < longitud; i++)
                  {
                    if (x1 + i * tamano > ancho - tamano * 2)
                      {
                        if (j < 0)
                          j = i;

                        offset_y[i] = alto - (i - j + 1) * tamano;
                        offset_x[i] = ancho - tamano;
                      }
                    else
                      {
                        offset_y[i] = alto - tamano;
                        offset_x[i] = x1 + i * tamano;
                      }
                  }

                redibujar();
              }
          }
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        por_omision();
        Font fuente = new Font(nombre_fuente, Font.PLAIN, tamano);
        g.setFont(fuente);

        for (int i = 0; i < mensaje.length(); i++)
          g.drawChars(arreglo_char, i, 1, offset_x[i], offset_y[i]);
      }
  }
