package es.developer.projectwar.controllers.states.unit;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.commands.MoveCommand;
import es.developer.projectwar.map.Map;
import es.developer.projectwar.models.UnitModel;

public class OnSelectedState extends UnitState{
	private static final String TAG = OnSelectedState.class.getCanonicalName();
	
	public OnSelectedState(){
		super();
		addCommand(Command.Wait);
		this.name = OnSelectedState.class.getSimpleName();
	}

	@Override
	public boolean handleInput(Command command, UnitModel unit, TMXTile position) {
		boolean result = false;

		switch(command){
		case Attack:
			unit.cleanEnabledTiles();
			unit.setState(new OnAttackState(unit));
			unit.getState().enter(unit);
			break;
		case Move:
			if(isUnitGridTouched(unit, position)){
				MoveCommand moveCommand = new MoveCommand();
				moveCommand.execute(unit, position);
				result = true;
			}
			unit.cleanEnabledTiles();
			break;
		case Wait:
			unit.setAvailable(false);
			unit.cleanEnabledTiles();
			break;
		case UnitInRange:
			addCommand(Command.Attack);
			setUnitCommands(unit);
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public void enter(UnitModel unit) {
		/*Just draw the enabled tiles and update commands if the unit is available.*/
		Log.i(TAG, "enter");
		if(unit.isAvailable()){
			unit.setCommands(commandSet);
			unit.setEnabledTiles(Map.getInstance().getTilesEnabled(unit));	
		}
	}

	//Returns if the selected tile is one of the unit's available to move to.
	private boolean isUnitGridTouched (UnitModel unitModel, TMXTile destination){
		boolean resul = false;
		TMXTile initial = unitModel.getPosition();
		int offsetX = Math.abs(initial.getTileColumn() - 
				destination.getTileColumn()); 
		int offsetY = Math.abs(initial.getTileRow() - 
				destination.getTileRow());
		if(offsetX + offsetY <= unitModel.getMovement() ){
			resul = true;
		}
		return resul;
	}
}
