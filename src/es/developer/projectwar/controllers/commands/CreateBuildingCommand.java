package es.developer.projectwar.controllers.commands;

import android.util.Log;

public class CreateBuildingCommand extends PlayerCommand{
	private static final String TAG = CreateBuildingCommand.class.getCanonicalName(); 
	
	public CreateBuildingCommand(Command type) {
		super(type);
	}

	@Override
	public void execute() {
		Log.i(TAG , "executing create building command");
	}

}
