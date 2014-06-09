package es.developer.projectwar.drawers;

import java.util.List;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.Log;
import es.developer.projectwar.map.MapModel;
import es.developer.projectwar.map.listeners.TilePropertiesListener;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.views.PlayerView;

//TODO This drawer shit just doesn't work, must be changed!
public class StaticGameDrawer implements IDrawer {
	private static final String TAG = StaticGameDrawer.class.getCanonicalName();
	private BaseGameActivity activity;
	private Scene scene;
	private BitmapTextureAtlas bitmapTextureAtlas;
	private PlayerView playerView;
	private List<PlayerModel> players;
	private MapModel mapModel;
	
	public StaticGameDrawer(Scene scene, BaseGameActivity activity){
		this.activity = activity;
		this.scene = scene;
		this.bitmapTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.DEFAULT);
		this.bitmapTextureAtlas.load();
	}

	@Override
	public void loadResources() {
		this.loadMapResource();
		this.loadPlayersResources();
	}
	
	public void loadPlayersResources(){
		for(PlayerModel playerModel: players){
			Log.i(TAG,"player resource: "+playerModel.getResource());
			ITextureRegion textureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(bitmapTextureAtlas, activity, playerModel.getResource(), 0, 0);
			IShape sprite = (new Sprite(32,32, textureRegion, activity.getVertexBufferObjectManager()));
			scene.attachChild(sprite);
			playerView = (new PlayerView(playerModel, sprite));
			playerModel.registerObserver(playerView);
		}		
	}
	
	//Map utilities couldn't be used before the map is loaded! 
	public void loadMapResource() {
		try {
			TMXLoader tmxLoader = new TMXLoader(activity.getAssets(), activity.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, 
					activity.getVertexBufferObjectManager(), new TilePropertiesListener(mapModel.getMapMatrix()));
			TMXTiledMap tiledMap = tmxLoader.loadFromAsset("tmx/projectwar.tmx");
			TMXLayer tmxLayer = tiledMap.getTMXLayers().get(0);
			mapModel.setTmxLayer(tmxLayer);
			scene.attachChild(tmxLayer);
			
		} catch (TMXLoadException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * @return the playerView
	 */
	public PlayerView getPlayerView() {
		return playerView;
	}

	/**
	 * @param mapModel the mapModel to set
	 */
	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
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
}