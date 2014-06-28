package es.developer.projectwar.controllers;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.states.player.PlayerInput;
import es.developer.projectwar.controllers.states.player.PlayerState;
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
			state.handleInput(PlayerInput.MapTouched, player, tile, NO_ID, null); 	
		}
	}

	@Override
	public void onUnitClicked(int id) {
		Log.i( TAG, "Unit clicked " + id );	
		//we will just handle inputs if the player is active
		if(player.isActive()){
			PlayerState state = player.getState();
			state.handleInput(PlayerInput.UnitTouched, player, null, id, null);
		}
	}

	@Override
	public void onBuildClicked(int id) {
		Log.i( TAG, "Building clicked " + id );	
		PlayerState state = player.getState();
		state.handleInput(PlayerInput.BuildingTouched, player, null, id, null);
	}

	@Override
	public void onPlayerCommandReceived(Command command) {
		if(player.isActive()){
			Log.i(TAG, "command received " + command.toString());
			PlayerState state = player.getState();
			state.handleInput(PlayerInput.CommandReceived, player, null,NO_ID, command);	
		}
	}
	
	@Override
	public void onUnitInRangeNotification() {
		player.getState().handleInput(PlayerInput.UnitInRange, player, null, NO_ID, null);
	}

	@Override
	public void onOppositeUnitClicked(PlayerModel player, int unitID) {
		//Receives the click event from an unit that doesn't belong to this player
		PlayerState state = this.player.getState();
		state.handleInput(PlayerInput.OppositeUnitTouched, player, null, unitID, null);
	}
	
	/*TODO Implement match method in a superclass*/
	public boolean match(PlayerModel player){
		boolean resul = false;
		if(this.player.equals(player)){
			resul = true;
		}
		return resul;
	}
	//TODO implement method in a superclass
	public PlayerModel getPlayer(){
		return player;
	}
}
