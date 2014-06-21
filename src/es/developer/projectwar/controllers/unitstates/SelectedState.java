package es.developer.projectwar.controllers.unitstates;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.map.Map;
import es.developer.projectwar.models.UnitModel;

public class SelectedState implements UnitState{

	@Override
	public boolean handleInput(Command command, UnitModel unit, TMXTile position) {
		boolean result = false;

		switch(command){
		case Attack:
			break;
		case Build:
			break;
		case Create:
			break;
		case Move:
			if(isUnitGridTouched(unit, position)){
				this.moveUnit(unit, position);
				result = true;
			}
			unit.cleanEnabledTiles();
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public void enter(UnitModel unit) {
		/*Just draw the enabled tiles if the unit is available.*/
		if(unit.isAvailable()){
			unit.setEnabledTiles(Map.getInstance().getTilesEnabled(unit));	
		}
	}

	//Gets an instance of the map to get the path between the unit position and his destination
	private void moveUnit(UnitModel unit, TMXTile end){ 	
		unit.setPathModifier(Map.getInstance().generatePath(unit.getPosition(), end));
		unit.setPosition(end);	
		//Set the unit as not available after it is moved
		unit.setAvailable(false);
	}

	//Returns if the selected tile is one of the unit's available to move to.
	private boolean isUnitGridTouched (UnitModel unitModel, TMXTile destination){
		boolean resul = false;
		TMXTile initial = unitModel.getPosition();
		int offsetX = Math.abs(initial.getTileColumn() - 
				destination.getTileColumn()); 
		int offsetY = Math.abs(initial.getTileRow() - destination.getTileRow());
		if(offsetX + offsetY <= unitModel.getMovement() ){
			resul = true;
		}
		return resul;
	}
}
