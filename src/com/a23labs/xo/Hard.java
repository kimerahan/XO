package com.a23labs.xo;
import java.util.Random;

import com.xo.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;

public class Hard  extends  Activity{
	Button[][] button = new Button[3][3];
	boolean gameContinues = true;
	int[][] game = new int[3][3];
	int row0, col0;
	TextView result;
	ImageView result2, line1, line2, line3, line4;
	ImageButton p2;
	Button newgame2,retrace;
	int computerTurn;
	int randomRow, randomCol;
	Random r = new Random();
	int takeCenter = 1;
	ImageView player1image;
	MediaPlayer mp;
	Animation anim;
	int[] gameReplay = new int[18];
	int moveCounter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hard);
		 mp = MediaPlayer.create(Hard.this, R.raw.beep);
			computerTurn = 1;
			line1 = (ImageView)findViewById(R.id.ImageView1);
			line1.setBackgroundColor(Color.rgb(0, 0, 0));
			
			line2 = (ImageView)findViewById(R.id.ImageView2);
			line2.setBackgroundColor(Color.rgb(0, 0, 0));
			
			line3 = (ImageView)findViewById(R.id.ImageView3);
			line3.setBackgroundColor(Color.rgb(0, 0, 0));
			
			line4 = (ImageView)findViewById(R.id.ImageView4);
			line4.setBackgroundColor(Color.rgb(0, 0, 0));
			
			button[0][0] = (Button) findViewById(R.id.b1);
			button[0][1] = (Button) findViewById(R.id.b2);
			button[0][2] = (Button) findViewById(R.id.b3);
			button[1][0] = (Button) findViewById(R.id.b4);
			button[1][1] = (Button) findViewById(R.id.button1);
			button[1][2] = (Button) findViewById(R.id.b6);
			button[2][0] = (Button) findViewById(R.id.b7);
			button[2][1] = (Button) findViewById(R.id.b8);
			button[2][2] = (Button) findViewById(R.id.b9);
			
			
			 
	        p2=(ImageButton)findViewById(R.id.imageButton3);
	        player1image = (ImageView)findViewById(R.id.player1image);
	        result = (TextView) findViewById(R.id.result);
			result2 = (ImageView)findViewById(R.id.imageView);
			
			retrace = (Button)findViewById(R.id.retrace);
			retrace.setEnabled(false);
			retrace.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent i = new Intent(Hard.this, Retrace.class);
					Bundle b = new Bundle();
					b.putIntArray("arraykey", gameReplay);
					i.putExtras(b);
					startActivity(i);
				}
			});
			
			
			newgame2 = (Button)findViewById(R.id.imageButton);
			
			newgame2.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					int row, col;
					for (row = 0; row < button.length; row++) {
						for (col = 0; col < button[row].length; col++) {
							button[row][col].setBackgroundResource(R.drawable.blanksquare);
							button[row][col].setClickable(true);
							game[row][col] = 0;
						}
					}
					result2.setBackgroundResource(R.drawable.blank);
				
					gameContinues = true;
					computerTurn = 1;
					takeCenter = r.nextInt(2);
					for(row = 0; row < gameReplay.length; row++){
						gameReplay[row] = 0;
					}
					moveCounter = 0;
					retrace.setEnabled(false);
				}
			});
			
			
			
			

			ButtonHandler handler = new ButtonHandler();
			int row, col;
			for (row = 0; row < button.length; row++) {
				for (col = 0; col < button[row].length; col++) {
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

		public boolean straightLine(int w){
			int row, col, counterW = 0, counter0 = 0;
			for(row = 0; row < game.length; row++){
				for(col = 0; col < game[row].length; col++){
					if(game[row][col] == w){
						counterW++;
					}
					if(game[row][col] == 0)
					{
						counter0++;
						row0 = row;
						col0 = col;
					}
				}
				if(counterW == 2 && counter0 == 1){
					return true;
				}
				counterW = 0;
				counter0 = 0;
			}
			
			for(col = 0; col < 3; col++){
				for(row = 0; row < 3; row++){
					if(game[row][col] == w){
						counterW++;
					}
					if(game[row][col] == 0)
					{
						counter0++;
						row0 = row;
						col0 = col;
					}
				}
				if(counterW == 2 && counter0 == 1){
					return true;
				}
				counterW = 0;
				counter0 = 0;
			}
			
			for(row = 0; row < 3; row++){
				if(game[row][row] == w){
					counterW++;
				}
				if(game[row][row] == 0){
					counter0++;
					row0 = row;
					col0 = row;
				}
			}
			if(counterW == 2 && counter0 == 1){
				return true;
			}
			counterW = 0;
			counter0 = 0;
			
			col = 2;
			for(row = 0; row < 3; row++){
				if(game[row][col] == w){
					counterW++;
				}
				if(game[row][col] == 0){
					counter0++;
					row0 = row;
					col0 = col;
				}
				col -= 1;
			}
			if(counterW == 2 && counter0 == 1){
				return true;
			}
			counterW = 0;
			counter0 = 0;
			
			return false;
		}
		
		public void buttonsUnclickable() {
			int row, col;
			for (row = 0; row < button.length; row++) {
				for (col = 0; col < button[row].length; col++) {
					button[row][col].setClickable(false);
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
		
		private void generateRandom(){
			row0 = r.nextInt(3);
			col0 = r.nextInt(3);
		}

		
		private class ButtonHandler implements OnClickListener {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int row, col;
				
				if (gameContinues == true){//User move
					for (row = 0; row < button.length; row++) {
						for (col = 0; col < button[row].length; col++) {
							if (button[row][col].isPressed()) {
								mp.start();
								button[row][col]
										.setBackgroundResource(R.drawable.xi);
								button[row][col].setClickable(false);
								gameReplay[moveCounter] = row;
								moveCounter++;
								gameReplay[moveCounter] = col;
								moveCounter++;
								game[row][col] = 1;

								if (checkWin(1)) {
									result2.setBackgroundResource(R.drawable.youwin);
									gameContinues = false;
									buttonsUnclickable();
									retrace.setEnabled(true);  //makes the button to click
								}
								else if(checkDraw()){
									result2.setBackgroundResource(R.drawable.gamedrawn);
									gameContinues = false;
									buttonsUnclickable();
								}
								break;
							}
						}
					}
				}
				
				if(gameContinues == true){//computer move
					if(straightLine(2)){ //Take win
						game[row0][col0] = 2;
						button[row0][col0].setBackgroundResource(R.drawable.oi);
						gameReplay[moveCounter] = row0;
						moveCounter++;
						gameReplay[moveCounter] = col0;
						moveCounter++;
						button[row0][col0].setClickable(false);
					}
					else if(straightLine(1)){ //Avoid loss
						game[row0][col0] = 2;
						button[row0][col0].setBackgroundResource(R.drawable.oi);
						gameReplay[moveCounter] = row0;
						moveCounter++;
						gameReplay[moveCounter] = col0;
						moveCounter++;
						button[row0][col0].setClickable(false);
					}
					else{ //Tactical move
						if(computerTurn == 1 && game[1][1] == 0 && takeCenter == 1){
							button[1][1].setBackgroundResource(R.drawable.oi);
							game[1][1] = 2;
							gameReplay[moveCounter] = 1;
							moveCounter++;
							gameReplay[moveCounter] = 1;
							moveCounter++;
							button[1][1].setClickable(false);
						}
						else{
							
							generateRandom();
							while(game[row0][col0] != 0){
								generateRandom();
								
							}
							button[row0][col0].setBackgroundResource(R.drawable.oi);
							game[row0][col0] = 2;
							gameReplay[moveCounter] = row0;
							moveCounter++;
							gameReplay[moveCounter] = col0;
							moveCounter++;
							button[row0][col0].setClickable(false);
						}
					}
					computerTurn++;
					if(checkWin(2)){
						result2.setBackgroundResource(R.drawable.computerwins);
						gameContinues = false;
						buttonsUnclickable();
					}
					else if(checkDraw()){
						result2.setBackgroundResource(R.drawable.gamedrawn);
						gameContinues = false;
						buttonsUnclickable();
					}
				}
				
			}
			


	 
	    
	}


}
