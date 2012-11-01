package com.example.unitdev2project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LightControlMenu extends Activity {
	private Button indoorButton = null;
    private Button outdoorButton = null;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightcontrol_menu);
        indoorButton = (Button)findViewById(R.id.indoorButton);
        indoorButton.setOnClickListener(listener);
        outdoorButton =(Button)findViewById(R.id.outdoorButton);
        outdoorButton.setOnClickListener(listener);
    }
        private OnClickListener listener=new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button btn=(Button)v;
				Intent intent=new Intent();
				switch (btn.getId())
				{
				case R.id.indoorButton:
					intent.setClass(LightControlMenu.this, LightMenu.class);
					startActivity(intent);
					break;
				case R.id.outdoorButton:
					intent.setClass(LightControlMenu.this, LightMenu.class);
					startActivity(intent);
					break;			
				}
				
				
			}

    };
         
}
