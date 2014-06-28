package es.developer.projectwar.controllers.states.player;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.UnitController;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.states.unit.OnSelectedState;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;

public class UnitSelectedState extends PlayerState{
	private static final String TAG = UnitSelectedState.class.getCanonicalName();
	private UnitController unitController;
	private int id;
	
	public UnitSelectedState( int id ){
		this.id = id;
		this.name = UnitSelectedState.class.getSimpleName();
	}
	
	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel,
			TMXTile position, int id, Command command) {
		
		UnitModel unitModel = playerModel.getUnit(this.id);

		switch(input){
		case BuildingTouched:
//			unitModel.cleanEnabledTiles();
//			playerModel.setState( new BuildingSelectedState(id) );
//			playerModel.getState().enter(playerModel, position);
			break;
		case MapTouched:
			/*If the move request isn't successfully executed, means that the player has a selected a non-reachable 
			 * position, so we get into a NothingSelectedState*/
			if(!unitController.onMoveRequest(position)){
				Log.i(TAG, "move request failed");
				playerModel.setState( new NothingSelectedState() );
				playerModel.getState().enter(playerModel, position);
			}else {  /*if the move command has been called we notify it as a new command input so we can get into
			 		   the OnCommandState*/
				this.handleInput(PlayerInput.CommandReceived, playerModel, position, id, Command.Move);
			}	
			break;
		case UnitTouched:
			unitModel.cleanEnabledTiles();
			playerModel.setState( new UnitSelectedState(id) );
			playerModel.getState().enter(playerModel, position);
			break;
		case CommandReceived:
			handleCommand(playerModel, command);
			break;
		case UnitInRange:
			Log.i(TAG, "sending UniInRangeNotification");
			unitController.onCommandReceived(Command.UnitInRange);
			break;
		default:
			break;
		}
	}
	
	public void handleCommand(PlayerModel player, Command command){
		switch(command){
		case Cancel:
		case Wait:
			//When we get a Cancel or Wait command we don't need to get into the CommandState
			unitController.onCommandReceived(command);
			break;
		default:
			//Whenever we get into a onCommand state we save the previous state
			PlayerState savedState = player.getState();
			player.setState(new OnCommandState(unitController, savedState));
			unitController.onCommandReceived(command);
			break;		
		}
	}
	
	@Override
	public void enter(PlayerModel player, TMXTile position) {
		Log.i(TAG, "enter");
		UnitModel unit = player.getUnit(this.id);
		//Each time we select a unit we instance an UnitController to handle all his inputs
		unit.setState(new OnSelectedState());
		unit.getState().enter(unit);
		unitController = new UnitController(unit);
		
		TMXTile unitPosition = unit.getPosition();
		player.setPosition(unitPosition);
		player.setSelectedUnit(unit);
	}
}
