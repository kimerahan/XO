package com.a23labs.xo;
import com.xo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends Activity{
	ImageButton play,stop,exit;
	ImageButton human,computer,about;
	MediaPlayer songs;
	ImageButton h,p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		p = (ImageButton)findViewById(R.id.imageButton7);
		h = (ImageButton)findViewById(R.id.imageButton6);
		 computer = (ImageButton)findViewById(R.id.imageButton1);
		 human = (ImageButton)findViewById(R.id.imageButton2);
		 play= (ImageButton)findViewById(R.id.imageButton3);
		 stop= (ImageButton)findViewById(R.id.imageButton4);
		   exit = (ImageButton)findViewById(R.id.imageButton8);
		   songs=MediaPlayer.create(Menu.this, R.raw.song);
		   about = (ImageButton)findViewById(R.id.imageButton5);
			about.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					//go to the about class
					Intent open =new Intent("com.xo.About");
					startActivity(open);
		
				}
			});

		  computer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
				builder.setMessage("       CHOOSE AN OPTION");
				builder.setCancelable(true); // cant leave the dialog
				builder.setPositiveButton("HARD",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int arg1) {
					
								Intent openhard =new Intent("com.xo.Hard");
								startActivity(openhard);
					
								
							}
						});
				builder.setNegativeButton("EASY",
						new DialogInterface.OnClickListener() {
                           
							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								
								Intent i = new Intent(getApplicationContext(), Easy.class);
								startActivity(i);
								
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		
		  human.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent openmultiplay =new Intent("com.xo.Multiplayer");
				startActivity(openmultiplay);
	
			}
		});
			
		  play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//start music
				
				songs=MediaPlayer.create(Menu.this, R.raw.song);
					if(songs.isPlaying()== false){
						
				        songs.start();
				        play.setClickable(false);
					}
			}
		});
		  stop.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					//stop music
					
				if(songs != null ){
						songs . stop();
						 play.setClickable(true);
					}
					
				}
				
			});
			
		  exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
				builder.setMessage("         Do You Wish To Exit");
				builder.setCancelable(true); // cant leave the dialog
				builder.setPositiveButton("EXIT",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								// TODO Auto-generated method stub
								if(songs != null)
								{
									songs.stop();
								}
								Menu.this.finish();
								finish();
								
								
								
							}
						});
			         	builder.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {
                           
							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								// TODO Auto-generated method stud
								dialog.cancel();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}

				
			
		});
		  
		  
		  
		  p.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent emailIntent= new Intent(android.content.Intent.ACTION_SEND);
					String aEmailList[] = {"codroid2@gmail.com"};
					emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,aEmailList);
					emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"**RATE XO GAME***");
					emailIntent.setType("text/plain");
					startActivity(emailIntent);
				}
			});
			h.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Uri uri =Uri .parse("https://www.facebook.com/a23labs");
					Intent intent = new Intent (Intent.ACTION_VIEW,uri);
					startActivity(intent);
					
				}
			});

	}



}
