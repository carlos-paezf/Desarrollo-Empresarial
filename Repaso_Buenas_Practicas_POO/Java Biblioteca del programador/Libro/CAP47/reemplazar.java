//********************************************************************
// reemplazar.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class reemplazar extends Applet implements Runnable
  {
    Graphics g;

    Image imagen_1;
    Image imagen_2;

    int ancho;
    int alto;

    boolean imagen_cargada = false;

    int valor_demora = 0;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        ancho = size().width;
        alto = size().height;

        String parametro = getParameter("VALOR_DEMORA");
        if (parametro != null)
          valor_demora = Integer.parseInt(parametro);

        imagen_1 = getImage(getCodeBase(), "imagen1.gif");
        imagen_2 = getImage(getCodeBase(), "imagen2.gif");

        Image imagen_fueraPant = createImage(300, 400);
        Graphics CG_fueraPant = imagen_fueraPant.getGraphics();
        CG_fueraPant.drawImage(imagen_1, 0, 0, this);
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
            ;
	  }
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (!imagen_cargada)
          ;

        while (true)
          {
            for (int x = 0; x < ancho; x++)
              {
                Graphics g2 = g.create();
                g2.clipRect(x, 0, 1, alto);
                g2.drawImage(imagen_2, 0, 0, ancho, alto, this);
                demora();
              }

            for (int x = ancho-1; x >= 0; x--)
              {
                Graphics g2 = g.create();
                g2.clipRect(x, 0, 1, alto);
                g2.drawImage(imagen_1, 0, 0, ancho, alto, this);
                demora();
              }
          }
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int w, int h)
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

    public void paint(Graphics _g)
      {
        if (!imagen_cargada)
          showStatus("Reemplazar:  cargando imagen");

        else
          {
            ancho = size().width;
            alto = size().height;
            showStatus("Reemplazar:  iniciado");
            g.drawImage(imagen_1, 0, 0, ancho, alto, this);
          }
      }
  }
