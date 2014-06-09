package es.developer.projectwar.scenes.listeners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import es.developer.projectwar.R;
import es.developer.projectwar.scenes.GameSetScene;

public class GameMenuListener implements OnClickListener{
	
	private Context context;
	
	public GameMenuListener(Context context){
		this.context = context;
	}

	@Override
	public void onClick(View element) {
		Intent intent = new Intent(context, GameSetScene.class) ;
		Bundle bundle = new Bundle();
		
		switch(element.getId()){
		case R.id.online_button:
			//TODO save game mode variable
			break;
		case R.id.local_button:
			//TODO save game mode variable
			break;
		}
		intent.putExtras(bundle);
		context.startActivity(intent);
	}
}
