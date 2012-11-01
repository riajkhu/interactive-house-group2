package com.example.unitdev2project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class CoffeeMenu extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee_menu);
	}

	public void off(View v) {
		ImageView pCoffeeOff = (ImageView) findViewById(R.id.pcoffeeOff);
		pCoffeeOff.setVisibility(View.INVISIBLE);
		ImageView pCoffeeOn = (ImageView) findViewById(R.id.pcoffeeOn);
		pCoffeeOn.setVisibility(View.VISIBLE);
		ImageView bCoffeeOff = (ImageView) findViewById(R.id.bcoffeeOff);
		bCoffeeOff.setVisibility(View.INVISIBLE);
		ImageView bCoffeeOn = (ImageView) findViewById(R.id.bcoffeeOn);
		bCoffeeOn.setVisibility(View.VISIBLE);
	}

	public void on(View v) {
		ImageView pCoffeeOff = (ImageView) findViewById(R.id.pcoffeeOff);
		pCoffeeOff.setVisibility(View.VISIBLE);
		ImageView pCoffeeOn = (ImageView) findViewById(R.id.pcoffeeOn);
		pCoffeeOn.setVisibility(View.INVISIBLE);
		ImageView bCoffeeOff = (ImageView) findViewById(R.id.bcoffeeOff);
		bCoffeeOff.setVisibility(View.VISIBLE);
		ImageView bCoffeeOn = (ImageView) findViewById(R.id.bcoffeeOn);
		bCoffeeOn.setVisibility(View.INVISIBLE);
	}

	// ********************************optionMenu for switch Activities*************************************
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "Menu").setIcon(android.R.drawable.ic_menu_add);
		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "Light").setIcon(android.R.drawable.ic_menu_edit);
		menu.add(Menu.NONE, Menu.FIRST + 3, 3, "Door").setIcon(android.R.drawable.ic_menu_send);
		menu.add(Menu.NONE, Menu.FIRST + 4, 4, "Coffee Machine").setIcon(android.R.drawable.ic_menu_info_details);
		// menu.add(Menu.NONE, Menu.FIRST + 5, 5, "sth4").setIcon(android.R.drawable.ic_menu_delete);
		// menu.add(Menu.NONE, Menu.FIRST + 6, 6,
		// "sth5").setIcon(android.R.drawable.ic_menu_help);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			intent.setClass(CoffeeMenu.this, OptionMenu.class);
			startActivity(intent);
			break;
		case Menu.FIRST + 2:
			intent.setClass(CoffeeMenu.this, LightMenu.class);
			startActivity(intent);
			// Toast.makeText(this, "Light seclected",
			// Toast.LENGTH_LONG).show();
			break;
		}
		return false;
	}

}
