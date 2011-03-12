package com.g7CircularPong;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.graphics.Paint;
import android.view.Window;
import android.view.WindowManager;
import android.content.pm.ActivityInfo;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class CircularPong extends Activity {
    /** Called when the activity is first created. */
	public static AlertDialog.Builder alert;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

    	Ball.isUp=false;
    	Ball.stillUp=true;
    	
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	   	setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
	   	
        alert = new AlertDialog.Builder(this);

        alert.setTitle("Highscore Submission");
        alert.setMessage("Enter your name:");

        alert.setPositiveButton("Start", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
        		Ball.stillUp=false;
        		Ball.isUp=false;
        		((Dialog) dialog).hide();
        		((Dialog) dialog).dismiss();
        	};
        });

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