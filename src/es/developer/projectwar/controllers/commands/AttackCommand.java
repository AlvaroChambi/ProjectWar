package es.developer.projectwar.controllers.commands;

import android.util.Log;

public class AttackCommand extends PlayerCommand {
	private static final String TAG = AttackCommand.class.getCanonicalName();
	public AttackCommand(Command type) {
		super(type);
	}

	@Override
	public void execute() {
		Log.i(TAG, "executing attack command");
	}

}
