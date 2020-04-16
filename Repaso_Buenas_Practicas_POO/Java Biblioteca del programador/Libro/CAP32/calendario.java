//********************************************************************
// calendario.java
//********************************************************************

import java.applet.*;
import java.awt.*;
import java.util.Date;

//********************************************************************

public class calendario extends Applet
  {
    Graphics g;
    Font fuente;
    FontMetrics font_metrics;

    Date fecha_actual;
    Date mes_a_mostrar;
    int ancho;
    int alto;
    int anio;
    int mes;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        fecha_actual = new Date();
        anio = fecha_actual.getYear();
        mes = fecha_actual.getMonth();
        mes_a_mostrar = new Date(anio, mes, 1);

        Panel p = new Panel();
        add("Norte", p);
        p.add(new Button("<"));
        p.add(new Button(">"));
      }

    //----------------------------------------------------------------

    public boolean action(Event evt, Object arg)
      {
        if (arg.equals("<"))
          {
            if (--mes < 0)
              {
                mes = 11;
                anio--;
              }
          }
        else
          {
            if (++mes > 11)
              {
                mes = 1;
                anio++;
              }
          }

        mes_a_mostrar = new Date(anio, mes, 1);
        repaint();

        return true;
      }

    //----------------------------------------------------------------

    String dia_de_semana(int dia)
      {
        switch (dia)
          {
            case 0 :  return ("Dom");
            case 1 :  return ("Lun");
            case 2 :  return ("Mar");
            case 3 :  return ("Mie");
            case 4 :  return ("Jue");
            case 5 :  return ("Vie");
            default:  return ("Sab");
          }
      }

    //----------------------------------------------------------------

    String nombre_mes(int mes)
      {
        switch (mes)
          {
            case 0 :  return ("Enero");
            case 1 :  return ("Febrero");
            case 2 :  return ("Marzo");
            case 3 :  return ("Abril");
            case 4 :  return ("Mayo");
            case 5 :  return ("Junio");
            case 6 :  return ("Julio");
            case 7 :  return ("Agosto");
            case 8 :  return ("Septiembre");
            case 9 :  return ("Octubre");
            case 10:  return ("Noviembre");
            default:  return ("Deciembre");
          }
      }

    //----------------------------------------------------------------

    int numero_de_dias(int mes, int anio)
      {
        switch (mes+1)
          {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
              return (31);

            case 4:
            case 6:
            case 9:
            case 11:
              return (30);

            default:
              if (anio%4 != 0)
                return (28);
              else if (anio%100 != 0)
                return (29);
              else if (anio%400 != 0)
                return (28);
              else
                return (29);
          }
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        ancho  = size().width;
        alto = size().height;

        if (ancho < alto)
          alto = ancho;
        else
          ancho = alto;

        g.setColor(Color.blue);
        g.fillRect(0, 0, size().width, size().height);

        g.setColor(Color.white);

        for (int i = 2; i < 9; i++)         // líneas horizontales
          {
            int y = (alto * i) / 8;
            g.drawLine(0, y, ancho-1, y);
          }

        int y = alto / 4;
        for (int i = 0; i < 8; i++)         // líneas verticales
          {
            int x = (ancho * i) / 7;
            g.drawLine(x, y, x, alto-1);
          }

        fuente = new Font("TimesRoman", Font.BOLD, alto/20);
        g.setFont(fuente);
        font_metrics = g.getFontMetrics();

        g.drawString(nombre_mes (mes_a_mostrar.getMonth()) + " " +
                     (1900 + mes_a_mostrar.getYear()),
                     0, alto/8);

        y = alto / 4;
        for (int i = 0; i < 7; i++)
          g.drawString(dia_de_semana(i), (ancho * i) / 7, y);

        int primero = mes_a_mostrar.getDay();
        int last = numero_de_dias(mes_a_mostrar.getMonth(),
                                  mes_a_mostrar.getYear());
        int dia = primero;

        y = (alto * 5) / 16;
        for (int i = 1; i <= last; i++)
          {
          g.drawString("" + i, (ancho * dia) / 7, y);
          if (++dia > 6)
            {
              dia = 0;
              y += alto / 8;
            }
          }
      }
  }
