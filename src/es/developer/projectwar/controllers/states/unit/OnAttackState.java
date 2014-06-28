package es.developer.projectwar.controllers.states.unit;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.commands.AttackCommand;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.UnitModel;

public class OnAttackState extends UnitState{
	private static final String TAG = OnAttackState.class.getCanonicalName();
	public UnitModel unit;
	public AttackCommand attackCommand;
	public UnitState savedState;
	
	public OnAttackState(UnitModel unit){
		super();
		this.name = OnAttackState.class.getSimpleName();
		this.unit = unit;
		addCommand(Command.Cancel);
		savedState = unit.getState();
		attackCommand = new AttackCommand();
	}

	@Override
	public boolean handleInput(Command command, UnitModel unit, TMXTile position) {
		switch(command){
		case SetTarget:
			attackCommand.execute(this.unit, unit);
			break;
		case Cancel:
			attackCommand.undo();
			unit.hideUnitsInRange();
			unit.setState(savedState);
			unit.getState().enter(unit);
			break;
		case Attack:
			//TODO implement attack
			break;
		default:
			break;
		
		}
		return false;
	}

	@Override
	public void enter(UnitModel unit) {
		Log.i(TAG, "enter");
		unit.setCommands(commandSet);
		unit.showUnitsInRange();
	}

}
