package org.ossnipes.shadowdawn.dev.client.engine;



import java.awt.Image;


public class Sprite {
	private Animation a;
	private float x;
	private float y;
	private float vx;
	private float vy;
	/** Create a new Sprite
         * @param a Animation to turn into a sprite
         * @see Animation
         */
	public Sprite(Animation a)
	{
		this.a = a;
	}

	/**
         * @param timePassed Time passed since last update
         */
	public void update(long timePassed)
	{
		x += vx * timePassed;
		y += vy * timePassed;
		a.update(timePassed);
	}
	// Some get and set methods, to keep the evil god NetBeans happy
	public void setVy(float vy)
	{
		this.vy = vy;
	}
	public float getVy()
	{
		return vy;
	}
	public void setVx(float vx)
	{
		this.vx = vx;
	}
	public float getVx()
	{
		return vx;
	}
	/**
	 * @return The value of Y (Remember, y = up-down)
	 */
	public float getY()
	{
		return y;
	}
	/**
	 * @param y The value to set y to (Remember, y = up-down)
	 */
	public void setY(float y)
	{
		this.y = y;
	}
	/**
	 * @return The value of X (Remember, x = left-right)
	 */
	public float getX()
	{
		return x;
	}
	/**
	 * @param x The value to set x to (Remember, x = left-right)
	 */
	public void setX(float x)
	{
		this.x = x;
	}
	public int getWidth()
	{
		return a.getImage().getWidth(null);
	}
	public int getHeight()
	{
		return a.getImage().getHeight(null);
	}
	// Get sprite image
	public Image getSprite()
	{
		return a.getImage();
	}
}
