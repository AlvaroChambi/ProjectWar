package es.developer.projectwar.controllers.playerstates;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.models.PlayerModel;

public interface PlayerState {
	//TODO Check a better way to pass input parameters in the handle input function
	/**
	 * 
	 * @param input: input action to handle
	 * @param playerModel: main actor
	 * @param position: position given by the input
	 * @param id: id given by the input
	 */
	public void handleInput(PlayerInput input, PlayerModel playerModel, TMXTile position, int id);
	public void enter(PlayerModel player, TMXTile position);
}
