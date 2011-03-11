package com.g7CircularPong;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Paint;
import android.view.Window;
import android.view.WindowManager;
import android.content.pm.ActivityInfo;

public class CircularPong extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);


       	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	   	setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

    	setContentView(new Surface(this));
    	Paint mPaint = new Paint();
    	mPaint.setDither(true);
    	mPaint.setColor(0xFFFFFF00);
    	mPaint.setStyle(Paint.Style.STROKE);
    	mPaint.setStrokeJoin(Paint.Join.ROUND);
    	mPaint.setStrokeCap(Paint.Cap.ROUND);
    	mPaint.setStrokeWidth(3);
    }
}