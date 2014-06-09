package es.developer.projectwar.controllers;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.map.Map;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;

public class UnitController implements IController{
	private static final String TAG = UnitController.class.getCanonicalName();

	public void selectUnit(UnitModel unit, PlayerModel player){
		/*Just draw the enabled tiles if the unit is available.*/
		Log.i(TAG, "player: " + player.getName());
		if(unit.isAvailable()){
			unit.setEnabledTiles(Map.getInstance().getTilesEnabled(unit));	
		}
		player.setSelectedUnit(unit);
	}

	//Gets an instance of the map to get the path that joins the unit position and his destination
	public void moveUnit(UnitModel unit, TMXTile end){ 	
		unit.setPathModifier(Map.getInstance().generatePath(unit.getPosition(), end));
		unit.setPosition(end);	
		//Set the unit as not available after it is moved
		unit.setAvailable(false);
	}

	//Returns if the selected tile is one of the unit's available to move to.
	public boolean isUnitGridTouched (UnitModel unitModel, TMXTile destination){
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
