package es.developer.projectwar.controllers;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.models.PlayerModel;

public interface PlayerEventsHandler {
	public void onMapClicked(TMXTile tile);
	public void onUnitClicked(int id);
	public void onBuildClicked(int id);
	public void onOppositeUnitClicked(PlayerModel player, int unitID);
}
