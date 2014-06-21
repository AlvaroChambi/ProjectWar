package es.developer.projectwar.controllers;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.playerstates.PlayerInput;
import es.developer.projectwar.controllers.playerstates.PlayerState;
import es.developer.projectwar.models.PlayerModel;

public class PlayerController implements IController, PlayerEventsHandler, IPlayerCommandListener{
	private static final String TAG = PlayerController.class.getCanonicalName();
	private PlayerModel player;
	private final int NO_ID = -1;
	
	public PlayerController( PlayerModel playerModel ){
		this.player = playerModel;
	}
	
	@Override
	public void onMapClicked(TMXTile tile) {
		//we will just handle inputs if the player is active
		if(player.isActive()){
			PlayerState state = player.getState();
			//too many parameters in handleInput, i don't really like that...
			state.handleInput(PlayerInput.MapTouched, player, tile, NO_ID); 	
		}
	}

	@Override
	public void onUnitClicked(int id) {
		Log.i( TAG, "Unit clicked " + id );	
		//we will just handle inputs if the player is active
		if(player.isActive()){
			PlayerState state = player.getState();
			state.handleInput(PlayerInput.UnitTouched, player, null, id);
		}
	}

	@Override
	public void onBuildClicked(int id) {
		Log.i( TAG, "Building clicked " + id );	
		PlayerState state = player.getState();
		state.handleInput(PlayerInput.BuildingTouched, player, null, id);
	}

	@Override
	public void onPlayerCommandReceived(Command command) {
		Log.i(TAG, "command received " + command.toString());
	}

	@Override
	public void onOppositeUnitClicked(PlayerModel player, int unitID) {
		//Receives the click event from an unit that doesn't belong to this player
		PlayerState state = player.getState();
		state.handleInput(PlayerInput.OppositeUnitTouched, player, null, unitID);
	}
	
	public boolean match(PlayerModel player){
		boolean resul = false;
		if(this.player.equals(player)){
			resul = true;
		}
		return resul;
	}
	
	public PlayerModel getPlayer(){
		return player;
	}
}
