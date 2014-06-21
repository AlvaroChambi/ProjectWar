package es.developer.projectwar.controllers;

import es.developer.projectwar.controllers.commands.Command;

public interface IPlayerCommandListener {

	public void onPlayerCommandReceived(Command command);
}
