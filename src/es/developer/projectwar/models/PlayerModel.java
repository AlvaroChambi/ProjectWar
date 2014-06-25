package es.developer.projectwar.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;
import es.developer.projectwar.controllers.states.player.NothingSelectedState;
import es.developer.projectwar.controllers.states.player.PlayerState;
import es.developer.projectwar.utils.TextureRegionFactory.TextureType;

public class PlayerModel extends MovableModel {
	private final static String TAG = PlayerModel.class.getCanonicalName();
	private List<UnitModel> units;
	private List<BuildingModel> buildings;
	private UnitModel selectedUnit;
	private Player type;
	private PlayerState state;
	private String name;
	private boolean active;

	public PlayerModel(){
		super();
		this.setTextureType(TextureType.player);
		this.setResource("selected_tile.png");
		units = new ArrayList<UnitModel>();
		//Player always start in a nothing selected state
		state = new NothingSelectedState();
		active = false;
	}

	public PlayerModel(int id){
		this();
		this.id = id;
	}

	public void addUnit(UnitModel unitModel){
		units.add(unitModel);
	}

	public void addBuilding(BuildingModel buildingModel){
		buildings.add(buildingModel);
	}
	/*I'm sure there some way to refactor this and the getUnit(id) method,
	 *  pretty much the same code*/
	public BuildingModel getBuilding(int id){  
		BuildingModel building = null;
		Iterator<BuildingModel> iterator = buildings.iterator();
		while(iterator.hasNext()){
			final BuildingModel actual = iterator.next();
			if(actual.getId() == id) {
				building = actual;
				Log.i(TAG,"building :"+id+ "found");
			}
		}
		return building;
	}
	
	public UnitModel getUnit(int id){
		UnitModel unit = null;
		Iterator<UnitModel> iterator = units.iterator();
		while(iterator.hasNext()){
			UnitModel actual = iterator.next();
			if(actual.getId() == id) {
				unit = actual;
				Log.i(TAG,"unit :"+id+ "found");
			}
		}
		return unit;
	}
	
	public boolean isUnitInAttackRange(UnitModel unit){
		boolean result = false;
		for(UnitModel target: this.getUnits()){
			if(unit != null && unit.isInAttackRange(target)){
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * @param selectedUnit the selectedUnit to set
	 */
	public void setSelectedUnit(UnitModel selectedUnit) {
		this.selectedUnit = selectedUnit;
		//this.selectedUnit.notifyListenersUpdate(UpdateInput.UNIT_SELECTED);
	}

	public void cleanUnitsAvailableState() {
		for(UnitModel unit: units){
			unit.setAvailable(true);
		}
	}
	
	public boolean equals(PlayerModel player){
		boolean resul = false;
		if(player.getName().equals(this.getName())) {
			resul = true;
		}
		return resul;
	}
	
	public boolean containsUnit(int id) {
		boolean resul = false;
		for(UnitModel unit: units){
			if(unit.getId() == id){
				resul = true;
			}
		}
		return resul;
	}

	/**
	 * @return the units
	 */
	public List<UnitModel> getUnits() {
		return units;
	}

	/**
	 * @param units the units to set
	 */
	public void setUnits(List<UnitModel> units) {
		this.units = units;
	}

	/**
	 * @return the type
	 */
	public Player getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Player type) {
		this.type = type;
	}

	/**
	 * @return the buildings
	 */
	public List<BuildingModel> getBuildings() {
		return buildings;
	}

	/**
	 * @param buildings the buildings to set
	 */
	public void setBuildings(List<BuildingModel> buildings) {
		this.buildings = buildings;
	}

	/**
	 * @return the state
	 */
	public PlayerState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(PlayerState state) {
		this.state = state;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the selectedUnit
	 */
	public UnitModel getSelectedUnit() {
		return selectedUnit;
	}
	
	/**
	 * @return true if the player is the actual game active player.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param set the player as the active one
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
