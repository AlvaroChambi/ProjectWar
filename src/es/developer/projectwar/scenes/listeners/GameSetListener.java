package es.developer.projectwar.scenes.listeners;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import es.developer.projectwar.R;
import es.developer.projectwar.map.MapModel;
import es.developer.projectwar.scenes.GameScene;
import es.developer.projectwar.scenes.GameSetScene;

public class GameSetListener implements OnClickListener, OnItemClickListener{

	private GameSetScene activity;
	private List<MapModel> mapList;
	private Bundle bundle;

	public GameSetListener(GameSetScene activity, List<MapModel> mapList){
		this.activity = activity;
		this.mapList = mapList;
		bundle = new Bundle();
	}
	@Override
	public void onClick(View element) {
		switch(element.getId()){
		case R.id.settings_go_button:
			this.nextActivity();
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		MapModel map = mapList.get(position);
		bundle.putString("MapName", map.getName());
		activity.updateView(map);
	}

	private void nextActivity(){
		if(bundle.containsKey("MapName")){
			Intent intent = new Intent(activity, GameScene.class);
			intent.putExtras(bundle);
			activity.startActivity(intent);	
		}
	}
}
