package es.developer.projectwar.views.pools;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.color.Color;

import es.developer.projectwar.utils.TextureRegionFactory;
import es.developer.projectwar.utils.TextureRegionFactory.TextureType;

public class MapTilePool extends GenericPool<IShape>{
//	private static final String TAG = MapTilePool.class.getCanonicalName();
	private TextureRegionFactory textureFactory;
	private Scene scene;
	
	public MapTilePool(TextureRegionFactory textureFactory, Scene scene){
		this.textureFactory = textureFactory;
		this.scene = scene;
	}
	/**
	 * Called when a object is required but there isn't one in the pool
	 */
	@Override
	protected IShape onAllocatePoolItem() {
		// Nothing passed as resource because it's taken from a static reference in the TextureRegionFactory class
		//Fix this!
		IShape sprite = textureFactory.createSprite( "" , TextureType.tile); 
		scene.attachChild(sprite);
		return sprite;
	}
	
	/**
	  * Called when a object is sent to the pool
	 */
	@Override
	protected void onHandleRecycleItem(IShape tileSprite) {
		tileSprite.setIgnoreUpdate(true);
		tileSprite.setVisible(false);
		tileSprite.setScale(1);
		tileSprite.setColor(Color.BLUE);
		tileSprite.setAlpha(0);
		super.onHandleRecycleItem(tileSprite);
	}

	/**
	  * Called just before the object is returned to the caller, this is where you write your initialize code
	 */
	@Override
	protected void onHandleObtainItem(IShape tileSprite) {
		tileSprite.setVisible(true);
		super.onHandleObtainItem(tileSprite);
	}
}
