package es.developer.projectwar.scenes;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import es.developer.projectwar.R;
import es.developer.projectwar.map.MapModel;
import es.developer.projectwar.scenes.adapters.MapListAdapter;
import es.developer.projectwar.scenes.listeners.GameSetListener;
import es.developer.projectwar.utils.parsers.MapsParser;

public class GameSetScene extends Activity{
	//private final static String TAG = GameSettingsScene.class.getCanonicalName();
	private Button goButton;
	private GameSetListener listener;
	private ListView mapListView;
	private MapListAdapter adapter;
	private MapsParser parser;
	private TextView mapName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamesettings_layout);
		parser = new MapsParser(this);
		//Retrieve map data from xml settings definition
		List<MapModel> mapList = parser.getMapsList();
 		listener = new GameSetListener(this, mapList);
		adapter = new MapListAdapter(this, mapList);
		
		goButton = (Button)findViewById(R.id.settings_go_button);
		mapName = (TextView)findViewById(R.id.settings_map_name);
		mapListView = (ListView)findViewById(R.id.settings_map_list);
		goButton.setOnClickListener(listener);
		mapListView.setOnItemClickListener(listener);
		mapListView.setAdapter(adapter);
	}
	
	public void updateView(MapModel map){
		mapName.setText(map.getName());
	}
}
