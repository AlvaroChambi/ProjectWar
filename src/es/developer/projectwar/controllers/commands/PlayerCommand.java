package es.developer.projectwar.controllers.commands;

import es.developer.projectwar.models.UnitModel;

public abstract class PlayerCommand {
	
	private Command type;
	protected UnitModel unit;
	
	public PlayerCommand(Command type){
		this.type = type;
	}
	
	public abstract void execute();
	
	/*TODO Think about creating some kind of 'commandable' resource as a superclass of units and buildings,
	 * now we will just handle unit executing commands */
	public void setActor(UnitModel unit){
		this.unit = unit;
	}
	
	public Command getType(){
		return type;
	}
}
