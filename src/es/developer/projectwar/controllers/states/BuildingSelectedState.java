package es.developer.projectwar.controllers.states;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.models.PlayerModel;

public class BuildingSelectedState implements PlayerState{
//	private static final String TAG = BuildingSelectedState.class.getCanonicalName();
	private int id;
	
	public BuildingSelectedState(int id){
		this.id = id;
	}
	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel,
			TMXTile position, int id) {
		switch(input){
		case BuildingTouched:
			playerModel.setState( new BuildingSelectedState(id) );
			playerModel.getState().enter(playerModel, position);
			break;
		case MapTouched:
			playerModel.setState( new NothingSelectedState() );
			playerModel.getState().enter(playerModel, position);
			break;
		case UnitTouched:
			playerModel.setState( new UnitSelectedState(id) );
			playerModel.getState().enter(playerModel, position);
			break;
		default:
			break;
		
		}
	}
	@Override
	public void enter(PlayerModel player, TMXTile position) {
		TMXTile buildingPosition = player.getUnit(this.id)
										 .getPosition();
		player.setPosition(buildingPosition);
	}
}
