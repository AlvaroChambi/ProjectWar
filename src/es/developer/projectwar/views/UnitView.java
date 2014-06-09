package es.developer.projectwar.views;

import java.util.Iterator;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;

import android.util.Log;
import es.developer.projectwar.drawers.listeners.IPlayerEventsListener;
import es.developer.projectwar.models.UnitModel;
import es.developer.projectwar.utils.UpdateInput;
import es.developer.projectwar.views.pools.MapTilePool;
import es.developer.projectwar.views.sprites.UnitSprite;

public class UnitView extends MovableView{
	private static final String TAG = UnitView.class.getCanonicalName();
	private UnitSprite sprite;
	private UnitModel model;
	private MapTilePool tilesPool;
	
	public UnitView(UnitModel model){
		this.model = model;
	}
	
	public UnitView(UnitModel model, UnitSprite sprite){
		this(model);
		this.sprite = sprite;
		//Set the animated sprite frame to show
		sprite.setCurrentTileIndex(6);
	}
	
	@Override
	public void onUpdateNotification(UpdateInput input) {
		switch(input){
		case POSITION_CHANGED:
			this.moveView();
			this.animate();
			break;
		case UNIT_SELECTED:
			this.showEnabledTiles();
			break;
		case UNIT_UNSELECTED:
			this.hideEnabledTiles();
			break;
		default:
			break;
		}
	}
	
	//Must complete animation for units
	private void animate(){
		sprite.animate(new long[]{200, 200, 200}, 3, 5, true);
	}
	
	private void showEnabledTiles(){
		Iterator<TMXTile> iterator = model.getEnabledTiles().iterator();
		Log.i(TAG, "drawing tiles " + model.getEnabledTiles().size());
		while(iterator.hasNext()){
			final TMXTile tile = iterator.next();
			Sprite sprite = tilesPool.obtainPoolItem();
			sprite.setPosition(tile.getTileX(), tile.getTileY());
			model.getEnabledTilesView().add(sprite);
		}
	}
	
	private void hideEnabledTiles(){
		Iterator<Sprite> iterator = model.getEnabledTilesView().iterator();
		while(iterator.hasNext()){
			final Sprite tile = iterator.next();
			tilesPool.recyclePoolItem(tile);
		}
		model.getEnabledTilesView().clear();
	}
	
	private void moveView(){
		//If it has no path defined just place the sprite in the final position, otherwise 
		//it will follow the path till his destiny.
		if(model.getPathModifier() == null){
			Log.i(TAG, "moving unit: no path");
			TMXTile tile = model.getPosition();
			sprite.setPosition(tile.getTileX(), tile.getTileY());
		}else{
			Log.i(TAG, "moving unit: path");
			sprite.registerEntityModifier(model.getPathModifier());
			//dispose the path before it's been used
			model.setPathModifier(null);
		}
	}
	
	public void setSpritePool(MapTilePool pool){
		this.tilesPool = pool;
	}
	
	public void setListener(IPlayerEventsListener listener){
		sprite.setListener(listener);
	}
}