package es.developer.projectwar.scenes.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.SurfaceScrollDetector;

import android.util.Log;
import es.developer.projectwar.drawers.listeners.IPlayerEventsListener;
import es.developer.projectwar.map.Map;
import es.developer.projectwar.scenes.camera.ScrollHandler;
import es.developer.projectwar.scenes.camera.ZoomEventHandler;

public class SceneTouchListener implements IOnSceneTouchListener{
	private static final String TAG = SceneTouchListener.class.getCanonicalName();
	private PinchZoomDetector zoomDetector;
	private SurfaceScrollDetector scrollDetector;
	private List<IPlayerEventsListener> listeners;
	private boolean scrolled ;
	private float posX ;
	private float  posY ;

	public SceneTouchListener(ZoomCamera camera){
		this.scrolled = false;
		this.zoomDetector = new PinchZoomDetector(new ZoomEventHandler(camera));
		this.scrollDetector = new SurfaceScrollDetector(new ScrollHandler(camera));
		listeners = new ArrayList<IPlayerEventsListener>();
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		zoomDetector.onTouchEvent(pSceneTouchEvent);
		if(zoomDetector.isZooming()) {
			scrollDetector.setEnabled(false);
		} else {
			if(pSceneTouchEvent.isActionDown()) {
				scrollDetector.setEnabled(true);
			}
			scrollDetector.onTouchEvent(pSceneTouchEvent);
		}

		int action = pSceneTouchEvent.getAction();

		switch(action){
		case TouchEvent.ACTION_UP:
			// notify event to listeners
			this.notifyListeners(pSceneTouchEvent);
			Log.i(TAG,"action up event!");
			break;
		case TouchEvent.ACTION_DOWN:
			posX = pSceneTouchEvent.getX();
			posY = pSceneTouchEvent.getY();
			break;
		case TouchEvent.ACTION_OUTSIDE:
			break;
		}
		scrolled = false;
		return false;
	}

	/**
	 * Method to know if we got and scroll action in the touch event
	 * @return scroll state
	 */
	public boolean isScrolled(TouchEvent touch) {
		scrolled = true;
		if(touch.getX() < posX + 5 && touch.getX() > posX - 5){
			scrolled = false;
		}
		if(touch.getY() < posY + 5 && touch.getY() > posY - 5){
			scrolled = false;
		}
		return scrolled;
	}

	public void setPlayerEventsListener(IPlayerEventsListener listener){
		listeners.add(listener);
	}
	
	private void notifyListeners(TouchEvent pSceneTouchEvent){
		Iterator <IPlayerEventsListener> iterator = listeners.iterator();
		while(iterator.hasNext()){
			final IPlayerEventsListener listener = iterator.next();
			listener.onMapClicked(Map.getInstance().getTile(pSceneTouchEvent.getX(), 
					pSceneTouchEvent.getY()));
		}
	}
}