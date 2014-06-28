package es.developer.projectwar.controllers.states.unit;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.commands.MoveCommand;
import es.developer.projectwar.models.UnitModel;

public class OnMovedState extends UnitState {
	private static final String TAG = OnMovedState.class.getCanonicalName();
	
	private MoveCommand command;
	
	public OnMovedState(MoveCommand command){
		super();
		addCommand(Command.Cancel);
		addCommand(Command.Wait);
		this.command = command;
		this.name = OnMovedState.class.getSimpleName();
	}
	
	@Override
	public boolean handleInput(Command command, UnitModel unit, TMXTile position) {
		
		switch(command){
		case Attack:
			unit.setState(new OnAttackState(unit));
			unit.getState().enter(unit);
			break;
		case Wait:
			unit.setAvailable(false);
			unit.cleanEnabledTiles();
			unit.setState(new OnSelectedState());
			unit.getState().enter(unit);
			break;
		case Cancel:
			this.command.undo();
			break;
		case UnitInRange:
			addCommand(Command.Attack);
			break;
		default:
			break;
		
		}
		return false;
	}

	@Override
	public void enter(UnitModel unit) {
		Log.i(TAG, "enter");
		unit.setCommands(commandSet);
	}
}
