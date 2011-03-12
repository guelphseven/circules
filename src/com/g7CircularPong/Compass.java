package com.g7CircularPong;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.content.Context;
import java.lang.Math;

public class Compass implements SensorEventListener{

	Sensor compassSensor;
	Sensor accelSensor;
	SensorManager manager;
	float[] vecGravity;
	float current, velocity=0;
	private float orientation;
	
	public Compass(Context c)
	{
		manager = (SensorManager)c.getSystemService(Context.SENSOR_SERVICE);
		compassSensor=manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		accelSensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		manager.registerListener(this, compassSensor, SensorManager.SENSOR_DELAY_GAME);
		manager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_GAME);
		vecGravity=new float[3];
		vecGravity[0]=0;
		vecGravity[1]=0;
		vecGravity[2]=0;
		orientation=0;
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) {
		int eventType = event.sensor.getType();
		if (eventType==Sensor.TYPE_ACCELEROMETER)
		{
			vecGravity=event.values;
			updatePosition();
		}
		
	}
	
	private void updatePosition()
	{
			/*float target=(float)Math.atan2(vecGravity[1], -vecGravity[0]);
			velocity*=0.98f;
			if ((target-orientation)%(2*Math.PI)<(orientation-target)%(2*Math.PI))
				velocity-=0.01;
			else
				velocity+=0.01;
			
			orientation+=velocity;*/
			//orientation=(float)Math.atan2(vecGravity[1], -vecGravity[0]);
			orientation=(float)Math.atan2(vecGravity[1], -vecGravity[0]);
	}
	
/*	private double floatMod(double num, double divider)
	{
		double a = num / divider;
		d=num-()
		if ()
	}
	*/
	public float getLatest()
	{
		updatePosition();
		return orientation;
	}

}
