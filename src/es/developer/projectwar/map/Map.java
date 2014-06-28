package es.developer.projectwar.map;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.Constants;

import android.util.Log;
import es.developer.projectwar.map.pathfinding.PathGenerator;
import es.developer.projectwar.models.UnitModel;
import es.developer.projectwar.utils.Coordinate;
import es.developer.projectwar.views.sprites.UnitSprite;

/**
 * Class that represents and handle all the game map issues.
 * @author Leid
 *
 */
public class Map {
	private static final String TAG = Map.class.getCanonicalName();
	private MapModel mapModel;
	private static Map instance;
	//TODO If nothing better comes to my mind at least change the singleton for a service locator.
	private Map(){
		
	}
	
	public Coordinate getCenter(){
		Coordinate center = new Coordinate(mapModel.getTmxLayer().getWidth()/2,
										mapModel.getTmxLayer().getHeight()/2);	
		return center;
	}

	public static synchronized Map getInstance(){
		if( instance == null ){
			instance = new Map();
		}
		return instance;
	}
	
	public void initializeMap( MapModel mapModel ){
		this.mapModel = mapModel;
	}

	public Path generatePath(UnitSprite sprite, float destinationX, float destinationY) {
		Path path = null;
//		Coordinate initialPosition, finalPosition;
//		PathGenerator pathGenerator = PathGenerator.getInstance();
//		initialPosition = this.getSpritePosition(sprite);
//		finalPosition = this.getTilePosition(destinationX, destinationY);
		//TODO fix path generation
		//path = pathGenerator.generatePath(initialPosition, finalPosition);	
		return path;
	}

	public PathModifier generatePath(TMXTile start, TMXTile end){
		PathModifier modifier = null;
		Coordinate initial = new Coordinate(start.getTileColumn(), start.getTileRow());
		Coordinate destination = new Coordinate(end.getTileColumn(), end.getTileRow());
		PathGenerator pathGenerator = new PathGenerator(mapModel);
		//TODO fix path generation
		Path path = pathGenerator.generatePath(initial, destination);
		int duration = pathGenerator.getDuration();
		modifier = new PathModifier(duration, path);
		modifier.setAutoUnregisterWhenFinished(true);
		return modifier;
	}

	public Coordinate getSpritePosition(Sprite unit){
		Coordinate position;
		TMXLayer tmxLayer = mapModel.getTmxLayer();
		float[] playerFootCordinates = unit.convertLocalToSceneCoordinates(12, 31);
		TMXTile playerTile = tmxLayer.getTMXTileAt(playerFootCordinates[Constants.VERTEX_INDEX_X], playerFootCordinates[Constants.VERTEX_INDEX_Y]);
		position = new Coordinate(playerTile.getTileColumn(),playerTile.getTileRow());
		return position;
	}

	public void setSelectionTilePosition(Sprite sprite, float x, float y){
		TMXLayer tmxLayer = mapModel.getTmxLayer();
		TMXTile tile = tmxLayer.getTMXTileAt(x, y);
		sprite.setPosition(tile.getTileX(), tile.getTileY());
	}
	
	public TMXTile getTileFromCoord(Coordinate coord){
		TMXLayer tmxLayer = mapModel.getTmxLayer();
		TMXTile tile = tmxLayer.getTMXTile(
								coord.getX(), coord.getY());
		return tile;
	}

	public TMXTile getTile(float x, float y){
		TMXLayer tmxLayer = mapModel.getTmxLayer();
		return tmxLayer.getTMXTileAt(x, y);
	}

	public Coordinate getTilePosition(float destinationX, float destinationY){
		Coordinate position = null;
		TMXLayer tmxLayer = mapModel.getTmxLayer();
		TMXTile tile = tmxLayer.getTMXTileAt(destinationX,destinationY);
		position = new Coordinate(tile.getTileColumn(),tile.getTileRow());
		return position;
	}

	public boolean isSpriteOnTile(Sprite sprite, float tileX, float tileY){
		boolean resul = false;
		if(this.getSpritePosition(sprite).equals(this.getTilePosition(tileX, tileY))){
			Log.i(TAG,"sprite touche event filtered");
			resul = true;
		}
		return resul;
	}

	public TMXTile getRandomTile(){
		TMXLayer tmxLayer = mapModel.getTmxLayer();
		MapMatrix mapMatrix = mapModel.getMapMatrix();
		final int centerX = 0 + (int)(Math.random() * (((mapMatrix.getColumns()-1) - 0) + 1));
		final int centerY = 0 + (int)(Math.random() * (((mapMatrix.getRows()-1) - 0) + 1));
		return tmxLayer.getTMXTile(centerX, centerY);
	}

	public boolean isTilePositionValid(int posX, int posY){
		
		boolean resul = false;
		if(posX > 0 && posX < mapModel.getColumns()
				&& posY > 0 && posY < mapModel.getRows()){
			resul = true;
		}
		return resul;
	}

	public List<TMXTile> getTilesEnabled(UnitModel unit){
		List<TMXTile> tiles = new ArrayList<TMXTile>();
		TMXLayer tmxLayer = mapModel.getTmxLayer();
		int moveLimit = unit.getMovement();
		TMXTile unitTile = unit.getPosition();
		Coordinate unitPosition = new Coordinate(unitTile.getTileColumn(),
				unitTile.getTileRow());
		for(int i = 0; i <= moveLimit; i++){
			for(int j = 0; j <= moveLimit; j++){
				if( i + j <= moveLimit){
					final int tileX = unitPosition.getX();
					final int tileY = unitPosition.getY();
					TMXTile tmxTile;
					if(this.isTilePositionValid(tileX + i, tileY + j)){
						tmxTile = tmxLayer.getTMXTile(tileX + i, tileY + j);
						tiles.add(tmxTile);
					}if(this.isTilePositionValid(tileX - i, tileY - j)){
						tmxTile = tmxLayer.getTMXTile(tileX - i, tileY - j);
						tiles.add(tmxTile);
					}if(this.isTilePositionValid(tileX + i, tileY - j)){
						tmxTile = tmxLayer.getTMXTile(tileX + i, tileY - j);
						tiles.add(tmxTile);
					}if(this.isTilePositionValid(tileX - i, tileY + j)){
						tmxTile = tmxLayer.getTMXTile(tileX - i, tileY + j);
						tiles.add(tmxTile);
					}
				}
			}
		}	
		return tiles;
	}
}
