package com.g7CircularPong;

import java.util.Random;
import java.lang.Math;
import java.lang.Thread;

public class Ball {

	int radius;
	float x, y;
	float xc, yc;
	Surface surface;
	
	public Ball(Surface surface)
	{
		this.surface=surface;
		Random rand=new Random();
		
		//Generate random direction with magnitude of 6
		xc=rand.nextFloat()*6;
		yc=(float)Math.sqrt(36-xc*xc)-3;
		xc-=3;
		
		//Advance based on random vector
		x+=xc+surface.getMidX();
		y+=yc+surface.getMidY();
		
		//Generate final random direction with magnitude of 1
		xc=rand.nextFloat()*2;
		yc=(float)Math.sqrt(4-xc*xc)-1;
		xc-=1;
		
	}
	
	public Thread movingLoop = new Thread()
	{
		public void run()
		{
			while (true)
			{
				x+=xc;
				y+=yc;
				try{Thread.sleep(10);}catch(Exception e){}
			}
		}
	};
	
	public int getx()
	{
		return (int)x;
	}
	
	public int gety()
	{
		return (int)y;
	}
	
	public int getRadius()
	{
		return (5);
	}
}
