package com.example.unitdev2project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;

public class Splash extends Activity {

	private Thread mSplashThread;
	private static boolean isMade = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);
		if (isMade == false) {
			final ImageView splashImageView = (ImageView) findViewById(R.id.SplashImageView);
			splashImageView.setBackgroundResource(R.drawable.splashanimate);
			final AnimationDrawable frameAnimation = (AnimationDrawable) splashImageView
					.getBackground();
			splashImageView.post(new Runnable() {
				public void run() {
					frameAnimation.start();
				}
			});

			final Splash sPlashScreen = this;

			mSplashThread = new Thread() {

				@Override
				public void run() {

					try {
						synchronized (this) {

							wait(4000);
						}
					} catch (InterruptedException ex) {
					}

					finish();

					// Run next activity
					Intent intent = new Intent();
					intent.setClass(sPlashScreen, MainMenu.class);
					startActivity(intent);
					

				}
			};

			mSplashThread.start();
			isMade = true;
		}
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent evt) {
		if (evt.getAction() == MotionEvent.ACTION_DOWN) {
			synchronized (mSplashThread) {
				mSplashThread.notifyAll();
			}
		}
		return true;
	}

}