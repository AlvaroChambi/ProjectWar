package es.developer.projectwar.scenes.sliders;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;

public class SlidingMenu extends DrawerLayout{

	private static final String TAG = SlidingMenu.class.getCanonicalName();
	
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public SlidingMenu(Context context) {
		super(context);

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		boolean result = false;

		if(this.isDrawerOpen(Gravity.RIGHT)){
			result =  false;
		}else{
			result = true;
		}
		return result;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i(TAG,"touch event");
		return super.onTouchEvent(event);
	}
}
