package es.developer.projectwar.scenes.camera;


import org.andengine.engine.camera.ZoomCamera;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;


public class ScrollHandler implements IScrollDetectorListener {
//	private static final String TAG = ScrollHandler.class.getCanonicalName();
	private ZoomCamera camera;
	
	public ScrollHandler (ZoomCamera camera){
		this.camera = camera;
	}
	
	@Override
	public void onScrollStarted(final ScrollDetector pScollDetector, final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = this.camera.getZoomFactor();
		this.camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
		
	}

	@Override
	public void onScroll(final ScrollDetector pScollDetector, final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = this.camera.getZoomFactor();
		this.camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
	
	}
	
	@Override
	public void onScrollFinished(final ScrollDetector pScollDetector, final int pPointerID, final float pDistanceX, final float pDistanceY) {
		final float zoomFactor = this.camera.getZoomFactor();
		this.camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
	
	}

}
