package es.developer.projectwar.scenes.huds;

import es.developer.projectwar.drawers.listeners.IPlayerEventsListener;


public interface IGameHUD {
	public void registerEventsHandler(IPlayerEventsListener handler);
}
