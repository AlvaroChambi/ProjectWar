package es.developer.projectwar.scenes.huds;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import es.developer.projectwar.R;
import es.developer.projectwar.controllers.GameCommand;
import es.developer.projectwar.controllers.IGameCommandListener;
import es.developer.projectwar.controllers.IPlayerCommandListener;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;
import es.developer.projectwar.scenes.adapters.MenuHUDAdapter;
import es.developer.projectwar.utils.UpdateInput;

public class AndroidNativeHUD extends FragmentGameHUD implements IPlayerCommandHUD, IGameCommandHUD{
	private static final String TAG = AndroidNativeHUD.class.getCanonicalName();
	
	private View rootView;
	private Button finish;
	private AndroidHUDListener listener;
	private TextView playerName, unitName, dayText;
	//Only for debug purpose
	private TextView playerState, unitState;
	private ListView commandsList;
	
	public AndroidNativeHUD(){
		listener = new AndroidHUDListener();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.game_hud_layout, container);	
		finish = (Button) rootView.findViewById(R.id.finish_command);
		playerName = (TextView) rootView.findViewById(R.id.actual_player_name);
		unitName = (TextView) rootView.findViewById(R.id.selected_unit_name);
		dayText = (TextView) rootView.findViewById(R.id.game_day_text);
		playerState = (TextView) rootView.findViewById(R.id.player_state);
		unitState = (TextView) rootView.findViewById(R.id.unit_state);
		commandsList = (ListView) rootView.findViewById(R.id.commands_list);
		commandsList.setOnItemClickListener(listener);
		finish.setOnClickListener(listener);
		return rootView;
	}
	
	@Override
	public void updateView() {
		/*Need to post UI changes in the main thread, but i don't know why is that being executed in another thread,
		maybe some opengl shit*/
		getActivity().runOnUiThread(new Runnable(){
			PlayerModel actualPlayer = game.getActualPlayer();
			@Override
			public void run() {
				String day = getString(R.string.day_text) + String.valueOf(game.getDay());
				dayText.setText(day);
				playerName.setText(actualPlayer.getName());
				UnitModel unit = actualPlayer.getSelectedUnit();
				//Update unit info related just if the player has some unit already selected
				if(unit != null){
					//TODO Just for show something in the HUD info, get unit type info from the unit model(always showing Soldier text)
					unitName.setText("Soldier " + actualPlayer.getSelectedUnit().getId());
					//Updates the commands that the adapter click listener have to handle, different units has different commands
					listener.setCommands(actualPlayer.getSelectedUnit().getCommands());
					commandsList.setAdapter(new MenuHUDAdapter(AndroidNativeHUD.this.getActivity(), 
							actualPlayer.getSelectedUnit().getCommands()));
					//Only for debug purpose
					playerState.setText(actualPlayer.getState().getName());
					unitState.setText(unit.getState().getName());
				}
			}
		});	
	}
	
	@Override
	public void onUpdateNotification(UpdateInput input) {
		Log.i(TAG, "updateNotification " + input.toString());
		this.updateView();
		switch(input){
		/*Whenever a unit position change we send a notification to the game controller 
		 *to update if it has some unit in his attack range
		 */
		case POSITION_CHANGED:
			listener.notifyGameCommandListener(GameCommand.UpdateUnit);
			break;
		default:
			break;	
		}
	}
	
	@Override
	public void registerGameCommandListener(IGameCommandListener listener) {
		this.listener.setGameCommandListener(listener);
		
	}

	@Override
	public void registerPlayerCommandListener(IPlayerCommandListener listener) {
		this.listener.addListener(listener);
	}
}
