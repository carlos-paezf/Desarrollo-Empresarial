//********************************************************************
// FTP.java
//********************************************************************

import java.io.*;
import sun.net.ftp.*;
import sun.net.*;

//********************************************************************

class FTP
  {
    FtpClient ftp;

    //----------------------------------------------------------------

    public static void main(String args[])
      {
        new FTP();
      }

    //----------------------------------------------------------------

    public FTP()
      {
        try
          {
            System.out.println("Teclear nombre del sitio FTP: ");
            String sitio_ftp = leer_linea();

            ftp = new FtpClient(sitio_ftp);
            ftp.login("anonymous", "su@correo.com");
            ftp.binary();

            leer_telnet();

            traer_archivo();
          }

        catch (IOException e)
          {
            System.out.println("Excepcion: " + e);
            return;
          }
      }

    //----------------------------------------------------------------

    String leer_linea() throws IOException
      {
        DataInputStream s = new DataInputStream(System.in);
        return s.readLine();
      }

    //----------------------------------------------------------------

    void leer_telnet() throws IOException
      {
        int c;

        TelnetInputStream t = ftp.list();

        while ((c = t.read()) >= 0)
          System.out.print("" + (char) c);

        t.close();
      }

    //----------------------------------------------------------------

    void leer_telnet_destino_archivo(String nomb_archivo)
                                   throws IOException
      {
        int c;

        TelnetInputStream t = ftp.get(nomb_archivo);

        RandomAccessFile f = new RandomAccessFile(nomb_archivo, "rw");

        while ((c = t.read()) >= 0)
          {
            System.out.print("" + (char) c);
            f.writeByte((byte) c);
          }

        f.close();

        t.close();
      }

    //----------------------------------------------------------------

    void traer_archivo() throws IOException
      {
        while (true)
          {
            System.out.print("\nTeclear nombre de archivo  ");
            System.out.println("(Presione ESC+Enter para salir):");
            String cadena = leer_linea();
            if (cadena.charAt(0) == (char) 27)
              return;

            try
              {
                if (cadena.startsWith("cd "))
                  {
                    System.out.println("Cambiar directorio a <" +
                                       cadena.substring(3) + ">");
                    ftp.cd(cadena.substring(3));
                    leer_telnet();
                  }
                else
                  {
                    System.out.println("Obteniendo el archivo <" +
                                       cadena + ">");
                    leer_telnet_destino_archivo(cadena);
                    break;
                  }
              }
            catch (FileNotFoundException e) {
              System.out.println("El archivo no se encontró!  Vuélva a intentarlo");
	    }
          }
      }
  }
