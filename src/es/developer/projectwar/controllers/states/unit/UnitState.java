package es.developer.projectwar.controllers.states.unit;

import java.util.ArrayList;
import java.util.List;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.UnitModel;

public abstract class UnitState {
	
	protected List<Command> commandSet;

	public abstract boolean handleInput(Command command, UnitModel unit, TMXTile position);
	public abstract void enter(UnitModel unit);
	
	/**
	 * Adds a command to the commandSet, doesn't add command if it's already on the list
	 * @param command
	 */
	public void addCommand(Command command){
		if(!commandSet.contains(command)){
			commandSet.add(command);
		}
	}
	
	public UnitState(){
		commandSet = new ArrayList<Command>();
	}
}
