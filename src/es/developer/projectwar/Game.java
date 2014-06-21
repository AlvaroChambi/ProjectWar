package es.developer.projectwar;

import java.util.List;

import es.developer.projectwar.models.Model;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;

public class Game extends Model{
	public static final int FIRST_DAY = 1;
	private PlayerModel actualPlayer;
	private List<PlayerModel> players;
	private GameState state;
	private GameMode mode;
	private int day;

	public Game(GameMode mode){
		this.mode = mode;
		this.setDay(Game.FIRST_DAY);	
	}

	/**
	 * @return the actualPlayer
	 */
	public PlayerModel getActualPlayer() {
		return actualPlayer;
	}

	/**
	 * @param actualPlayer the actualPlayer to set
	 */
	public void setActualPlayer(PlayerModel actualPlayer) {
		this.actualPlayer = actualPlayer;
		this.actualPlayer.setActive(true);
		this.notifyListenersUpdate(null);
	}

	/**
	 * @return the players
	 */
	public List<PlayerModel> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<PlayerModel> players) {
		this.players = players;
	}

	/**
	 * @return the state
	 */
	public GameState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(GameState state) {
		this.state = state;
	}

	/**
	 * @return the mode
	 */
	public GameMode getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(GameMode mode) {
		this.mode = mode;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
		// send an update notification with no special parameters
		this.notifyListenersUpdate(null);
	}

	public enum GameMode{
		Local,
		Online;
	}

	public enum GameState{
		Started,
		Paused,
		Finished;
	}
	
	/**
	 * Return the player that owns the unit with the given id
	 * @param unitID
	 * @return owner player
	 */
	public PlayerModel getOwnerPlayer(int unitID){
		PlayerModel resul = null;
		for(PlayerModel player: players){
			if(player.containsUnit(unitID)){
				resul = player;
			}
		}
		return resul;
	}
	
	/**
	 * Returns the unit that match with the id, null if there is not match
	 * @param id unit id
	 * @return unit that match the id
	 */

	public UnitModel getUnit(int id) {
		UnitModel unit = null;
		for(PlayerModel player: players){
			if(player.containsUnit(id)){
				unit = player.getUnit(id);
			}
		}
		return unit;
	}
}
