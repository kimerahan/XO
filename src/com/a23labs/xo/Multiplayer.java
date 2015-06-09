package com.a23labs.xo;
import com.xo.R;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Multiplayer extends Activity{
	Button newgame2;
	Button[][] button = new Button[3][3];
	ImageView line1;
	ImageView line2;
	ImageView line3;
	ImageView line4;
	TextView result;
	int[][] game = new int[3][3];
	boolean toggle,firstTime = true;
	MediaPlayer mp2;
	ImageView  player1text, player2text;
	ImageButton player1image, player2image;
	Animation anim;
	int total=0,count=0,c=0,counter=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiplay);
		int row, col;
		toggle = true;
		result = (TextView)findViewById(R.id.result2);
		
		line1 = (ImageView)findViewById(R.id.ImageView1);
		line1.setBackgroundColor(Color.rgb(0, 0, 0));
		
		line2 = (ImageView)findViewById(R.id.ImageView2);
		line2.setBackgroundColor(Color.rgb(0, 0, 0));
		
		line3 = (ImageView)findViewById(R.id.ImageView3);
		line3.setBackgroundColor(Color.rgb(0, 0, 0));
		
		line4 = (ImageView)findViewById(R.id.ImageView4);
		line4.setBackgroundColor(Color.rgb(0, 0, 0));
		
		player1image = (ImageButton)findViewById(R.id.player1image);
		player2image = (ImageButton)findViewById(R.id.imageButton3);
		player1text = (ImageView)findViewById(R.id.player1text);
		player2text = (ImageView)findViewById(R.id.player2text);
		
		
		player1image.setImageResource(R.drawable.player1);
		player2image.setImageResource(R.drawable.player2);
		player1text.setVisibility(View.VISIBLE);
		player2text.setVisibility(View.INVISIBLE);
		anim = AnimationUtils.loadAnimation(this, R.anim.buttonanim);
		player1image.setAnimation(anim);
		player2image.setAnimation(anim);
		player1image.startAnimation(anim);
		
		
		
		
		result = (TextView)findViewById(R.id.result2);
		  	 newgame2 = (Button)findViewById(R.id.imageButton);
		 
		  	newgame2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					counter++;
					int row, col;
					for(row = 0; row < button.length; row++){
						for(col = 0; col < button[row].length; col++){
							button[row][col].setBackgroundResource(R.drawable.blanksquare);
							game[row][col] = 0;  //clear grid to 0s
							button[row][col].setClickable(true);
							result.setText("");
							toggle = true;
							player1image.setImageResource(R.drawable.player1);
							player1text.setVisibility(View.VISIBLE);
							player2text.setVisibility(View.INVISIBLE);
						}
					}
				}
			});
					
		  	button[0][0] = (Button)findViewById(R.id.b1);
			button[0][1] = (Button)findViewById(R.id.b2);
			button[0][2] = (Button)findViewById(R.id.b3);
			button[1][0] = (Button)findViewById(R.id.b4);
			button[1][1] = (Button)findViewById(R.id.button1);
			button[1][2] = (Button)findViewById(R.id.b6);
			button[2][0] = (Button)findViewById(R.id.b7);
			button[2][1] = (Button)findViewById(R.id.b8);
			button[2][2] = (Button)findViewById(R.id.b9);
			
			player1image .setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Context context2 = getApplicationContext();
					CharSequence text2 =count+"% IN " +"["+counter +"]"+" ATTEMPTS   ";
					int duration1 = Toast.LENGTH_LONG;

					Toast toast2 = Toast.makeText(context2, text2, duration1);
					toast2.getView().setBackgroundColor(Color.TRANSPARENT);
					toast2.show();
					
				}
			});
             player2image .setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					Context context2 = getApplicationContext();
					CharSequence text2 = c+"% IN "+"["+counter +"]"+" ATTEMPTS";
					int duration1 = Toast.LENGTH_LONG;

					Toast toast2 = Toast.makeText(context2, text2, duration1);
					toast2.getView().setBackgroundColor(Color.TRANSPARENT);
					toast2.show();
					
				}
			});
			
			ButtonHandler handler = new ButtonHandler();
			for(row = 0; row < button.length; row++){
				for(col = 0; col < button[row].length; col++){
					button[row][col].setOnClickListener(handler);
				}
			}
		}
		
		public boolean checkDraw() {
			int row, col;

			for (row = 0; row < 3; row++) {
				for (col = 0; col < 3; col++) {
					if (game[row][col] == 0) {
						return false;
					}
				}
			}
			return true;
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
		}  //checks through the row and columns to find whether there is a combination of three similar characters
		
		public void buttonsUnclickable() {
			int row, col;
			for (row = 0; row < button.length; row++) {
				for (col = 0; col < button[row].length; col++) {
					button[row][col].setClickable(false);
				}
			}
		}
		
		private class ButtonHandler implements OnClickListener
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int row, col;
				for(row = 0; row < button.length; row++){
					for(col = 0; col < button[row].length; col++){
						if(button[row][col].isPressed()){
							
							if(toggle == true){  //Player 1
								mp2 = MediaPlayer.create(Multiplayer.this, R.raw.touchsound);
								mp2.start();
								button[row][col].setBackgroundResource(R.drawable.xi);
								button[row][col].setClickable(false);
								game[row][col] = 1;
								
								
								toggle = false;
								
								if(checkWin(1)){
									count+=3;
									result.setText(""+count);
									result.setTextColor(Color.BLUE);
									result.setText("Player 1 wins!");
									buttonsUnclickable();
									Context context = getApplicationContext();
									CharSequence text = "[X] IS BETTER";
									int duration = Toast.LENGTH_LONG;
									Toast toast = Toast.makeText(context, text, duration);
									toast.getView().setBackgroundColor(Color.TRANSPARENT);
									toast.show();
									if(counter==5){
										total=total+count;
										result.setText("" +count);
									}
									
								}
								else if(checkDraw()){
									count=count+1;
									result.setText("" +count);
									result.setTextColor(Color.BLACK);
									result.setText("GAME DRAWN!");
									buttonsUnclickable();
																	}
								else
								{
									player1image.setImageResource(R.drawable.player1);
									player2image.setImageResource(R.drawable.player2);
									player1text.setVisibility(View.INVISIBLE);
									player2text.setVisibility(View.VISIBLE);
									player2image.startAnimation(anim);
								}
								
							}
							else{ //Player 2
								mp2 = MediaPlayer.create(Multiplayer.this, R.raw.beep);
								mp2.start();
								button[row][col].setBackgroundResource(R.drawable.oi);
								button[row][col].setClickable(false);
								game[row][col] = 2;
								
								
								toggle = true;
								
								if(checkWin(2)){
									c+=3;
									result.setText(""+c);
									result.setTextColor(Color.RED);
									result.setText("Player 2 wins!");
									buttonsUnclickable();
									Context context = getApplicationContext();
									CharSequence text = "[O] IS BETTER";
									int duration = Toast.LENGTH_LONG;

									Toast toast = Toast.makeText(context, text, duration);
									toast.getView().setBackgroundColor(Color.TRANSPARENT);
									toast.show();
									if(counter==5){
										total=total+c;
										result.setText("" +c);
									}
									
								}
								else if(checkDraw()){
									c=c+1;
									result.setText("" +c);
									result.setTextColor(Color.BLACK);
									result.setText("GAME DRAWN!");
									buttonsUnclickable();
										}
								else
								{
									player1image.setImageResource(R.drawable.player1);
									player2image.setImageResource(R.drawable.player2);
									player1text.setVisibility(View.VISIBLE);
									player2text.setVisibility(View.INVISIBLE);
									player1image.startAnimation(anim);
								}
							}
							
						}
					}
				}
			}


	}


}
