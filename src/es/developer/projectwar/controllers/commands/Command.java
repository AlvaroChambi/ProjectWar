package es.developer.projectwar.controllers.commands;

import java.util.ArrayList;
import java.util.List;

public enum Command {
	Attack,
	Cancel,
	Move,
	UnitInRange,
	SetTarget,
	Wait;
	public static final List<Command> NO_COMMANDS = new ArrayList<Command>();
}
