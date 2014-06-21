package es.developer.projectwar.scenes.huds;

import es.developer.projectwar.controllers.IGameCommandListener;

public interface IGameCommandHUD {

	public void registerGameCommandListener(IGameCommandListener listener);
}
