package es.developer.projectwar.controllers.states;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.models.PlayerModel;

public class CreateUnitCommandState implements PlayerState{

	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel,
			TMXTile position, int id) {

	}

	@Override
	public void enter(PlayerModel player, TMXTile position) {

	}
}