package es.developer.projectwar.drawers;

import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import es.developer.projectwar.controllers.PlayerEventsHandler;
import es.developer.projectwar.utils.TextureRegionFactory;

public abstract class DynamicGameDrawer implements IDrawer {	
	protected TextureRegionFactory textureFactory;
	protected BaseGameActivity activity;
	protected Scene scene;
	
	public abstract void setListener(PlayerEventsHandler listener);
	public DynamicGameDrawer(Scene scene, BaseGameActivity activity){
		textureFactory = new TextureRegionFactory(activity);
		this.activity = activity;
		this.scene = scene;
	}
}