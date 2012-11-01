package com.example.unitdev2project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OptionMenu extends Activity{
	private Button lightButton = null;
    private Button doorButton = null;
    private Button coffeeButton= null;
    private Button musicButton= null;
    Typeface buttonFont;

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_menu);
    	buttonFont = Typeface.createFromAsset(getAssets(), "buttonFont.ttf");
        lightButton = (Button)findViewById(R.id.light);
        lightButton.setOnClickListener(listener);
        lightButton.setTypeface(buttonFont, Typeface.BOLD);
        doorButton = (Button)findViewById(R.id.door);
        doorButton.setOnClickListener(listener);
        doorButton.setTypeface(buttonFont, Typeface.BOLD);
        coffeeButton = (Button)findViewById(R.id.coffee);
        coffeeButton.setOnClickListener(listener);  
        coffeeButton.setTypeface(buttonFont, Typeface.BOLD);
        musicButton = (Button)findViewById(R.id.music);
        musicButton.setOnClickListener(listener);
        musicButton.setTypeface(buttonFont, Typeface.BOLD);
        
    }

	
    private OnClickListener listener=new OnClickListener()
	{
		
		public void onClick(View v)
		{
			Button btn=(Button)v;
			Intent intent=new Intent();
			switch (btn.getId())
			{
			case R.id.light:
				//intent.setClass(OptionMenu.this, LightMenu.class);
				intent.setClass(OptionMenu.this, LightControlMenu.class);
				startActivity(intent);
				break;
			case R.id.door:
				Toast.makeText(OptionMenu.this, "DoorMenu selected", Toast.LENGTH_SHORT).show();
//				intent.setClass(OptionMenu.this,DoorActivity.class);
//				startActivity(intent);
				break;
			case R.id.coffee:
				intent.setClass(OptionMenu.this, CoffeeMenu.class);
				startActivity(intent);
				break;
			case R.id.music:
				intent.setClass(OptionMenu.this, MusicMenu.class);
				startActivity(intent);
				break;
			}
		}
	

};

}
