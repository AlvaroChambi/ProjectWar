package es.developer.projectwar.controllers.states.player;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.PlayerModel;

public class NothingSelectedState implements PlayerState{
	private static final String TAG = NothingSelectedState.class.getCanonicalName();
	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel, 
			TMXTile position, int id, Command command){
		switch(input){
		case BuildingTouched:
//			playerModel.setState(new BuildingSelectedState(id));
//			playerModel.getState().enter(playerModel, position);
			break;
		case MapTouched:
			playerModel.setPosition(position);
			break;
		case UnitTouched:	
			playerModel.setState(new UnitSelectedState(id));
			playerModel.getState().enter(playerModel, position);
			break;
		default:
			break;		
		}
	}

	@Override
	public void enter(PlayerModel player, TMXTile position) {
		Log.i(TAG, "enter");
		player.setPosition(position);
		player.getSelectedUnit().setCommands(Command.NO_COMMANDS);
	}
}
