package com.g7CircularPong;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.content.Context;
import java.lang.Math;

public class Compass implements SensorEventListener{

	Sensor compassSensor;
	SensorManager manager;
	private float position;
	
	public Compass(Context c)
	{
		manager = (SensorManager)c.getSystemService(Context.SENSOR_SERVICE);
		compassSensor=manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		manager.registerListener(this, compassSensor, SensorManager.SENSOR_DELAY_UI);
		position=0;
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) {
		int eventType = event.sensor.getType();
		if(eventType == Sensor.TYPE_MAGNETIC_FIELD)
		{
			float[] values=event.values;
			position=(float)(Math.tan(values[1]/values[0])*57.29577954);

		}
		// TODO Auto-generated method stub
		
	}
	
	public float getLatest()
	{
		return position;
	}

}
