package com.example.unitdev2project;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class DeviceMenu extends Activity {

	// declare variables
	Button lightOffButton;
	Button lightOnButton;
	ImageView imageview;
	Typeface buttonFont;
	Typeface textFont;
	boolean buttonclicked = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_menu);

		setup(); // initialize all menu items
		setupListeners(); // initialize clicklisteners on button
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_device_menu, menu);
		return true;
	}

	public void setup() {
		// setup fonts
		buttonFont = Typeface.createFromAsset(getAssets(), "buttonFont.ttf");
		textFont = Typeface.createFromAsset(getAssets(), "textFont.ttf");
		// setup buttons and imageview
		lightOnButton = (Button) findViewById(R.id.lightOnButton);
		
		imageview = (ImageView) findViewById(R.id.deviceUnitImage);
		// set fonts
		lightOnButton.setBackgroundResource(R.drawable.buttonoff);
		lightOnButton.setTypeface(buttonFont, Typeface.BOLD);
	

	}

	public void setupListeners() {

		// click method for light on button
		lightOnButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Create bitmap from drawable so we can set it in imageview
				if (buttonclicked == true) {
					Bitmap lOff = BitmapFactory.decodeResource(
							v.getResources(), R.drawable.lightoff);
					buttonclicked = true;
					imageview.setImageBitmap(lOff);

					lightOnButton.setBackgroundResource(R.drawable.buttonoff);
					buttonclicked=false;
				} else {

					Bitmap lOn = BitmapFactory.decodeResource(v.getResources(),
							R.drawable.lighton);
					buttonclicked = true;
					imageview.setImageBitmap(lOn);

					lightOnButton.setBackgroundResource(R.drawable.buttonon);
				}

			}
		});

		// click method for light off button
		
	}
}
