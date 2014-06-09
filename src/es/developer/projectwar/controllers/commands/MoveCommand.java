package es.developer.projectwar.controllers.commands;

import android.util.Log;

public class MoveCommand extends PlayerCommand{
	private static final String TAG = MoveCommand.class.getCanonicalName();
	
	public MoveCommand(Command type) {
		super(type);
	}

	@Override
	public void execute() {
		Log.i(TAG, "executing move command");
	}

}
