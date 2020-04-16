//********************************************************************
// flores.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class flores extends Applet implements Runnable
  {
    Graphics g;

    Image fondo;
    boolean imagen_cargada = false;

    int ancho;
    int alto;
    int valor_demora = 100;

    int flor_ancho = 10;
    int flor_alto = 20;
    int numero_de_flores = 20;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        String parametro;

        parametro = getParameter("DEMORA");
        if (parametro != null)
          valor_demora = Integer.parseInt(parametro);

        parametro = getParameter("FLORES");
        if (parametro != null)
          numero_de_flores = Integer.parseInt(parametro);

        por_omision();

        fondo = getImage(getCodeBase(), "flores.gif");
        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, ancho, alto, this);
      }

    //----------------------------------------------------------------

    void por_omision()
      {
        ancho = size().width;
        alto = size().height;
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int ancho, int alto)
      {
        if (infoflags == ALLBITS)
          {
            if (!imagen_cargada)
              {
                imagen_cargada = true;
                showStatus("");
                repaint();
                (new Thread(this)).start();
              }
            return false;
          }
        else
          return true;
      }

    //----------------------------------------------------------------

    public void run()
      {
        int contador = 0;

        repaint();
        while (true)
          {
          agregar_flor();

          if (++contador == numero_de_flores)
            {
              contador = 0;
              dibujar_nombre_tienda();
              demora(valor_demora * 5);
              repaint();
            }
          else
            demora(valor_demora);
          }
      }

    //----------------------------------------------------------------

    void dibujar_flor(Graphics g)
      {
        g.setColor(Color.red);
        g.drawArc(0, 0, flor_ancho*2, flor_alto*2, 270, 135);
        g.setColor(Color.yellow);
        g.fillOval(flor_ancho, 0, flor_ancho, flor_alto);
      }

    //----------------------------------------------------------------

    void agregar_flor()
      {
        Graphics g2 = g.create();
        g2.translate((int) (Math.random() * ancho),
                     (int) (Math.random() * alto));
        dibujar_flor(g2);
      }

    //----------------------------------------------------------------

    void dibujar_nombre_tienda()
      {
        g.setColor(Color.green);
        g.fillRect(ancho/4, alto/4, ancho/2, alto/2);
        g.setColor(Color.red);
        g.drawRect(ancho/4, alto/4, ancho/2, alto/2);

        Font fuente = new Font("TimesRoman", Font.BOLD, alto/10);
        g.setFont(fuente);
        g.setColor(Color.black);
        g.drawString("Tienda de", (ancho*3)/8, (alto*3)/8);
        g.drawString("Flores" , (ancho*3)/8,  alto   /2);
        g.drawString("de Betty"   , (ancho*3)/8, (alto*5)/8);
      }

    //----------------------------------------------------------------

    void demora(int valor_demora)
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

    public void paint(Graphics g)
      {
        if (!imagen_cargada)
          showStatus("Flores:  cargando imagen");

        else
          {
            por_omision();
            g.drawImage(fondo, 0, 0, ancho, alto, this);
          }
      }
  }
