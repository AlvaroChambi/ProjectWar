package es.developer.projectwar.models;

import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.map.Map;
import es.developer.projectwar.utils.Coordinate;
import es.developer.projectwar.utils.UpdateInput;

public abstract class MovableModel extends Model{
	//private static final String TAG = MovableModel.class.getCanonicalName();
	protected TMXTile tilePosition;
	protected int id;
	private String resource;
	
	public MovableModel(){
		super();
	}

	public void setPosition(TMXTile position) {
		this.tilePosition = position;
		this.notifyListenersUpdate(UpdateInput.POSITION_CHANGED);
	}

	public TMXTile getPosition(){
		return tilePosition;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public void setPostion(int x, int y){
		Coordinate coordinate = new Coordinate(x,y);
		//TODO That dependency must be cleared, i just don' know how now...
		this.tilePosition = Map.getInstance().getTileFromCoord(coordinate);
	}
}
