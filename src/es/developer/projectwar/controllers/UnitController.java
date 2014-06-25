package es.developer.projectwar.controllers;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.UnitModel;

public class UnitController implements IController{
//	private static final String TAG = UnitController.class.getCanonicalName();
	
	private UnitModel unit;
	
	public UnitController(UnitModel unit){
		this.unit = unit;
	}
	
	/**
	 * Return true if the unit could really move to the destination
	 * @param position
	 * @return
	 */
	public boolean onMoveRequest(TMXTile position){
		boolean result = false;
		if(unit.isAvailable()){
			result = unit.getState().handleInput(Command.Move, unit, position);
		}
		return result;
	}
	
	public void onCommandReceived(Command command){
		unit.getState().handleInput(command, unit, null);
	}
}
