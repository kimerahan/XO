package com.a23labs.xo;

import com.xo.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Context context = getApplicationContext();
		CharSequence text = "**Loading............";
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.getView().setBackgroundColor(Color.TRANSPARENT);
		toast.show();
		Thread timer = new  Thread(){
			public void run(){
				try{
				sleep(4000);
				
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					finish();
					Intent openspl =new Intent("com.xo.Menu");
					startActivity(openspl);
				}
			}
		};timer.start();


	}

	

    }


    

