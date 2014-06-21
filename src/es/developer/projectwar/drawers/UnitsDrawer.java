package es.developer.projectwar.drawers;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.Log;
import es.developer.projectwar.controllers.PlayerEventsHandler;
import es.developer.projectwar.models.MovableModel;
import es.developer.projectwar.models.UnitModel;
import es.developer.projectwar.views.IObserver;
import es.developer.projectwar.views.UnitView;
import es.developer.projectwar.views.pools.MapTilePool;
import es.developer.projectwar.views.sprites.UnitSprite;

public class UnitsDrawer extends DynamicGameDrawer{
	private static final String TAG = UnitsDrawer.class.getCanonicalName();
	private List<UnitModel> models;
	private List<UnitView> views;
	//Adding a tiles pool to the unit drawer so any added unit uses the same instance if the pool.
	private MapTilePool tilesPool;
	
	public UnitsDrawer(Scene scene, BaseGameActivity activity, List<UnitModel> models){
		super(scene,activity);
		this.models = models;
		views = new ArrayList<UnitView>();
		tilesPool = new MapTilePool(this.textureFactory, scene);
	}

	@Override
	public void loadResources() {
		for(UnitModel model: models){
			this.attachToScene(model);
		}
	}

	@Override
	public void setListener(PlayerEventsHandler listener) {
		for(UnitView view: views){
			view.setListener(listener);
		}	
	}
	
	/**
	 * Register an observer for the drawer contained units
	 */
	public void registerUnitsObserver(IObserver observer){
		for( UnitModel unit: models){
			unit.registerObserver(observer);
		}
	}
	
	//Generates the view with the given model and attach it to the scene.
	//Also set the view as a model observer.
	private void attachToScene(UnitModel model){
		UnitSprite sprite = (UnitSprite)attachTouchableToScene(model);
		sprite.setSpriteId(model.getId());
		UnitView view = new UnitView(model, sprite);
		model.registerObserver(view);
		view.setSpritePool(tilesPool);
		views.add(view);
	}
	
	//Generate a entity shape from the given model and add it to the scene
	private IShape attachTouchableToScene(MovableModel model){
		Log.i(TAG,"added shape to the scene: " + model.getTextureType());
		IShape entity = textureFactory.createSprite(
						model.getResource(), 
						model.getTextureType());
		TMXTile position = model.getPosition();
		entity.setPosition(position.getTileX(), position.getTileY());
		//Scale the sprite to x1.5
		entity.setScale(1.5f);
		scene.attachChild(entity);
		scene.registerTouchArea(entity);
		return entity;
	}
}
