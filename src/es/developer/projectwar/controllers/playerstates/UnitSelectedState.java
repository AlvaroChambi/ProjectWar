package es.developer.projectwar.controllers.playerstates;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.UnitController;
import es.developer.projectwar.controllers.unitstates.SelectedState;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;

public class UnitSelectedState implements PlayerState{
//	private static final String TAG = UnitSelectedState.class.getCanonicalName();
	private UnitController controller;
	private int id;
	
	public UnitSelectedState( int id ){
		this.id = id;
	}
	
	//Think about moving all unit related code to a UnitController, but just think for now...
	@Override
	public void handleInput(PlayerInput input, PlayerModel playerModel,
			TMXTile position, int id) {
		//Get the unit which id was stored when this state was created, input id is meant to switch to another state
		UnitModel unitModel = playerModel.getUnit(this.id);

		switch(input){
		case BuildingTouched:
//			unitModel.cleanEnabledTiles();
//			playerModel.setState( new BuildingSelectedState(id) );
//			playerModel.getState().enter(playerModel, position);
			break;
		case MapTouched:
			/*If the move request isn't successfully executed, means that the player has a selected a non-reachable 
			 * position, so we get into a NothingSelectedState*/
			if(!controller.onMoveRequest(position)){
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
		//Each time we select a unit we instance an UnitController to handle all his inputs
		unit.setState(new SelectedState());
		unit.getState().enter(unit);
		controller = new UnitController(unit);
		
		TMXTile unitPosition = unit.getPosition();
		player.setPosition(unitPosition);
		player.setSelectedUnit(unit);
	}
}
