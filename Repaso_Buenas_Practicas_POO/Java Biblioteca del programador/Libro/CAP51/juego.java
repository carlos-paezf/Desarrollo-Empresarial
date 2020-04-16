//*******************************************************************
// juego.java
//*******************************************************************

import java.applet.*;
import java.awt.*;

//*******************************************************************

public class juego extends Applet
  {
    Graphics g;
    cartas baraja;

    int ancho;
    int alto;
    int carta;
    int numero;
    int palo;

    boolean carta_desplegada[];
    int cartas_casa[];
    int cartas_jugador[];

    int contador_cartas;
    int ancho_carta = 100;
    int alto_carta = 140;

    int numero_cartas_casa;
    int numero_cartas_jugador;

    boolean mano_terminada = true;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        baraja = new cartas(g);

        carta_desplegada = new boolean [52];
        cartas_casa   = new int     [10];
        cartas_jugador   = new int     [10];

        new botones(g, "Repartir"  , 10,  50, 80, 20);
        new botones(g, "Carta", 10,  80, 80, 20);
        new botones(g, "Paso"  , 10, 110, 80, 20);

        repartir();
      }

    //----------------------------------------------------------------

    void borrar_cartas()
      {
        int i;

        for (i = 0; i < 52; i++)
          carta_desplegada[i] = false;

        contador_cartas = 0;

        numero_cartas_casa = 0;
        numero_cartas_jugador = 0;
      }

    //----------------------------------------------------------------

    void por_omision()
      {
        ancho = size().width;
        alto = size().height;
      }

    //----------------------------------------------------------------

    void traer_carta()
      {
        carta = (int) (Math.random() * 52);

        do
          {
            if (!carta_desplegada[carta])
              {
                carta_desplegada[carta] = true;
                break;
              }
            else if (++carta == 52)
              carta = 0;

          } while (true);

        traer_numero_y_palo(carta);
      }

    //----------------------------------------------------------------

    void traer_numero_y_palo(int carta)
      {
        numero = carta % 13;
        palo = carta / 13;
      }

    //----------------------------------------------------------------

    void repartir()
      {
        mano_terminada = false;

        borrar_cartas();

        for (int x = 0; x < 2; x++)
          {
            for (int y = 0; y < 2; y++)
              {
                traer_carta();

                if (y == 0)
                  cartas_casa[numero_cartas_casa++] = carta;
                else
                  cartas_jugador[numero_cartas_jugador++] = carta;

                if (x == 0 && y == 0)
                  baraja.dibujar_reverso(ancho_carta, 0,
                                      ancho_carta, alto_carta);
                else
                  baraja.dibujar(carta,
                                 (x+1)*ancho_carta, y*alto_carta,
                                 ancho_carta, alto_carta);

                contador_cartas++;
                showStatus("Cartas repartidas: " + contador_cartas);
              }
          }
      }

    //----------------------------------------------------------------

    void hit_me()
      {
        if (mano_terminada)
          return;

        traer_carta();

        cartas_jugador[numero_cartas_jugador++] = carta;

        baraja.dibujar(carta,
                       numero_cartas_jugador*ancho_carta, alto_carta,
                       ancho_carta, alto_carta);

        contador_cartas++;
        showStatus("Cartas repartidas: " + contador_cartas);

        int total_jugador = obtener_total(cartas_jugador,
                                     numero_cartas_jugador);

        if (total_jugador > 21)
          terminado();
      }

    //----------------------------------------------------------------

    int obtener_total(int cartas[], int numero_cartas)
      {
        int total = 0;
        int ases = 0;

        for (int i = 0; i < numero_cartas; i++)
          {
            traer_numero_y_palo(cartas[i]);

            switch (numero)
              {
                case 10:
                case 11:
                case 12:
                  total += 10;
                  break;

                case 0:
                  ases++;
                  total += 1;
                  break;

                default:
                  total += numero + 1;
              }
          }

        for (int a = 0; a < ases; a++)
          {
            if (total + 10 > 21)
              break;
            else
              total += 10;
          }

        return total;
      }

    //----------------------------------------------------------------

    void terminado()
      {
        if (mano_terminada)
          return;

        mano_terminada = true;
        baraja.dibujar(cartas_casa [0], ancho_carta, 0,
                       ancho_carta, alto_carta);

        int total_jugador = obtener_total(cartas_jugador,
                                     numero_cartas_jugador);

        int total_casa = obtener_total(cartas_casa,
                                     numero_cartas_casa);

        if (total_jugador > 21)
          {
            g.setColor(Color.red);
            g.drawString("Jugador", 10, 160);
            g.drawString("SE PASA", 10, 180);
            g.drawString("Casa", 10, 205);
            g.drawString("GANA"  , 10, 225);
            return;
          }

        while (total_casa < 17)
          {
            traer_carta();

            cartas_casa[numero_cartas_casa++] = carta;

            baraja.dibujar(carta, numero_cartas_casa*ancho_carta, 0,
                          ancho_carta, alto_carta);

            contador_cartas++;
            showStatus("Cartas repartidas: " + contador_cartas);

            total_casa = obtener_total(cartas_casa,
                                    numero_cartas_casa);
          }

        if (total_casa > 21)
          {
            g.setColor(Color.green);
            g.drawString("Casa", 10, 160);
            g.drawString("SE PASA", 10, 180);
            g.drawString("Jugador", 10, 205);
            g.drawString("GANA"  , 10, 225);
          }
        else if (total_jugador > total_casa)
          {
            g.setColor(Color.green);
            g.drawString("Jugador", 10, 160);
            g.drawString("GANA"  , 10, 180);
          }
        else if (total_casa == total_jugador)
          {
            g.setColor(Color.yellow);
            g.drawString("EMPATE", 10, 160);
          }
        else
          {
            g.setColor(Color.red);
            g.drawString("Casa", 10, 160);
            g.drawString("GANA"  , 10, 180);
          }
      }

    //----------------------------------------------------------------

    public boolean mouseUp(Event evt, int x, int y)
      {
        botones.dibujar_elementos();
        return true;
      }

    //----------------------------------------------------------------

    public boolean mouseDown(Event evt, int x, int y)
      {
        int button_pushed = botones.push_button(x, y);

        switch (button_pushed)
          {
          case 0:
            if (!mano_terminada)
              break;

            clear();
            repartir();
            repaint();
            break;

          case 1:
            hit_me();
            break;

          case 2:
            terminado();
            break;
          }

        return true;
      }

    //----------------------------------------------------------------

    void clear()
      {
        g.setColor(Color.black);
        g.fillRect(0, 0, ancho, alto);
      }

    //----------------------------------------------------------------

    public void update(Graphics _g)
      {
        clear();

        if (mano_terminada)
          baraja.dibujar(cartas_casa[0], ancho_carta, 0,
                         ancho_carta, alto_carta);
        else
          baraja.dibujar_reverso(ancho_carta, 0, ancho_carta, alto_carta);

        for (int i = 1; i < numero_cartas_casa; i++)
          baraja.dibujar(cartas_casa[i], (i+1)*ancho_carta, 0,
                         ancho_carta, alto_carta);

        for (int i = 0; i < numero_cartas_jugador; i++)
          baraja.dibujar(cartas_jugador[i],
                         (i+1)*ancho_carta, alto_carta,
                         ancho_carta, alto_carta);

        botones.dibujar_elementos();
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        por_omision();
        update(g);
      }
  }
