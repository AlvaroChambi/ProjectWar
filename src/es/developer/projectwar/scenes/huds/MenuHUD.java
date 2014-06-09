package es.developer.projectwar.scenes.huds;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import es.developer.projectwar.Game;
import es.developer.projectwar.R;
import es.developer.projectwar.controllers.GameController;
import es.developer.projectwar.drawers.listeners.IPlayerEventsListener;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;
import es.developer.projectwar.scenes.adapters.MenuHUDAdapter;
import es.developer.projectwar.scenes.listeners.MenuHUDListener;
import es.developer.projectwar.utils.UpdateInput;
import es.developer.projectwar.views.IObserver;

public class MenuHUD extends Fragment implements IGameHUD, IObserver{
	private static final String TAG = MenuHUD.class.getCanonicalName();
	private View rootView;
	private Button finish;
	private MenuHUDListener listener;
	private TextView playerName, unitName, dayText;
	private ListView commandsList;
	private Game game;
	private GameController gameController;

	public MenuHUD(){
		listener = new MenuHUDListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.game_hud_layout, container);	
		finish = (Button) rootView.findViewById(R.id.finish_command);
		playerName = (TextView) rootView.findViewById(R.id.actual_player_name);
		unitName = (TextView) rootView.findViewById(R.id.selected_unit_name);
		dayText = (TextView) rootView.findViewById(R.id.game_day_text);
		commandsList = (ListView) rootView.findViewById(R.id.commands_list);
		commandsList.setOnItemClickListener(listener);
		finish.setOnClickListener(listener);
		return rootView;
	}

	public void updateView(){	
		/*Need to post UI changes in the main thread, but i don't know why is that being executed in another thread,
		maybe some opengl shit*/
		getActivity().runOnUiThread(new Runnable(){
			PlayerModel actualPlayer = game.getActualPlayer();
			@Override
			public void run() {
				String day = getString(R.string.day_text) + String.valueOf(game.getDay());
				Log.i(TAG, "dayText: " + day);
				dayText.setText(day);
				playerName.setText(actualPlayer.getName());
				UnitModel unit = actualPlayer.getSelectedUnit();
				//Update unit info related just if the player has some unit already selected
				if(unit != null){
					//TODO Just for show something in the HUD info, get unit type info from the unit model(always showing Soldier text)
					unitName.setText("Soldier " + actualPlayer.getSelectedUnit().getId());
					//Updates the commands that the adapter click listener have to handle, different units has different commands
					listener.setCommands(actualPlayer.getSelectedUnit().getCommands());
					commandsList.setAdapter(new MenuHUDAdapter(MenuHUD.this.getActivity(), 
							actualPlayer.getSelectedUnit().getCommands()));
				}
			}
		});	
	}

	@Override
	public void registerEventsHandler(IPlayerEventsListener handler) {
		listener.setEventsHandler(handler);
	}

	@Override
	public void onUpdateNotification(UpdateInput input) {
		Log.i(TAG,"update notification");
		switch(input){
		case POSITION_CHANGED:
			break;
		case UNIT_SELECTED:
			this.updateView();
			break;
		case UNIT_UNSELECTED:
			break;
		default:
			break;
		}
	}	
	
	public void registerGameController(GameController gameController){
		this.gameController = gameController;
		this.listener.setGameController(this.gameController);
	}

	public void registerGame(Game game){
		this.game = game;
		//Update hud info when the game is properly registered
		this.updateView();
	}
}
