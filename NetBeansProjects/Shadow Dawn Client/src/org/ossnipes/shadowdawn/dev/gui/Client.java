package org.ossnipes.shadowdawn.dev.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.ossnipes.shadowdawn.dev.engine.*;

public class Client extends Core implements KeyListener {
    Image bg;
    Image slarge1;
    Image slarge2;
    Sound sou;
    Sprite spr;
    Animation a;
    SockMan sock;
    String message = "Hi";
    public static void main(String args[])
    {
        Client c = new Client();
        c.run();
    }
    @Override
    public synchronized void init()
    {
        super.init();
        sock = new SockMan("localhost");
        Window w = s.getFullScreenWindow();
        w.addKeyListener(this);
        bg = new ImageIcon("C:\\JTest\\bg.png").getImage();
        slarge1 = new ImageIcon("C:\\JTest\\s1large.png").getImage();
        slarge2 = new ImageIcon("C:\\JTest\\s1large2.png").getImage();
        a = new Animation();
        a.addScene(slarge1, 250);
        a.addScene(slarge2, 250);
        spr = new Sprite(a);
        spr.setVx(0.5f);
        spr.setVy(0.5f);
        try {
        sou = new Sound("C:\\JTest\\spacemusic.au", true);
        //sou.Start();
        } catch (Exception e) {System.out.println("failed to play sound file " + sou);}
    }
    @Override
    public void update(long timePassed)
    {
        if (spr.getX() < 0)
        {
            spr.setVx(Math.abs(spr.getVx()));
        }else if (spr.getX() + spr.getWidth() >= s.getWidth())
        {
            spr.setVx(-Math.abs(spr.getVx()));
        }
        if (spr.getY() < 0)
        {
            spr.setVy(Math.abs(spr.getVy()));
        }else if (spr.getY() + spr.getHeight() >= s.getHeight())
        {
            spr.setVy(-Math.abs(spr.getVy()));
        }
        spr.update(timePassed);
    }
    @Override
    public void draw(Graphics2D g)
    {
        g.drawImage(bg, 0, 0, null);
        g.drawString(message, 250, 250);
        g.drawImage(spr.getSprite(), Math.round(spr.getX()), Math.round(spr.getY()), null);
    }
	public void keyPressed(KeyEvent e)
        {
            int id = e.getKeyCode();
            switch(id)
            {
                case KeyEvent.VK_ESCAPE:
                    
                    Stop();
                    e.consume();
                    break;
                case KeyEvent.VK_CONTROL:
                    message = "Don't try and control the king, he got crazy moves!";
                    e.consume();
                    break;
                case KeyEvent.VK_UP:
                    if(spr.getVy() > 0)
                    {
                        spr.setVy(0);
                        message = "OK :(, I'll stop my Y";
                    }
                    else if (spr.getVy() == 0)
                    {
                        spr.setVy(0.5f);
                        message = "Vroom! Vroom! Y";
                    }
                    e.consume();
                    break;
                case KeyEvent.VK_LEFT:
                    if (spr.getVx() > 0)
                    {
                        spr.setVx(0);
                        message = "OK :(, I'll stop my X";
                    }
                    else if (spr.getVx() == 0)
                    {
                        spr.setVx(0.5f);
                        message = "Vroom! Vroom! X";
                    }
            }

        }
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
