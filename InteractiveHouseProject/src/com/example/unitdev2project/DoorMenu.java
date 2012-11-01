package com.example.unitdev2project;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/*
 * This class is use for door control. Download pictures from server by using NIO method.
 */
public class DoorMenu extends Activity{

	static SocketChannel client = null;
	private static final int PORT = 8000;
	private static final String SERVER = "194.47.32.171";

	Socket echoSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	private Button dlButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.door_menu);

		try {
			echoSocket = new Socket(SERVER, PORT);
			System.out.println("Connected to " + SERVER + ". Enter text:");

		} catch (IOException e) {

			System.out.println(e);

		}
	}

	public void heyyy(View v) {
		Toast.makeText(DoorMenu.this, "mohan", 1000).show();
		Log.i("hmmm", "onclick");

		try {
			Toast.makeText(DoorMenu.this, "hhhhhhhhhh", 2000).show();
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedInputStream bis = new BufferedInputStream(echoSocket.getInputStream());
			String ss = "download button pressed";
			out.println("dddd");
			Toast.makeText(DoorMenu.this, "channel vl connected", 4000).show();
			String a = in.readLine();
			for (int i = 0; i < 20000000; i++) {
			}

			if (a.equalsIgnoreCase("dddd"));

			Toast.makeText(DoorMenu.this, a, Toast.LENGTH_LONG).show();

			try {
				client = SocketChannel.open(new InetSocketAddress("194.47.32.171", 8989));
				
				Toast.makeText(DoorMenu.this, "hi", Toast.LENGTH_LONG).show();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Toast.makeText(DoorMenu.this, a, Toast.LENGTH_LONG).show();
		} catch (IOException e) {
		}

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		FileOutputStream fout = null;

		try {
			fout = openFileOutput("hello.jpg", Context.MODE_WORLD_WRITEABLE);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if (fout == null) {
			Log.i("hhhh", "jjjjjj");
			Toast.makeText(DoorMenu.this, "aaaaaaaaa", Toast.LENGTH_LONG).show();

		}
		FileChannel fc = fout.getChannel();

		Toast.makeText(DoorMenu.this, "channel connected", Toast.LENGTH_LONG).show();
		int i = 0;
		int n = 0;
		int loop = 0;
		try {
			while (i != -1) {
				buffer.clear();
				i = client.read(buffer);

				System.out.println("this");

				if (i == -1) {
					break;
				}
				buffer.flip();
				if (buffer.limit() < buffer.capacity()) {
				}

				fc.write(buffer);

				n = n + i;
				loop++;
				if (loop < 20) {
				}

			}
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	// ********************************optionMenu for switch Activities*************************************
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			menu.add(Menu.NONE, Menu.FIRST + 1, 1, "Menu").setIcon(android.R.drawable.ic_menu_add);
			menu.add(Menu.NONE, Menu.FIRST + 2, 2, "Light").setIcon(android.R.drawable.ic_menu_edit);
			menu.add(Menu.NONE, Menu.FIRST + 3, 3, "Door").setIcon(android.R.drawable.ic_menu_send);
			menu.add(Menu.NONE, Menu.FIRST + 4, 4, "Coffee Machine").setIcon(android.R.drawable.ic_menu_info_details);
			// menu.add(Menu.NONE, Menu.FIRST + 5, 5,
			// "sth4").setIcon(android.R.drawable.ic_menu_delete);
			// menu.add(Menu.NONE, Menu.FIRST + 6, 6,
			// "sth5").setIcon(android.R.drawable.ic_menu_help);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			Intent intent = new Intent();
			switch (item.getItemId()) {
			case Menu.FIRST + 1:
				intent.setClass(DoorMenu.this, OptionMenu.class);
				startActivity(intent);
				break;
			case Menu.FIRST + 2:
				intent.setClass(DoorMenu.this, LightMenu.class);
				startActivity(intent);
				break;
			// case Menu.FIRST + 3:
			// intent.setClass(CoffeeMenu.this,DoorActivity.class);
			// //Toast.makeText(this, "Door seclected", Toast.LENGTH_LONG).show();
			// break;
			// case Menu.FIRST + 4:
			// intent.setClass(CoffeeMenu.this,CoffeeActivity.class);
			// //Toast.makeText(this, "Coffee Machine seclected",
			// Toast.LENGTH_LONG).show();
			// break;
			// case Menu.FIRST + 5:
			//
			// Toast.makeText(this, "sth4 seclected", Toast.LENGTH_LONG).show();
			// break;
			//
			// case Menu.FIRST + 6:
			//
			// Toast.makeText(this, "sth5 seclected", Toast.LENGTH_LONG).show();
			// break;
			}
			return false;
		}
}
