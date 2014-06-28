package es.developer.projectwar.controllers.states.player;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.UnitController;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.PlayerModel;

public class OnCommandState extends PlayerState {
	private static final String TAG = OnCommandState.class.getCanonicalName();
	private PlayerState savedState;
	private UnitController unitController;
	
	public OnCommandState(UnitController controller, PlayerState state){
		this.unitController = controller;
		this.savedState = state;
		this.name = OnCommandState.class.getSimpleName();
	}
	
	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel,
			TMXTile position, int id, Command command) {	
		switch(input){
		case CommandReceived:
			unitController.onCommandReceived(command);
			this.handleCommand(playerModel, command);
			break;	
		case OppositeUnitTouched:
			Log.i(TAG,"handle opposite unit touched");
			unitController.onTargetSelected(playerModel.getUnit(id));
			break;
		case UnitInRange:
			Log.i(TAG,"Sending UnitInRange notification to the UnitController");
			unitController.onCommandReceived(Command.UnitInRange);
			break;
		default:
			break;
		
		}
		
	}
	
	/*TODO If the command is cancelled or finished we set the player to his previous state(i don't like that, all unit commands should
	 * be handled for the unit controller)*/
	private void handleCommand(PlayerModel player, Command command){
		switch(command){
		case Cancel:			
		case Wait:
			Log.i(TAG, "returning to the previuos state: " + savedState.getName());
			player.setState(this.savedState);
			break;
		default:
			PlayerState savedState = player.getState();
			player.setState(new OnCommandState(unitController, savedState));
			player.getState().enter(player, null);
			break;
		}
	}

	@Override
	public void enter(PlayerModel player, TMXTile position) {
		Log.i(TAG, "enter");
	}
}
