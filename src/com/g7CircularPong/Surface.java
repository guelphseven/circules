package com.g7CircularPong;
import android.content.Context;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


public class Surface extends View {
	int i=0;
	Canvas c;
	int width=-1, height=-1;
	int radius=-1;
	Paddle paddle;
	Ball ball;
	Compass compass;
	
	public Surface(Context context) {
		super(context);
		compass=new Compass(context);
	}
	
	protected void onDraw(Canvas canvas){
		if (width<0)
		{
			width=canvas.getWidth();
			height=canvas.getHeight();
			if (width<height)
				radius=width/2;
			else
				radius=height/2;
			
			paddle=new Paddle(45);
			ball=new Ball(this);
		}
		
		//The Circle
		Paint myPaint = new Paint();
		myPaint.setStrokeWidth(3);
		myPaint.setColor(0xFF097286);
		canvas.drawCircle(width/2, height/2, radius, myPaint);

		//The arc
		myPaint = new Paint();
		myPaint.setStrokeWidth(3);
		myPaint.setColor(0xFFFF0000);
		myPaint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(new RectF(width/2-radius,height/2-radius,width/2+radius, height/2+radius), 
				(compass.getLatest()-paddle.getSize()/2), paddle.getSize(), false, myPaint);
		
		//The ball
		myPaint = new Paint();
		myPaint.setStrokeWidth(3);
		myPaint.setColor(0xFFFFFFFF);
		canvas.drawCircle(ball.getx(), ball.gety(), ball.getRadius(), myPaint);
		
		if (!ball.movingLoop.isAlive())
			ball.movingLoop.start();
		
		invalidate();
	}
	
	public int getMidX()
	{
		return width/2;
	}
	
	public int getMidY()
	{
		return height/2;
	}
	
	public int getRadius()
	{
		return radius;
	}
	/*Thread callBack = new Thread()
	{
		public void run()
		{
			while (i<200)
			{
				try{Thread.sleep(1);}catch(Exception e){};
				onDraw(c);
			}
		}
	};*/

}
