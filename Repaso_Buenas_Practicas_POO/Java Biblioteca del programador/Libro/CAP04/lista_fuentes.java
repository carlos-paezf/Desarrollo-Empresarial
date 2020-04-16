//********************************************************************
// lista_fuentes.java
//********************************************************************

import java.applet.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

//********************************************************************

public class lista_fuentes extends Applet
  {
    final int tam_fuente = 30;

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        String lista_fuentes[] = toolkit.getFontList();

        System.out.println("");

        for (int i = 0; i < lista_fuentes.length; i++)
          {
            System.out.println(i + " " + lista_fuentes[i]);

            Font fuente = new Font(lista_fuentes[i], i%4, tam_fuente);
              // El segundo parámetro selecciona uno de los cuatro estilos disponibles:
              //    PLAIN, BOLD, ITALIC, BOLD+ITALIC  - los cuales representan,
              //    NORMAL, NEGRITAS, CURSIVA, CURSIVA+NEGRITAS respectivamente

            g.setFont(fuente);
            g.setColor(Color.blue);
            g.drawString(lista_fuentes[i], 20, tam_fuente*(i+1));
          }
      }
  }
