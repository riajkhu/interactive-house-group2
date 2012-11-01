package com.example.unitdev2project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MusicMenu extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_menu);
	}
	
	public void play(View v) {
		ImageView musicOffImage =(ImageView) findViewById(R.id.musicOffImage);
		musicOffImage.setVisibility(View.INVISIBLE);
		ImageView musicOnImage =(ImageView) findViewById(R.id.musicOnImage);
		musicOnImage.setVisibility(View.VISIBLE);
	}

	public void stop(View v) {
		ImageView musicOffImage =(ImageView) findViewById(R.id.musicOffImage);
		musicOffImage.setVisibility(View.VISIBLE);
		ImageView musicOnImage =(ImageView) findViewById(R.id.musicOnImage);
		musicOnImage.setVisibility(View.INVISIBLE);
	}

	public void up(View v) {
		Toast.makeText(MusicMenu.this, "Up pressed", Toast.LENGTH_SHORT).show();
	}

	public void down(View v) {
		Toast.makeText(MusicMenu.this, "Down pressed", Toast.LENGTH_SHORT).show();

	}
	public void previous(View v){
		Toast.makeText(MusicMenu.this, "previous pressed", Toast.LENGTH_SHORT).show();
	}
	public void next(View v){
		Toast.makeText(MusicMenu.this, "next pressed", Toast.LENGTH_SHORT).show();
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
				intent.setClass(MusicMenu.this, OptionMenu.class);
				startActivity(intent);
				break;
			case Menu.FIRST + 2:
				intent.setClass(MusicMenu.this, LightMenu.class);
				startActivity(intent);
				break;
			}
			return false;
		}
}
