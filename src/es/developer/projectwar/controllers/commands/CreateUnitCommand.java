package es.developer.projectwar.controllers.commands;

import android.util.Log;

public class CreateUnitCommand extends PlayerCommand{
	private static final String TAG = CreateUnitCommand.class.getCanonicalName();
	
	public CreateUnitCommand(Command type) {
		super(type);
	}

	@Override
	public void execute() {
		Log.i(TAG, "executing create unit command");
	}

}
