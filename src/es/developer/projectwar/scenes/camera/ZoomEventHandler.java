package es.developer.projectwar.scenes.camera;

import org.andengine.engine.camera.ZoomCamera;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;

import android.util.Log;


public class ZoomEventHandler  implements IPinchZoomDetectorListener {
	private static final String TAG = ZoomEventHandler.class.getCanonicalName();
	private ZoomCamera camera;
	private float startedZoomFactor;
	public ZoomEventHandler(ZoomCamera camera2){
		this.camera = camera2;
	}
	@Override
	public void onPinchZoomStarted(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent) {
		this.startedZoomFactor = this.camera.getZoomFactor();

	}

	@Override
	public void onPinchZoom(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent, final float pZoomFactor) {
		this.camera.setZoomFactor(this.startedZoomFactor * pZoomFactor);
		Log.i(TAG,"zoomFactor: "+ camera.getZoomFactor());
	}

	@Override
	public void onPinchZoomFinished(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent, final float pZoomFactor) {
		this.camera.setZoomFactor(this.startedZoomFactor * pZoomFactor);
//		Map.MAP_HEIGHT = Map.MAP_HEIGHT*camera.getZoomFactor();
//		Map.MAP_WIDTH = Map.MAP_WIDTH*camera.getZoomFactor();
	}
}
