package es.developer.projectwar.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.Game;
import es.developer.projectwar.models.PlayerModel;
/**
 * GameController receives all units click events
 * @author Leid
 *
 */
public class GameController implements IController, PlayerEventsHandler, IGameCommandListener {
	//	private static final String TAG = GameController.class.getCanonicalName();
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
			this.notifyOppositeUnitCliked(game.getOwnerPlayer(id), id);
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
		default:
			break;
		
		}
	}
	
	@Override
	public void onOppositeUnitClicked(PlayerModel player, int unitID) {
		
	}
	
	/**
	 * Set the next active player, if all the players was already selected, finish this turn and starts the 
	 * next one.
	 */
	public void shiftPlayer(){
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
	
	/*notify opposite unit click event to the active player through the controller related with him*/
	private void notifyOppositeUnitCliked(PlayerModel player, int unitID){
		for(PlayerController controller: controllers){
			if(controller.match(game.getActualPlayer())){
				controller.onOppositeUnitClicked(player, unitID);
				player.getUnit(unitID).setTargeted();
			}
		}
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
