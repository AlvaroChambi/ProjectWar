package es.developer.projectwar.scenes;

import java.util.Iterator;
import java.util.List;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.SimpleLayoutGameActivity;
import org.andengine.util.color.Color;

import android.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import es.developer.projectwar.Game;
import es.developer.projectwar.Game.GameMode;
import es.developer.projectwar.R;
import es.developer.projectwar.controllers.GameController;
import es.developer.projectwar.controllers.PlayerController;
import es.developer.projectwar.drawers.StaticGameDrawer;
import es.developer.projectwar.drawers.UnitsDrawer;
import es.developer.projectwar.map.Map;
import es.developer.projectwar.map.MapModel;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.scenes.huds.AndroidNativeHUD;
import es.developer.projectwar.scenes.huds.FragmentGameHUD;
import es.developer.projectwar.scenes.listeners.SceneTouchListener;
import es.developer.projectwar.scenes.sliders.SlidingMenu;
import es.developer.projectwar.utils.parsers.MapsParser;
import es.developer.projectwar.utils.parsers.PlayersParser;

public class GameScene extends SimpleLayoutGameActivity{
	private static final String TAG = GameScene.class.getCanonicalName();
	public static int CAMERA_WIDTH;
	public static int CAMERA_HEIGHT;

	private ZoomCamera camera;
	private Scene scene;
	private AndroidNativeHUD menuHUD;
	private Game game;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		CAMERA_WIDTH = dm.widthPixels;
		CAMERA_HEIGHT = dm.heightPixels;
		this.camera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.setZoomFactor(2);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
					new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.camera);
	}
	
	@Override
	protected int getLayoutID() {
		return R.layout.game_scene_layout;
	}

	@Override
	protected int getRenderSurfaceViewID() {
		return R.id.gameSurfaceView;
	}

	@Override
	protected void onSetContentView() {
		super.onSetContentView();
		Log.i(TAG,"onSetContentView");
		menuHUD = (AndroidNativeHUD)this.setMenuFragment();
	}

	@Override
	protected void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");	
	}

	@Override
	protected Scene onCreateScene() {
		Log.i(TAG,"onCreateScene");
		this.mEngine.registerUpdateHandler(new FPSLogger());
		scene = new Scene();
		scene.setTouchAreaBindingOnActionDownEnabled(true);
		//scene.registerUpdateHandler(updateHandler);
		scene.setOnAreaTouchTraversalFrontToBack();
		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		
		//Scene touch listener also handle all the scroll and zoom events.
		SceneTouchListener touchListener = new SceneTouchListener(camera);
		scene.setOnSceneTouchListener(touchListener);
		
		this.loadResources(touchListener);
		//Initializes game settings
		return scene;
	}
	//The initialization of everything is a total mess...
	private void loadResources(SceneTouchListener touchListener){
		//TODO Encapsulate all xml parsing process into a data provider.
		MapsParser parser = new MapsParser(this);
		MapModel mapModel = parser.getMap(retriveMapName());
		
		PlayersParser playerParser = new PlayersParser(this, mapModel.getConfiguration());
		
		//Load initial static resources(Map, player)
		StaticGameDrawer staticDrawer = new StaticGameDrawer(scene, this);
		staticDrawer.setMapModel(mapModel);
		staticDrawer.loadMapResource();
		//Singleton Map must be changed!, maybe a service locator while noting better comes to my mind
		Map.getInstance().initializeMap(mapModel);
		//TODO Just parsing and getting one player, resolve it to get the actual and the IAPlayer urgent!
		List<PlayerModel> players = playerParser.getPlayers();
		staticDrawer.setPlayers(players);
		staticDrawer.loadPlayersResources();
		//Create the game model
		game = new Game(GameMode.Local);
		game.setPlayers(players);
		this.loadPlayers(players, touchListener);
		menuHUD.setGame(game);
	}
	
	private void loadPlayers(List<PlayerModel> players, SceneTouchListener touchListener){
		Iterator <PlayerModel> iterator = players.iterator();
		GameController gameController = new GameController(game);
		while(iterator.hasNext()){
			final PlayerModel player = iterator.next();
			UnitsDrawer unitsDrawer = new UnitsDrawer(scene, this, player.getUnits());
			unitsDrawer.loadResources();
			PlayerController playerController = new PlayerController(player);
			touchListener.setPlayerEventsListener(playerController);
			//Set the unit click events listeners the actual player controller and the gameController for every player
			unitsDrawer.setListener(playerController);
			unitsDrawer.setListener(gameController);
			menuHUD.registerPlayerCommandListener(playerController);
			//Set the HUD as an unit observer so it can be notified and display all the relevant info
			unitsDrawer.registerUnitsObserver(menuHUD);
			gameController.registerController(playerController);
		}
		menuHUD.registerGameCommandListener(gameController);
	}
	
	 //Add player action HUD to the main scene and return the fragment that contains it

	private FragmentGameHUD setMenuFragment() {
		SlidingMenu drawer = (SlidingMenu) this.findViewById(R.id.drawer_layout);
		drawer.setScrimColor(Color.TRANSPARENT.getABGRPackedInt());
		drawer.openDrawer(Gravity.RIGHT);
		FragmentManager manager = this.getFragmentManager();
		return (FragmentGameHUD) manager.findFragmentById(R.id.menu_hud_fragment);
	}
	
	/**
	 * Retrieves the name of the map selected in the GameSetScene.
	 * @return mapName
	 * @throws NullPointerException
	 */
	private String retriveMapName() throws NullPointerException{
		String name = this.getIntent().getExtras().getString("MapName");
		return name;
	}
}
