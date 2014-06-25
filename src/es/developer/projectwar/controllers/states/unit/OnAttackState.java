package es.developer.projectwar.controllers.states.unit;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.UnitModel;

public class OnAttackState extends UnitState{
	
	public OnAttackState(){
		super();
	}

	@Override
	public boolean handleInput(Command command, UnitModel unit, TMXTile position) {
		switch(command){
		case Attack:
			break;
		case Cancel:
			break;
		case Move:
			break;
		case Wait:
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
