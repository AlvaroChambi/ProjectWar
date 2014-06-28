package es.developer.projectwar.controllers.commands;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.states.unit.OnMovedState;
import es.developer.projectwar.controllers.states.unit.UnitState;
import es.developer.projectwar.map.Map;
import es.developer.projectwar.models.UnitModel;


public class MoveCommand implements UnitCommand, IPathModifierListener{
	private static final String TAG = MoveCommand.class.getCanonicalName();
	private TMXTile savedPosition;
//	private List<TMXTile> savedTiles;
	private UnitState savedState;
	private UnitModel unit;

	public void execute(UnitModel unit, TMXTile position){
		this.unit = unit;
		this.savedPosition = unit.getPosition();
		//TODO Check if there is some need of saving the previous enabled tiles
//		this.savedTiles = unit.getEnabledTiles();
		this.savedState = unit.getState();
		//While the unit is moving we remove the commands
		unit.setCommands(Command.NO_COMMANDS);
		unit.setState(new OnMovedState(this));
		PathModifier pathModifier = Map.getInstance().generatePath(unit.getPosition(), position);
		pathModifier.setPathModifierListener(this);
		unit.setPathModifier(pathModifier);
		unit.setPosition(position);	
	}
	


	@Override
	public void undo() {
		Log.i(TAG, "cacelling move command");
		Log.i(TAG, "previous column " + savedPosition.getTileColumn());
		Log.i(TAG, "previous row " + savedPosition.getTileRow());
		this.unit.setState(savedState);
		this.unit.setPosition(savedPosition);
//		this.unit.setEnabledTiles(savedTiles);
		this.unit.getState().enter(unit);
	}

	@Override
	public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPathWaypointStarted(PathModifier pPathModifier,
			IEntity pEntity, int pWaypointIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPathWaypointFinished(PathModifier pPathModifier,
			IEntity pEntity, int pWaypointIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
		//TODO when the unit reaches his path we set his state to MovedState
		unit.getState().enter(unit);
	}
}
