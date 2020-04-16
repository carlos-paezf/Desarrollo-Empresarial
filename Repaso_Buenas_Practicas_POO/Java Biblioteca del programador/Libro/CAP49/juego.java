//********************************************************************
// juego.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

public class juego extends Applet implements Runnable
  {
    Graphics g;
    cartas baraja;

    Thread mi_hilo;

    int ancho;
    int alto;

    boolean carta_desplegada[];
    int palos[];

    int contador_cartas;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        baraja = new cartas(g);

        carta_desplegada = new boolean[54];
        palos = new int[5];    // el 5to es para contar los comodines
        borrar_cartas();

        baraja.comodin = getImage(getCodeBase(), "comodin.gif");

        por_omision();

        baraja.dibujar_comodin(0, 0, ancho/4, alto/2);
      }

    //----------------------------------------------------------------

    void borrar_cartas()
      {
        int i;

        for (i = 0; i < 54; i++)
          carta_desplegada[i] = false;

        for (i = 0; i < 5; i++)
          palos[i] = 0;

        contador_cartas = 0;
      }

    //----------------------------------------------------------------

    void por_omision()
      {
        ancho = size().width;
        alto = size().height;
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

    public void stop()
      {
        if (mi_hilo != null)
          {
            mi_hilo.stop();
            mi_hilo = null;
          }
      }

    //----------------------------------------------------------------

    void demora(int cantidad)
      {
        try
          {
            Thread.sleep(cantidad);
          }
        catch (InterruptedException e)
          {
          }
      }

    //----------------------------------------------------------------

    public void run()
      {
        while (mi_hilo != null)
          {
            repaint();
            demora(1000);
          }
      }

    //----------------------------------------------------------------

    public void update(Graphics _g)
      {
        g.setColor(Color.black);
        g.fillRect(0, 0, ancho, alto);

        int carta = (int) (Math.random() * 54);

        do
          {
            if (!carta_desplegada[carta])
              {
                carta_desplegada[carta] = true;
                break;
              }
            else if (++carta == 54)
              carta = 0;

          } while (true);

        baraja.dibujar(carta, 0, 0, ancho/2, alto);

        g.setColor(Color.black);
        g.fillRect(ancho/2, 0, ancho, alto);
        g.setColor(Color.green);

        int palo = carta / 13;
        ++palos[palo];

        g.drawString("" + palos[0] + " Espadas",
                     ancho/2, (alto*1)/5);

        g.drawString("" + palos[1] + " Treboles",
                     ancho/2, (alto*2)/5);

        g.drawString("" + palos[2] + " Corazones",
                     ancho/2, (alto*3)/5);

        g.drawString("" + palos[3] + " Diamantes",
                     ancho/2, (alto*4)/5);

        g.drawString("" + palos[4] + " Comodines",
                     ancho/2, (alto*5)/5);

        contador_cartas++;
        showStatus("Cartas repartidas: " + contador_cartas);

        if (contador_cartas == 54)
          {
            demora(2000);
            g.setColor(Color.black);
            g.fillRect(0, 0, ancho, alto);
            g.setColor(Color.green);
            g.drawString("Restablecer", 0, alto/2);
            borrar_cartas();
            demora(2000);
          }
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        por_omision();
      }
  }
