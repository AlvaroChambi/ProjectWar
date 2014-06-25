package es.developer.projectwar.controllers.states.player;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.UnitController;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.PlayerModel;

public class OnCommandState implements PlayerState {
	private static final String TAG = OnCommandState.class.getCanonicalName();
	private PlayerState savedState;
	private UnitController controller;
	
	public OnCommandState(UnitController controller, PlayerState state){
		this.controller = controller;
		this.savedState = state;
	}
	
	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel,
			TMXTile position, int id, Command command) {	
		switch(input){
		case CommandReceived:
			controller.onCommandReceived(command);
			this.handleCommand(playerModel, command);
			break;	
		case OppositeUnitTouched:
			//TODO implement
			break;
		case UnitInRange:
			Log.i(TAG,"Sending UnitInRange notification to the UnitController");
			controller.onCommandReceived(Command.UnitInRange);
			break;
		default:
			break;
		
		}
		
	}
	
	/*TODO If the command is cancelled or finished we set the player to his previous state, don't like that, all commands should
	 * be handled for the unit controller*/
	private void handleCommand(PlayerModel player, Command command){
		switch(command){
		case Cancel:
		case Wait:
			player.setState(this.savedState);
			break;
		default:
			break;
		}
	}

	@Override
	public void enter(PlayerModel player, TMXTile position) {
		//TODO hide player view
	}
}
