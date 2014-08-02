package org.ossnipes.shadowdawn.dev.client.engine;



import java.awt.Image;
import java.util.ArrayList;




public class Animation {
	private ArrayList scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;

	// Constructor
	public Animation()
	{
		scenes = new ArrayList();
		totalTime = 0;
		start();
	}
	// Add scene to scenes ArrayList
	public synchronized void addScene(Image i, long t)
	{
		totalTime += t;
		scenes.add(new OneScene(i, totalTime));
	}
	// Start method, starts the animation from the beginning
	public synchronized void start()
	{
		movieTime = 0;
		sceneIndex = 0;
	}

	// Update the current scene
	public synchronized void update(long timePassed)
	{
		if (scenes.size() > 1)
		{
			movieTime += timePassed;
			if (movieTime >= totalTime)
			{
				movieTime = 0;
				sceneIndex = 0;
			}
			while(movieTime > getScene(sceneIndex).endTime)
			{
				sceneIndex++;
			}
		}
	}
	// Get current scene :)
	public synchronized Image getImage()
	{
		if (scenes.size() == 0)
		{
			return null;
		}
		else
		{
			return getScene(sceneIndex).pic;
		}
	}
	public OneScene getScene(int sceneIndex)
	{
		return (OneScene)scenes.get(sceneIndex);
	}
	// A private inner class
	private class OneScene
	{
		Image pic;
		long endTime;
		public OneScene(Image pic, long endTime)
		{
			this.pic = pic;
			this.endTime = endTime;
		}
	}
}