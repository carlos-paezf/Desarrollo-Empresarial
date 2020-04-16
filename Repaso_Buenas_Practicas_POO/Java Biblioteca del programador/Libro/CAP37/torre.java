//********************************************************************
// torre.java
//********************************************************************

import java.awt.*;
import java.util.Vector;

//********************************************************************

public class torre
  {
    Graphics g;

    Vector v;

    int x;
    int y;
    int w;
    int d;

    //----------------------------------------------------------------

    torre(Graphics g)
      {
        this.g = g;

        v = new Vector();
      }

    //----------------------------------------------------------------

    public void agregar(int i)
      {
        Integer I = new Integer(i);

        v.addElement(I);
      }

    //----------------------------------------------------------------

    public int pop()
      {
        int disco = ((Integer) (v.lastElement())).intValue();
        int tamano = v.size();

        subir_disco(tamano, disco);
        v.removeElementAt(tamano - 1);

        return(disco);
      }

    //----------------------------------------------------------------

    public void push(int i)
      {
        Integer I = new Integer(i);

        v.addElement(I);

        int tamano = v.size();

        bajar_disco(tamano, i);
      }

    //----------------------------------------------------------------

    public void paint(int _x, int _w, int h)
      {
        x = _x;
        w = _w;
        y = h / 20;
        d = w / 20;

        dibujar_sujetadores(g);

        int numero_de_discos = v.size();

        for (int i = 0; i < numero_de_discos; i++)
          {
            int j = ((Integer) (v.elementAt(i))).intValue();
            dibujar_disco(x+j*d, y*(18-i), w-2*j*d, y);
          }
      }

    //----------------------------------------------------------------

    void dibujar_sujetadores(Graphics g)
      {
        g.setColor(Color.green);
        g.fillRect(x, y*19, w, y/5);
        g.fillRect(x+w/2-w/20, y*2, w/10, y*17);
        g.setColor(Color.black);
      }

    //----------------------------------------------------------------

    void demora()
      {
        try
          {
            Thread.sleep(hanoi.demora);
          }
        catch (InterruptedException e)
          {
          }
      }

    //----------------------------------------------------------------

    void subir_disco(int tamano, int disco)
      {
        borrar_disco(x+disco*d, y*(19-tamano), w-2*disco*d, y);

        for (int i = y*(18-tamano); i >= y; i-=y)
          {
            dibujar_disco(x+disco*d, i, w-2*disco*d, y);
            demora();
            borrar_disco(x+disco*d, i, w-2*disco*d, y);
          }
      }

    //----------------------------------------------------------------

    void bajar_disco(int tamano, int disco)
      {
        for (int i = y; i < y*(19-tamano); i+=y)
          {
            dibujar_disco(x+disco*d, i, w-2*disco*d, y);
            demora();
            borrar_disco(x+disco*d, i, w-2*disco*d, y);
          }

        dibujar_disco(x+disco*d, y*(19-tamano), w-2*disco*d, y);
      }

    //----------------------------------------------------------------

    void dibujar_disco(int x, int y, int w, int h)
      {
        g.setColor(Color.black);
        g.fillRect(x, y, w-1, h-1);

        g.setColor(Color.white);
        g.drawRect(x, y, w-1, h-1);

        g.setColor(Color.black);
      }

    //----------------------------------------------------------------

    void borrar_disco(int x, int y, int w, int h)
      {
        Graphics g2;
        g2 = g.create();

        g2.clipRect(x, y, w, h);

        g2.drawImage(hanoi.fondo,
                     0, 0,
                     hanoi.ancho, hanoi.alto,
                     null);

        dibujar_sujetadores(g2);
      }
  }
