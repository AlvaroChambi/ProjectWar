package es.developer.projectwar.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.Game;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;
/**
 * GameController receives all units click events
 * @author Leid
 *
 */
public class GameController implements IController, PlayerEventsHandler, 
									   IGameCommandListener, IPlayerCommandListener {
	private static final String TAG = GameController.class.getCanonicalName();
	private Game game;
	private LinkedList<PlayerModel> playersQueue;
	private List<PlayerController> controllers;

	public GameController(Game game){
		this.game = game;
		this.initPlayersQueue();
		controllers = new ArrayList<PlayerController>();
	}

	@Override
	public void onMapClicked(TMXTile tile) {

	}

	@Override
	public void onUnitClicked(int id) {
		/*Each player handle his units click events, but in order to get a notification of 
		 * other player unit's click events the GameController handles them*/
		if(!game.getActualPlayer().containsUnit(id)){
			this.onOppositeUnitClicked(game.getOwnerPlayer(id), id);
		}else {  //Whenever a unit of the actual player is clicked we search for a unit in range
			this.searchUnitInRange(game.getActualPlayer().getSelectedUnit());
		}
	}

	@Override
	public void onBuildClicked(int id) {

	}

	@Override
	public void onGameCommandReceived(GameCommand command) {
		switch(command){
		case End:
			this.shiftPlayer();
			break;
		case UpdateUnit:
			/*TODO searching and notifying unit in attack range of the selected unit whenever UpdateUnit
			 * event is received*/
			this.searchUnitInRange(game.getActualPlayer().getSelectedUnit());	
			break;
		default:
			break;

		}
	}

	@Override
	public void onOppositeUnitClicked(PlayerModel player, int unitID) {
		//TODO Change it to save some kind of "reachable" state in the unit so we don't have to check if the unit is in range
		/*Notifies if a opposite unit that is in the attack range of the selected unit is clicked*/
		if(unitInAttackRange(player.getUnit(unitID))){
			Log.i(TAG, "sending opposite unit clicked notification");
			this.notifyOppositeUnitCliked(player, unitID);	
		}
	}

	@Override
	public void onPlayerCommandReceived(Command command) {
		switch(command){
		case Attack:
			
			break;
		default:
			break;
		
		}
	}

	@Override
	public void onUnitInRangeNotification() {
		Log.i(TAG, "Sending UnitInRangeNotification");
		this.getController(game.getActualPlayer()).onUnitInRangeNotification();
	}

	/**
	 * Set the next active player, if all the players was already selected, finish this turn and starts the 
	 * next one.
	 */
	private void shiftPlayer(){
		/*First we update the active state from the actual player, since it's been switched 
		 * we must set it as non active*/
		game.getActualPlayer().setActive(false);
		if(!playersQueue.isEmpty()){
			game.setActualPlayer(playersQueue.remove());
		}else {
			/*If there's no more player in the queue we restart it and 
			 * start the next day*/
			this.initPlayersQueue();
			this.cleanUnitAvailableState();
			int day = game.getDay();
			game.setDay(++day);
		}
	}

	private void searchUnitInRange(UnitModel unit){
		if(this.unitInAttackRange(unit)){
			this.onUnitInRangeNotification();
		}
	}

	private boolean unitInAttackRange(UnitModel unit){
		boolean result = false;
		for(PlayerModel player: game.getPlayers()){
			if(!player.isActive() &&
					player.isUnitInAttackRange(unit)){
				result = true;
			}
		}
		return result;
	}

	/*notify opposite unit click event to the active player through the controller related with him*/
	private void notifyOppositeUnitCliked(PlayerModel player, int unitID){
		for(PlayerController controller: controllers){
			if(controller.match(game.getActualPlayer())){
				controller.onOppositeUnitClicked(player, unitID);
				player.getUnit(unitID).setTargeted();
			}
		}
	}

	private PlayerController getController(PlayerModel player){
		PlayerController result = null;
		for(PlayerController controller: controllers){
			if(controller.match(player)){
				result = controller;
			}
		}	
		return result;
	}

	//When the day is over we set all the unit's as available
	private void cleanUnitAvailableState(){
		for(PlayerModel player: game.getPlayers()){
			player.cleanUnitsAvailableState();
		}
	}

	private void initPlayersQueue(){
		playersQueue = new LinkedList<PlayerModel>();
		Iterator<PlayerModel> iterator = game.getPlayers().iterator();
		while(iterator.hasNext()){
			final PlayerModel player = iterator.next();
			playersQueue.add(player);
		}
		game.setActualPlayer(playersQueue.remove());
	}

	public void registerController(PlayerController playerController){
		controllers.add(playerController);
	}

}
