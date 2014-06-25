package es.developer.projectwar.controllers.states.unit;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.commands.MoveCommand;
import es.developer.projectwar.models.UnitModel;

public class OnMoveState extends UnitState {
//	private static final String TAG = OnMoveState.class.getCanonicalName();
	
	private MoveCommand command;
	
	public OnMoveState(MoveCommand command){
		super();
		addCommand(Command.Cancel);
		addCommand(Command.Wait);
		this.command = command;
	}
	
	@Override
	public boolean handleInput(Command command, UnitModel unit, TMXTile position) {
		
		switch(command){
		case Attack:
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
		unit.setCommands(commandSet);
	}
}
