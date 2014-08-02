package org.ossnipes.shadowdawn.dev.engine;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class ScreenManager {
	private GraphicsDevice vc;
	// Hide or show our cursor depending on boolean v
	public void setCursorHide(boolean v)
	{
		Window w = getFullScreenWindow();
		if (v)
		{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.createImage(new byte[]{0});
		Cursor noCursor = toolkit.createCustomCursor(image, new Point(1,1),
		                                             "blank cursor");
		((Component)w).setCursor(noCursor);
		}
		else if (!v)
		{
			((Component)w).setCursor(Cursor.getDefaultCursor());
		}
	}
	public ScreenManager()
	{
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
	}
	// Get all available DMs
	public DisplayMode[] getCompatibleDisplayModes()
	{
		return vc.getDisplayModes();
	}
	public DisplayMode findFirstCompatibleMode(DisplayMode[] modeList)
	{
		DisplayMode[] goodModes = vc.getDisplayModes();
		for (int i = 0; i<modeList.length; i++)
		{
			// Inner for loop
			for (int x = 0;x < goodModes.length; x++)
			{
				if (displayModesMatch(modeList[i], goodModes[x]))
				{
					return modeList[i];
				}
			}
		}
		return null;
	}
	public DisplayMode getCurrentDisplayMode()
	{
		return vc.getDisplayMode();
	}

	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2)
	{
		if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight())
		{
			return false;
		}
		if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth())
		{
			return false;
		}
		if (m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate())
		{
			return false;
		}
		return true;
	}
	// just make one with the default title "JPain"
	public void setFullScreen(DisplayMode dm)
	{
		JFrame frame = new JFrame("JPain");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setUndecorated(true);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);
		vc.setFullScreenWindow(frame);

		if (dm != null && vc.isDisplayChangeSupported())
		{
			try
			{
				vc.setDisplayMode(dm);
			}catch (Exception ex){ System.out.println("Crappy video cards for the lose! Get a display change supported card! :("); }
			frame.createBufferStrategy(2);
		}
	}
	// Will set graphics object equal to what this method returns :D
	public Graphics2D getGraphics()
	{
		Window w = vc.getFullScreenWindow();
		if (w != null)
		{
			BufferStrategy bs = w.getBufferStrategy();
			return (Graphics2D)bs.getDrawGraphics();
		}
		else
		{
			return null;
		}
	}
	public void update()
	{
		Window w = vc.getFullScreenWindow();
		BufferStrategy bs = w.getBufferStrategy();
		if (!bs.contentsLost())
		{
			bs.show();
		}
	}
	// Returns the full screen window
	public Window getFullScreenWindow()
	{
		return vc.getFullScreenWindow();
	}
	// Get the window width
	public int getWidth()
	{
		Window w = vc.getFullScreenWindow();
		if (w != null)
		{
			return w.getWidth();
		}
		else
		{
			System.out.println("There is no window!");
			return 0;
		}

	}
	// Get the window width
	public int getHeight()
	{
		Window w = vc.getFullScreenWindow();
		if (w != null)
		{
			return w.getHeight();
		}
		else
		{
			System.out.println("There is no window!");
			return 0;
		}

	}
	// Restore the screen if evil people want to quit :(
	public void restoreScreen()
	{
		Window w = vc.getFullScreenWindow();
		if (w != null)
		{
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
        public void close(int exitCode)
        {
            restoreScreen();
            System.exit(exitCode);
        }
	// Make a compatible image
	public BufferedImage createCompatibleImage(int w, int h, int t)
	{
		// Our Window is win! :P
		Window win = vc.getFullScreenWindow();
		if (win != null)
		{
			GraphicsConfiguration gc = win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, t);
		}
		// It didn't work :(
		System.out.println("You have a null window, why the hell are you calling this?");
		return null;
	}
}
