package es.developer.projectwar.controllers.commands;

import android.util.Log;
import es.developer.projectwar.models.UnitModel;

public class AttackCommand implements UnitCommand{
	private static final String TAG = AttackCommand.class.getCanonicalName();
	
	public void execute(UnitModel unit, UnitModel target){
		Log.i(TAG, "executing attack command");
	}

	@Override
	public void undo() {
		Log.i(TAG, "undo");
	}

}
