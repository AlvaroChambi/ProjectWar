package es.developer.projectwar.views.sprites;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;
import es.developer.projectwar.drawers.listeners.IPlayerEventsListener;

public class UnitSprite extends AnimatedSprite{
	private final static String TAG = UnitSprite.class.getCanonicalName();
	private int spriteId;
	private List<IPlayerEventsListener> listeners;
	private Timer timer;
	private boolean active;

	public UnitSprite(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		this.listeners = new ArrayList<IPlayerEventsListener>();
		timer = new Timer();
		active = true;
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		//if active means that the delay between click events has passed
		if( active ){
			Log.i(TAG,"onAreaTouched");
			active = false;
			timer.schedule(new ClickTimerTask(), 200);
			this.notifyListeners();	
		}
		//return false if you want to leave the event as not treated, true if you handle the event and dont want to go through.
		return true;
	}
	
	public void notifyListeners(){
		for(IPlayerEventsListener listener: listeners){
			listener.onUnitClicked(this.spriteId);
		}
	}
	
	/**
	 * @param listener the listener to set
	 */
	public void setListener(IPlayerEventsListener listener) {
		listeners.add(listener);
	}
	/**
	 * @return the spriteId
	 */
	public int getSpriteId() {
		return spriteId;
	}

	/**
	 * @param spriteId the spriteId to set
	 */
	public void setSpriteId(int spriteId) {
		this.spriteId = spriteId;
	}
	
	public class ClickTimerTask extends TimerTask{
		//Set a delay between the sprite touch events
		@Override
		public void run() {
			Log.i(TAG,"timer task running");
			active = true;
		}
	}
}
