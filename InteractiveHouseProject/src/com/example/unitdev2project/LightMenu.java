package com.example.unitdev2project;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.SocketChannel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class LightMenu extends Activity {

	static SocketChannel client = null;
	private static final int PORT = 7890;
	//private static final String SERVER = "194.47.41.160";
	private static final String SERVER = "192.168.1.102";

	Socket echoSocket = null;
	PrintWriter out = null;
	BufferedReader in1 = null;

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
		setContentView(R.layout.light_menu);
		try {
			echoSocket = new Socket(SERVER, PORT);
			System.out.println("Connected to " + SERVER + ". Enter text:");

		} catch (IOException e) {

			System.out.println(e);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_device_menu, menu);
		// option Menu for switch Activity
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "Menu").setIcon(android.R.drawable.ic_menu_add);
		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "Light").setIcon(android.R.drawable.ic_menu_edit);
		menu.add(Menu.NONE, Menu.FIRST + 3, 3, "Door").setIcon(android.R.drawable.ic_menu_send);
		menu.add(Menu.NONE, Menu.FIRST + 4, 4, "Coffee Machine").setIcon(android.R.drawable.ic_menu_info_details);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			intent.setClass(LightMenu.this, OptionMenu.class);
			startActivity(intent);
			break;

		case Menu.FIRST + 2:
			intent.setClass(LightMenu.this, LightMenu.class);
			startActivity(intent);
			break;

		case Menu.FIRST + 3:
			intent.setClass(LightMenu.this, DoorMenu.class);
			startActivity(intent);
			break;

		case Menu.FIRST + 4:
			intent.setClass(LightMenu.this, CoffeeMenu.class);
			startActivity(intent);
			break;
		}
		return false;
	}

	public void off(View v) throws IOException {
		ImageView lightOff = (ImageView) findViewById(R.id.lightOffButton);
		lightOff.setVisibility(View.INVISIBLE);
		try {

			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in1 = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedInputStream bis = new BufferedInputStream(echoSocket.getInputStream());
			out.println("bulb:on");
			String check = in1.readLine();
			if (check != null && (check.equalsIgnoreCase("bulb is on") || check.equalsIgnoreCase("bulb remains unchanged"))) {
				ImageView liton = (ImageView) findViewById(R.id.lightOnImage);
				liton.setVisibility(View.VISIBLE);
				ImageView lightOn = (ImageView) findViewById(R.id.lightOnButton);
				lightOn.setVisibility(View.VISIBLE);
				ImageView litoff = (ImageView) findViewById(R.id.lightOffImage);
				litoff.setVisibility(View.INVISIBLE);
				Toast.makeText(LightMenu.this, check, 3000).show();
			} else {

				Toast.makeText(LightMenu.this, "no thing", 3000).show();

			}
		} catch (IOException e) {
		}

	}

	public void on(View v) throws IOException {
		ImageView lightOn = (ImageView) findViewById(R.id.lightOnButton);
		lightOn.setVisibility(View.INVISIBLE);
		try {

			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in1 = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedInputStream bis = new BufferedInputStream(echoSocket.getInputStream());
			out.println("bulb:off");

			String check = in1.readLine();
			if (check != null && (check.equalsIgnoreCase("bulb is off") || check.equalsIgnoreCase("bulb remains unchanged"))) {
				ImageView lightOff1 = (ImageView) findViewById(R.id.lightOffButton);
				lightOff1.setVisibility(View.VISIBLE);
				ImageView litoff = (ImageView) findViewById(R.id.lightOffImage);
				litoff.setVisibility(View.VISIBLE);
				ImageView liton = (ImageView) findViewById(R.id.lightOnImage);
				liton.setVisibility(View.INVISIBLE);
				Toast.makeText(LightMenu.this, check, 3000).show();
			} else {

				Toast.makeText(LightMenu.this, "no thing", 3000).show();

			}

		} catch (IOException e) {
		}

	}

}
