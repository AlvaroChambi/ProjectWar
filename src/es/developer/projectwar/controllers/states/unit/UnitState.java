package es.developer.projectwar.controllers.states.unit;

import java.util.ArrayList;
import java.util.List;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.UnitModel;

public abstract class UnitState {
	
	protected List<Command> commandSet;
	protected String name;

	public abstract boolean handleInput(Command command, UnitModel unit, TMXTile position);
	public abstract void enter(UnitModel unit);
	
	public String getName(){
		return name;
	}
	
	/**
	 * Adds a command to the commandSet, doesn't add command if it's already on the list
	 * @param command
	 */
	public void addCommand(Command command){
		if(!commandSet.contains(command)){
			commandSet.add(command);
		}
	}
	
	/**
	 * Set the unit commands just when the unit is available
	 * @param unit
	 */
	public void setUnitCommands(UnitModel unit){
		if(unit.isAvailable()){
			unit.setCommands(commandSet);
		}
	}
	
	public UnitState(){
		commandSet = new ArrayList<Command>();
	}
}
