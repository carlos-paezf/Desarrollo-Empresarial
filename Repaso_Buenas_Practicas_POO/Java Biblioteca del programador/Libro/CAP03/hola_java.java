//********************************************************************
// hola_java.java
//********************************************************************

import java.applet.*;
import java.awt.Graphics;

//********************************************************************

public class hola_java extends Applet
  {
    public void paint(Graphics g)
      {
        algo a = new algo(105);

        g.drawString("Hola, Java! Valor " + a, 20, 20);
      }
  }

//********************************************************************

class algo
  {
    private int valor;

    //----------------------------------------------------------------

    public algo(int valor)
      {
        this.valor = valor;
      }

    //----------------------------------------------------------------

    public String toString()
      {
        String s;
        s = "<" + valor + ">";
        return s;
      }
  }

