package es.developer.projectwar.controllers.states.player;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.PlayerModel;

public abstract class PlayerState {
	
	protected String name;
	
	//TODO Check a better way to pass input parameters in the handle input function
	/**
	 * 
	 * @param input: input action to handle
	 * @param playerModel: main actor
	 * @param position: position given by the input
	 * @param id: id given by the input
	 */
	public abstract void handleInput(PlayerInput input, PlayerModel playerModel, TMXTile position, int id, Command command);
	public abstract void enter(PlayerModel player, TMXTile position);
	
	public String getName(){
		return name;
	}
}
