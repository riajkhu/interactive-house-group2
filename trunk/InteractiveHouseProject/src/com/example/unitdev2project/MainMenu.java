package com.example.unitdev2project;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends Activity implements SensorEventListener {

	// declare all variables
	Typeface versionFont;
	Typeface buttonFont;
	Typeface textFont;
	Button loginButton;
	Button registerButton;
	Button quitButton;
	Button speechButton;
	TextView versionText;
	EditText passwordText;
	EditText loginText;
	CheckBox checkboxSave;
	final int check = 1111; // request code for speech
	String speechInput; // speech data
	String saveUsername; //string to save username
	String savePassword; //string to save password
	String loadUsername; //string to load username
	String loadPassword; //string to load password

	//public so other activities can use it, static so we dont change it
	public static String saveFile = "sharedpref"; //file of saved contents
	SharedPreferences sharedData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu); // set menu.xml as layout
		//retrieve and hold sharedpreference
		//mode 0 for private use
		sharedData = getSharedPreferences(saveFile, 0);
		// get sensor for shaking
		SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		// check if phone has accelerometer
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			// set Sensor to accelerometer
			Sensor sense = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			// register listener to sensor, delay so it doesent recognize small shakes
			sm.registerListener(this, sense, SensorManager.SENSOR_DELAY_NORMAL);
		}
		//get values from storage
		loadUsername = sharedData.getString("sharedUserString", "UserName");
		loadPassword = sharedData.getString("sharedPasswordString", "Password");
		Log.d(loadUsername, loadPassword);
		setup(); // initialize all menu items
		setupListeners(); // initialize clicklisteners on button

	
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	public void onSensorChanged(SensorEvent arg0) {
		// if phone is shaked, do this
	//	login();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	public void setup() {
		// setup fonts
		
		buttonFont = Typeface.createFromAsset(getAssets(), "buttonFont.ttf");
		textFont = Typeface.createFromAsset(getAssets(), "textFont.ttf");
		// setup buttons
		loginButton = (Button) findViewById(R.id.loginButton);
		registerButton = (Button) findViewById(R.id.registerButton);
		quitButton = (Button) findViewById(R.id.quitButton);
		speechButton = (Button) findViewById(R.id.speechButton);
		// setup text
		versionText = (TextView) findViewById(R.id.versionText);
		loginText = (EditText) findViewById(R.id.loginText);
		passwordText = (EditText) findViewById(R.id.passText);
		//setup checkbox
		checkboxSave = (CheckBox) findViewById(R.id.checkSave);
		// set fonts
		loginButton.setTypeface(buttonFont, Typeface.BOLD);
		registerButton.setTypeface(buttonFont, Typeface.BOLD);
		quitButton.setTypeface(buttonFont, Typeface.BOLD);
		passwordText.setTypeface(textFont);
		loginText.setTypeface(textFont);

		//set Text
		loginText.setText(loadUsername);
		passwordText.setText(loadPassword);
	}

	public void setupListeners() {

		// click method for login button
		loginButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				login();
			}
		});

		// click method for register button
		registerButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				register();
			}
		});

		// click method for quit button
		quitButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				quit();
			}
		});

		// click method for quit button
		speechButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				speech();
			}
		});
		
		checkboxSave.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (checkboxSave.isChecked()) {
					//get text data from editText save to string
					saveUsername = loginText.getText().toString();
					savePassword = passwordText.getText().toString();
					//edit sharedPreferences
					SharedPreferences.Editor editor = sharedData.edit();
					editor.putString("sharedUserString", saveUsername);
					editor.putString("sharedPassString", savePassword);
					editor.commit(); //save changes 
					Log.d("Value: " + saveUsername, savePassword);
				}
				else {
				}
			}
		});

	}

	public void speech() {
		//get android speech intent
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); 
		//set language model
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); 
		//prompt for speech from user
		i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please speak"); 
		// pass intent and requestcode to activity results
		startActivityForResult(i, check); 
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// check if code and vaiid result is ok
		if (requestCode == check && resultCode == RESULT_OK) { 
			//get speech data
			speechInput = data.getStringExtra(RecognizerIntent.EXTRA_RESULTS); 
			checkSpeech();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void checkSpeech() { // check speech input from user
		if (speechInput.equalsIgnoreCase("login")) {
			login();
		}
		if (speechInput.equalsIgnoreCase("register")) {
			register();
		}
		if (speechInput.equalsIgnoreCase("quit")) {
			quit();
		}
	}

	public void login() { // login method
		//start loginMenu activity
      //  Intent loginIntent = new Intent("android.intent.action.LoginMenu");
		//Intent loginIntent = new Intent("android.intent.action.DeviceMenu");
		Intent loginIntent = new Intent().setClass(MainMenu.this, New_option.class);
		startActivity(loginIntent);
	}

	public void register() { // register method
		//start Register activity
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Title");
		alert.setMessage("Message");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  String value = input.getText().toString();
		  if(value.equalsIgnoreCase("111111"))
		    startRegister();
		  else {
			  Toast.makeText(getApplicationContext(),
						"Wrong Pin code",
						Toast.LENGTH_LONG).show();
		  }}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});
	alert.show();
	    
 

	}
	public void startRegister() {
	       Intent registerIntent = new Intent("android.intent.action.Register");
			startActivity(registerIntent);	
	}
	public void quit() { // quit method
		finish(); //exit application
	}
}
