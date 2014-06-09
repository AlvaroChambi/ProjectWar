package es.developer.projectwar.views;

import es.developer.projectwar.utils.UpdateInput;


public interface IObserver {
	public void onUpdateNotification(UpdateInput input);
}
