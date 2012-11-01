package com.example.unitdev2project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

	Typeface versionFont;
	Typeface buttonFont;
	Typeface textFont;
	Button confirmButton;
	Button backButton;
	TextView userNameText;
	TextView passwordText;
	EditText passwordField;
	EditText userNameField;
	String userText = null;
	String passText = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		setup();
		setupListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	public void setup() {
		// setup fonts
		versionFont = Typeface.createFromAsset(getAssets(), "versionFont.ttf");
		buttonFont = Typeface.createFromAsset(getAssets(), "buttonFont.ttf");
		textFont = Typeface.createFromAsset(getAssets(), "textFont.ttf");
		// setup buttons
		confirmButton = (Button) findViewById(R.id.confirmButton);
		backButton = (Button) findViewById(R.id.backButton);
		// setup text
		userNameText = (TextView) findViewById(R.id.userName);
		passwordText = (TextView) findViewById(R.id.password);
		userNameField = (EditText) findViewById(R.id.userNameField);
		passwordField = (EditText) findViewById(R.id.passwordField);
		// set fonts
		confirmButton.setTypeface(buttonFont, Typeface.BOLD);
		backButton.setTypeface(buttonFont, Typeface.BOLD);
		passwordText.setTypeface(textFont);
		userNameText.setTypeface(textFont);
	}

	public void setupListeners() {

		confirmButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//check if the username/password is empty
				if (userNameField.getText().length() >= 3 && passwordField.getText().length() >= 3) {
					userText = userNameField.getText().toString();
					passText = passwordField.getText().toString();
					Intent mainMenuIntent = new Intent(
							"android.intent.action.MainMenu");
					startActivity(mainMenuIntent);

				} else {
					Toast.makeText(getApplicationContext(),
							"Please input a Username and password that is more than 3 characters long",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mainMenuIntent = new Intent(
						"android.intent.action.MainMenu");
				startActivity(mainMenuIntent);
			}
		});
	}
}
