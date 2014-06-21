package es.developer.projectwar.scenes.huds;

import android.app.Fragment;
import es.developer.projectwar.Game;
import es.developer.projectwar.scenes.listeners.AndroidHUDListener;
import es.developer.projectwar.views.IObserver;

public abstract class FragmentGameHUD extends Fragment implements IObserver{
	
	protected Game game;
	protected AndroidHUDListener listener;
	
	public abstract void updateView();
	
	public void setGame(Game game){
		this.game = game;
		game.registerObserver(this);
		this.updateView();
	}
}
