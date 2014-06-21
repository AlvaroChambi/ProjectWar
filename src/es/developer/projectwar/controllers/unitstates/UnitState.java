package es.developer.projectwar.controllers.unitstates;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.UnitModel;

public interface UnitState {

	public boolean handleInput(Command command, UnitModel unit, TMXTile position);
	public void enter(UnitModel unit);
}
