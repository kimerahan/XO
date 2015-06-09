package com.a23labs.xo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.xo.R;

public class Easy  extends Activity {
	private int  myRequest=9199343;
	/*private static final String PERMISSIONS = "publish_actions";
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private boolean pendingPublishReauthorization = false;
	private UiLifecycleHelper uiHelper;
	*/protected static final int LENGTH = 80000000;
	Button[][] button = new Button[3][3];
	boolean gameContinues = true;
	int[][] game = new int[3][3];
	int row0, col0;
	TextView result;
	ImageView result2, line1, line2, line3, line4;
	ImageButton p2,p1;
	Button newgame2;
	int computerTurn;
	int randomRow, randomCol,count,counter=1;
	int total=0;
	
	ImageView player1image;
	Animation anim;
	ImageButton fbshare;
	int x;
	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.playfield);
		
         p2 =(ImageButton)findViewById(R.id.imageButton1);
         p1 = (ImageButton)findViewById(R.id.player1image);
         fbshare = (ImageButton)findViewById(R.id.imageButton2);
        
         mp = MediaPlayer.create(Easy.this, R.raw.beep);
         
         computerTurn = 1;
		line1 = (ImageView)findViewById(R.id.ImageView1);   //the black lines positioned 
		line1.setBackgroundColor(Color.rgb(0, 0, 0));

		line2 = (ImageView)findViewById(R.id.ImageView2);
		line2.setBackgroundColor(Color.rgb(0, 0, 0));

		line3 = (ImageView)findViewById(R.id.ImageView3);
		line3.setBackgroundColor(Color.rgb(0, 0, 0));

		line4 = (ImageView)findViewById(R.id.ImageView4);
		line4.setBackgroundColor(Color.rgb(0, 0, 0));

		button[0][0] = (Button) findViewById(R.id.b1); //box 1 row 1
		button[0][1] = (Button) findViewById(R.id.b2); //box 2 row 1
		button[0][2] = (Button) findViewById(R.id.b3);  ///box 3 row 1
		button[1][0] = (Button) findViewById(R.id.b4);  //box 1 row 2 
		button[1][1] = (Button) findViewById(R.id.button1); //box 2 row 2
		button[1][2] = (Button) findViewById(R.id.b6);  // box 3 row 2
		button[2][0] = (Button) findViewById(R.id.b7);  //box 1 row 3
		button[2][1] = (Button) findViewById(R.id.b8);  //box 2 row 3 
		button[2][2] = (Button) findViewById(R.id.b9);  //box 3 row 3
		
		
		result = (TextView) findViewById(R.id.result);
		result2 = (ImageView)findViewById(R.id.imageView);
		p1.setVisibility(View.VISIBLE);
		p2.setVisibility(View.VISIBLE);
		p2.setImageResource(R.drawable.android);
		anim = AnimationUtils.loadAnimation(this, R.anim.buttonanim);
		p1.setAnimation(anim);
		p1.startAnimation(anim);
		
		/*uiHelper= new UiLifecycleHelper(this,null);
		uiHelper.onCreate(savedInstanceState);
		*/fbshare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//publishStory(); 
				String mySharedLink = "I Have "+count+"% IN " +"["+counter++ +"]"+" ATTEMPTS in XO game"+ "Try it too\n "+"https://play.google.com/store/apps/details?id=com.xo";
				String myAppId = "919934304704706";

				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				shareIntent.setType("text/plain").putExtra("com.facebook.platform.extra.APPLICATION_ID", myAppId);
				shareIntent.putExtra(Intent.EXTRA_TEXT, mySharedLink);

				// Include your Facebook App Id for attribution
			//	shareIntent.putExtra("com.facebook.platform.extra.APPLICATION_ID", myAppId);

				startActivityForResult(Intent.createChooser(shareIntent, "share Points"),myRequest);
				
							}
			
		});
		
       	newgame2 = (Button)findViewById(R.id.imageButton);
		newgame2.setOnClickListener(new OnClickListener() {

	
			@Override
			public void onClick(View arg0) {
				counter++;
				// TODO Auto-generated method stub

				int row, col;
				for (row = 0; row < button.length; row++) {
					for (col = 0; col < button[row].length; col++) {
						button[row][col].setBackgroundResource(R.drawable.blanksquare);
						button[row][col].setClickable(true);
						game[row][col] = 0;       //set all the grid boxes to 0 (empty)
						p1.setImageResource(R.drawable.player1);
						p2.setImageResource(R.drawable.android);
						p1.setVisibility(View.VISIBLE);
						p2.setVisibility(View.VISIBLE);
					}


				}
				result2.setBackgroundResource(R.drawable.blank);
				gameContinues = true;  //let the game continue
				computerTurn = 1;
				
				
				//when you press newgame it gets saved and the counter increments
					total=total+count;
					result.setText("" +count);
					//Context context = getApplicationContext();
					
					if(counter %4 ==0){
						Context context2 = getApplicationContext();
						CharSequence text2 = "GAMES SAVED";
						int duration1 = Toast.LENGTH_SHORT;

						Toast toast2 = Toast.makeText(context2, text2, duration1);
						toast2.getView().setBackgroundColor(Color.TRANSPARENT);
						toast2.show();
					}
					
			}
						
		});

		

		ButtonHandler handler = new ButtonHandler();
		int row, col;
		for (row = 0; row < button.length; row++) {
			for (col = 0; col < button[row].length; col++) {
				button[row][col].setOnClickListener(handler);   //let all the buttons respond to the Handler method
				mp.start();
			}
		}

	}
	/*private void onSessionStateChange(Session session,SessionState state,Exception exception){
		if(state.isOpened()){
			fbshare.setVisibility(View.VISIBLE);
			if(pendingPublishReauthorization && state.equals(SessionState.OPENED_TOKEN_UPDATED)){
				pendingPublishReauthorization = false;
				publishStory();
			}
		}else if (state.isClosed()){
			fbshare.setVisibility(View.INVISIBLE);
		}
	}
	
	private void publishStory(){
		Session session = Session.getActiveSession();
		if(session!=null){
			List<String>permissions = session.getPermissions();
			if(hasPublishPermissions(permissions)){
				pendingPublishReauthorization= true;
				Session.NewPermissionsRequest newPermissionsRequest = new Session
	                    .NewPermissionsRequest(this, PERMISSIONS);
				session.requestNewPublishPermissions(newPermissionsRequest);
				return;
			}
		}
		Bundle postParams= new Bundle();
		postParams.putString("XO Points",count+"% IN " +"["+counter++ +"]"+" ATTEMPTS ");
		postParams.putString(" XO link","https://play.google.com/store/apps/details?id=com.xo");
		
		Request.Callback callback = new Request.Callback() {
			
			@Override
			public void onCompleted(Response response) {
			  JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
			  String postId = null;
			  try{
				  postId = graphResponse.getString("id");
			  }catch(JSONException e){
				  Log.i("facebookpublishStory","JSON error"+e.getMessage());
			  }
				FacebookRequestError error = response.getError();
				if(error != null){
					Toast.makeText(getApplicationContext(),error.getErrorMessage(),Toast.LENGTH_SHORT).show();
					
				}else{
					Toast.makeText(getApplicationContext(), postId, Toast.LENGTH_SHORT).show();
				}
			}
		};
		Request request = new Request (session,"me/feed",postParams,HttpMethod.POST,callback);
		RequestAsyncTask task = new RequestAsyncTask(request);
		task.execute();
	}
	private boolean isSubjectOf(Collection<String> subset,Collection<String> superset){
		for(String string : subset){
			if(!superset.contains(string)){
				return false;
			}
		}
		return true;
	}
	private boolean hasPublishPermissions(List<String> permissions2) {
		if(permissions2.contains(PERMISSIONS)){
			return true;
		}else {
		return false;
		}
	}
	public void onSavedInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putBoolean(PENDING_PUBLISH_KEY,pendingPublishReauthorization);
		uiHelper.onSaveInstanceState(outState);
	}
	*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean checkWin(int w) {
		int row, col, counter = 0;
		for (row = 0; row < game.length; row++) {
			for (col = 0; col < game[row].length; col++) {
				if (game[row][col] == w) {
					counter++;  //run through to check if there is a combination
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
					counterW++; //if you find the first same character
				}
				if(game[row][col] == 0)
				{
					counter0++;
					row0 = row;
					col0 = col;
				}
			}
			if(counterW == 2 && counter0 == 1){//if you find the second same character
				return true;
			}
			counterW = 0;
			counter0 = 0;
		}

		for(col = 0; col < 3; col++){  //if you find the third same character
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
				button[row][col].setClickable(false);  //set buttons unable to be clicked 
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

	private class ButtonHandler implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			int row, col;
			boolean jumpOut = false;
			if (gameContinues == true){     //User move
				for (row = 0; row < button.length; row++) {
					for (col = 0; col < button[row].length; col++) {
						if (button[row][col].isPressed()) {
							button[row][col]          //when someone clicks show an x
									.setBackgroundResource(R.drawable.xi);
							button[row][col].setClickable(false);
							game[row][col] = 1;  //assign the box a 1

							if (checkWin(1)) {
								//counter++;
								count+=5;
								result.setText(""+count);
								
								result2.setBackgroundResource(R.drawable.youwin);
								gameContinues = false;
								Context context = getApplicationContext();
								CharSequence text = "**CONGS**";
								int duration = Toast.LENGTH_SHORT;

								Toast toast = Toast.makeText(context, text, duration);
								toast.getView().setBackgroundColor(Color.TRANSPARENT);
								toast.show();
								
								
								buttonsUnclickable();
								p1.setImageResource(R.drawable.player1);
								p2.setImageResource(R.drawable.android);
								p1.setVisibility(View.VISIBLE);
								p2.setVisibility(View.INVISIBLE);
								
																	
							}

							
							else if(checkDraw()){
								count=count+1;
								result.setText("" +count);
								//result.setText("Game Drawn!");
								result2.setBackgroundResource(R.drawable.gamedrawn);
								gameContinues = false;
								buttonsUnclickable();
								
								p1.setImageResource(R.drawable.player1);
								p2.setImageResource(R.drawable.android);
								p1.setVisibility(View.VISIBLE);
								p2.setVisibility(View.VISIBLE);
							}
							else{
								
								p2.setImageResource(R.drawable.android);
								p1.setVisibility(View.VISIBLE);
								p2.setVisibility(View.VISIBLE);
								//p2.startAnimation(anim);
								p1.startAnimation(anim);
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
					button[row0][col0].setClickable(false);
				}
				else if(straightLine(1)){ //Avoid loss
					game[row0][col0] = 2;
					button[row0][col0].setBackgroundResource(R.drawable.oi);
					button[row0][col0].setClickable(false);
				}
				else{ //Tactical move
					if(computerTurn == 1 && game[1][1] == 0){
						button[1][1].setBackgroundResource(R.drawable.oi);
						game[1][1] = 2;
						button[1][1].setClickable(false);
					}
					else{
						for(col = 2; col >= 0; col--){    
							for(row = 0; row < 3; row++){
								if(game[row][col] == 0){
									button[row][col].setBackgroundResource(R.drawable.oi);
									game[row][col] = 2;
									button[row][col].setClickable(false);
									jumpOut = true;
									break;
								}
							}
							if(jumpOut == true){
								break;
							}
						}
					}
				}
				computerTurn++;
				if(checkWin(2)){
					count-=1;
					result.setText(""+count);
					result2.setBackgroundResource(R.drawable.computerwins);
					//player1image.setVisibility(View.VISIBLE);
					p2.setVisibility(View.VISIBLE);
					p1.setImageResource(R.drawable.player1);
					p2.setImageResource(R.drawable.android);
					p1.setVisibility(View.INVISIBLE);
					p2.setVisibility(View.VISIBLE);
					p2.startAnimation(anim);
					Context context = getApplicationContext();
					CharSequence text = "OOPS!!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.getView().setBackgroundColor(Color.TRANSPARENT);
					toast.show();
					
					gameContinues = false;
					buttonsUnclickable();
				}
				else if(checkDraw()){
					result2.setBackgroundResource(R.drawable.gamedrawn);
					gameContinues = false;
					buttonsUnclickable();
					p1.setImageResource(R.drawable.player1);
					p2.setImageResource(R.drawable.android);
					//player1image.startAnimation(anim);
					p1.setVisibility(View.VISIBLE);
					p2.setVisibility(View.VISIBLE);
					p2.startAnimation(anim);
				}
				else{
					p1.setImageResource(R.drawable.player1);
					p2.setImageResource(R.drawable.android);
					p1.setVisibility(View.VISIBLE);
					p2.setVisibility(View.INVISIBLE);
					p2.startAnimation(anim);
				}
			}
		}
	
	}


}
