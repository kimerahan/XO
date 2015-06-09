package com.a23labs.xo;

import com.xo.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class About extends Activity{
	TextView t1;
	//ImageButton h,p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		Context context2 = getApplicationContext();
		CharSequence text2 = "--YOU CAN RATE OR REACH US--";
		int duration2 = Toast.LENGTH_LONG;

		Toast toast2 = Toast.makeText(context2, text2, duration2);
		toast2.getView().setBackgroundColor(Color.TRANSPARENT);
		toast2.show();
		
			}


	
}
