package edu.uprm.rock_a_void;

import android.widget.ImageView;

public class mObject
{
	ImageView Image;
	boolean active;
	double radius;
	
	public mObject(ImageView image)
	{
		Image = image;
		active = false;
	}
	
	public mObject(ImageView image, double rad)
	{
		Image = image;
		this.radius = rad;
		active = false;
	}
	
	public boolean getActive()
	{
		return(active);
	}
	
	
	public void resetTranslationY(float num)
	{
		this.Image.setTranslationY(num);
		this.active = true;
	}
	
	public void horizontalTranslation(float num)
	{
		this.Image.setTranslationX(num);
	}
	
	public float getTranslationY()
	{
		return(Image.getTranslationY());
	}
	
	public void setTranslationY(float num)
	{
		Image.setTranslationY(num);
	}
	
	public void deActivate()
	{
		active = false;
	}
	
	public boolean crash(mObject m2)
	{
		double radiuses = Math.abs(this.radius - m2.radius);
		double x1 = Image.getX(), x2 = m2.Image.getX();
		double y1 = Image.getY(), y2 = m2.Image.getY();
		double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		
		/*
		 	Debug Purposes.
			System.out.println("Ship: " + rockImage.getX() + " " + rockImage.getY());
			System.out.println("Rock: " + m2.rockImage.getX() + " " + m2.rockImage.getY());
			System.out.println("Distance: " + distance);
			System.out.println("Radiuses: " + radiuses);
		*/
		
		return(radiuses > distance);
	}
}
