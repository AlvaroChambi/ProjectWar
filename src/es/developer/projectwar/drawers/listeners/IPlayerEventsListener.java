package es.developer.projectwar.drawers.listeners;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.PlayerCommand;
import es.developer.projectwar.models.PlayerModel;

public interface IPlayerEventsListener {
	public void onMapClicked(TMXTile tile);
	public void onUnitClicked(int id);
	public void onBuildClicked(int id);
	public void onOppositeUnitClicked(PlayerModel player, int unitID);
	public void onCommandSelected(PlayerCommand command);
}
