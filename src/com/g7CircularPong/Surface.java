package com.g7CircularPong;
import android.app.AlertDialog;
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
	int strokeWidth;
	Paddle paddle;
	Ball ball;
	Compass compass;
	int paddleSize;
	
	public Surface(Context context) {
		super(context);
		compass=new Compass(context);
	}
	
	protected void onDraw(Canvas canvas){
		
		/*if (Ball.stillUp && !Ball.isUp)
		{
			Ball.isUp=true;
			AlertDialog a=CircularPong.alert.create();
			a.show();
		}*/

		/*if (!Ball.isUp)
		{*/
			if (width<0)
			{

				width=canvas.getWidth();
				height=canvas.getHeight();
				if (width<height)
					radius=width/2;
				else
					radius=height/2;
				
				paddleSize=45;
				ball=new Ball(this, compass, paddleSize);
			}
			
			//The Circle
			Paint myPaint = new Paint();
			myPaint.setAntiAlias(true);
			myPaint.setStrokeWidth(radius/20);
			myPaint.setStyle(Paint.Style.FILL);
			myPaint.setColor(0xFFAAAAAA);
			canvas.drawCircle(width/2, height/2, radius, myPaint);
	
			float o = compass.getLatest();
			myPaint.setStyle(Paint.Style.STROKE);
			myPaint.setColor(0xFFFF0000);
			canvas.drawArc(new RectF(width/2-radius, height/2-radius, width/2+radius, 
					height/2+radius),Math.round(o*57.29577954f-paddleSize/2),
					paddleSize,false,myPaint);
	
			//The ball
			myPaint.setStrokeWidth(3);
			myPaint.setColor(0xFFFFFFFF);
			myPaint.setStyle(Paint.Style.FILL);
			canvas.drawCircle(ball.getx(), ball.gety(), ball.getRadius(), myPaint);
			
			myPaint.setStrokeWidth(1);
			myPaint.setColor(0xFFFFFFFF);
			myPaint.setStyle(Paint.Style.STROKE);
			canvas.drawText("Score:"+String.valueOf(ball.getScore()), 0, 15, myPaint);
			
			if (!ball.movingLoop.isAlive())
				ball.movingLoop.start();
		
		invalidate();
		/*}*/
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
