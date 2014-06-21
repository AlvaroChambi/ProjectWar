package es.developer.projectwar.controllers;

public interface IGameCommandListener {
	
	public void onGameCommandReceived(GameCommand command);
}
