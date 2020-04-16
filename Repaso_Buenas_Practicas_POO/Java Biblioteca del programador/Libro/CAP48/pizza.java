//********************************************************************
// pizza.java
//********************************************************************

import java.applet.*;
import java.awt.*;

//********************************************************************

class Coordenada
  {
    public int x;
    public int y;

    public Coordenada(int x, int y)
      {
        this.x = x;
        this.y = y;
      }
  }

//********************************************************************

public class pizza extends Applet
  {
    static final int TOTAL_INGREDIENTES = 7;
    static final int ORDENAR            = TOTAL_INGREDIENTES;
    static final int PRECIO             = ORDENAR + 1;
    static final int BORRAR             = PRECIO + 1;
    static final int TOTAL_ELEMENTOS    = BORRAR + 1;

    Graphics g;
    Image fondo;
    Image  ingrediente_gif[];

    boolean imagen_cargada = false;

    Coordenada coordenada[];
    String nombre_ingrediente[];

    FontMetrics font_metrics;

    int ingrediente_actual = 0;

    int nueva_x;
    int nueva_y;
    int ancho;
    int alto;

    int tamano_de_ingredientes =  50;
    int tamano_fuente =  20;
    int posicion_boton = 350;
    int tamano_de_pizza = 300;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        Font fuente = new Font("TimesRoman", Font.BOLD, tamano_fuente);
        g.setFont(fuente);
        font_metrics = g.getFontMetrics();

        ancho = size().width;
        alto = size().height;

        fondo = getImage(getCodeBase(), "pizza.gif");

        ingrediente_gif = new Image[TOTAL_INGREDIENTES];
        for (int i = 0; i < TOTAL_INGREDIENTES; i++)
          ingrediente_gif[i] = getImage(getCodeBase(), "t" + (i+1) + ".gif");

        traer_coordenadas();
        traer_nombre_ingredientes();

        Image imagenFueraPant = createImage(ancho, alto);
        Graphics CGFueraPant = imagenFueraPant.getGraphics();
        CGFueraPant.drawImage(fondo, 0, 0, this);

        for (int i = 0; i < TOTAL_INGREDIENTES; i++)
          {
            showStatus("Pizza: cargando imagen " + i);
            while (!imagen_cargada)
              ;
            imagen_cargada = false;
            CGFueraPant.drawImage( ingrediente_gif [i], 0, 0,
                  tamano_de_ingredientes, tamano_de_ingredientes, this);
          }

        showStatus("Pizza:  cargando la última imagen");
        while (!imagen_cargada)
          ;
        repaint();
      }

    //----------------------------------------------------------------

    void traer_coordenadas()
      {
        coordenada = new Coordenada[TOTAL_ELEMENTOS];

        for (int i = 0; i < TOTAL_INGREDIENTES; i++)
          coordenada[i] = new Coordenada(i * tamano_de_ingredientes, 0);

        coordenada[ORDENAR] = new Coordenada(posicion_boton, 100);
        coordenada[PRECIO] = new Coordenada(posicion_boton, 125);
        coordenada[BORRAR] = new Coordenada(posicion_boton, 150);
      }

    //----------------------------------------------------------------

    void traer_nombre_ingredientes()
      {
        nombre_ingrediente = new String[TOTAL_ELEMENTOS];

        nombre_ingrediente[0] = new String("Tocino        ");
        nombre_ingrediente[1] = new String("Queso         ");
        nombre_ingrediente[2] = new String("Pimiento verde");
        nombre_ingrediente[3] = new String("Aceitunas     ");
        nombre_ingrediente[4] = new String("Cebollas      ");
        nombre_ingrediente[5] = new String("Pepperoni     ");
        nombre_ingrediente[6] = new String("Jitomate      ");
      }

    //----------------------------------------------------------------

    void dibujar_elementos()
      {
        int fuente_alto = font_metrics.getHeight();

        g.setColor(Color.white);

        for (int i = 0; i < TOTAL_INGREDIENTES; i++)
          {
            g.drawImage( ingrediente_gif[i],
                        coordenada[i].x,
                        coordenada[i].y,
                        tamano_de_ingredientes,
                        tamano_de_ingredientes,
                        null);

            String number = "" + (i+1);
            g.drawString(number,
                         coordenada[i].x + (tamano_de_ingredientes - 
                            font_metrics.stringWidth(number))/2,
                         coordenada[i].y + font_metrics.getAscent() +
                            (tamano_de_ingredientes - fuente_alto)/2);
          }

        g.setColor(Color.cyan);

        g.fillRect(coordenada[ORDENAR].x, coordenada[ORDENAR].y,
                   100, tamano_fuente);
        g.fillRect(coordenada[PRECIO].x, coordenada[PRECIO].y,
                   100, tamano_fuente);
        g.fillRect(coordenada[BORRAR].x, coordenada[BORRAR].y,
                   100, tamano_fuente);

        g.setColor(Color.blue);

        g.drawString("Orden",
                     coordenada[ORDENAR].x,
                     coordenada[ORDENAR].y + tamano_fuente);

        g.drawString("Precio",
                     coordenada[PRECIO].x,
                     coordenada[PRECIO].y + tamano_fuente);

        g.drawString("Restablecer",
                     coordenada[BORRAR].x,
                     coordenada[BORRAR].y + tamano_fuente);

        g.setColor(Color.white);

        g.drawRect(coordenada[ORDENAR].x, coordenada[ORDENAR].y,
                   100, tamano_fuente);
        g.drawRect(coordenada[PRECIO].x, coordenada[PRECIO].y,
                   100, tamano_fuente);
        g.drawRect(coordenada[BORRAR].x, coordenada[BORRAR].y,
                   100, tamano_fuente);

        int x = posicion_boton;
        int y = 220;

        g.setColor(Color.black);
        for (int i = 0; i < TOTAL_INGREDIENTES; i++)
          {
            g.drawString ((i+1) + ". " + nombre_ingrediente[i], x, y);
            y += fuente_alto;
          }
      }

    //----------------------------------------------------------------

    void mover_ingrediente(int indice)
      {
        int x_anterior = coordenada[indice].x;
        int y_anterior = coordenada[indice].y;
        int dx    = nueva_x - x_anterior;
        int dy    = nueva_y - y_anterior;
        int fin   = Math.max(Math.abs(dx), Math.abs(dy));

        for (int i = 0; i < fin; i++)
          {
            int x = x_anterior + dx * i / fin;
            int y = y_anterior + dy * i / fin;

            Graphics g2;
            g2 = g.create();

            g2.clipRect(coordenada [indice].x, coordenada [indice].y,
                        tamano_de_ingredientes, tamano_de_ingredientes);

            g2.setColor(Color.white);
            g2.drawRect(0, tamano_de_ingredientes,
                        tamano_de_pizza, tamano_de_pizza);

            g2.drawImage(fondo, 0, tamano_de_ingredientes,
                         tamano_de_pizza, tamano_de_pizza, null);

            coordenada[indice].x = x;
            coordenada[indice].y = y;

            g.drawImage( ingrediente_gif[indice],
                        coordenada[indice].x,
                        coordenada[indice].y,
                        tamano_de_ingredientes,
                        tamano_de_ingredientes,
                        null);
          }
      }

    //----------------------------------------------------------------

    public boolean mouseDown(Event evt, int x, int y)
      {
        if (x > coordenada[ORDENAR].x       &&
            x < coordenada[ORDENAR].x + 100 &&
            y > coordenada[ORDENAR].y       &&
            y < coordenada[ORDENAR].y + tamano_fuente)
          {
            g.setColor(Color.black);
            g.fillRect(coordenada[ORDENAR].x, coordenada[ORDENAR].y,
                       100, tamano_fuente);

            g.setColor(Color.white);
            g.drawString("Orden",
                         coordenada [ORDENAR].x,
                         coordenada [ORDENAR].y + tamano_fuente);

            ordenar();
            return true;
          }

        if (x > coordenada[PRECIO].x       &&
            x < coordenada[PRECIO].x + 100 &&
            y > coordenada[PRECIO].y       &&
            y < coordenada[PRECIO].y + tamano_fuente)
          {
            g.setColor(Color.black);
            g.fillRect(coordenada[PRECIO].x, coordenada[PRECIO].y,
                       100, tamano_fuente);
            g.setColor(Color.white);
            g.drawString("Precio",
                         coordenada[PRECIO].x,
                         coordenada[PRECIO].y + tamano_fuente);
            precio();
            return true;
          }

        if (x > coordenada[BORRAR].x       &&
            x < coordenada[BORRAR].x + 100 &&
            y > coordenada[BORRAR].y       &&
            y < coordenada[BORRAR].y + tamano_fuente)
          {
            g.setColor(Color.black);
            g.fillRect(coordenada[BORRAR].x, coordenada[BORRAR].y,
                      100, tamano_fuente);

            g.setColor (Color.white);
            g.drawString("Restablecer",
                        coordenada[BORRAR].x,
                        coordenada[BORRAR].y + tamano_fuente);

            traer_coordenadas();
            return true;
          }

        for (int i = 0; i < TOTAL_INGREDIENTES; i++)
          {
            if (x > coordenada[i].x                    &&
                x < coordenada[i].x + tamano_de_ingredientes &&
                y > coordenada[i].y                    &&
                y < coordenada[i].y + tamano_de_ingredientes)
              {
                ingrediente_actual = i + 1;
              }
          }

        return true;
      }

    //----------------------------------------------------------------

    public boolean mouseDrag(Event evt, int x, int y)
      {
        if (ingrediente_actual != 0)
          {
            nueva_x = x - tamano_de_ingredientes/2;
            nueva_y = y - tamano_de_ingredientes/2;

            if (nueva_x < 0)
              nueva_x = 0;

            if (nueva_y < 0)
              nueva_y = 0;

            mover_ingrediente(ingrediente_actual - 1);
          }

        return true;
      }

    //----------------------------------------------------------------

    public boolean mouseUp(Event evt, int x, int y)
      {
        ingrediente_actual = 0;
        repaint();
        return true;
      }

    //----------------------------------------------------------------

    void precio()
      {
        double total = 10.69;
        boolean sin_elementos = true;

        System.out.println("\n------------------------------\n" +
                           "Pizza:        $" + total + "\n");
        for (int i = 0; i < TOTAL_INGREDIENTES; i++)
          {
          if (coordenada[i].x > 0 &&
              coordenada[i].x < tamano_de_pizza &&
              coordenada[i].y > tamano_de_ingredientes &&
              coordenada[i].y < tamano_de_pizza + 
                                tamano_de_ingredientes)
            {
              sin_elementos = false;
              total += 0.95;
              System.out.println( nombre_ingrediente[i] + "   $0.95");
            }
          }

        if (sin_elementos)
          System.out.println("   SIN INGREDIENTES");

        System.out.println("\nTotal         $" + total);
      }

    //----------------------------------------------------------------

    void ordenar()
      {
        System.out.println("\n------------------------------\n" +
                           "PIZZA ORDENADA!\n");
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
        if (imagen_cargada)
          {
            showStatus("");

            ancho = size().width;
            alto = size().height;

            g.setColor(Color.lightGray);
            g.fillRect(0, 0, ancho, alto);

            g.drawImage(fondo, 0, tamano_de_ingredientes,
                        tamano_de_pizza, tamano_de_pizza, this);

            g.setColor(Color.white);
            g.drawRect(0, tamano_de_ingredientes,
                       tamano_de_pizza, tamano_de_pizza);

            dibujar_elementos();
          }
      }
  }
