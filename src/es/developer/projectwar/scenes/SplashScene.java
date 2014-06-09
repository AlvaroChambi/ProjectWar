package es.developer.projectwar.scenes;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import es.developer.projectwar.R;

public class SplashScene extends Activity{
	private final int SPLASH_DISPLAY_LENGTH = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_splash_layout);

		Handler handler = new Handler();
		handler.postDelayed(getRunnableStartApp(), SPLASH_DISPLAY_LENGTH);
	}

	private Runnable getRunnableStartApp() {
		Runnable runnable = new Runnable(){
			public void run() {
				Intent intent = new Intent(SplashScene.this,MainMenuScene.class);
				startActivity(intent);
			}
		};
		return runnable;
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}	
}
