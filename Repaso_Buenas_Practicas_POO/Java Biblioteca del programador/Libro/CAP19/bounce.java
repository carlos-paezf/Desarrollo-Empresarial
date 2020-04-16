//********************************************************************
// bounce.java
//********************************************************************

import java.util.Hashtable;
import java.applet.*;
import java.io.*;
import java.awt.*;
import java.net.*;

//********************************************************************

class BounceImage
  {
    static float inelasticity = .96f;
    static float Ax = 0.0f;
    static float Ay = 0.0002f;
    static float Ar = 0.9f;

    public float x = 0;
    public float y = 0;
    public float old_x = 0;
    public float old_y = 0;
    public float Vx = 0.1f;
    public float Vy = 0.05f;
    public float Vr = 0.005f + (float)Math.random() * 0.001f;
    public float findex = 0f;

    public int width;
    public int height;
    public int index;

    bounce parent;

    static boolean imagesReadIn = false;

    //----------------------------------------------------------------

    public BounceImage(bounce parent)
      {
        this.parent = parent;

        width = 100;
        height = 100;
      }

    //----------------------------------------------------------------

    public void move(float x1, float y1)
      {
        x = x1;
        y = y1;
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        int i = index;

        if (parent.bounceimages[i] == null)
          i = 0;

        Graphics g2;
        g2 = g.create();
        g2.clipRect((int) old_x, (int) old_y, width, height);
        g2.drawImage(parent.background, 0, 0, parent.image_size.width,
                     parent.image_size.height, null);

        g.drawImage(parent.bounceimages[i], (int) x, (int) y, null);

        old_x = x;
        old_y = y;
      }

    //----------------------------------------------------------------

    public void step(long deltaT)
      {
        boolean collision_x = false;
        boolean collision_y = false;

        float jitter = (float)Math.random() * .01f - .005f;

        x += Vx * deltaT + (Ax / 2.0) * deltaT * deltaT;
        y += Vy * deltaT + (Ay / 2.0) * deltaT * deltaT;

        if (x <= 0.0f)
          {
            x = 0.0f;
            Vx = -Vx * inelasticity + jitter;
            collision_x = true;
          }

        Dimension d = parent.image_size;

        if (x + width >= d.width)
          {
            x = d.width - width;
            Vx = -Vx * inelasticity + jitter;
            collision_x = true;
          }

        if (y <= 0)
          {
            y = 0;
            Vy = -Vy * inelasticity + jitter;
            collision_y = true;
          }

        if (y + height >= d.height)
          {
            y = d.height - height;
            Vx *= inelasticity;
            Vy = -Vy * inelasticity + jitter;
            collision_y = true;
          }

        move(x, y);
        Vy = Vy + Ay * deltaT;
        Vx = Vx + Ax * deltaT;

        findex += Vr * deltaT;
        if (collision_x || collision_y)
          Vr *= Ar;

        while (findex <= 0.0)
          findex += parent.bounceimages.length;

        index = ((int) findex) % parent.bounceimages.length;
      }
  }

//********************************************************************

public class bounce extends Applet implements Runnable
  {
    static Graphics g;
    static Image background;

    boolean images_initialized = false;
    boolean done_loading_image = false;

    BounceImage images[];
    Image bounceimages[];

    boolean time_to_die;
    Dimension image_size;
    AudioClip music;

    //----------------------------------------------------------------

    public void init()
      {
        g = getGraphics();

        background = getImage(getCodeBase(), "piano.gif");

        image_size = size();

        image_size.height -= 60;

        Image offScrImage = createImage(image_size.width,
                                        image_size.height);
        Graphics offScrGC = offScrImage.getGraphics();
        offScrGC.drawImage(background, 0, 0, image_size.width,
                           image_size.height, this);
      }

    //----------------------------------------------------------------

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
                               int width, int height)
      {
        if (infoflags == ALLBITS)
          {
            done_loading_image = true;
            showStatus("");
            repaint();
            (new Thread(this)).start();
            return false;
          }
        else
          return true;
      }

    //----------------------------------------------------------------

    void makeImages(int nimages)
      {
        bounceimages = new Image[8];
        for (int i = 1 ; i <= 8 ; i++)
          bounceimages[i-1] = getImage (getCodeBase (),
                                        "images/happy/t" + i + ".gif");

        images = new BounceImage[nimages];
        for (int i = 0; i < nimages; i++)
          {
          BounceImage img = images[i] = new BounceImage(this);
          img.move(1 + img.width*.8f*(i%3) + (i/3)*.3f*img.width,
                   img.height*.3f + (i%3)*.3f*img.height);
          }

        music = getAudioClip(getCodeBase(), "bounce.au");
      }

    //----------------------------------------------------------------

    public void run()
      {
        long lasttime;

        try
          {
            if (images == null)
              {
                System.out.println("Making images ...");
                makeImages(4);
              }

            if (music != null)
              music.loop();

            lasttime = System.currentTimeMillis();
            while (!time_to_die)
              {
                int i;
                long now = System.currentTimeMillis();
                long deltaT = now - lasttime;
                boolean active = false;
                Dimension d = image_size;

                for (i = 0; i < images.length; i++)
                  {
                    BounceImage img = images[i];

                    img.step(deltaT);

                    if (img.Vy > .05 || -img.Vy > .05 ||
                        img.y + img.width < d.height - 10)
                      {
                        active = true;
                      }
                  }
                if (!active && images.length != 0)
                  {
                    for (i = 0; i < images.length; i++)
                      {
                        BounceImage img = images[i];

                        img.Vx = (float)Math.random() / 4.0f - 0.125f;
                        img.Vy = -(float)Math.random() / 4.0f - 0.2f;
                        img.Vr = 0.05f - (float)Math.random() * 0.1f;
                      }
                  }
                paint_images();
                lasttime = now;
                try
                  {
                    Thread.sleep (100);
                  }
                catch (InterruptedException e)
                  {
                    return;
                  }
              }
          }
        finally
          {
            if (music != null)
              music.stop ();
          }
      }

    //----------------------------------------------------------------

    public void start()
      {
        time_to_die = false;
      }

    //----------------------------------------------------------------

    public void stop()
      {
        time_to_die = true;
        music.stop();
      }

    //----------------------------------------------------------------

    public void paint_images()
      {
        if (images != null)
          for (int i = 0; i < images.length; i++)
            if (images[i] != null)
              images[i].paint(g);
      }

    //----------------------------------------------------------------

    public void paint(Graphics g)
      {
        if (!done_loading_image)
          showStatus("Bounce:  loading image");

        else
          {
            g.drawImage(background, 0, 0, image_size.width,
                        image_size.height, this);

            paint_images();

            Font f = new Font("TimesRoman", Font.BOLD, 30);

            g.setFont(f);
            g.setColor(Color.blue);
            g.drawString("Joe's Sports Bar...Where heads roll!",
                         0, image_size.height+50);
          }
      }
  }
