package com.a23labs.xo;
import com.xo.R;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Retrace extends  Activity {
	Button[][] button = new Button[3][3];
	int[] gameReplay = new int[18];
	int replayCounter;
	TextView retraceResult;
	int[][] game = new int[3][3];
	MediaPlayer mp;
	ImageView retraceImageView;
	ImageButton image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.retrace);
        image=(ImageButton)findViewById(R.id.imageButton1);
		mp = MediaPlayer.create(Retrace.this, R.raw.beep);
		//retraceResult = (TextView)findViewById(R.id.retraceResult);
		
		button[0][0] = (Button) findViewById(R.id.b1);
		button[0][1] = (Button) findViewById(R.id.b2);
		button[0][2] = (Button) findViewById(R.id.b3);
		button[1][0] = (Button) findViewById(R.id.b4);
		button[1][1] = (Button) findViewById(R.id.button1);
		button[1][2] = (Button) findViewById(R.id.b6);
		button[2][0] = (Button) findViewById(R.id.b7);
		button[2][1] = (Button) findViewById(R.id.b8);
		button[2][2] = (Button) findViewById(R.id.b9);
		
		//gameReplay[0] and gameReplay[1] is where the user played first
		retraceImageView = (ImageView)findViewById(R.id.retraceImageView);
				
		
		Bundle b = this.getIntent().getExtras();
		gameReplay = b.getIntArray("arraykey");
		
		
		ButtonHandler handler = new ButtonHandler();
		
		int row, col;
		for(row = 0; row < button.length;row++){
			for(col = 0; col < button[row].length; col++){
				button[row][col].setOnClickListener(handler);
			}
		}
		
	}
	
	public boolean checkWin(int w) {
		int row, col, counter = 0;
		for (row = 0; row < game.length; row++) {
			for (col = 0; col < game[row].length; col++) {
				if (game[row][col] == w) {
					counter++;
				}
			}
			if (counter == 3) {
				return true;
			}
			counter = 0;
		}

		for (col = 0; col < 3; col++) {
			for (row = 0; row < 3; row++) {
				if (game[row][col] == w) {
					counter++;
				}
			}
			if (counter == 3) {
				return true;
			}
			counter = 0;
		}

		for (row = 0; row < 3; row++) {
			if (game[row][row] == w) {
				counter++;
			}
		}
		if (counter == 3) {
			return true;
		}
		counter = 0;

		col = 2;
		for (row = 0; row < 3; row++) {
			if (game[row][col] == w) {
				counter++;
			}
			col -= 1;
		}
		if (counter == 3) {
			return true;
		}
		counter = 0;

		return false;
	}
	
	private class ButtonHandler implements OnClickListener
	{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int row, col;
			for(row = 0; row < button.length; row++){
				for(col = 0; col < button[row].length; col++){
					if(button[row][col].isPressed()){
						if(row == gameReplay[replayCounter] && col == gameReplay[replayCounter+1]){
							mp.start();
							retraceImageView.setImageResource(0);
							button[row][col]
									.setBackgroundResource(R.drawable.xi);
							game[row][col] = 1;
							button[row][col].setClickable(false);
							replayCounter += 2;
							
							if(checkWin(1)){
								Context context2 = getApplicationContext();
								CharSequence text2 = "**YOU ARE THE RETRACE CHAMP**";
								int duration1 = Toast.LENGTH_LONG;

								Toast toast2 = Toast.makeText(context2, text2, duration1);
								toast2.getView().setBackgroundColor(Color.TRANSPARENT);
								toast2.show();
								retraceImageView.setBackgroundResource(R.drawable.youwin);
								retraceImageView.setImageResource(R.drawable.youwin);
							}
							if(checkWin(1) == false){
								button[gameReplay[replayCounter]][gameReplay[replayCounter+1]].setBackgroundResource(R.drawable.oi);
								game[gameReplay[replayCounter]][gameReplay[replayCounter+1]] = 2;
								replayCounter += 2;
							}
						}
						else{
							retraceImageView.setImageResource(R.drawable.wrongmove);
						}
					}
				}
			}
		}
	}
	


}
