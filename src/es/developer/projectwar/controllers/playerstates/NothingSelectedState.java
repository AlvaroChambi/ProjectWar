package es.developer.projectwar.controllers.playerstates;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.models.PlayerModel;

public class NothingSelectedState implements PlayerState{

	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel, 
			TMXTile position, int id){
		switch(input){
		case BuildingTouched:
//			playerModel.setState(new BuildingSelectedState(id));
//			playerModel.getState().enter(playerModel, position);
			break;
		case MapTouched:
			playerModel.getState().enter(playerModel, position);
			break;
		case UnitTouched:	
			playerModel.setState(new UnitSelectedState(id));
			playerModel.getState().enter(playerModel, position);
			break;
		default:
			break;		
		}
	}

	@Override
	public void enter(PlayerModel player, TMXTile position) {
		player.setPosition(position);
	}
}
