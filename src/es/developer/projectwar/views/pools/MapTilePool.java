package es.developer.projectwar.views.pools;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.pool.GenericPool;

import es.developer.projectwar.utils.TextureRegionFactory;
import es.developer.projectwar.utils.TextureRegionFactory.TextureType;

public class MapTilePool extends GenericPool<Sprite>{
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
	protected Sprite onAllocatePoolItem() {
		// Nothing passed as resource because it's taken from a static reference in the TextureRegionFactory class
		//Fix this!
		Sprite sprite = (Sprite) textureFactory.createSprite( "" , TextureType.tile); 
		scene.attachChild(sprite);
		return sprite;
	}
	
	/**
	  * Called when a object is sent to the pool
	 */
	@Override
	protected void onHandleRecycleItem(Sprite tileSprite) {
		tileSprite.setIgnoreUpdate(true);
		tileSprite.setVisible(false);
		super.onHandleRecycleItem(tileSprite);
	}

	/**
	  * Called just before the object is returned to the caller, this is where you write your initialize code
	 */
	@Override
	protected void onHandleObtainItem(Sprite tileSprite) {
		tileSprite.setVisible(true);
		super.onHandleObtainItem(tileSprite);
	}
}
