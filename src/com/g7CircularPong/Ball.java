package com.g7CircularPong;

import java.util.Random;
import java.lang.Math;
import java.lang.Thread;

public class Ball {

	int radius;
	float x, y;
	float xc, yc;
	float velocity;
	Surface surface;
	Compass compass;
	float paddleSize;
	float angle;
	int score=-1;
	static boolean stillUp=false;
	static boolean isUp=false;
	
	
	public Ball(Surface surface, Compass compass, int paddleSize)
	{
		this.surface=surface;
		this.compass=compass;
		this.paddleSize=(paddleSize)/57.29577954f;//Give a full degree of extra forgivness on each side
		score=0;
		
		resetBall();		
	}
	
	public Thread movingLoop = new Thread()
	{
		public void run()
		{
			int radius=surface.getRadius();
			double distance=0;
			int midX=surface.getMidX();
			int midY=surface.getMidY();
			double nx, ny;
			while (true)
			{
				score++;
				x+=xc;
				y+=yc;
				distance=Math.sqrt((x-midX)*(x-midX)+(y-midY)*(y-midY));
				if (distance>=radius)
				{

					double scale = radius / distance;
					double offset = distance - radius;
					x = (float)((x-midX)*scale);
					y = (float)((y-midY)*scale);
					nx = x/radius;
					ny = y/radius;
					double dp = xc*nx + yc*ny;
					xc = (float)(xc - (2*nx)*(dp));
					yc = (float)(yc - (2*ny)*(dp));
					x = (float)((x+midX)+xc*offset);
					y = (float)((y+midY)+yc*offset);
					
					angle=(float)(Math.atan2(y-midY, x-midX)%(Math.PI*2));					
					float latest=compass.getLatest();
					if (angle<(latest-paddleSize/2)%(Math.PI*2) || 
						angle>(latest+paddleSize/2)%(Math.PI*2))
					{
						try{Thread.sleep(50);}catch(Exception e){}
						latest=compass.getLatest();
						if (angle<(latest-paddleSize/2)%(Math.PI*2) ||
							angle>(latest+paddleSize/2)%(Math.PI*2))
						{
						resetBall();
						}
					}
					
					//reverse
					/*angle=Math.tan(yc/xc);
					tangent=Math.tan((y-midY)/(x-midX))+Math.PI/2;
					difference=angle-tangent;
					angle=tangent-(Math.PI-difference);
					*/
					
					
				}
				
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
	
	public void restartGame()
	{
		/*stillUp=true;
		while(stillUp)
			try{Thread.sleep(100);}catch(Exception e){}
		isUp=false;*/
		for (int i=0; i<30; i++)
		{
			x+=xc;
			y+=yc;
			try{Thread.sleep(10);}catch(Exception e){}
		}
		resetBall();
	}
	
	public void resetBall()
	{
		Random rand=new Random();
		
		try{Thread.sleep(3000);}catch(Exception e){}
		
		//Generate random direction with magnitude of radius/3
		velocity=surface.getRadius()/2;
		xc=rand.nextFloat()*velocity*2-velocity;
		yc=(float)Math.sqrt(velocity*velocity*4-xc*xc)-velocity;
		score=0;
		
		//Advance based on random vector
		x=xc+surface.getMidX();
		y=yc+surface.getMidY();
		
		//Generate final random direction with magnitude of 1
		velocity=2;
		xc=rand.nextFloat()*velocity*2-velocity;
		yc=(float)Math.sqrt(velocity*velocity-xc*xc);
		if (rand.nextInt(1)==0)
			yc*=-1;
		for (int i=0; i<30; i++)
		{
			x+=xc;
			y+=yc;
			try{Thread.sleep(100);}catch(Exception e){}
		}
	}
	
	public int getScore()
	{
		return score;
	}
}
