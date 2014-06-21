package es.developer.projectwar.models;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.unitstates.UnitState;
import es.developer.projectwar.utils.TextureRegionFactory.TextureType;
import es.developer.projectwar.utils.UpdateInput;

public abstract class UnitModel extends MovableModel{
//	private static final String TAG = UnitModel.class.getCanonicalName();
	private int movement;
	private List<TMXTile> enabledTiles;
	private List<Sprite> enabledTilesView;
	private PathModifier pathModifier;
	protected List<Command> commands;
	private boolean available;
	private UnitState state;
	
	public UnitModel(int id){
		super();
		enabledTilesView = new ArrayList<Sprite>();
		setCommands(new ArrayList<Command>());
		this.setAvailable(true);
		this.id = id;
		this.setTextureType(TextureType.unit);
	}
	/**
	 * @return the movement
	 */
	public int getMovement() {
		return movement;
	}
	/**
	 * @param movement the movement to set
	 */
	public void setMovement(int movement) {
		this.movement = movement;
	}
	/**
	 * @return the enabledTiles
	 */
	public List<TMXTile> getEnabledTiles() {
		return enabledTiles;
	}
	/**
	 * @param enabledTiles the enabledTiles to set
	 */
	public void setEnabledTiles(List<TMXTile> enabledTiles) {
		this.enabledTiles = enabledTiles;
	}
	
	public void cleanEnabledTiles() {
		if(this.enabledTiles != null){
			this.enabledTiles.clear();
			this.notifyListenersUpdate(UpdateInput.UNIT_UNSELECTED);	
		}
	}
	/**
	 * @return the enabledTilesView
	 */
	public List<Sprite> getEnabledTilesView() {
		return enabledTilesView;
	}
	/**
	 * @param enabledTilesView the enabledTilesView to set
	 */
	public void setEnabledTilesView(List<Sprite> enabledTilesView) {
		this.enabledTilesView = enabledTilesView;
	}
	/**
	 * @return the pathModifier
	 */
	public PathModifier getPathModifier() {
		return pathModifier;
	}
	/**
	 * @param pathModifier the pathModifier to set
	 */
	public void setPathModifier(PathModifier pathModifier) {
		this.pathModifier = pathModifier;
	}
	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	/**
	 * @return the commands
	 */
	public List<Command> getCommands() {
		return commands;
	}
	/**
	 * @param commands the commands to set
	 */
	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}
	
	/**
	 * Sets the unit as targeted
	 */
	public void setTargeted(){
		this.notifyListenersUpdate(UpdateInput.TARGETED_UNIT);
	}
	/**
	 * @return the state
	 */
	public UnitState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(UnitState state) {
		this.state = state;
	}
}
