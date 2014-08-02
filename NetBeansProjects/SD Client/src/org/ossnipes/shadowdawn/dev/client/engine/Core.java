package org.ossnipes.shadowdawn.dev.client.engine;



import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;


public abstract class Core{
	boolean running;
		// An array of every ****ing DisplayMode I can think of. Ready, set, go!
		// Also, credit for most of these goes to Windows display properties :P
		public DisplayMode[] modeList = {
			new DisplayMode(800, 600, 32, 0),
			new DisplayMode(800, 600, 24, 0),
			new DisplayMode(800, 600, 16, 0),
			new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0),
			new DisplayMode(640, 480, 16, 0)
		};
	public void run()
	{
		try
		{
			init();
			gameLoop();
		}finally {s.restoreScreen();}
	}
	protected ScreenManager s;
	public void Stop()
	{
		running = false;
                System.exit(0);
	}
	public void init()
	{
		s = new ScreenManager();
		DisplayMode dm = s.findFirstCompatibleMode(modeList);
		s.setFullScreen(dm);
		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN,20));
		w.setBackground(Color.BLACK);
		w.setForeground(Color.WHITE);
		running = true;
	}
	public void gameLoop()
	{
		long startTime = System.currentTimeMillis();
		long cTime = startTime;
		while (running)
		{
			long timePassed = System.currentTimeMillis() - cTime;
			cTime += timePassed;
			update(timePassed);
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();

			try
			{
				Thread.sleep(20);
			} catch (Exception e) { System.out.println("Can't sleep? See the doctor!"); }
		}
	}
	// Needs overwriting, should update things.
	public void update(long timePassed){}
	// Draw the stuff
	public abstract void draw(Graphics2D g);

}
