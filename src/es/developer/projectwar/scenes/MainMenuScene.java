package es.developer.projectwar.scenes;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import es.developer.projectwar.R;
import es.developer.projectwar.scenes.listeners.GameMenuListener;

public class MainMenuScene extends Activity{
	
	private Button onlineButton;
	private Button localButton;
	private GameMenuListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_menu_layout);
		
		onlineButton = (Button) findViewById(R.id.online_button);
		localButton = (Button) findViewById(R.id.local_button);
		listener = new GameMenuListener(this);
		
		onlineButton.setOnClickListener(listener);
		localButton.setOnClickListener(listener);
	}
}
