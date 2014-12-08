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
		return(this.active);
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
		return(this.Image.getTranslationY());
	}
	
	public void setTranslationY(float num)
	{
		this.Image.setTranslationY(num);
	}
	
	public void deActivate()
	{
		this.active = false;
	}
	
	public void debug()
	{
		System.out.println("Active: " + this.active);
		System.out.println("Coords: " + this.Image.getX() + " " + this.Image.getY());
		System.out.println("Radius: " + this.radius);
	}
	
	public boolean crash(mObject m2)
	{
		double radiuses_res = Math.pow(this.radius - m2.radius, 2.0);
		double radiuses_sum = Math.pow(this.radius + m2.radius, 2.0);
		double x1 = Image.getX(), x2 = m2.Image.getX();
		double y1 = Image.getY(), y2 = m2.Image.getY();
		double distance = Math.pow(x1 - x2, 2.0) + Math.pow(y1 - y2, 2.0);
		
		/*
		 	//Debug Purposes.
			System.out.println("Ship: " + Image.getX() + " " + Image.getY());
			System.out.println("Rock: " + m2.Image.getX() + " " + m2.Image.getY());
			System.out.println("Distance: " + distance);
			System.out.println("Radiuses: " + radiuses_res + " " + radiuses_sum);
			System.out.println("Logic: " + (radiuses_res <= distance && distance <= radiuses_sum));
		*/
		
		return((radiuses_res <= distance && distance <= radiuses_sum) && m2.active);
	}
}
