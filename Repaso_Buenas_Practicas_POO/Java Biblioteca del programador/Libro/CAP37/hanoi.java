//********************************************************************
// hanoi.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class hanoi extends Applet implements Runnable
  {
    Graphics g;

    static public Image fondo;

    static public int ancho;
    static public int alto;

    static public int demora = 500;

    private boolean imagen_cargada = false;

    private int total_discos = 5;

    private Thread mi_hilo;

    private boolean hilo_iniciado = false;

    torre t[];

    int torre_origen = 0;
    int torre_destino   = 2;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        String parametro;

        parametro = getParameter("TOTAL");
        if (parametro != null)
          total_discos = Integer.parseInt(parametro);

        parametro = getParameter("DEMORA");
        if (parametro != null)
          demora = Integer.parseInt(parametro);

        ancho  = size().width;
        alto = size().height;

        fondo = getImage(getCodeBase(), "egipto.gif");

        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, this);

        t = new torre[3];

        t[0] = new torre(g);
        t[1] = new torre(g);
        t[2] = new torre(g);

        for (int i = 0; i < total_discos; i++)
          t[0].agregar(i);
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int ancho, int alto)
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

    public void paint(Graphics g)
      {
        if (!imagen_cargada)
          showStatus("Torre de Hanoi:  cargando imagen");

        else
          {
            if (hilo_iniciado)
              if (mi_hilo.isAlive())
                showStatus("Torres de Hanoi:  Corriendo");
              else
                showStatus("Torres de Hanoi:  Haga clic otra vez para reiniciar");
            else
              showStatus("Torres de Hanoi:  Haga clic para iniciar");

            ancho  = size().width;
            alto = size().height;

            g.drawImage(fondo, 0, 0, ancho, alto, this);

            int x_inc = ancho / 10;

            t[0].paint(x_inc*1, x_inc*2, alto);
            t[1].paint(x_inc*4, x_inc*2, alto);
            t[2].paint(x_inc*7, x_inc*2, alto);
          }
      }

    //----------------------------------------------------------------

    public boolean mouseDown(Event evt, int x, int y)
      {
        if (!hilo_iniciado || !mi_hilo.isAlive())
          {
            mi_hilo = new Thread(this);
            mi_hilo.start();
            showStatus("Torre de Hanoi:  Corriendo");
            hilo_iniciado = true;
          }

        return true;
      }

    //----------------------------------------------------------------

    public void run()
      {
        mover_torre(total_discos, torre_origen, torre_destino, 1);

        int temp = torre_destino;
        torre_destino = torre_origen;
        torre_origen = temp;

        showStatus("Torre de Hanoi:  Haga clic otra vez para reiniciar");
      }

    //----------------------------------------------------------------

    private void mover_torre(int discos, int origen, int destino, int temporal)
      {
        if (discos > 0)
          {
            mover_torre(discos-1, origen, temporal, destino);
            mover_disco(origen, destino);
            mover_torre(discos-1, temporal, destino, origen);
          }
      }

    //----------------------------------------------------------------

    private void mover_disco(int origen, int destino)
      {
        int disco = t[origen].pop();
        t[destino].push(disco);
      }
  }
