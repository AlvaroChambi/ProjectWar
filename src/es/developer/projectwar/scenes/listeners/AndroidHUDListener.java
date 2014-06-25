package es.developer.projectwar.scenes.listeners;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import es.developer.projectwar.R;
import es.developer.projectwar.controllers.GameCommand;
import es.developer.projectwar.controllers.IGameCommandListener;
import es.developer.projectwar.controllers.IPlayerCommandListener;
import es.developer.projectwar.controllers.commands.Command;

public class AndroidHUDListener implements OnClickListener, OnItemClickListener{
	private static final String TAG = AndroidHUDListener.class.getCanonicalName();
	private IGameCommandListener gameCommandListener;
	private List<Command> commands;
	//TODO Change it as soon as i can
	private List<IPlayerCommandListener> playerCommandListeners;

	public AndroidHUDListener(){
		playerCommandListeners = new ArrayList<IPlayerCommandListener>();
	}

	@Override	
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		this.notifyListeners(commands.get(position));

	}

	@Override
	public void onClick(View element) {
		switch(element.getId()){
		case R.id.finish_command:
			if(gameCommandListener!= null){
				gameCommandListener.onGameCommandReceived(GameCommand.End);
			}
			Log.i(TAG , "finish!");
			break;
		}
	}
	
	public void addListener(IPlayerCommandListener listener){
		playerCommandListeners.add(listener);
	}
	
	public void notifyGameCommandListener(GameCommand command){
		gameCommandListener.onGameCommandReceived(command);
	}
	
	public void notifyListeners(Command command){
		for(IPlayerCommandListener listener: playerCommandListeners){
			listener.onPlayerCommandReceived(command);
		}
	}

	/**
	 * @param gameCommandListener the gameCommandListener to set
	 */
	public void setGameCommandListener(IGameCommandListener gameCommandListener) {
		this.gameCommandListener = gameCommandListener;
	}

	/**
	 * @param commands the commands to set
	 */
	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}
}
