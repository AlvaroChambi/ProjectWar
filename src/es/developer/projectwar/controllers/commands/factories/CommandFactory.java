package es.developer.projectwar.controllers.commands.factories;

import es.developer.projectwar.controllers.commands.AttackCommand;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.commands.CreateBuildingCommand;
import es.developer.projectwar.controllers.commands.CreateUnitCommand;
import es.developer.projectwar.controllers.commands.MoveCommand;
import es.developer.projectwar.controllers.commands.PlayerCommand;

public class CommandFactory {

	public PlayerCommand createCommand(Command type){
		PlayerCommand command = null;
		switch(type){
		case Attack:
			command = new AttackCommand(type);
			break;
		case Build:
			command = new CreateBuildingCommand(type);
			break;
		case Create:
			command = new CreateUnitCommand(type);
			break;
		case Move:
			command = new MoveCommand(type);
			break;
		default:
			break;
		}
		return command;
	}
}
