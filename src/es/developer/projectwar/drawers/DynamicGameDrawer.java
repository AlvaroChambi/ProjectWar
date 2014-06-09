package es.developer.projectwar.drawers;

import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import es.developer.projectwar.drawers.listeners.IPlayerEventsListener;
import es.developer.projectwar.utils.TextureRegionFactory;

public abstract class DynamicGameDrawer implements IDrawer {	
	protected TextureRegionFactory textureFactory;
	protected BaseGameActivity activity;
	protected Scene scene;
	
	public abstract void setListener(IPlayerEventsListener listener);
	public DynamicGameDrawer(Scene scene, BaseGameActivity activity){
		textureFactory = new TextureRegionFactory(activity);
		this.activity = activity;
		this.scene = scene;
	}
}