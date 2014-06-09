package es.developer.projectwar.models;

import es.developer.projectwar.controllers.commands.Command;

public class SoldierModel extends UnitModel{

	public SoldierModel(int id) {
		super(id);
		this.setResource("player.png");
		this.setMovement(4);
		this.setSoliderCommands();
	}
	
	private void setSoliderCommands(){
		commands.add(Command.Attack);
		commands.add(Command.Move);
		commands.add(Command.Build);
	}
}
