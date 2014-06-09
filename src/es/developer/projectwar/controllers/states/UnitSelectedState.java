package es.developer.projectwar.controllers.states;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.UnitController;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;

public class UnitSelectedState implements PlayerState{
	private static final String TAG = UnitSelectedState.class.getCanonicalName();
	private UnitController controller;
	private int id;
	
	public UnitSelectedState( int id ){
		this.id = id;
		controller = new UnitController();
	}
	
	//Think about moving all unit related code to a UnitController, but just think for now...
	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel,
			TMXTile position, int id) {
		//Get the unit which id was stored when this state was created, input id is meant to switch to another state
		UnitModel unitModel = playerModel.getUnit(this.id);

		switch(input){
		case BuildingTouched:
			unitModel.cleanEnabledTiles();
			playerModel.setState( new BuildingSelectedState(id) );
			playerModel.getState().enter(playerModel, position);
			break;
		case MapTouched:
			//if the input touched tile is reachable for the unit and the unit is available it has to move to the 
			//destination, otherwise just switch the state
			if( unitModel.isAvailable() && 
						controller.isUnitGridTouched( unitModel, position ) ){
				controller.moveUnit(unitModel, position);
				unitModel.cleanEnabledTiles();
				Log.i(TAG,"moving unit " + id);
			}else {
				unitModel.cleanEnabledTiles();
				playerModel.setState( new NothingSelectedState() );
				playerModel.getState().enter(playerModel, position);
			}
			break;
		case UnitTouched:
			unitModel.cleanEnabledTiles();
			playerModel.setState( new UnitSelectedState(id) );
			playerModel.getState().enter(playerModel, position);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void enter(PlayerModel player, TMXTile position) {
		UnitModel unit = player.getUnit(this.id);
		TMXTile unitPosition = unit.getPosition();
		player.setPosition(unitPosition);
		controller.selectUnit(unit, player);
	}
}
